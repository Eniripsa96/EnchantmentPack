package com.sucy.potion.passive;

import com.sucy.potion.data.EnchantDefaults;
import com.sucy.potion.data.ItemSets;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

/**
 * Passively grants bonus jump height
 */
public class Jump extends PotionPassive {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public Jump(Plugin plugin) {
        super(plugin, EnchantDefaults.JUMP, ItemSets.BOOTS.getItems());
        description = "Passively grants jump bonus";
    }

    /**
     * @return potion type applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.JUMP;
    }
}
