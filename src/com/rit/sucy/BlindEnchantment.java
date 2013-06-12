package com.rit.sucy;

import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

/**
 * Blinds enemies on hit
 */
public class BlindEnchantment extends PotionInflictEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public BlindEnchantment(Plugin plugin) {
        super(plugin, EnchantDefaults.BLIND, ItemSets.PICKAXES.getItems());
    }

    /**
     * @return potion type applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.BLINDNESS;
    }
}
