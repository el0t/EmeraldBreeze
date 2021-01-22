package com.elot.emeraldbreeze.items.crafting;

import com.elot.emeraldbreeze.EmeraldBreeze;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;

public interface IModRecipe extends IRecipe<RecipeWrapper> {

    ResourceLocation DEHYDRATING = new ResourceLocation(EmeraldBreeze.MOD_ID, "dehydrating");
    ResourceLocation TUMBLING = new ResourceLocation(EmeraldBreeze.MOD_ID, "tumbling");

    @Nonnull
    @Override
    default IRecipeType<?> getType(){
        return Registry.RECIPE_TYPE.getOrDefault(DEHYDRATING);
    }
    @Override
    default boolean canFit(int width, int height){
        return false;
    }
    Ingredient getInput();
}
