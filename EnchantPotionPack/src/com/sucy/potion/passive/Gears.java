package com.sucy.potion.passive;

import com.rit.sucy.service.SuffixGroups;
import com.sucy.potion.data.EnchantDefaults;
import com.sucy.potion.data.ItemSets;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

/**
 * Passively grants bonus jump height
 */
public class Gears extends PotionPassive {
    public Gears(Plugin plugin) {
        super(plugin, EnchantDefaults.GEARS, ItemSets.BOOTS.getItems());
        description = "Passively grants movement speed bonus";
        suffixGroups.add(SuffixGroups.SPEED.getKey());
    }
    public PotionEffectType type() {
        return PotionEffectType.SPEED;
    }
}
