package com.rit.sucy;

import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

/**
 * Applies wither when hit
 */
public class CursedEnchantment extends PotionReflectEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public CursedEnchantment(Plugin plugin) {
        super(plugin, EnchantDefaults.CURSED, ItemSets.CHESTPLATES.getItems());
    }

    /**
     * @return potion type applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.WITHER;
    }
}
