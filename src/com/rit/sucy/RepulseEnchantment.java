package com.rit.sucy;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.Hashtable;

/**
 * Pushes all nearby enemies away
 */
public class RepulseEnchantment extends ConfigurableEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public RepulseEnchantment(Plugin plugin) {
        super(plugin, EnchantDefaults.REPULSE, ItemSets.SWORDS.getItems(), 2);
    }

    /**
     * Pushes all enemies away on right click
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

            // Pull in nearby enemies
            int range = range(level);
            for (Entity entity : player.getNearbyEntities(range, range, range)) {
                if (!(entity instanceof LivingEntity)) continue;
                Vector velocity = entity.getLocation().subtract(player.getLocation()).toVector();
                velocity.setY(velocity.getY() / 3);
                entity.setVelocity(velocity.multiply(speed(level) / (1 + velocity.lengthSquared())));
            }

            // Update the cooldown timer
            timers.put(player.getName(), System.currentTimeMillis());
        }
    }
}
