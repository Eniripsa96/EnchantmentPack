package com.rit.sucy;

import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

/**
 * Grants invisibility when struck
 */
public class PhantomEnchantment extends PotionAbsorbEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public PhantomEnchantment(Plugin plugin) {
        super (plugin, EnchantDefaults.PHANTOM, ItemSets.BOOTS.getItems());
    }

    /**
     * @return type of potion applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.INVISIBILITY;
    }
}
