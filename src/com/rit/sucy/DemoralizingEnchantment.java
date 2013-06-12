package com.rit.sucy;

import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

/**
 * Applies weakness when hit by an enemy
 */
public class DemoralizingEnchantment extends PotionReflectEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public DemoralizingEnchantment(Plugin plugin) {
        super(plugin, EnchantDefaults.DEMORALIZING, ItemSets.CHESTPLATES.getItems());
    }

    /**
     * @return type of potion applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.WEAKNESS;
    }
}
