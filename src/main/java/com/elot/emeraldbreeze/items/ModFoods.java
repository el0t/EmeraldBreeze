package com.elot.emeraldbreeze.items;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class ModFoods {

    public static final Food GARLIC = (new Food.Builder().hunger(2).saturation(0.2F).build());
    public static final Food POPCORN = (new Food.Builder().hunger(1).setAlwaysEdible().fastToEat().build());
    public static final Food SAFE_HERBS = (new Food.Builder().setAlwaysEdible().fastToEat().build());
    public static final Food NETTLE = (new Food.Builder().setAlwaysEdible().fastToEat()
            .effect(new EffectInstance(Effects.HUNGER, 300, 2), 1.0F)).build();
    public static final Food DRIED_MEAT = buildJerky(7);
    public static final Food DRIED_CHICKEN = buildJerky(6);
    public static final Food DRIED_FISH = buildJerky(5);
    public static final Food DRIED_FLESH = buildJerky(4);

    private static Food buildJerky(int hunger){
        return (new Food.Builder().hunger(hunger).saturation(0.7F).meat().build());
    }

}
