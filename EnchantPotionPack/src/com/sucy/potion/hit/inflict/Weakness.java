package com.sucy.potion.hit.inflict;

import com.sucy.potion.data.EnchantDefaults;
import com.sucy.potion.data.ItemSets;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

/**
 * Applies weakness on hit
 */
public class Weakness extends PotionInflict {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public Weakness(Plugin plugin) {
        super(plugin, EnchantDefaults.WEAKNESS, ItemSets.AXES.getItems());
        description = "Lowers enemies' damage on hit";
    }

    /**
     * @return type of potion applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.WEAKNESS;
    }
}
