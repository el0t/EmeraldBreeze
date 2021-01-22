package com.elot.emeraldbreeze.util;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemStackHandler;

public class ModItemHandler extends ItemStackHandler {

    public ModItemHandler(int size, ItemStack... stacks){
        super(size);

        for(int index = 0; index <= stacks.length; index++){
            this.stacks.set(index, stacks[index]);
        }
    }

    public boolean isEmpty(){
        for (ItemStack stack : this.stacks){
            if (stack.isEmpty() || stack.getItem() == Items.AIR){ return true; }
        }
        return false;
    }

    public void clear(){
        for(int index = 0; index < this.getSlots(); index++){
            this.stacks.set(index, ItemStack.EMPTY);
            this.onContentsChanged(index);
        }
    }

    public void removeStackFromSlot(int index){
        this.stacks.set(index, ItemStack.EMPTY);
        this.onContentsChanged(index);
    }

    public ItemStack decreaseStackSize(int index, int count){
        ItemStack stack = getStackInSlot(index);
        stack.shrink(count);
        this.onContentsChanged(index);
        return stack;
    }

    public NonNullList<ItemStack> toNonNullList() {
        NonNullList<ItemStack> list = NonNullList.create();
        for (ItemStack stack : this.stacks){
            list.add(stack);
        }
        return list;
    }

    public void setNonNullList(NonNullList<ItemStack> list){
        if (list.size() == 0){ return; }
        if (list.size() != this.getSlots()){
            throw new IndexOutOfBoundsException("NonNullList must be the same size as ItemStackHandler!");
        }
        for (int index = 0; index < list.size(); index++){
            this.stacks.set(index, list.get(index));
        }
    }

    @Override
    public String toString(){
        return this.stacks.toString();
    }

}
