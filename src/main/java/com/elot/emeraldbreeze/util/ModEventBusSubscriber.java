package com.elot.emeraldbreeze.util;

import com.elot.emeraldbreeze.EmeraldBreeze;
import com.elot.emeraldbreeze.blocks.ModCrop;
import com.elot.emeraldbreeze.core.init.BlockInit;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
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
}