package com.sucy.enchant.potion.hit.inflict;

import com.sucy.enchant.api.ItemSet;
import org.bukkit.potion.PotionEffectType;

/**
 * Slows enemies on hit
 */
public class Slow extends PotionInflict {

    public Slow() {
        super("Slow", "Slows enemies on hit");

        addNaturalItems(ItemSet.HOES.getItems());
    }

    /**
     * @return type of potion applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.SLOW;
    }
}
