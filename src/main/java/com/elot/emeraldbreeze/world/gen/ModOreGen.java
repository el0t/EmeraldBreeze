package com.elot.emeraldbreeze.world.gen;

import com.elot.emeraldbreeze.EmeraldBreeze;
import com.elot.emeraldbreeze.core.init.BlockInit;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod.EventBusSubscriber
public class ModOreGen {

    private static final ArrayList<ConfiguredFeature<?, ?>> overworldOres = new ArrayList<ConfiguredFeature<?, ?>>();
    private static final ArrayList<ConfiguredFeature<?, ?>> netherOres = new ArrayList<ConfiguredFeature<?, ?>>();
    private static final ArrayList<ConfiguredFeature<?, ?>> endOres = new ArrayList<ConfiguredFeature<?, ?>>();

    private static ConfiguredFeature ONYX_ORE = Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
            BlockInit.ONYX_ORE.get().getDefaultState(), 3)) //vein size
            .range(39).square()                       //spawn height
            .func_242731_b(4);                        //frequency of veins per chunk
    /* BASE_STONE_OVERWORLD is for generating in stone, granite, diorite, and andesite
        NETHERRACK is for generating in netherrack
       BASE_STONE_NETHER is for generating in netherrack, basalt and blackstone
       End ores can use a new BlockMatchRuleTest to generate in end stone           */

    private static ConfiguredFeature SILVER_ORE = Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, BlockInit.SILVER_ORE.get().getDefaultState(), 8)).range(64).square().func_242731_b(10);
    private static ConfiguredFeature MITHRIL_ORE = Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, BlockInit.MITHRIL_ORE.get().getDefaultState(), 6)).range(24).square().func_242731_b(6);
    private static ConfiguredFeature KODUR_ORE = Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, BlockInit.KODUR_ORE.get().getDefaultState(), 12)).withPlacement(Features.Placements.NETHER_SPRING_ORE_PLACEMENT).square().func_242731_b(10);
    private static ConfiguredFeature TOLMANITE_ORE = Feature.NO_SURFACE_ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, BlockInit.TOLMANITE_ORE.get().getDefaultState(), 8)).range(42).square().func_242731_b(5);
    private static ConfiguredFeature SIMION_ORE = Feature.ORE.withConfiguration(new OreFeatureConfig(new BlockMatchRuleTest(Blocks.END_STONE), BlockInit.SIMION_ORE.get().getDefaultState(), 4)).range(128).square().func_242731_b(6);
    private static ConfiguredFeature ESMANITE_ORE = Feature.ORE.withConfiguration(new OreFeatureConfig(new BlockMatchRuleTest(Blocks.END_STONE), BlockInit.ESMANITE_ORE.get().getDefaultState(), 8)).range(128).square().func_242731_b(10);
    public static void registerOres() {

        //Overworld Ores
        overworldOres.add(register("onyx_ore", ONYX_ORE)); //frequency of veins per chunk
        overworldOres.add(register("silver_ore", SILVER_ORE));
        overworldOres.add(register("mithril_ore", MITHRIL_ORE));
        //Nether Ores
        netherOres.add(register("kodur_ore", KODUR_ORE));
        netherOres.add(register("tolmanite_ore", TOLMANITE_ORE));
        //End Ores
        endOres.add(register("simion_ore", SIMION_ORE));
        endOres.add(register("esmanite_ore", ESMANITE_ORE));
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void generateOres(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        if (event.getCategory().equals(Biome.Category.NETHER)) {
            for (ConfiguredFeature<?, ?> ore : netherOres) {
                if (ore != null) generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
            }
        }
        if (event.getCategory().equals(Biome.Category.THEEND)) {
            for (ConfiguredFeature<?, ?> ore : endOres) {
                if (ore != null) generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
            }
        }
        for (ConfiguredFeature<?, ?> ore : overworldOres) {
            if (ore != null) generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
        }
    }


    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, EmeraldBreeze.MOD_ID + ":" + name, configuredFeature);
    }
}

