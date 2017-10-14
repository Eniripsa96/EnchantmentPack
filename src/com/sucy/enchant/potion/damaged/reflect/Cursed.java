package com.sucy.enchant.potion.damaged.reflect;

import com.sucy.enchant.api.ItemSet;
import org.bukkit.potion.PotionEffectType;

/**
 * Applies wither when hit
 */
public class Cursed extends PotionReflect {

    public Cursed() {
        super("Cursed", "Applies wither to foes when hit");

        addNaturalItems(ItemSet.CHESTPLATES.getItems());
    }

    /**
     * @return potion type applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.WITHER;
    }
}
