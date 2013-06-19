package com.sucy.active;

import com.rit.sucy.CustomEnchantment;
import com.rit.sucy.EnchantmentAPI;
import com.sucy.active.data.EnchantDefaults;
import com.sucy.active.enchants.Grapple;
import com.sucy.active.enchants.Rejuvenating;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Map;

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
     * Remove passive abilities when players leave
     *
     * @param event event details
     */
    @EventHandler (priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onDisconnect(PlayerQuitEvent event) {
        if (event.getPlayer().isInsideVehicle()) {
            event.getPlayer().getVehicle().eject();
        }
    }

    @EventHandler (priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onFish(PlayerFishEvent event) {
        if (event.getState() == PlayerFishEvent.State.IN_GROUND) {
            for (Map.Entry<CustomEnchantment, Integer> entry : EnchantmentAPI.getEnchantments(event.getPlayer().getItemInHand()).entrySet()) {
                if (entry.getKey().name().equals(EnchantDefaults.GRAPPLE.getName())) {
                    ((Grapple)entry.getKey()).apply(event.getPlayer(), event.getHook(), entry.getValue());
                }
            }
        }
        else if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            ItemStack caught = ((Item)event.getCaught()).getItemStack();
            for (Map.Entry<CustomEnchantment, Integer> entry : EnchantmentAPI.getEnchantments(event.getPlayer().getItemInHand()).entrySet()) {
                if (entry.getKey().name().equals(EnchantDefaults.FRIED.getName())) {
                    caught.setType(Material.COOKED_FISH);
                }
                else if (entry.getKey().name().equals(EnchantDefaults.ANGLER.getName())) {
                    caught.setAmount(entry.getValue() + 1);
                }
            }
        }
    }

    @EventHandler (priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onConsume(PlayerItemConsumeEvent event) {
        for (Map.Entry<CustomEnchantment, Integer> entry : EnchantmentAPI.getEnchantments(event.getItem()).entrySet()) {
            if (entry.getKey().name().equals(EnchantDefaults.REJUVENATING.getName())) {
                ((Rejuvenating)entry.getKey()).apply(event.getPlayer(), entry.getValue());
            }
        }
    }
}