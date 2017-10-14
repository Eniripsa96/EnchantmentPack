package com.sucy.enchant.potion.hit.inflict;

import com.sucy.enchant.api.ItemSet;
import org.bukkit.potion.PotionEffectType;

/**
 * Applies wither on hit
 */
public class Wither extends PotionInflict {

    public Wither() {
        super("Wither", "Applies wither on hit");

        addNaturalItems(ItemSet.HOES.getItems());
    }

    /**
     * @return potion type applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.WITHER;
    }
}
