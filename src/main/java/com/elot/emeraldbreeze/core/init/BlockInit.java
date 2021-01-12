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

public class BlockInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, EmeraldBreeze.MOD_ID);

    public static final RegistryObject<Block> ONYX_BLOCK = BLOCKS.register("onyx_block", () -> new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.BLACK).hardnessAndResistance(5.0f, 30.0f).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.METAL)));
    public static final RegistryObject<Block> SILVER_BLOCK = BLOCKS.register("silver_block", () -> new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.STONE).hardnessAndResistance(5.0f,30.0f).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.METAL)));
    public static final RegistryObject<Block> MITHRIL_BLOCK = BLOCKS.register("mithril_block", ()-> new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.BLUE).hardnessAndResistance(12.0f,45.0f).harvestTool(ToolType.PICKAXE).harvestLevel(3).sound(SoundType.METAL)));
    public static final RegistryObject<Block> ONYX_ORE = BLOCKS.register("onyx_ore", () -> new ModOreBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(3.0f,3.0f).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.STONE)));
    public static final RegistryObject<Block> SILVER_ORE = BLOCKS.register("silver_ore", () -> new ModOreBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(3.0f,3.0f).harvestTool(ToolType.PICKAXE).harvestLevel(1).sound(SoundType.STONE)));
    public static final RegistryObject<Block> MITHRIL_ORE = BLOCKS.register("mithril_ore", () -> new ModOreBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLUE).hardnessAndResistance(5.0f,10.0f).harvestTool(ToolType.PICKAXE).harvestLevel(3).sound(SoundType.STONE)));
    public static final RegistryObject<Block> CORELLIAN_CORN = BLOCKS.register("corellian_corn", () -> new ModCrop(AbstractBlock.Properties.from(Blocks.WHEAT)));
    public static final RegistryObject<Block> GARLIC_CROP = BLOCKS.register("garlic", () -> new ModCrop(AbstractBlock.Properties.from(Blocks.POTATOES)));
    public static final RegistryObject<Block> DEHYDRATOR = BLOCKS.register("dehydrator", () -> new DehydratorBlock(AbstractBlock.Properties.create(Material.IRON)));
}
