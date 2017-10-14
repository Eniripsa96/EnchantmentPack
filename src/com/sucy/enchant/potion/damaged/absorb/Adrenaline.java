package com.sucy.enchant.potion.damaged.absorb;

import com.sucy.enchant.api.ItemSet;
import org.bukkit.potion.PotionEffectType;

/**
 * Gain damage when hit
 */
public class Adrenaline extends PotionAbsorb {

    public Adrenaline() {
        super("Adrenaline", "Grants extra damage when hit");

        addNaturalItems(ItemSet.LEGGINGS.getItems());
    }

    /**
     * @return potion type applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.INCREASE_DAMAGE;
    }
}
