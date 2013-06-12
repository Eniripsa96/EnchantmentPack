package com.rit.sucy;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.Hashtable;

/**
 * Pulls a target enemy towards you
 */
public class PullEnchantment extends ConfigurableEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public PullEnchantment(Plugin plugin) {
        super(plugin, EnchantDefaults.PULL, ItemSets.PICKAXES.getItems(), 2);
    }

    /**
     * Pulls the target enemy closer
     *
     * @param player player with the enchantment
     * @param level  enchantment level
     * @param event  event details
     */
    @Override
    public void applyEntityEffect(Player player, int level, PlayerInteractEntityEvent event) {

        // Make sure the target is living
        if (!(event.getRightClicked() instanceof LivingEntity)) return;

        // Make sure the cooldown timer isn't null
        if (timers.get(player.getName()) == null) timers.put(player.getName(), 0l);

        // Check the cooldown
        if (System.currentTimeMillis() - timers.get(player.getName()) < cooldown(level)) return;

        // Pull the target in
        Vector velocity = player.getLocation().subtract(event.getRightClicked().getLocation()).toVector();
        velocity.setY(velocity.getY() / 2);
        event.getRightClicked().setVelocity(velocity.multiply(speed(level)));

        // Update the cooldown timer
        timers.put(player.getName(), System.currentTimeMillis());
    }
}
