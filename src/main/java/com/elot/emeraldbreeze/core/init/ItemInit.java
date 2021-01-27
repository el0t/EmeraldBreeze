package com.elot.emeraldbreeze.core.init;

import com.elot.emeraldbreeze.EmeraldBreeze;

import com.elot.emeraldbreeze.items.ModArmorMaterial;
import com.elot.emeraldbreeze.items.ModFoods;
import com.elot.emeraldbreeze.items.ModItemTier;
import com.elot.emeraldbreeze.items.InventoryItem;
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
    public static final RegistryObject<Item> KODUR_INGOT = ITEMS.register("kodur_ingot", ()-> new Item(new Item.Properties().group(EmeraldBreeze.TAB)));
    public static final RegistryObject<Item> TOLMANITE_INGOT = ITEMS.register("tolmanite_ingot", () -> new Item(new Item.Properties().group(EmeraldBreeze.TAB)));
    //TODO add output for esmanite and simion mining

    //TODO add tolmanite armor and weapons
    public static final RegistryObject<Item> MITHRIL_SWORD = ITEMS.register("mithril_sword", () -> new SwordItem(ModItemTier.MITHRIL, 3, -2.4F, new Item.Properties().group(EmeraldBreeze.TAB)));
    public static final RegistryObject<Item> MITHRIL_PICKAXE = ITEMS.register("mithril_pickaxe", () -> new PickaxeItem(ModItemTier.MITHRIL, 1, -2.8F, new Item.Properties().group(EmeraldBreeze.TAB)));
    public static final RegistryObject<Item> MITHRIL_SHOVEL = ITEMS.register("mithril_shovel", () -> new ShovelItem(ModItemTier.MITHRIL, 1.5F, -3.0F, new Item.Properties().group(EmeraldBreeze.TAB)));
    public static final RegistryObject<Item> MITHRIL_AXE = ITEMS.register("mithril_axe", () -> new AxeItem(ModItemTier.MITHRIL, 5.0F, -3.0F, new Item.Properties().group(EmeraldBreeze.TAB)));
    public static final RegistryObject<Item> MITHRIL_HOE = ITEMS.register("mithril_hoe", () -> new HoeItem(ModItemTier.MITHRIL, -2, 0.0F, new Item.Properties().group(EmeraldBreeze.TAB)));
    public static final RegistryObject<Item> MITHRIL_HELMET = ITEMS.register("mithril_helmet", () -> new ArmorItem(ModArmorMaterial.MITHRIL, EquipmentSlotType.HEAD, (new Item.Properties()).group(EmeraldBreeze.TAB)));
    public static final RegistryObject<Item> MITHRIL_CHESTPLATE = ITEMS.register("mithril_chestplate", () -> new ArmorItem(ModArmorMaterial.MITHRIL, EquipmentSlotType.CHEST, (new Item.Properties()).group(EmeraldBreeze.TAB)));
    public static final RegistryObject<Item> MITHRIL_LEGGINGS = ITEMS.register("mithril_leggings", () -> new ArmorItem(ModArmorMaterial.MITHRIL, EquipmentSlotType.LEGS, (new Item.Properties()).group(EmeraldBreeze.TAB)));
    public static final RegistryObject<Item> MITHRIL_BOOTS = ITEMS.register("mithril_boots", () -> new ArmorItem(ModArmorMaterial.MITHRIL, EquipmentSlotType.FEET, (new Item.Properties()).group(EmeraldBreeze.TAB)));
    public static final RegistryObject<Item> CORELLIAN_SEED = ITEMS.register("corellian_seed", () -> new BlockItem(BlockInit.CORELLIAN_CORN.get(),(new Item.Properties()).group(EmeraldBreeze.TAB)));
    public static final RegistryObject<Item> GARLIC_SEED = ITEMS.register("garlic_seed", () -> new BlockItem(BlockInit.GARLIC_CROP.get(), (new Item.Properties().group(EmeraldBreeze.TAB))));
    public static final RegistryObject<Item> CORELLIAN_CORNCOB = ITEMS.register("corellian_corncob", () -> new Item(new Item.Properties().group(EmeraldBreeze.TAB)));
    public static final RegistryObject<Item> GARLIC = ITEMS.register("garlic", () -> new Item(new Item.Properties().food(ModFoods.GARLIC).group(EmeraldBreeze.TAB)));
    public static final RegistryObject<Item> POPCORN = ITEMS.register("popcorn", () -> new Item(new Item.Properties().food(ModFoods.POPCORN).group(EmeraldBreeze.TAB)));
    public static final RegistryObject<Item> DRIED_BEEF = ITEMS.register("dried_beef", () -> new Item(new Item.Properties().food(ModFoods.DRIED_MEAT).group(EmeraldBreeze.TAB)));
    public static final RegistryObject<Item> DRIED_PORK = ITEMS.register("dried_pork", () -> new Item(new Item.Properties().food(ModFoods.DRIED_MEAT).group(EmeraldBreeze.TAB)));
    public static final RegistryObject<Item> DRIED_CHICKEN = ITEMS.register("dried_chicken", () -> new Item(new Item.Properties().food(ModFoods.DRIED_CHICKEN).group(EmeraldBreeze.TAB)));
    public static final RegistryObject<Item> DRIED_COD = ITEMS.register("dried_cod", () -> new Item(new Item.Properties().food(ModFoods.DRIED_FISH).group(EmeraldBreeze.TAB)));
    public static final RegistryObject<Item> DRIED_SALMON = ITEMS.register("dried_salmon", () -> new Item(new Item.Properties().food(ModFoods.DRIED_CHICKEN).group(EmeraldBreeze.TAB)));
    public static final RegistryObject<Item> DRIED_MUTTON = ITEMS.register("dried_mutton", () -> new Item(new Item.Properties().food(ModFoods.DRIED_CHICKEN).group(EmeraldBreeze.TAB)));
    public static final RegistryObject<Item> DRIED_RABBIT = ITEMS.register("dried_rabbit", () -> new Item(new Item.Properties().food(ModFoods.DRIED_FISH).group(EmeraldBreeze.TAB)));
    public static final RegistryObject<Item> DRIED_FLESH = ITEMS.register("dried_flesh", () -> new Item(new Item.Properties().food(ModFoods.DRIED_FLESH).group(EmeraldBreeze.TAB)));

    //TODO add silver dagger, silver arrows

    //TODO add pouch
    // public static final RegistryObject<Item> POUCH = ITEMS.register("pouch", () -> );

    //TODO cursebreaker item

    //TODO add kodur ring

    //TODO update CrystalBallItem as desired

    //TODO add Dried plants

    //TODO add Blasting recipes for ALL mod ores??



}
