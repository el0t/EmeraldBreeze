package com.elot.emeraldbreeze.core.init;

import com.elot.emeraldbreeze.EmeraldBreeze;
import com.elot.emeraldbreeze.tileentity.DehydratorTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityTypeInit {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, EmeraldBreeze.MOD_ID);

    public static final RegistryObject<TileEntityType<DehydratorTileEntity>> DEHYDRATOR = TILE_ENTITY_TYPES.register(
            "dehydrator", () -> TileEntityType.Builder.create(DehydratorTileEntity::new,
                    BlockInit.DEHYDRATOR.get()).build(null));

}
