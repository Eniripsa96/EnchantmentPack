package com.sucy.potion.hit.steal;

import com.rit.sucy.service.SuffixGroups;
import com.sucy.potion.data.EnchantDefaults;
import com.sucy.potion.data.ItemSets;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

public class Distortion extends PotionSteal {

    public Distortion(Plugin plugin) {
        super(plugin, EnchantDefaults.DISTORTION, ItemSets.HOES.getItems());
        description = "Grants invisibility on hit";
        suffixGroups.add(SuffixGroups.INVISIBILITY.getKey());
    }

    public PotionEffectType type() {
        return PotionEffectType.INVISIBILITY;
    }
}
