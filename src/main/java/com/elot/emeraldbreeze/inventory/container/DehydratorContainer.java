package com.elot.emeraldbreeze.inventory.container;

import com.elot.emeraldbreeze.core.init.BlockInit;
import com.elot.emeraldbreeze.core.init.ContainerTypeInit;
import com.elot.emeraldbreeze.tileentity.DehydratorTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
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

        //TODO
        // build interface of slots




    }
    public DehydratorContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }

    private static DehydratorTileEntity getTileEntity(PlayerInventory playerInventory, PacketBuffer data) {
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

    //TODO
    // Override public ItemStack transferStackInSlot --
}
