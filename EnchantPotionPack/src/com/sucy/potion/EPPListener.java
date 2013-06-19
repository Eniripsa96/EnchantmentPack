package com.sucy.potion;

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
public class EPPListener implements Listener {

    /**
     * Plugin reference
     */
    Plugin plugin;

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public EPPListener(Plugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * Apply passive abilities when players join
     *
     * @param event event details
     */
    @EventHandler
    public void onConnect(PlayerJoinEvent event) {
        EnchantPotionPack.instance.night.initializePlayer(event.getPlayer());
        EnchantPotionPack.instance.jump.initializePlayer(event.getPlayer());
    }

    /**
     * Remove passive abilities when players leave
     *
     * @param event event details
     */
    @EventHandler
    public void onDisconnect(PlayerQuitEvent event) {
        EnchantPotionPack.instance.night.clearPlayer(event.getPlayer().getName());
        EnchantPotionPack.instance.jump.clearPlayer(event.getPlayer().getName());
    }

    /**
     * Remove passive abilities when players are kicked
     *
     * @param event event details
     */
    @EventHandler
    public void onDisconnect(PlayerKickEvent event) {
        EnchantPotionPack.instance.night.clearPlayer(event.getPlayer().getName());
        EnchantPotionPack.instance.jump.clearPlayer(event.getPlayer().getName());
    }
}