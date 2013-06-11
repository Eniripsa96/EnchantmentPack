package com.rit.sucy;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class EPListener implements Listener {

    Plugin plugin;

    public EPListener(Plugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onBreak(BlockBreakEvent event) {
        for (Trap trap : Trap.traps.values()) {
            if (trap.contains(event.getBlock().getLocation())) {
                event.setCancelled(true);
                event.getPlayer().sendMessage("This block is protected by a magical spell...");
            }
        }
    }

    @EventHandler
    public void onConnect(PlayerJoinEvent event) {
        NightVisionEnchantment.initializePlayer(event.getPlayer());
        JumpEnchantment.initializePlayer(event.getPlayer());
    }

    @EventHandler
    public void onDisconnect(PlayerQuitEvent event) {
        NightVisionEnchantment.clearPlayer(event.getPlayer().getName());
        JumpEnchantment.clearPlayer(event.getPlayer().getName());
    }

    @EventHandler
    public void onDisconnect(PlayerKickEvent event) {
        NightVisionEnchantment.clearPlayer(event.getPlayer().getName());
        JumpEnchantment.clearPlayer(event.getPlayer().getName());
    }
}