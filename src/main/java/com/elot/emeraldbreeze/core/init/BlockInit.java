package com.elot.emeraldbreeze.core.init;

import com.elot.emeraldbreeze.EmeraldBreeze;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            EmeraldBreeze.MOD_ID);

    public static final RegistryObject<Block> ONYX_BLOCK = BLOCKS.register("onyx_block",
            () -> new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.BLACK)
                    .hardnessAndResistance(5.0f, 30.0f).harvestTool(ToolType.PICKAXE)
                    .harvestLevel(2).sound(SoundType.METAL)));
    public static final RegistryObject<Block> SILVER_BLOCK = BLOCKS.register("silver_block",
            () -> new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.STONE)
                    .hardnessAndResistance(5.0f,30.0f).harvestTool(ToolType.PICKAXE)
                    .harvestLevel(2).sound(SoundType.METAL)));
    public static final RegistryObject<Block> MITHRIL_BLOCK = BLOCKS.register("mithril_block",
            ()-> new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.BLUE)
                    .hardnessAndResistance(12.0f,45.0f).harvestTool(ToolType.PICKAXE)
                    .harvestLevel(3).sound(SoundType.METAL)));

}
