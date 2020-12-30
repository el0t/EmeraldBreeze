package com.elot.emeraldbreeze.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class ModCrop extends CropsBlock {
    private Item seedItem = (Item) getSeedsItem();
    public static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.makeCuboidShape(0.0D,0.0D,0.0D,16.0D,2.0D,16.0D),
            Block.makeCuboidShape(0.0D,0.0D,0.0D,16.0D,4.0D,16.0D),
            Block.makeCuboidShape(0.0D,0.0D,0.0D,16.0D,6.0D,16.0D),
            Block.makeCuboidShape(0.0D,0.0D,0.0D,16.0D,8.0D,16.0D),
            Block.makeCuboidShape(0.0D,0.0D,0.0D,16.0D,10.0D,16.0D),
            Block.makeCuboidShape(0.0D,0.0D,0.0D,16.0D,12.0D,16.0D),
            Block.makeCuboidShape(0.0D,0.0D,0.0D,16.0D,14.0D,16.0D),
            Block.makeCuboidShape(0.0D,0.0D,0.0D,16.0D,16.0D,16.0D), };


    public ModCrop(Properties builder) {
        super(builder);
    }

    public void setSeedItem(Item seedItem) {
        this.seedItem = seedItem;
    }

    @Override
    protected IItemProvider getSeedsItem() {
        return seedItem;
    }

    //Set the size of the crop's hitbox based on its age.
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE_BY_AGE[state.get(this.getAgeProperty())];
    }
}
