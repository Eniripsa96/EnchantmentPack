package com.sucy.potion.passive;

import com.sucy.potion.data.EnchantDefaults;
import com.sucy.potion.data.ItemSets;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

/**
 * Passively grants night vision
 */
public class NightVision extends PotionPassive {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public NightVision(Plugin plugin) {
        super(plugin, EnchantDefaults.NIGHT_VISION, ItemSets.HELMETS.getItems());
        description = "Passively grants night vision";
    }

    /**
     * @return type of potion applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.NIGHT_VISION;
    }
}