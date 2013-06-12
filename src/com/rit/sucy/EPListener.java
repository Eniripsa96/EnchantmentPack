package com.rit.sucy;

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
public class EPListener implements Listener {

    /**
     * Plugin reference
     */
    Plugin plugin;

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public EPListener(Plugin plugin) {
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
        for (Trap trap : Trap.traps.values()) {
            if (trap.contains(event.getBlock().getLocation())) {
                event.setCancelled(true);
                event.getPlayer().sendMessage("This block is protected by a magical spell...");
            }
        }
    }

    /**
     * Apply passive abilities when players join
     *
     * @param event event details
     */
    @EventHandler
    public void onConnect(PlayerJoinEvent event) {
        EnchantmentPack.instance.night.initializePlayer(event.getPlayer());
        EnchantmentPack.instance.jump.initializePlayer(event.getPlayer());
    }

    /**
     * Remove passive abilities when players leave
     *
     * @param event event details
     */
    @EventHandler
    public void onDisconnect(PlayerQuitEvent event) {
        EnchantmentPack.instance.night.clearPlayer(event.getPlayer().getName());
        EnchantmentPack.instance.jump.clearPlayer(event.getPlayer().getName());
    }

    /**
     * Remove passive abilities when players are kicked
     *
     * @param event event details
     */
    @EventHandler
    public void onDisconnect(PlayerKickEvent event) {
        EnchantmentPack.instance.night.clearPlayer(event.getPlayer().getName());
        EnchantmentPack.instance.jump.clearPlayer(event.getPlayer().getName());
    }
}