package com.elot.emeraldbreeze.tileentity;

import com.elot.emeraldbreeze.EmeraldBreeze;
import com.elot.emeraldbreeze.blocks.DehydratorBlock;
import com.elot.emeraldbreeze.core.init.RecipeSerializerInit;
import com.elot.emeraldbreeze.core.init.TileEntityTypeInit;
import com.elot.emeraldbreeze.inventory.container.DehydratorContainer;
import com.elot.emeraldbreeze.items.crafting.DehydratingRecipe;
import com.elot.emeraldbreeze.util.ModItemHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.extensions.IForgeItemStack;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.lwjgl.system.CallbackI;

import javax.annotation.Nullable;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DehydratorTileEntity extends LockableLootTileEntity implements ITickableTileEntity, INamedContainerProvider {
    private NonNullList<ItemStack> inventoryContents = NonNullList.withSize(12, ItemStack.EMPTY);
    protected int numPlayersUsing;
    private IItemHandlerModifiable items = createHandler();
    private LazyOptional<IItemHandlerModifiable> itemHandler = LazyOptional.of(() -> items);
    private ITextComponent customName;
    Biome biome = world.getBiome(this.getPos());
    private int[] dryTimes = {0,0,0,0,0,0,0,0,0,0,0,0};
    private int maxDryTime = 300;//TODO why does this only cook in slot 1?

    public DehydratorTileEntity(TileEntityType<?> tileEntityTypeIn){
        super(tileEntityTypeIn);
    }
    public DehydratorTileEntity(){
        this(TileEntityTypeInit.DEHYDRATOR.get());
    }



    //INVENTORY AND CONTAINER METHODS
    @Override
    public int getSizeInventory() {
        return 12;  //Inventory size of the Dehydrator is 12
    }
    @Override
    public NonNullList<ItemStack> getItems() {
        return this.inventoryContents;
    }
    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {
        this.inventoryContents = itemsIn;
    }
    protected ITextComponent getDefaultName(){
        return new TranslationTextComponent("container."+ EmeraldBreeze.MOD_ID+".dehydrator");
    }
    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new DehydratorContainer(id, player, this);
    }
    @Override
    public Container createMenu(final int windowID, final PlayerInventory playerInventory, final PlayerEntity playerEntity){
        return new DehydratorContainer(windowID, playerInventory, this);
    }

    @Override
    public CompoundNBT write(CompoundNBT compoundNBT){
        super.write(compoundNBT);
        if(!this.checkLootAndWrite(compoundNBT)){
            ItemStackHelper.saveAllItems(compoundNBT, this.inventoryContents);
        }
        return compoundNBT;
    }
    @Override
    public void read(BlockState blockState, CompoundNBT compoundNBT){
        super.read(blockState, compoundNBT);
        this.inventoryContents = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        if(!this.checkLootAndRead(compoundNBT)){
            ItemStackHelper.loadAllItems(compoundNBT, this.inventoryContents);
        }
    }
    @Override
    public boolean receiveClientEvent(int id, int type){
        if (id ==1){
            this.numPlayersUsing = type;
            return true;
        }else{
            return super.receiveClientEvent(id, type);
        }
    }
    @Override
    public void openInventory(PlayerEntity playerEntity){
        if(!playerEntity.isSpectator()){
            if(this.numPlayersUsing <0){ numPlayersUsing=0;}
            ++this.numPlayersUsing;
            this.onOpenOrClose();
        }
    }
    @Override
    public void closeInventory(PlayerEntity playerEntity) {
        if(!playerEntity.isSpectator()){
            --this.numPlayersUsing;
            this.onOpenOrClose();
        }
    }
    public static int getPlayersUsing(IBlockReader reader, BlockPos pos){
        BlockState blockState = reader.getBlockState(pos);
        if(blockState.hasTileEntity()){
            TileEntity tileEntity = reader.getTileEntity(pos);
            if (tileEntity instanceof DehydratorTileEntity){
                return ((DehydratorTileEntity)tileEntity).numPlayersUsing;
            }
        }
        return 0;
    }

    protected void onOpenOrClose() {
        Block block = this.getBlockState().getBlock();
        if(block instanceof DehydratorBlock){
            this.world.addBlockEvent(this.pos, block, 1,this.numPlayersUsing);
            this.world.notifyNeighborsOfStateChange(this.pos, block);
        }
    }

    public static void swapContents(DehydratorTileEntity a, DehydratorTileEntity b){
        NonNullList<ItemStack> list = a.getItems();
        a.setItems(b.getItems());
        b.setItems(list);
    }

    @Override
    public void updateContainingBlockInfo() {
        super.updateContainingBlockInfo();
        if(this.itemHandler != null){
            this.itemHandler.invalidate();
            this.itemHandler = null;
        }
    }
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, LazyOptional.of(() -> this.items));
    }

    private IItemHandlerModifiable createHandler(){

        return new InvWrapper(this);
    }


    @Override
    public void remove(){
        super.remove();
        if(itemHandler!=null){
            itemHandler.invalidate();
        }
    }

    //DEHYDRATOR SPECIFIC METHODS
    public int adjustedDryTime(){
        int x = maxDryTime;
        float heat = biome.getTemperature();
        x /= Math.round(heat);
        if(biome.isHighHumidity()){
            x+=200;
        }
        return x;
    }
    private void inventoryChanged() {
        this.markDirty();
        this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(),
                Constants.BlockFlags.BLOCK_UPDATE);
    }
    @Override
    public void tick(){
        boolean dirty = false;
        if (this.world != null && !this.world.isRemote){
            if(this.world.getDimensionType().hasSkyLight() && this.world.isDaytime()) {

                for (int i = 0; i < items.getSlots(); i++) {

                    if (this.getRecipe(this.items.getStackInSlot(i)) != null) {
                        if (this.dryTimes[i] < adjustedDryTime()){
                            this.dryTimes[i] += 1;
                        } else {
                            this.dryTimes[i] = 0;
                            ItemStack output = this.getRecipe(this.items.getStackInSlot(i)).getRecipeOutput();
                            this.items.setStackInSlot(i, ItemStack.EMPTY);
                            this.items.insertItem(i, output.copy(), false);
                        }
                        //TODO fix tick method
                        dirty = true;
                    }
                }
            }
        }
        if (dirty) { inventoryChanged(); }
    }

    @Nullable
    private DehydratingRecipe getRecipe(ItemStack stack){
        if(stack==null){ return null; }
        Set<IRecipe<?>> recipes = findRecipesByType(RecipeSerializerInit.DEHYDRATING, this.world);
        for(IRecipe<?> iRecipe : recipes){
            DehydratingRecipe recipe = (DehydratingRecipe) iRecipe;
            if(recipe.matches(new RecipeWrapper(this.items), this.world)){
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


    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbt = new CompoundNBT();
        this.write(nbt);
        return new SUpdateTileEntityPacket(this.pos, 0, nbt);
    }



}
