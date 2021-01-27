package com.elot.emeraldbreeze.client.gui;

import com.elot.emeraldbreeze.EmeraldBreeze;
import com.elot.emeraldbreeze.inventory.container.PouchContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

//TODO pouch screen class
public class PouchScreen extends ContainerScreen<PouchContainer> {
    private final ResourceLocation BACKGROUND_TEXTURE =
            new ResourceLocation(EmeraldBreeze.MOD_ID, "textures/gui/container/pouch.png");

    public PouchScreen(PouchContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {

    }
}
