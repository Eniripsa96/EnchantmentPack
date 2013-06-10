package com.rit.sucy;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

import java.util.Hashtable;

// Lifesteal custom enchantment
public class LifestealEnchantment extends CustomEnchantment {

    int max;
    double chanceBase;
    double chanceBonus;
    long cooldownBase;
    long cooldownBonus;
    int healthBase;
    int healthBonus;

    // Cooldown timer for the lifesteal ability
    Hashtable<String, Long> timers;

    // Constructor
    public LifestealEnchantment(Plugin plugin) {
        super("Lifesteal", plugin.getConfig().getStringList("Lifesteal.items").toArray(new String[0]));
        timers = new Hashtable<String, Long>();
        max = plugin.getConfig().getInt("Lifesteal.max");
        chanceBonus = plugin.getConfig().getDouble("Lifesteal.chanceBonus");
        chanceBase = plugin.getConfig().getDouble("Lifesteal.chanceBase") - chanceBonus;
        cooldownBonus = (long)(1000 * plugin.getConfig().getDouble("Lifesteal.cooldownBonus"));
        cooldownBase = (long)(1000 * plugin.getConfig().getDouble("Lifesteal.cooldownBase")) + cooldownBonus;
        healthBonus = plugin.getConfig().getInt("Lifesteal.healthBonus");
        healthBase = plugin.getConfig().getInt("Lifesteal.healthBase") - healthBonus;
    }

    @Override
    public int getEnchantmentLevel(int expLevel) {
        return expLevel * max / 50 + 1;
    }

    // Apply the enchantment effect
    @Override
    public void applyEffect(LivingEntity user, LivingEntity target, int enchantLevel, EntityDamageByEntityEvent event) {

        String name = user instanceof Player ? ((Player)user).getName() : "mob";

        if (timers.get(name) == null) timers.put(name, 0l);
        if (System.currentTimeMillis() - timers.get(name) < cooldownBase - cooldownBonus * enchantLevel) return;
        if (Math.random() * 100 >= chanceBase + chanceBonus * enchantLevel) return;

        // Gain health depending on enchantment level
        int health = user.getHealth() + healthBase + healthBonus * enchantLevel;
        if (health > user.getMaxHealth()) health = user.getMaxHealth();
        user.setHealth(health);
        timers.put(name, System.currentTimeMillis());
    }
}
