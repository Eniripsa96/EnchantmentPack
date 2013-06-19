package com.sucy.potion.damaged.absorb;

import com.sucy.potion.data.EnchantDefaults;
import com.sucy.potion.data.ItemSets;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

/**
 * Grants invisibility when struck
 */
public class Phantom extends PotionAbsorb {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public Phantom(Plugin plugin) {
        super (plugin, EnchantDefaults.PHANTOM, ItemSets.BOOTS.getItems());
        description = "Grants temporary invisibility when hit";
    }

    /**
     * @return type of potion applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.INVISIBILITY;
    }
}
