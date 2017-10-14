package com.sucy.enchant.potion.passive;

import com.sucy.enchant.api.ItemSet;
import org.bukkit.potion.PotionEffectType;

/**
 * Passively grants bonus jump height
 */
public class Jump extends PotionPassive {

    public Jump() {
        super("Jump", "Passively grants jump bonus");

        addNaturalItems(ItemSet.BOOTS.getItems());
    }

    /**
     * @return potion type applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.JUMP;
    }
}
