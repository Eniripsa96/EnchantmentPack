package com.sucy.enchant.potion.hit.steal;

import com.sucy.enchant.api.ItemSet;
import org.bukkit.potion.PotionEffectType;

public class Berserking extends PotionSteal {

    public Berserking() {
        super("Berserking", "Grants bonus damage on attack");

        addNaturalItems(ItemSet.AXES.getItems());
    }

    public PotionEffectType type() {
        return PotionEffectType.INCREASE_DAMAGE;
    }
}
