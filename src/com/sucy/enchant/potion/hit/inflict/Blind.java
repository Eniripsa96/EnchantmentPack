package com.sucy.enchant.potion.hit.inflict;

import com.sucy.enchant.api.ItemSet;
import org.bukkit.potion.PotionEffectType;

/**
 * Blinds enemies on hit
 */
public class Blind extends PotionInflict {

    public Blind() {
        super("Blind", "Blinds enemies on hit");

        addNaturalItems(ItemSet.PICKAXES.getItems());
    }

    /**
     * @return potion type applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.BLINDNESS;
    }
}
