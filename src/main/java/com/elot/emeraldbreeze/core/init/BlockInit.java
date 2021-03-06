package com.elot.emeraldbreeze.core.init;

import com.elot.emeraldbreeze.EmeraldBreeze;
import com.elot.emeraldbreeze.blocks.DehydratorBlock;
import com.elot.emeraldbreeze.blocks.ModCrop;
import com.elot.emeraldbreeze.blocks.ModOreBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.lwjgl.system.CallbackI;

public class BlockInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, EmeraldBreeze.MOD_ID);

    public static final RegistryObject<Block> ONYX_BLOCK = BLOCKS.register("onyx_block", () -> new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.BLACK).hardnessAndResistance(5.0f, 30.0f).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.METAL)));
    public static final RegistryObject<Block> SILVER_BLOCK = BLOCKS.register("silver_block", () -> new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.STONE).hardnessAndResistance(5.0f,30.0f).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.METAL)));
    public static final RegistryObject<Block> MITHRIL_BLOCK = BLOCKS.register("mithril_block", ()-> new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.BLUE).hardnessAndResistance(12.0f,45.0f).harvestTool(ToolType.PICKAXE).harvestLevel(3).sound(SoundType.METAL)));

    //TODO assets for kodur, tolmanite blocks
    public static final RegistryObject<Block> KODUR_BLOCK = BLOCKS.register("kodur_block", ()-> new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.BROWN).hardnessAndResistance(4.0f,24.0f).harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)));
    public static final RegistryObject<Block> TOLMANITE_BLOCK = BLOCKS.register("tolmanite_block", () -> new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.RED).hardnessAndResistance(15.0f, 50.0f).harvestTool(ToolType.PICKAXE).harvestLevel(4)));
    //TODO data - loot_tables

    public static final RegistryObject<Block> ONYX_ORE = BLOCKS.register("onyx_ore", () -> new ModOreBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(3.0f,3.0f).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.STONE)));
    public static final RegistryObject<Block> SILVER_ORE = BLOCKS.register("silver_ore", () -> new ModOreBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(3.0f,3.0f).harvestTool(ToolType.PICKAXE).harvestLevel(1).sound(SoundType.STONE)));
    public static final RegistryObject<Block> MITHRIL_ORE = BLOCKS.register("mithril_ore", () -> new ModOreBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLUE).hardnessAndResistance(5.0f,10.0f).harvestTool(ToolType.PICKAXE).harvestLevel(3).sound(SoundType.STONE)));

    //TODO assets for kodur, tolmanite, esmanite, simion ores
    public static final RegistryObject<Block> KODUR_ORE = BLOCKS.register("kodur_ore", () -> new ModOreBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.NETHERRACK).hardnessAndResistance(4.0f, 2.0f).harvestTool(ToolType.PICKAXE).harvestLevel(1).sound(SoundType.STONE)));
    public static final RegistryObject<Block> TOLMANITE_ORE = BLOCKS.register("tolmanite_ore", () -> new ModOreBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.NETHERRACK).hardnessAndResistance(8.0f, 10.0f).harvestTool(ToolType.PICKAXE).harvestLevel(1).sound(SoundType.STONE)));
    public static final RegistryObject<Block> ESMANITE_ORE = BLOCKS.register("esmanite_ore", () -> new ModOreBlock(AbstractBlock.Properties.from(Blocks.END_STONE)));
    public static final RegistryObject<Block> SIMION_ORE = BLOCKS.register("simion_ore", () -> new ModOreBlock(AbstractBlock.Properties.from(Blocks.END_STONE)));
    //TODO data - loot_tables, recipes if needed?

    public static final RegistryObject<Block> CORELLIAN_CORN = BLOCKS.register("corellian_corn", () -> new ModCrop(AbstractBlock.Properties.from(Blocks.WHEAT)));
    public static final RegistryObject<Block> GARLIC_CROP = BLOCKS.register("garlic", () -> new ModCrop(AbstractBlock.Properties.from(Blocks.POTATOES)));
    public static final RegistryObject<Block> DEHYDRATOR = BLOCKS.register("dehydrator", () -> new DehydratorBlock(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(2.0f)));

}
