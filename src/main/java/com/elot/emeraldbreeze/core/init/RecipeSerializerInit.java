package com.elot.emeraldbreeze.core.init;

import com.elot.emeraldbreeze.EmeraldBreeze;
import com.elot.emeraldbreeze.items.crafting.IModRecipe;
import com.elot.emeraldbreeze.items.crafting.ModRecipe;
import com.elot.emeraldbreeze.items.crafting.ModRecipeSerializer;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeSerializerInit {

    public static final IRecipeSerializer<ModRecipe> MOD_RECIPE_SERIALIZER = new ModRecipeSerializer();
    public static final IRecipeType<IModRecipe> DEHYDRATING = registerType(IModRecipe.RECIPE_TYPE_ID);

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, EmeraldBreeze.MOD_ID);

    public static final RegistryObject<IRecipeSerializer<?>> MOD_SERIALIZER = RECIPE_SERIALIZERS.register("modrecipes",
            () -> MOD_RECIPE_SERIALIZER);

    private static class RecipeType<T extends IRecipe<?>> implements IRecipeType<T> {
        @Override
        public String toString() {
            return Registry.RECIPE_TYPE.getKey(this).toString();
        }
    }

    private static <T extends IRecipeType> T registerType(ResourceLocation recipeTypeId) {
        return (T) Registry.register(Registry.RECIPE_TYPE, recipeTypeId, new RecipeType<>());
    }
}
