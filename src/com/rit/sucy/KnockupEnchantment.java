package com.rit.sucy;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;

public class KnockupEnchantment extends CustomEnchantment {

    Plugin plugin;
    int max;
    double chanceBase;
    double chanceBonus;
    double speedBase;
    double speedBonus;

    public KnockupEnchantment(Plugin plugin) {
        super("Knockup", plugin.getConfig().getStringList("Knockup.items").toArray(new String[0]), 10);
        this.plugin = plugin;
        max = plugin.getConfig().getInt("Knockup.max");
        chanceBonus = plugin.getConfig().getDouble("Knockup.chanceBonus");
        chanceBase = plugin.getConfig().getDouble("Knockup.chanceBase") - chanceBonus;
        speedBonus = plugin.getConfig().getDouble("Knockup.speedBonus");
        speedBase = plugin.getConfig().getDouble("Knockup.speedBase") - speedBonus;
    }

    @Override
    public int getEnchantmentLevel(int expLevel) {
        return expLevel * max / 50 + 1;
    }

    @Override
    public void applyEffect(LivingEntity user, LivingEntity target, int level, EntityDamageByEntityEvent event) {
        if (Math.random() * 100 < chanceBase + chanceBonus * level)
            new KnockupTask(target, (float)(speedBase + speedBonus * level)).runTaskLater(plugin, 1);
    }
}