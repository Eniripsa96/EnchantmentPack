package com.sucy.active.enchants;

import com.sucy.active.ConfigurableEnchantment;
import com.sucy.active.data.ConflictGroup;
import com.sucy.active.data.EnchantDefaults;
import com.sucy.active.data.ItemSets;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

/**
 * Dashes forward, dealing damage along the way
 */
public class Dash extends ConfigurableEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public Dash(Plugin plugin) {
        super(plugin, EnchantDefaults.DASH, ItemSets.SWORDS.getItems(), 2, ConflictGroup.FORCE);
        description = "Dash forward dealing damage to all enemies";
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

        // Make sure it was a right click
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {

            // Check the cooldown
            if (cooldown(level, player.getName())) return;

            // Dash forward
            Vector vector = player.getLocation().getDirection();
            vector.setY(0);
            vector.multiply(speed(level) / vector.length());
            vector.setY(0.3);
            player.setVelocity(vector);

            // Apply damage while moving
            DashTask task = new DashTask(plugin, this, player, damage(level), 3);
            task.runTask(plugin);

            // Update cooldown timer
            timers.put(player.getName(), System.currentTimeMillis());
        }
    }
}
