package com.sucy.active.enchants;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TossTask extends BukkitRunnable {

    boolean cancelled = false;
    Player player;

    public TossTask(Player player) {
        this.player = player;
    }

    public void run() {
        if (player.getPassenger() != null) {
            player.eject();
        }
    }
}
