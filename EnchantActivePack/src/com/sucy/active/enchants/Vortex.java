package com.sucy.active.enchants;

import com.rit.sucy.service.SuffixGroups;
import com.sucy.active.ConfigurableEnchantment;
import com.sucy.active.data.ConflictGroup;
import com.sucy.active.data.EnchantDefaults;
import com.sucy.active.data.ItemSets;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

/**
 * Drags all nearby enemies towards you
 */
public class Vortex extends ConfigurableEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public Vortex(Plugin plugin) {
        super(plugin, EnchantDefaults.VORTEX, ItemSets.PICKAXES.getItems(), 1, ConflictGroup.FORCE);
        description = "Pulls in all nearby enemies";
        suffixGroups.add(SuffixGroups.FORCE.getKey());
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

        // Make sure it is a right click
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {

            // Check the cooldown
            if (cooldown(level, player.getName())) return;

            // Drag all nearby enemies closer
            int range = range(level);
            for (Entity entity : player.getNearbyEntities(range, range, range)) {
                if (!works(entity, player)) continue;
                Vector velocity = player.getLocation().subtract(entity.getLocation()).toVector();
                velocity.setY(velocity.getY() / 2);
                entity.setVelocity(velocity.multiply(speed(level)));
                timers.put(player.getName(), System.currentTimeMillis());
            }
        }
    }
}
