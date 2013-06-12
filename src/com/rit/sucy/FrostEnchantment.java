package com.rit.sucy;

import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

/**
 * Slows enemies on hit
 */
public class FrostEnchantment extends PotionReflectEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public FrostEnchantment(Plugin plugin) {
        super(plugin, EnchantDefaults.FROST, ItemSets.LEGGINGS.getItems());
    }

    /**
     * @return type of potion applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.SLOW;
    }
}
