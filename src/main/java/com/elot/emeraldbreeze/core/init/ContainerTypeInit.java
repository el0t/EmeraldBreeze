package com.elot.emeraldbreeze.core.init;

import com.elot.emeraldbreeze.EmeraldBreeze;
import com.elot.emeraldbreeze.inventory.container.DehydratorContainer;
import com.elot.emeraldbreeze.inventory.container.PouchContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerTypeInit {

    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, EmeraldBreeze.MOD_ID);

    public static final RegistryObject<ContainerType<DehydratorContainer>> DEHYDRATOR = CONTAINER_TYPES.
            register("dehydrator", () -> IForgeContainerType.create(DehydratorContainer::new));
    public static final RegistryObject<ContainerType<PouchContainer>> POUCH = CONTAINER_TYPES.
            register("pouch", () -> IForgeContainerType.create(PouchContainer::new));
            //TODO needs appropraite contstructor in PouchContainer class

}
