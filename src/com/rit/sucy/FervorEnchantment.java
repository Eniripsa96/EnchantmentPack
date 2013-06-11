package com.rit.sucy;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Hashtable;

public class FervorEnchantment extends CustomEnchantment {

    int max;
    double chanceBase;
    double chanceBonus;
    int tierBase;
    int tierBonus;
    double durationBase;
    double durationBonus;

    public FervorEnchantment(Plugin plugin) {
        super("Fervor", new String[] { "wood_sword", "stone_sword", "iron_sword", "gold_sword", "diamond_sword" });
        max = plugin.getConfig().getInt("Fervor.max");
        chanceBonus = plugin.getConfig().getDouble("Fervor.chanceBonus");
        chanceBase = plugin.getConfig().getDouble("Fervor.chanceBase") - chanceBonus;
        tierBonus = plugin.getConfig().getInt("Fervor.tierBonus");
        tierBase = plugin.getConfig().getInt("Fervor.tierBase") - tierBonus;
        durationBonus = plugin.getConfig().getDouble("Fervor.durationBonus");
        durationBase = plugin.getConfig().getDouble("Fervor.durationBase") - durationBonus;
    }

    @Override
    public int getEnchantmentLevel(int expLevel) {
        return expLevel * max / 50 + 1;
    }

    @Override
    public void applyEffect(LivingEntity user, LivingEntity target, int level, EntityDamageByEntityEvent event) {
        if (Math.random() * 100 < chanceBase - chanceBonus * level)
            user.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
                    (int)(20 * (durationBase + durationBonus * level)), tierBase + tierBonus * level - 1), true);
    }
}
