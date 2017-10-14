package com.sucy.enchant.potion.damaged.reflect;

import com.sucy.enchant.api.ItemSet;
import org.bukkit.potion.PotionEffectType;

/**
 * Applies blinding when hit by an enemy
 */
public class Brilliance extends PotionReflect {

    public Brilliance() {
        super("Brilliance", "Blinds foes when hit");

        addNaturalItems(ItemSet.HELMETS.getItems());
    }

    /**
     * @return potion type applied by this potion
     */
    public PotionEffectType type() {
        return PotionEffectType.BLINDNESS;
    }
}
