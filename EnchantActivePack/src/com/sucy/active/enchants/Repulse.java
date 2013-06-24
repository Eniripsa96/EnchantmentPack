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
 * Pushes all nearby enemies away
 */
public class Repulse extends ConfigurableEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public Repulse(Plugin plugin) {
        super(plugin, EnchantDefaults.REPULSE, ItemSets.SWORDS.getItems(), 1, ConflictGroup.FORCE);
        description = "Pushes all enemies away";
        suffixGroups.add(SuffixGroups.FORCE.getKey());
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

        // Make sure it was a right click
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {

            // Check the cooldown
            if (cooldown(level, player.getName())) return;

            // Pull in nearby enemies
            int range = range(level);
            for (Entity entity : player.getNearbyEntities(range, range, range)) {
                if (!works(entity, event.getPlayer())) continue;
                Vector velocity = entity.getLocation().subtract(player.getLocation()).toVector();
                velocity.setY(velocity.getY() / 3);
                entity.setVelocity(velocity.multiply(speed(level) / (1 + velocity.lengthSquared())));
                timers.put(player.getName(), System.currentTimeMillis());
            }
        }
    }
}
