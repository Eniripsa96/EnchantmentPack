package com.rit.sucy;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class PotionRunnable extends BukkitRunnable {

    Player player;
    PotionEffectType type;
    int level;

    public PotionRunnable(Player player, PotionEffectType type, int level) {
        this.player = player;
        this.type = type;
        this.level = level - 1;
    }

    public void run() {
        player.addPotionEffect(new PotionEffect(type, 399, level), true);
    }
}
