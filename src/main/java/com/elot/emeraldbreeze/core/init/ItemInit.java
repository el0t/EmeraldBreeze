package com.elot.emeraldbreeze.core.init;

import com.elot.emeraldbreeze.EmeraldBreeze;

import com.elot.emeraldbreeze.items.ModArmorMaterial;
import com.elot.emeraldbreeze.items.ModItemTier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EmeraldBreeze.MOD_ID);

    public static final RegistryObject<Item> ONYX = ITEMS.register("onyx", () -> new Item(new Item.Properties().group(EmeraldBreeze.TAB)));
    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot", () -> new Item(new Item.Properties().group(EmeraldBreeze.TAB)));
    public static final RegistryObject<Item> MITHRIL_INGOT = ITEMS.register("mithril_ingot", () -> new Item(new Item.Properties().group(EmeraldBreeze.TAB)));
    public static final RegistryObject<Item> MITHRIL_SWORD = ITEMS.register("mithril_sword", () -> new SwordItem(ModItemTier.MITHRIL, 3, -2.4F, new Item.Properties().group(EmeraldBreeze.TAB)));
    public static final RegistryObject<Item> MITHRIL_PICKAXE = ITEMS.register("mithril_pickaxe", () -> new PickaxeItem(ModItemTier.MITHRIL, 1, -2.8F, new Item.Properties().group(EmeraldBreeze.TAB)));
    public static final RegistryObject<Item> MITHRIL_SHOVEL = ITEMS.register("mithril_shovel", () -> new ShovelItem(ModItemTier.MITHRIL, 1.5F, -3.0F, new Item.Properties().group(EmeraldBreeze.TAB)));
    public static final RegistryObject<Item> MITHRIL_AXE = ITEMS.register("mithril_axe", () -> new AxeItem(ModItemTier.MITHRIL, 5.0F, -3.0F, new Item.Properties().group(EmeraldBreeze.TAB)));
    public static final RegistryObject<Item> MITHRIL_HOE = ITEMS.register("mithril_hoe", () -> new HoeItem(ModItemTier.MITHRIL, -2, 0.0F, new Item.Properties().group(EmeraldBreeze.TAB)));
    public static final RegistryObject<Item> MITHRIL_HELMET = ITEMS.register("mithril_helmet", () -> new ArmorItem(ModArmorMaterial.MITHRIL, EquipmentSlotType.HEAD, (new Item.Properties()).group(EmeraldBreeze.TAB)));
    public static final RegistryObject<Item> MITHRIL_CHESTPLATE = ITEMS.register("mithril_chestplate", () -> new ArmorItem(ModArmorMaterial.MITHRIL, EquipmentSlotType.CHEST, (new Item.Properties()).group(EmeraldBreeze.TAB)));
    public static final RegistryObject<Item> MITHRIL_LEGGINGS = ITEMS.register("mithril_leggings", () -> new ArmorItem(ModArmorMaterial.MITHRIL, EquipmentSlotType.LEGS, (new Item.Properties()).group(EmeraldBreeze.TAB)));
    public static final RegistryObject<Item> MITHRIL_BOOTS = ITEMS.register("mithril_boots", () -> new ArmorItem(ModArmorMaterial.MITHRIL, EquipmentSlotType.FEET, (new Item.Properties()).group(EmeraldBreeze.TAB)));

}
