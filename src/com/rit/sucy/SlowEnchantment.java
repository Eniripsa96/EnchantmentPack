package com.rit.sucy;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SlowEnchantment extends CustomEnchantment {

    int max;
    double chanceBase;
    double chanceBonus;
    int tierBase;
    int tierBonus;
    double durationBase;
    double durationBonus;

    public SlowEnchantment(Plugin plugin) {
        super("Slowing", plugin.getConfig().getStringList("Slowing.items").toArray(new String[0]));
        max = plugin.getConfig().getInt("Slowing.max");
        chanceBonus = plugin.getConfig().getDouble("Slowing.chanceBonus");
        chanceBase = plugin.getConfig().getDouble("Slowing.chanceBase") - chanceBonus;
        tierBonus = plugin.getConfig().getInt("Slowing.tierBonus");
        tierBase = plugin.getConfig().getInt("Slowing.tierBase") - tierBonus;
        durationBonus = plugin.getConfig().getDouble("Slowing.durationBonus");
        durationBase = plugin.getConfig().getDouble("Slowing.durationBase") - durationBonus;
    }

    @Override
    public int getEnchantmentLevel(int expLevel) {
        return expLevel * max / 50 + 1;
    }

    @Override
    public void applyEffect(LivingEntity user, LivingEntity target, int enchantLevel, EntityDamageByEntityEvent event) {
        if (Math.random() * 100 < chanceBase + chanceBonus * enchantLevel)
            target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,
                    (int)(20 * (durationBase + durationBonus * enchantLevel)), tierBase + tierBonus * enchantLevel - 1), true);
    }
}
