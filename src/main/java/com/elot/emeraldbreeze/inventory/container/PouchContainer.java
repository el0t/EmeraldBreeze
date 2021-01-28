package com.elot.emeraldbreeze.inventory.container;

import com.elot.emeraldbreeze.core.init.ContainerTypeInit;
import com.elot.emeraldbreeze.items.PouchItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.items.IItemHandler;


public class PouchContainer extends Container {
    private final ItemStack item;
    private final IItemHandler itemHandler;
    private int blockedSlot = -1;

    public PouchContainer(int id, PlayerInventory playerInventory){
        super(ContainerTypeInit.POUCH.get(), id);
        this.item = getHeldPouch(playerInventory.player);
        this.itemHandler = ((PouchItem)this.item.getItem()).getInventory(this.item);

        //TODO create container
        // using blockedSlot to prevent pouch issue

        // Pouch inventory
        int startX = 71;
        int startY = 33;
        int slotSizePlus2 = 18;
        for(int row = 0; row < 1; row++){
            for(int column = 0; column < 1; column++){
                this.addSlot(new Slot(playerInventory, 9 + (row*2)+column,
                        startX + (column*slotSizePlus2),
                        startY + (row*slotSizePlus2)));
            }
        }

        //Player inventory
        int startPlayerX = 8;
        int startPlayerY = 84;
        for(int row = 0; row < 3; row++){
            for(int column = 0; column < 9; column++){
                this.addSlot(new Slot(playerInventory, 9 + (row*9)+column,
                        startPlayerX + (column*slotSizePlus2),
                        startPlayerY + (row*slotSizePlus2)));
            }
        }
        // Hotbar
        int hotbarY = 142;
        for(int column = 0; column < 9; column++){
            Slot slot = addSlot(
                    new Slot(playerInventory, column, startPlayerX+(column*slotSizePlus2), hotbarY){
                        @Override
                        public boolean canTakeStack(PlayerEntity playerIn){ return slotNumber != blockedSlot; }
            });

            if (column==playerInventory.currentItem
                    && ItemStack.areItemStacksEqual(playerInventory.getCurrentItem(),this.item)){
                blockedSlot = slot.slotNumber;
            }
        }

    }
    public PouchContainer(int id, PlayerInventory playerInventory, PacketBuffer data){
        this(id, playerInventory);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) { return true; }

    @Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, PlayerEntity player) {
        if (slotId < 0 || slotId > inventorySlots.size()) { return super.slotClick(slotId, dragType, clickTypeIn, player); }

        Slot slot = inventorySlots.get(slotId);
        if(!canTake(slotId, slot, dragType, player, clickTypeIn)){ return slot.getStack(); }
        else return super.slotClick(slotId,dragType,clickTypeIn,player);
    }
    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.getSlot(index);
        if (slot!=null && slot.getHasStack()
                && index!=blockedSlot){     //added condition for blockedSlot to prevent pouch issues
            ItemStack slotStack = slot.getStack();
            itemStack = slotStack.copy();
            if(index<36){
                if(!this.mergeItemStack(slotStack,36,this.inventorySlots.size(), true)){
                    return ItemStack.EMPTY;
                }
            } else if(!this.mergeItemStack(slotStack, 0, 36, false)){
                return ItemStack.EMPTY;
            }
            if (slotStack.isEmpty()){
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }
        return itemStack;
    }

    @Override
    public void onContainerClosed(PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        ((PouchItem) this.item.getItem()).saveInventory(this.item, this.itemHandler);
    }
    public boolean canTake(int slotId, Slot slot, int dragType, PlayerEntity playerEntity, ClickType clickType){
        if (slotId == blockedSlot || slotId <= itemHandler.getSlots()-1
                && isPouchItem(playerEntity.inventory.getItemStack())){ return false; }
        if (clickType == ClickType.SWAP){
            int hotbarId = itemHandler.getSlots() + 27 + dragType;
            if(blockedSlot == hotbarId){ return false; } //prevents swapping pouch into itself

            Slot hotbarSlot = getSlot(hotbarId);
            if (slotId <= itemHandler.getSlots() - 1)
                return !isPouchItem(slot.getStack())&&!isPouchItem(hotbarSlot.getStack());
        }
        return true;
    }
    private static boolean isPouchItem(ItemStack stack){
        return stack.getItem() instanceof PouchItem;
    }
    private static ItemStack getHeldPouch(PlayerEntity playerEntity){
        if(isPouchItem(playerEntity.getHeldItemMainhand())){ return playerEntity.getHeldItemMainhand(); }
        if(isPouchItem(playerEntity.getHeldItemOffhand())){ return playerEntity.getHeldItemOffhand(); }
        return ItemStack.EMPTY;
        //Main hand should take priority, if 2 pouches are held
    }
}
