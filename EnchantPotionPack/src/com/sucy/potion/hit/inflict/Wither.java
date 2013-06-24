package com.sucy.potion.hit.inflict;

import com.rit.sucy.service.SuffixGroups;
import com.sucy.potion.data.EnchantDefaults;
import com.sucy.potion.data.ItemSets;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

/**
 * Applies wither on hit
 */
public class Wither extends PotionInflict {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public Wither(Plugin plugin) {
        super(plugin, EnchantDefaults.WITHER, ItemSets.HOES.getItems());
        description = "Applies wither on hit";
        suffixGroups.add(SuffixGroups.WITHER.getKey());
    }

    /**
     * @return potion type applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.WITHER;
    }
}
