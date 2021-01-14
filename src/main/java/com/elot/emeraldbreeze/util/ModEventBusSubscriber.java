package com.elot.emeraldbreeze.util;

import com.elot.emeraldbreeze.EmeraldBreeze;
import com.elot.emeraldbreeze.blocks.ModCrop;
import com.elot.emeraldbreeze.client.gui.DehydratorScreen;
import com.elot.emeraldbreeze.core.init.BlockInit;
import com.elot.emeraldbreeze.core.init.ContainerTypeInit;
import com.elot.emeraldbreeze.inventory.container.DehydratorContainer;
import com.elot.emeraldbreeze.tileentity.DehydratorTileEntity;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = EmeraldBreeze.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusSubscriber {

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();
        BlockInit.BLOCKS.getEntries().stream().filter(block -> !(block.get() instanceof ModCrop))
                .map(RegistryObject::get).forEach(block -> {
                    final Item.Properties properties = new Item.Properties().group(EmeraldBreeze.TAB);
                    final BlockItem blockItem = new BlockItem(block, properties);
                    blockItem.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
                    registry.register(blockItem);
                });
        EmeraldBreeze.LOGGER.info("REGISTERED BLOCK-ITEMS");
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event){
    // Apply Render types
        RenderTypeLookup.setRenderLayer(BlockInit.CORELLIAN_CORN.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.GARLIC_CROP.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.DEHYDRATOR.get(), RenderType.LINES);
    // Register Screen factory for mod Tile entities
        ScreenManager.registerFactory(ContainerTypeInit.DEHYDRATOR.get(), DehydratorScreen::new);
    }

}