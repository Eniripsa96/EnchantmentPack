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
 * Drags all nearby enemies towards you
 */
public class VortexEnchantment extends ConfigurableEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public VortexEnchantment(Plugin plugin) {
        super(plugin, EnchantDefaults.VORTEX, ItemSets.PICKAXES.getItems(), 2);
    }

    /**
     * Drags all nearby enemies closer on right click
     *
     * @param player player with the enchantment
     * @param level  enchantment level
     * @param event  event details
     */
    @Override
    public void applyMiscEffect(Player player, int level, PlayerInteractEvent event) {

        // Make sure the cooldown timer is not null
        if (timers.get(player.getName()) == null) timers.put(player.getName(), 0l);

        // Make sure it is a right click
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {

            // Check the cooldown
            if (System.currentTimeMillis() - timers.get(player.getName()) < cooldown(level)) return;

            // Drag all nearby enemies closer
            int range = range(level);
            for (Entity entity : player.getNearbyEntities(range, range, range)) {
                if (!(entity instanceof LivingEntity)) continue;
                Vector velocity = player.getLocation().subtract(entity.getLocation()).toVector();
                velocity.setY(velocity.getY() / 2);
                entity.setVelocity(velocity.multiply(speed(level)));
            }

            // Update the cooldown timer
            timers.put(player.getName(), System.currentTimeMillis());
        }
    }
}
