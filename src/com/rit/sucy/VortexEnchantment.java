package com.rit.sucy;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.Hashtable;

public class VortexEnchantment extends CustomEnchantment {

    int max;
    long cooldownBase;
    long cooldownBonus;
    int rangeBase;
    int rangeBonus;
    double speedBase;
    double speedBonus;

    Hashtable<String, Long> timers;

    public VortexEnchantment(Plugin plugin) {
        super("Vortex", new String[] { "wood_pickaxe", "stone_pickaxe", "iron_pickaxe", "gold_pickaxe", "diamond_pickaxe" }, 2);
        timers = new Hashtable<String, Long>();
        max = plugin.getConfig().getInt("Vortex.max");
        cooldownBonus = (long)(1000 * plugin.getConfig().getDouble("Vortex.cooldownBonus"));
        cooldownBase = (long)(1000 * plugin.getConfig().getDouble("Vortex.cooldownBase")) + cooldownBonus;
        rangeBonus = plugin.getConfig().getInt("Vortex.rangeBonus");
        rangeBase = plugin.getConfig().getInt("Vortex.rangeBase") - rangeBonus;
        speedBonus = plugin.getConfig().getDouble("Vortex.speedBonus");
        speedBase = plugin.getConfig().getDouble("Vortex.speedBase") - speedBonus;
    }

    @Override
    public int getEnchantmentLevel(int expLevel) {
        return expLevel * max / 50 + 1;
    }

    @Override
    public void applyMiscEffect(Player player, int level, PlayerInteractEvent event) {

        if (timers.get(player.getName()) == null) timers.put(player.getName(), 0l);
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (System.currentTimeMillis() - timers.get(player.getName()) < cooldownBase + cooldownBonus * level) return;

            int range = rangeBase + rangeBonus * level;
            for (Entity entity : player.getNearbyEntities(range, range, range)) {
                if (!(entity instanceof LivingEntity)) continue;
                Vector velocity = player.getLocation().subtract(entity.getLocation()).toVector();
                velocity.setY(velocity.getY() / 2);
                entity.setVelocity(velocity.multiply(speedBase + speedBonus * level));
            }
            timers.put(player.getName(), System.currentTimeMillis());
        }
    }
}
