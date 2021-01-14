package com.elot.emeraldbreeze.inventory.container;

import com.elot.emeraldbreeze.core.init.BlockInit;
import com.elot.emeraldbreeze.core.init.ContainerTypeInit;
import com.elot.emeraldbreeze.tileentity.DehydratorTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

import java.util.Objects;

public class DehydratorContainer extends Container {

    public final DehydratorTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;

    public DehydratorContainer(final int windowID, final PlayerInventory playerInventory, final DehydratorTileEntity tileEntity){
        super(ContainerTypeInit.DEHYDRATOR.get(), windowID);
        this.tileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());

        //Dehydrator inventory
        int startX = 53;
        int startY = 17;
        int slotSizePlus2 = 18;
        for(int row = 0; row < 3; row++){
            for(int column = 0; column < 4; column++){
                this.addSlot(new Slot(tileEntity, (row*4)+column, //Genius maths from turty for the index
                        startX + (column*slotSizePlus2),
                        startY + (row*slotSizePlus2)){
                    public int getSlotStackLimit(){ return 1; }
                    // this should set the maximum pieces of jerky to 1 per slot
                });
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
        //Hotbar inventory
        int hotbarY = 142;
        for(int column = 0; column < 9; column++){
            this.addSlot(new Slot(playerInventory,column,startX+(column*slotSizePlus2),hotbarY));
        }
    }
    public DehydratorContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }

    private static DehydratorTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
        Objects.requireNonNull(playerInventory,"playerInventory cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        final TileEntity tileEntityAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
        if(tileEntityAtPos instanceof DehydratorTileEntity){
            return (DehydratorTileEntity) tileEntityAtPos;
        } else
            throw new IllegalStateException("Tile entity is not correct?! " + tileEntityAtPos);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(canInteractWithCallable, playerIn, BlockInit.DEHYDRATOR.get());
    }
    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index){
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot!=null && slot.getHasStack()){
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
}
