package com.sucy.passive.enchants;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class RegenTask extends BukkitRunnable {

    Player player;

    public RegenTask(Player player) {
        this.player = player;
    }

    public void run() {
        if (!player.isOnline())
            return;

        int health = player.getHealth() + 1;
        if (health > player.getMaxHealth())
            return;
        player.setMaxHealth(health);
    }
}
