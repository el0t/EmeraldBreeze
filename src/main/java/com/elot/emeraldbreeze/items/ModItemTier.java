package com.elot.emeraldbreeze.items;

import com.elot.emeraldbreeze.core.init.ItemInit;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;

public enum ModItemTier implements IItemTier {
    /* harvestLevel, maxUses, efficiency, attackDamage, enchantability, repairMaterial
        WOOD    (0, 59, 2.0F, 0.0F, 15, () -> {return Ingredient.fromTag(ItemTags.PLANKS);}),
        STONE   (1, 131, 4.0F, 1.0F, 5, () -> {return Ingredient.fromTag(ItemTags.STONE_TOOL_MATERIALS);}),
        IRON    (2, 250, 6.0F, 2.0F, 14, () -> {return Ingredient.fromItems(Items.IRON_INGOT);}),
        DIAMOND (3, 1561, 8.0F, 3.0F, 10, () -> {return Ingredient.fromItems(Items.DIAMOND);}),
        GOLD    (0, 32, 12.0F, 0.0F, 22, () -> {return Ingredient.fromItems(Items.GOLD_INGOT);}),
      NETHERITE (4, 2031, 9.0F, 4.0F, 15, () -> {return Ingredient.fromItems(Items.NETHERITE_INGOT);});*/

    //Mod items

    SIMION(1, 69, 2.0F, 1.0F, 31, () -> {
        return toIngredient(ItemInit.SIMION_LUMP.get()); }),
    KODUR(2, 125, 5.0F, 2.0F, 5, () -> {
        return toIngredient(ItemInit.KODUR_INGOT.get()); }),
    MITHRIL(3, 750, 8.0F, 3.0F, 9, () -> {
        return toIngredient(ItemInit.MITHRIL_INGOT.get()); }),
    TOLMANITE(4, 1015, 9.0F, 4.0F, 12, () -> {
        return toIngredient(ItemInit.TOLMANITE_INGOT.get()); });

    private ModItemTier(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn, Supplier<Ingredient> repairMaterialIn){
        this.harvestLevel = harvestLevelIn;
        this.maxUses = maxUsesIn;
        this.efficiency = efficiencyIn;
        this.attackDamage = attackDamageIn;
        this.enchantability = enchantabilityIn;
        this.repairMaterial = new LazyValue<>(repairMaterialIn);
    }

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final LazyValue<Ingredient> repairMaterial;

    @Override
    public int getMaxUses() {
        return maxUses;
    }
    @Override
    public float getEfficiency() {
        return efficiency;
    }
    @Override
    public float getAttackDamage() {
        return attackDamage;
    }
    @Override
    public int getHarvestLevel() {
        return harvestLevel;
    }
    @Override
    public int getEnchantability() {
        return enchantability;
    }
    @Override
    public Ingredient getRepairMaterial() {
        return repairMaterial.getValue();
    }
    private static Ingredient toIngredient(Item item){ return Ingredient.fromItems(item); }
}
