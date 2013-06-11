package com.rit.sucy;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PoisonEnchantment extends CustomEnchantment {

    int max;
    double chanceBase;
    double chanceBonus;
    int tierBase;
    int tierBonus;
    double durationBase;
    double durationBonus;

    public PoisonEnchantment(Plugin plugin) {
        super("Poison", new String[] { "wood_sword", "stone_sword", "iron_sword", "gold_sword", "diamond_sword" });
        max = plugin.getConfig().getInt("Poison.max");
        chanceBonus = plugin.getConfig().getDouble("Poison.chanceBonus");
        chanceBase = plugin.getConfig().getDouble("Poison.chanceBase") - chanceBonus;
        tierBonus = plugin.getConfig().getInt("Poison.tierBonus");
        tierBase = plugin.getConfig().getInt("Poison.tierBase") - tierBonus;
        durationBonus = plugin.getConfig().getDouble("Poison.durationBonus");
        durationBase = plugin.getConfig().getDouble("Poison.durationBase") - durationBonus;
    }

    @Override
    public int getEnchantmentLevel(int expLevel) {
        return expLevel * max / 50 + 1;
    }

    @Override
    public void applyEffect(LivingEntity user, LivingEntity target, int level, EntityDamageByEntityEvent event) {
        if (Math.random() * 100 < chanceBase + chanceBonus * level)
            target.addPotionEffect(new PotionEffect(PotionEffectType.POISON,
                    (int)(20 * (durationBase + durationBonus * level)), tierBase + tierBonus * level - 1), true);
    }
}