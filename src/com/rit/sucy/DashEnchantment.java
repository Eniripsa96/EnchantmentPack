package com.rit.sucy;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;
import sun.awt.ConstrainableGraphics;

import java.util.Hashtable;

/**
 * Dashes forward, dealing damage along the way
 */
public class DashEnchantment extends ConfigurableEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public DashEnchantment(Plugin plugin) {
        super(plugin, EnchantDefaults.DASH, ItemSets.SWORDS.getItems(), 2);
    }

    /**
     * Applies the enchantment effect upon right click
     *
     * @param player player with the enchantment
     * @param level  enchantment level
     * @param event  event details
     */
    @Override
    public void applyMiscEffect(Player player, int level, PlayerInteractEvent event) {

        // Make sure the cooldown timer isn't null
        if (timers.get(player.getName()) == null) timers.put(player.getName(), 0l);

        // Make sure it was a right click
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {

            // Check the cooldown
            if (System.currentTimeMillis() - timers.get(player.getName()) < cooldown(level)) return;

            // Dash forward
            Vector vector = player.getLocation().getDirection();
            vector.setY(0);
            vector.multiply(speed(level) / vector.length());
            vector.setY(0.2);
            player.setVelocity(vector);

            // Apply damage while moving
            DashTask task = new DashTask(plugin, player, damage(level), 3);
            task.runTask(plugin);

            // Update cooldown timer
            timers.put(player.getName(), System.currentTimeMillis());
        }
    }
}
