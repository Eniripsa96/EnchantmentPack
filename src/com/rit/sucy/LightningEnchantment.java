package com.rit.sucy;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;

public class LightningEnchantment extends CustomEnchantment {

    int max;
    double chanceBase;
    double chanceBonus;

    // Constructor
    public LightningEnchantment(Plugin plugin) {
        super("Lightning", new String[] { "wood_axe", "stone_axe", "iron_axe", "gold_axe", "diamond_axe" });
        max = plugin.getConfig().getInt("Lightning.max");
        chanceBonus = plugin.getConfig().getDouble("Lightning.chanceBonus");
        chanceBase = plugin.getConfig().getDouble("Lightning.chanceBase") - chanceBonus;

    }

    @Override
    public int getEnchantmentLevel(int expLevel) {
        return expLevel * max / 50 + 1;
    }

    // Strikes lightning at the target's location
    @Override
    public void applyEffect(LivingEntity user, LivingEntity target, int level, EntityDamageByEntityEvent event) {
        if (Math.random() * 100 < chanceBase + chanceBonus * level)
            target.getWorld().strikeLightning(target.getLocation());
    }
}
