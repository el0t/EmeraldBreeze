package com.elot.emeraldbreeze.items;

import com.elot.emeraldbreeze.util.ModItemHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.util.Constants;

public class InventoryItem extends Item implements IInventory {
    private final ItemStack pouchItem;
    private static int inventorySize;
    private ItemStack[] inventory = new ItemStack[inventorySize];
    private ModItemHandler itemHandler = new ModItemHandler(inventorySize, inventory);

    public InventoryItem(ItemStack stack, Properties properties) {
        super(properties);
        inventorySize = 4;
        pouchItem = stack;
            if(!pouchItem.hasTag()){ stack.setTag(new CompoundNBT()); }
            read(stack.getTag());
    }
    public InventoryItem(ItemStack stack, int invSize, Properties properties){
        super(properties);
        pouchItem = stack;
        inventorySize = invSize;
            if(!pouchItem.hasTag()){ stack.setTag(new CompoundNBT()); }
            read(stack.getTag());
    }

    @Override
    public int getSizeInventory() { return inventorySize; }

    public static void setInventorySize(int inventorySize) { InventoryItem.inventorySize = inventorySize; }

    @Override
    public boolean isEmpty() { return itemHandler.isEmpty(); }

    @Override
    public ItemStack getStackInSlot(int index) { return inventory[index]; }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        markDirty();
        return itemHandler.decreaseStackSize(index,count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        itemHandler.removeStackFromSlot(index);
        markDirty();
        return getStackInSlot(index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        inventory[index] = stack;
        markDirty();
    }

    @Override
    public void markDirty() {
        for(int i = 0; i < getSizeInventory(); i++){
            if(getStackInSlot(i) != null && getStackInSlot(i).getCount()==0){
                inventory[i] = null;
            }
        }
        write(pouchItem.getTag());
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) { return true; }

    @Override
    public void clear() { itemHandler.clear(); }

// NBT methods
    public void read(CompoundNBT compoundNBT){
        ListNBT items = compoundNBT.getList("ItemInventory", Constants.NBT.TAG_COMPOUND);
        for(int i = 0; i < (long) items.size(); i++){
            CompoundNBT item = (CompoundNBT) items.getCompound(i);
            int slot = item.getInt("Slot");
            if (slot >= 0 && slot < getSizeInventory()){
                inventory[slot] = ItemStack.read(item);
            }
        }
    }
    public void write(CompoundNBT compoundNBT){
        ListNBT items = new ListNBT();
        for(int i = 0; i < getSizeInventory(); i++){
            if(getStackInSlot(i) != null) {
                CompoundNBT item = new CompoundNBT();
                item.putInt("Slot", i);
                getStackInSlot(i).write(item);
            }
        }
        compoundNBT.put("ItemInventory", items);
    }

}
