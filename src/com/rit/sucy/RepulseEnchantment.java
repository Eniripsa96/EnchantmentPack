package com.rit.sucy;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.Hashtable;

public class RepulseEnchantment extends CustomEnchantment {

    int max;
    long cooldownBase;
    long cooldownBonus;
    int rangeBase;
    int rangeBonus;
    double speedBase;
    double speedBonus;

    Hashtable<String, Long> timers;

    public RepulseEnchantment(Plugin plugin) {
        super("Repulse", plugin.getConfig().getStringList("Repulse.items").toArray(new String[0]), 2);
        timers = new Hashtable<String, Long>();
        max = plugin.getConfig().getInt("Repulse.max");
        cooldownBonus = (long)(1000 * plugin.getConfig().getDouble("Repulse.cooldownBonus"));
        cooldownBase = (long)(1000 * plugin.getConfig().getDouble("Repulse.cooldownBase")) + cooldownBonus;
        rangeBonus = plugin.getConfig().getInt("Repulse.rangeBonus");
        rangeBase = plugin.getConfig().getInt("Repulse.rangeBase") - rangeBonus;
        speedBonus = plugin.getConfig().getDouble("Repulse.speedBonus");
        speedBase = plugin.getConfig().getDouble("Repulse.speedBase") - speedBonus;
    }

    @Override
    public int getEnchantmentLevel(int expLevel) {
        return expLevel * max / 50 + 1;
    }

    @Override
    public void applyMiscEffect(Player player, int level, PlayerInteractEvent event) {

        if (timers.get(player.getName()) == null) timers.put(player.getName(), 0l);
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (timers.get(player.getName()) == null) timers.put(player.getName(), 0l);
            if (System.currentTimeMillis() - timers.get(player.getName()) < cooldownBase - cooldownBonus * level) return;

            int range = rangeBase + rangeBonus * level;
            for (Entity entity : player.getNearbyEntities(range, range, range)) {
                if (!(entity instanceof LivingEntity)) continue;
                Vector velocity = entity.getLocation().subtract(player.getLocation()).toVector();
                velocity.setY(velocity.getY() / 3);
                entity.setVelocity(velocity.multiply((speedBase + speedBonus * level) / (1 + velocity.lengthSquared())));
            }
            timers.put(player.getName(), System.currentTimeMillis());
        }
    }
}
