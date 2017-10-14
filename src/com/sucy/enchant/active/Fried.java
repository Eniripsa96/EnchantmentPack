package com.sucy.enchant.active;

import com.sucy.enchant.api.CustomEnchantment;
import org.bukkit.Material;

public class Fried extends CustomEnchantment {

    public static Fried instance;

    public Fried() {
        super("Fried", "Catches cooked fish instead of raw ones");

        setMaxLevel(1);
        setWeight(10);
        addNaturalItems(Material.FISHING_ROD);

        instance = this;
    }
}
