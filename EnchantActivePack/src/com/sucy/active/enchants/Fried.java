package com.sucy.active.enchants;

import com.rit.sucy.service.SuffixGroups;
import com.sucy.active.ConfigurableEnchantment;
import com.sucy.active.data.EnchantDefaults;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;

public class Fried extends ConfigurableEnchantment {

    public Fried(Plugin plugin) {
        super(plugin, EnchantDefaults.FRIED, new Material[] { Material.FISHING_ROD }, 10);
        description = "Catches cooked fish instead of raw ones";
        suffixGroups.add(SuffixGroups.FISHING.getKey());
        suffixGroups.add(SuffixGroups.FIRE.getKey());
    }
}
