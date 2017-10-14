package com.sucy.enchant.potion.hit.inflict;

import com.sucy.enchant.api.ItemSet;
import org.bukkit.potion.PotionEffectType;

/**
 * Applies poison on hit
 */
public class Poison extends PotionInflict {

    public Poison() {
        super("Poison", "Poisons enemies on hit");

        addNaturalItems(ItemSet.HOES.getItems());
    }

    /**
     * @return type of potion applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.POISON;
    }
}