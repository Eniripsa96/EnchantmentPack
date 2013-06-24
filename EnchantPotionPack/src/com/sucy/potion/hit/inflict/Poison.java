package com.sucy.potion.hit.inflict;

import com.rit.sucy.service.SuffixGroups;
import com.sucy.potion.data.EnchantDefaults;
import com.sucy.potion.data.ItemSets;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

/**
 * Applies poison on hit
 */
public class Poison extends PotionInflict {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public Poison(Plugin plugin) {
        super(plugin, EnchantDefaults.POISON,  ItemSets.HOES.getItems());
        description = "Poisons enemies on hit";
        suffixGroups.add(SuffixGroups.POISON.getKey());
    }

    /**
     * @return type of potion applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.POISON;
    }
}