package com.rit.sucy;

import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

/**
 * Poisons enemies when they hit you
 */
public class ToxicEnchantment extends PotionReflectEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public ToxicEnchantment(Plugin plugin) {
        super(plugin, EnchantDefaults.TOXIC, ItemSets.CHESTPLATES.getItems());
    }

    /**
     * @return type of potion applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.POISON;
    }
}
