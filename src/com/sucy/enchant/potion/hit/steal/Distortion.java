package com.sucy.enchant.potion.hit.steal;

import com.sucy.enchant.api.ItemSet;
import org.bukkit.potion.PotionEffectType;

public class Distortion extends PotionSteal {

    public Distortion() {
        super("Distortion", "Grants invisibility on hit");

        addNaturalItems(ItemSet.HOES.getItems());
    }

    public PotionEffectType type() {
        return PotionEffectType.INVISIBILITY;
    }
}
