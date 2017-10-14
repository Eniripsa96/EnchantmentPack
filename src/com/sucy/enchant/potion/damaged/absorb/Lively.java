package com.sucy.enchant.potion.damaged.absorb;

import com.sucy.enchant.api.ItemSet;
import org.bukkit.potion.PotionEffectType;

/**
 * Gains movement speed when hit
 */
public class Lively extends PotionAbsorb {

    public Lively() {
        super("Lively", "Grants bonus speed when hit");

        addNaturalItems(ItemSet.BOOTS.getItems());
    }

    /**
     * @return potion type applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.SPEED;
    }
}
