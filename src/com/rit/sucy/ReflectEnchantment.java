package com.rit.sucy;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;

public class ReflectEnchantment extends CustomEnchantment {

    int max;
    double chanceBase;
    double chanceBonus;
    double percentBase;
    double percentBonus;

    public ReflectEnchantment(Plugin plugin) {
        super("Reflection", plugin.getConfig().getStringList("Reflection.items").toArray(new String[0]));
        max = plugin.getConfig().getInt("Reflection.max");
        chanceBonus = plugin.getConfig().getDouble("Reflection.chanceBonus");
        chanceBase = plugin.getConfig().getDouble("Reflection.chanceBase") - chanceBonus;
        percentBonus = plugin.getConfig().getDouble("Reflection.percentBonus");
        percentBase = plugin.getConfig().getDouble("Reflection.percentBase") - percentBonus;
    }

    @Override
    public int getEnchantmentLevel(int expLevel) {
        return expLevel * max / 50 + 1;
    }

    @Override
    public void applyDefenseEffect(LivingEntity user, LivingEntity target, int enchantLevel,
            EntityDamageEvent event) {
        if (Math.random() * 100 < chanceBase + chanceBonus * enchantLevel && target != null)
                target.damage((int)(event.getDamage() * (percentBase + percentBonus * enchantLevel)), user);
    }
}
