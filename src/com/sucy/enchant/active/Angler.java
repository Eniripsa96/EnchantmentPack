package com.sucy.enchant.active;

import com.sucy.enchant.api.CustomEnchantment;
import org.bukkit.Material;

public class Angler extends CustomEnchantment {

    public static Angler instance;

    public Angler() {
        super("Angler", "Yields extra fish while fishing");
        setMaxLevel(5);
        setWeight(4);
        addNaturalItems(Material.FISHING_ROD);

        instance = this;
    }
}
