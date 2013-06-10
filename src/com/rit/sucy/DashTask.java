package com.rit.sucy;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class DashTask extends BukkitRunnable {

    Plugin plugin;
    Player player;
    int damage;
    int remaining;

    public DashTask(Plugin plugin, Player player, int damage, int remaining) {
        this.plugin = plugin;
        this.player = player;
        this.damage = damage;
        this.remaining = remaining;
    }

    public void run() {
        player.sendMessage("Damaging");
        for (Entity entity : player.getNearbyEntities(1, 1, 1)) {
            if (entity instanceof LivingEntity) ((LivingEntity) entity).damage(damage, player);
        }
        if (remaining > 0) new DashTask(plugin, player, damage, remaining - 1).runTaskLater(plugin, 4);
    }
}
