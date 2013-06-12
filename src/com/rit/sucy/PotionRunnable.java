package com.rit.sucy;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Continuously applies a potion effect
 */
public class PotionRunnable extends BukkitRunnable {

    /**
     * Player to apply the effect to
     */
    Player player;

    /**
     * Effect to apply
     */
    PotionEffectType type;

    /**
     * Potion tier to apply
     */
    int level;

    /**
     * Constructor
     *
     * @param player player to apply the potion to
     * @param type   type of the potion
     * @param level  potion tier
     */
    public PotionRunnable(Player player, PotionEffectType type, int level) {
        this.player = player;
        this.type = type;
    }

    /**
     * Applies the potion effect
     */
    public void run() {
        player.addPotionEffect(new PotionEffect(type, 399, level), true);
    }
}
