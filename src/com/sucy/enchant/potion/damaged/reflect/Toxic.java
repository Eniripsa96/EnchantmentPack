package com.sucy.enchant.potion.damaged.reflect;

import com.sucy.enchant.api.ItemSet;
import org.bukkit.potion.PotionEffectType;

/**
 * Poisons enemies when they hit you
 */
public class Toxic extends PotionReflect {

    public Toxic() {
        super("Toxic", "Poisons enemies when hit");

        addNaturalItems(ItemSet.CHESTPLATES.getItems());
    }

    /**
     * @return type of potion applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.POISON;
    }
}
