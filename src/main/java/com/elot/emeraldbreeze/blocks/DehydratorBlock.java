package com.elot.emeraldbreeze.blocks;

import com.elot.emeraldbreeze.core.init.TileEntityTypeInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;


public class DehydratorBlock extends ContainerBlock {

    public DehydratorBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state){
        return true;
    }
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world){
        return TileEntityTypeInit.DEHYDRATOR.get().create();
    }

    //TODO
    // this whole class......




    //Deprecated
    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return TileEntityTypeInit.DEHYDRATOR.get().create();
    }
}
