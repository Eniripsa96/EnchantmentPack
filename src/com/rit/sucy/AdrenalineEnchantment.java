package com.rit.sucy;

import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

/**
 * Gain damage when hit
 */
public class AdrenalineEnchantment extends PotionAbsorbEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public AdrenalineEnchantment(Plugin plugin) {
        super(plugin, EnchantDefaults.ADRENALINE, ItemSets.LEGGINGS.getItems());
    }

    /**
     * @return potion type applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.INCREASE_DAMAGE;
    }
}
