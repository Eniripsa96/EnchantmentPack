package com.sucy.enchant.potion.hit.inflict;

import com.sucy.enchant.api.ItemSet;
import org.bukkit.potion.PotionEffectType;

/**
 * Applies weakness on hit
 */
public class Weakness extends PotionInflict {

    public Weakness() {
        super("Weakness", "Lowers enemies' damage on hit");

        addNaturalItems(ItemSet.AXES.getItems());
    }

    /**
     * @return type of potion applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.WEAKNESS;
    }
}
