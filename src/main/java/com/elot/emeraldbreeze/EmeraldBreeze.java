package com.elot.emeraldbreeze;

import com.elot.emeraldbreeze.core.init.*;
import com.elot.emeraldbreeze.world.gen.ModOreGen;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("emeraldbreeze")
public class EmeraldBreeze
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "emeraldbreeze";

    public EmeraldBreeze() {
        // Register the setup method for modloading
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);

        ItemInit.ITEMS.register(bus);
        BlockInit.BLOCKS.register(bus);
        TileEntityTypeInit.TILE_ENTITY_TYPES.register(bus);
        ContainerTypeInit.CONTAINER_TYPES.register(bus);


        RecipeSerializerInit.RECIPE_SERIALIZERS.register(bus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        /* LOGGER.info("HELLO FROM PREINIT");
           LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName()); */
        // ore generation
        ModOreGen.registerOres();
    }

    //Creative Mode tabs
    public static final ItemGroup TAB = new ItemGroup("eb1") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemInit.ONYX.get());
        }};

}
