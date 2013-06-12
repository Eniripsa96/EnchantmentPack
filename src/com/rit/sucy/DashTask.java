package com.rit.sucy;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Damages nearby enemies over time when a player is dashing
 */
public class DashTask extends BukkitRunnable {

    /**
     * Plugin reference
     */
    Plugin plugin;

    /**
     * Player reference
     */
    Player player;

    /**
     * Damage to deal per run
     */
    int damage;

    /**
     * Remaining runs to do
     */
    int remaining;

    /**
     * Constructor
     *
     * @param plugin    plugin reference
     * @param player    player reference
     * @param damage    damage to deal
     * @param remaining remaining runs
     */
    public DashTask(Plugin plugin, Player player, int damage, int remaining) {
        this.plugin = plugin;
        this.player = player;
        this.damage = damage;
        this.remaining = remaining;
    }

    /**
     * Damages all nearby enemies and queues another task if necessary
     */
    public void run() {
        for (Entity entity : player.getNearbyEntities(1, 1, 1)) {
            if (entity instanceof LivingEntity) ((LivingEntity) entity).damage(damage, player);
        }
        if (remaining > 0) new DashTask(plugin, player, damage, remaining - 1).runTaskLater(plugin, 4);
    }
}
