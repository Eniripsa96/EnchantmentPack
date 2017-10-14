package com.sucy.enchant.potion.damaged.absorb;

import com.sucy.enchant.api.ItemSet;
import org.bukkit.potion.PotionEffectType;

/**
 * Grants invisibility when struck
 */
public class Phantom extends PotionAbsorb {

    public Phantom() {
        super ("Phantom", "Grants temporary invisibility when hit");

        addNaturalItems(ItemSet.BOOTS.getItems());
    }

    /**
     * @return type of potion applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.INVISIBILITY;
    }
}
