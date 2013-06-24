package com.sucy.trap.enchant;

import com.rit.sucy.service.SuffixGroups;
import com.sucy.trap.data.EnchantDefaults;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

public class SlowTrap extends PotionTrap {

    public SlowTrap(Plugin plugin) {
        super(plugin, EnchantDefaults.SLOW_TRAP, 4, PotionEffectType.SLOW);
        description = "Places a trap that slows enemies";
        suffixGroups.add(SuffixGroups.SLOWING.getKey());
        layout = new boolean[][]{
                { false,  true, false,  true },
                {  true, false,  true, false,  true },
                { false,  true,  true,  true, false },
                {  true, false,  true, false,  true },
                { false,  true, false,  true }};
    }
}
