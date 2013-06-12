package com.rit.sucy;

import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

/**
 * Applies wither on hit
 */
public class WitherEnchantment extends PotionInflictEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public WitherEnchantment(Plugin plugin) {
        super(plugin, EnchantDefaults.WITHER, ItemSets.SWORDS.getItems());
    }

    /**
     * @return potion type applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.WITHER;
    }
}
