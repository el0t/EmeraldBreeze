package com.elot.emeraldbreeze.tileentity;

import com.elot.emeraldbreeze.EmeraldBreeze;
import com.elot.emeraldbreeze.core.init.RecipeSerializerInit;
import com.elot.emeraldbreeze.core.init.TileEntityTypeInit;
import com.elot.emeraldbreeze.inventory.container.DehydratorContainer;
import com.elot.emeraldbreeze.items.crafting.ModRecipe;
import com.elot.emeraldbreeze.util.ModItemHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DehydratorTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    private ITextComponent customName;
    private ModItemHandler inventory;
    private int[] dryTimes = {0,0,0,0,0,0,0,0,0,0,0,0};
    private int maxDryTime = 900;

    public DehydratorTileEntity(TileEntityType<?> tileEntityTypeIn){
        super(tileEntityTypeIn);
        this.inventory = new ModItemHandler(12);
    }
    public DehydratorTileEntity(){
        this(TileEntityTypeInit.DEHYDRATOR.get());
    }

    @Override
    public Container createMenu(final int windowID, final PlayerInventory playerInventory, final PlayerEntity playerEntity){
        return new DehydratorContainer(windowID, playerInventory, this);
    }


    @Override
    public void tick(){
        boolean dirty = false;
        Biome biome = world.getBiome(this.getPos());
        int adjustedDryTime = this.maxDryTime;

        if (this.world != null && !this.world.isRemote){
            if(this.world.getDimensionType().hasSkyLight() && this.world.isDaytime()) {
                if(biome.isHighHumidity()){ adjustedDryTime += 200; }
                adjustedDryTime = Math.round(adjustedDryTime*(1/biome.getTemperature()));

                for (int index = 0; index < inventory.getSlots(); index++) {
                    if (this.getRecipe(this.inventory.getStackInSlot(index)) != null) {
                        if (this.dryTimes[index] < adjustedDryTime){
                            this.dryTimes[index] += 1;
                            dirty = true;
                        } else {
                            this.dryTimes[index] = 0;
                            ItemStack output = this.getRecipe(this.inventory.getStackInSlot(index)).getRecipeOutput();
                            this.inventory.decreaseStackSize(index, 1);
                            this.inventory.insertItem(index, output.copy(), false);
                            dirty = true;
                        }
                    }
                }
            }

        }
        if (dirty) {
            this.markDirty();
            this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(),
                    Constants.BlockFlags.BLOCK_UPDATE);
        }
    }

    //TODO
    // read and write methods ??



    @Nullable
    private ModRecipe getRecipe(ItemStack stack){
        if(stack==null){ return null; }
        //TODO figure out recipes. I don't even know if this code will work:
        Set<IRecipe<?>> recipes = findRecipesByType(RecipeSerializerInit.DEHYDRATING, this.world);
        for(IRecipe<?> iRecipe : recipes){
            ModRecipe recipe = (ModRecipe) iRecipe;
            if(recipe.matches(new RecipeWrapper(this.inventory), this.world)){
                return recipe;
            }
        }
        return null;
    }

    public static Set<IRecipe<?>> findRecipesByType(IRecipeType<?> typeIn, World world) {
        return world != null ? world.getRecipeManager().getRecipes().stream()
                .filter(recipe -> recipe.getType() == typeIn).collect(Collectors.toSet()) : Collections.emptySet();
    }
    @SuppressWarnings("resource")
    @OnlyIn(Dist.CLIENT)
    public static Set<IRecipe<?>> findRecipesByType(IRecipeType<?> typeIn) {
        ClientWorld world = Minecraft.getInstance().world;
        return world != null ? world.getRecipeManager().getRecipes().stream()
                .filter(recipe -> recipe.getType() == typeIn).collect(Collectors.toSet()) : Collections.emptySet();
    }
    @Override
    public ITextComponent getDisplayName() {
        return this.getName();
    }
    public ITextComponent getName(){
        return this.customName != null ? this.customName : this.getDefaultName();
    }
    private ITextComponent getDefaultName(){
        return new TranslationTextComponent("container."+ EmeraldBreeze.MOD_ID+".dehydrator");
    }
    public void setCustomName(ITextComponent name){
        this.customName = name;
    }
    @Nullable
    public ITextComponent getCustomName(){
        return this.customName;
    }
    public static Set<ItemStack> getAllRecipeInputs(IRecipeType<?> typeIn, World worldIn) {
        Set<ItemStack> inputs = new HashSet<ItemStack>();
        Set<IRecipe<?>> recipes = findRecipesByType(typeIn, worldIn);
        for (IRecipe<?> recipe : recipes) {
            NonNullList<Ingredient> ingredients = recipe.getIngredients();
            ingredients.forEach(ingredient -> {
                for (ItemStack stack : ingredient.getMatchingStacks()) {
                    inputs.add(stack);
                }
            });
        }
        return inputs;
    }

    public final IItemHandlerModifiable getInventory() {
        return this.inventory;
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbt = new CompoundNBT();
        this.write(nbt);
        return new SUpdateTileEntityPacket(this.pos, 0, nbt);
    }


    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, LazyOptional.of(() -> this.inventory));
    }
}
