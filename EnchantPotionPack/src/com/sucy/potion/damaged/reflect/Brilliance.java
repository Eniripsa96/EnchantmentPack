package com.sucy.potion.damaged.reflect;

import com.sucy.potion.data.EnchantDefaults;
import com.sucy.potion.data.ItemSets;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

/**
 * Applies blinding when hit by an enemy
 */
public class Brilliance extends PotionReflect {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public Brilliance(Plugin plugin) {
        super(plugin, EnchantDefaults.BRILLIANCE, ItemSets.HELMETS.getItems());
        description = "Blinds foes when hit";
    }

    /**
     * @return potion type applied by this potion
     */
    public PotionEffectType type() {
        return PotionEffectType.BLINDNESS;
    }
}
