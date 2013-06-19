package com.sucy.potion.damaged.absorb;

import com.sucy.potion.data.EnchantDefaults;
import com.sucy.potion.data.ItemSets;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

/**
 * Gain damage when hit
 */
public class Adrenaline extends PotionAbsorb {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public Adrenaline(Plugin plugin) {
        super(plugin, EnchantDefaults.ADRENALINE, ItemSets.LEGGINGS.getItems());
        description = "Grants extra damage when hit";
    }

    /**
     * @return potion type applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.INCREASE_DAMAGE;
    }
}
