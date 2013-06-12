package com.rit.sucy;

import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

/**
 * Applies poison on hit
 */
public class PoisonEnchantment extends PotionInflictEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public PoisonEnchantment(Plugin plugin) {
        super(plugin, EnchantDefaults.POISON,  ItemSets.SWORDS.getItems());
    }

    /**
     * @return type of potion applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.POISON;
    }
}