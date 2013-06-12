package com.rit.sucy;

import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

/**
 * Gains movement speed when hit
 */
public class LivelyEnchantment extends PotionAbsorbEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public LivelyEnchantment(Plugin plugin) {
        super(plugin, EnchantDefaults.LIVELY, ItemSets.BOOTS.getItems());
    }

    /**
     * @return potion type applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.SPEED;
    }
}
