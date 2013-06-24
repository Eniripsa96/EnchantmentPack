package com.sucy.potion.damaged.reflect;

import com.rit.sucy.service.SuffixGroups;
import com.sucy.potion.data.EnchantDefaults;
import com.sucy.potion.data.ItemSets;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

/**
 * Slows enemies on hit
 */
public class Frost extends PotionReflect {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public Frost(Plugin plugin) {
        super(plugin, EnchantDefaults.FROST, ItemSets.LEGGINGS.getItems());
        description = "Slows enemies when hit";
        suffixGroups.add(SuffixGroups.SLOWING.getKey());
    }

    /**
     * @return type of potion applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.SLOW;
    }
}
