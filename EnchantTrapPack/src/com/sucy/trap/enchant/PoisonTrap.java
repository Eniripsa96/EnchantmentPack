package com.sucy.trap.enchant;

import com.rit.sucy.service.SuffixGroups;
import com.sucy.trap.data.EnchantDefaults;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Places a poison trap that poisons all foes who pass through it
 */
public class PoisonTrap extends PotionTrap {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public PoisonTrap(Plugin plugin) {
        super(plugin, EnchantDefaults.POISON_TRAP, 4, PotionEffectType.POISON);
        description = "Places a trap that poisons enemies";
        suffixGroups.add(SuffixGroups.POISON.getKey());
        layout = new boolean[][] {
                { false, false, false,  true },
                { false, false,  true,  true,  true },
                { false,  true, false, false, false,  true },
                {  true,  true, false,  true, false,  true,  true },
                { false,  true, false, false, false,  true },
                { false, false,  true,  true,  true },
                { false, false, false,  true }};
    }
}