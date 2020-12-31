package com.elot.emeraldbreeze.blocks;

import com.elot.emeraldbreeze.core.init.BlockInit;
import net.minecraft.block.OreBlock;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class ModOreBlock extends OreBlock {

    public ModOreBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected int getExperience(Random rand) {
        if (this == BlockInit.ONYX_ORE.get()){
            return MathHelper.nextInt(rand,2,6);
        } else {
            return super.getExperience(rand);
        }
    }
}
