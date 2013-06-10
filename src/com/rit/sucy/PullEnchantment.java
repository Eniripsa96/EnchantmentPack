package com.rit.sucy;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.Hashtable;

public class PullEnchantment extends CustomEnchantment {

    int max;
    long cooldownBase;
    long cooldownBonus;
    double speedBase;
    double speedBonus;

    Hashtable<String, Long> timers;

    public PullEnchantment(Plugin plugin) {
        super("Pull", plugin.getConfig().getStringList("Pull.items").toArray(new String[0]), 2);
        timers = new Hashtable<String, Long>();
        max = plugin.getConfig().getInt("Pull.max");
        cooldownBonus = (long)(1000 * plugin.getConfig().getDouble("Pull.cooldownBonus"));
        cooldownBase = (long)(1000 * plugin.getConfig().getDouble("Pull.cooldownBase")) + cooldownBonus;
        speedBonus = plugin.getConfig().getDouble("Pull.speedBonus");
        speedBase = plugin.getConfig().getDouble("Pull.speedBase") - speedBonus;
    }

    @Override
    public int getEnchantmentLevel(int expLevel) {
        return expLevel * max / 50 + 1;
    }

    @Override
    public void applyEntityEffect(Player player, int level, PlayerInteractEntityEvent event) {

        if (!(event.getRightClicked() instanceof LivingEntity)) return;
        if (timers.get(player.getName()) == null) timers.put(player.getName(), 0l);
        if (System.currentTimeMillis() - timers.get(player.getName()) < cooldownBase + cooldownBonus * level) return;

        Vector velocity = player.getLocation().subtract(event.getRightClicked().getLocation()).toVector();
        velocity.setY(velocity.getY() / 2);
        event.getRightClicked().setVelocity(velocity.multiply(speedBase + speedBonus * level));
        timers.put(player.getName(), System.currentTimeMillis());
    }
}
