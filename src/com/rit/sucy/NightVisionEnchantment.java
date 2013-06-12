package com.rit.sucy;

import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

/**
 * Passively grants night vision
 */
public class NightVisionEnchantment extends PotionPassiveEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public NightVisionEnchantment(Plugin plugin) {
        super(plugin, EnchantDefaults.NIGHT_VISION, ItemSets.HELMETS.getItems());
    }

    /**
     * @return type of potion applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.NIGHT_VISION;
    }
}