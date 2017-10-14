package com.sucy.enchant.potion.damaged.reflect;

import com.sucy.enchant.api.ItemSet;
import org.bukkit.potion.PotionEffectType;

/**
 * Slows enemies on hit
 */
public class Frost extends PotionReflect {

    public Frost() {
        super("Frost", "Slows enemies when hit");

        addNaturalItems(ItemSet.LEGGINGS.getItems());
    }

    /**
     * @return type of potion applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.SLOW;
    }
}
