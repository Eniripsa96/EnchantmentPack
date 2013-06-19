package com.sucy.potion.hit.inflict;

import com.sucy.potion.data.EnchantDefaults;
import com.sucy.potion.data.ItemSets;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

/**
 * Slows enemies on hit
 */
public class Slow extends PotionInflict {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public Slow(Plugin plugin) {
        super(plugin, EnchantDefaults.SLOWING, ItemSets.PICKAXES.getItems());
        description = "Slows enemies on hit";
    }

    /**
     * @return type of potion applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.SLOW;
    }
}
