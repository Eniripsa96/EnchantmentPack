package com.rit.sucy;

import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

/**
 * Applies weakness on hit
 */
public class WeaknessEnchantment extends PotionInflictEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public WeaknessEnchantment(Plugin plugin) {
        super(plugin, EnchantDefaults.WEAKNESS, ItemSets.SHOVELS.getItems());
    }

    /**
     * @return type of potion applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.WEAKNESS;
    }
}
