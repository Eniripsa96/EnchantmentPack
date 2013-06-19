package com.sucy.trap;

import com.sucy.trap.enchant.Trap;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

/**
 * Listener for the EnchantmentPack
 */
public class ETPListener implements Listener {

    /**
     * Plugin reference
     */
    Plugin plugin;

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public ETPListener(Plugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * Prevent traps from being broken
     *
     * @param event event details
     */
    @EventHandler (priority = EventPriority.HIGHEST)
    public void onBreak(BlockBreakEvent event) {
        for (Trap trap : Trap.getTraps()) {
            if (trap.contains(event.getBlock().getLocation())) {
                event.setCancelled(true);
                event.getPlayer().sendMessage("This block is protected by a magical spell...");
            }
        }
    }
}