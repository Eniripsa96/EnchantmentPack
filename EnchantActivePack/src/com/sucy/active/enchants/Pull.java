package com.sucy.active.enchants;

import com.sucy.active.ConfigurableEnchantment;
import com.sucy.active.data.ConflictGroup;
import com.sucy.active.data.EnchantDefaults;
import com.sucy.active.data.ItemSets;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

/**
 * Pulls a target enemy towards you
 */
public class Pull extends ConfigurableEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public Pull(Plugin plugin) {
        super(plugin, EnchantDefaults.PULL, ItemSets.PICKAXES.getItems(), 3, ConflictGroup.FORCE);
        description = "Pulls the target enemy closer";
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

        // Make sure it is a valid target
        if (!works(event.getRightClicked(), player)) return;

        // Check the cooldown
        if (cooldown(level, player.getName())) return;

        // Pull the target in
        Vector velocity = player.getLocation().subtract(event.getRightClicked().getLocation()).toVector();
        velocity.setY(velocity.getY() / 2);
        event.getRightClicked().setVelocity(velocity.multiply(speed(level)));

        // Update the cooldown timer
        timers.put(player.getName(), System.currentTimeMillis());
    }
}
