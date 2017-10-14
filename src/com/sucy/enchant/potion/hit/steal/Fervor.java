package com.sucy.enchant.potion.hit.steal;

import com.sucy.enchant.api.ItemSet;
import org.bukkit.potion.PotionEffectType;

/**
 * Gains movement speed on attack
 */
public class Fervor extends PotionSteal {

    public Fervor() {
        super("Fervor", "Grants bonus speed on hit");

        addNaturalItems(ItemSet.SWORDS.getItems());
    }

    /**
     * @return type of potion applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.SPEED;
    }
}
