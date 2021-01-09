package com.elot.emeraldbreeze.items;

import net.minecraft.item.Food;

public class ModFoods {

    public static final Food GARLIC = (new Food.Builder().hunger(2).saturation(0.2F).build());
    public static final Food POPCORN = (new Food.Builder().hunger(1).setAlwaysEdible().fastToEat().build());




}
