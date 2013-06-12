package com.rit.sucy;

import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

/**
 * Applies blinding when hit by an enemy
 */
public class BrillianceEnchantment extends PotionReflectEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public BrillianceEnchantment(Plugin plugin) {
        super(plugin, EnchantDefaults.BRILLIANCE, ItemSets.HELMETS.getItems());
    }

    /**
     * @return potion type applied by this potion
     */
    public PotionEffectType type() {
        return PotionEffectType.BLINDNESS;
    }
}
