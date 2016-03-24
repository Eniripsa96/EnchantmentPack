package com.sucy.passive.enchants;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class RegenTask extends BukkitRunnable {

    private Player player;
    private boolean stopped = false;

    public RegenTask(Player player) {
        this.player = player;
    }

    public void stop() {
        stopped = true;
        cancel();
    }

    @Override
    public void run() {
        if (!player.isOnline() || stopped)
        {
            cancel();
            return;
        }

        double health = player.getHealth() + 1;
        if (health > player.getMaxHealth())
            health = player.getMaxHealth();
        player.setHealth(health);
    }
}
