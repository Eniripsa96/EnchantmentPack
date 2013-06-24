package com.sucy.potion.hit.inflict;

import com.rit.sucy.service.SuffixGroups;
import com.sucy.potion.data.EnchantDefaults;
import com.sucy.potion.data.ItemSets;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

/**
 * Blinds enemies on hit
 */
public class Blind extends PotionInflict {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public Blind(Plugin plugin) {
        super(plugin, EnchantDefaults.BLIND, ItemSets.PICKAXES.getItems());
        description = "Blinds enemies on hit";
        suffixGroups.add(SuffixGroups.BLIND.getKey());
    }

    /**
     * @return potion type applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.BLINDNESS;
    }
}
