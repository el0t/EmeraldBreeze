package com.elot.emeraldbreeze.items;

import com.elot.emeraldbreeze.EmeraldBreeze;
import com.elot.emeraldbreeze.inventory.container.PouchContainer;
import com.elot.emeraldbreeze.util.ModItemHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class PouchItem extends Item {

    public PouchItem(Properties properties) {
        super(properties.maxStackSize(1));
    }
    public int getInventorySize(ItemStack stack){ return 4; } //size of pouch = 4
    protected TranslationTextComponent getDefaultName(){
        return new TranslationTextComponent("container."+EmeraldBreeze.MOD_ID+".pouch");
    }
    public IItemHandler getInventory(ItemStack stack){
        ModItemHandler itemHandler = new ModItemHandler(getInventorySize(stack));
        itemHandler.deserializeNBT(stack.getOrCreateTag().getCompound("Inventory"));
        return itemHandler;
    }
    public void saveInventory(ItemStack stack, IItemHandler itemHandler){
        if(itemHandler instanceof ItemStackHandler){
            stack.getOrCreateTag().put("Inventory", ((ItemStackHandler) itemHandler).serializeNBT());
        }
    }

    @Override
    public int getItemStackLimit(ItemStack stack) { return 1; }
    @Override
    public int getUseDuration(ItemStack stack) { return 2; }
    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(!worldIn.isRemote()){
            if(!playerIn.isSneaking()){
                playerIn.openContainer(
                        new SimpleNamedContainerProvider((id, playerInv, player) -> new PouchContainer(id, playerInv),
                                getDefaultName()));
            }
        }
        return new ActionResult<>(ActionResultType.SUCCESS, playerIn.getHeldItem(handIn));
    }
}
