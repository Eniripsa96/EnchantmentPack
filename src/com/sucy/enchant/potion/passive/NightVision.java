package com.sucy.enchant.potion.passive;

import com.sucy.enchant.api.ItemSet;
import org.bukkit.potion.PotionEffectType;

/**
 * Passively grants night vision
 */
public class NightVision extends PotionPassive {

    public NightVision() {
        super("Night Vision", "Passively grants night vision");

        addNaturalItems(ItemSet.HELMETS.getItems());
    }

    /**
     * @return type of potion applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.NIGHT_VISION;
    }
}