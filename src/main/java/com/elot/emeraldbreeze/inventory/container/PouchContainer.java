package com.elot.emeraldbreeze.inventory.container;

import com.elot.emeraldbreeze.core.init.ContainerTypeInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;

public class PouchContainer extends Container {
    private final IInventory inventory;
    public PouchContainer(int id, PlayerInventory playerInventory, IInventory inventory){
        super(ContainerTypeInit.POUCH.get(), id);
        this.inventory = inventory;
        inventory.openInventory(playerInventory.player);
    }
    public PouchContainer(int id, PlayerInventory playerInventory){
        this(id, playerInventory, new Inventory(4));
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return this.inventory.isUsableByPlayer(playerIn);
    }
}
