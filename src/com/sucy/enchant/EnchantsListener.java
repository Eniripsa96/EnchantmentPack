package com.sucy.enchant;

import com.sucy.enchant.active.Angler;
import com.sucy.enchant.active.Fried;
import com.sucy.enchant.active.Grapple;
import com.sucy.enchant.active.Rejuvenating;
import com.sucy.enchant.api.CustomEnchantment;
import com.sucy.enchant.api.Enchantments;
import com.sucy.enchant.trap.enchant.Trap;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Map;

/**
 * Listener for the EnchantmentPack
 */
public class EnchantsListener implements Listener {

    EnchantsListener(final Plugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * Remove passive abilities when players leave
     *
     * @param event event details
     */
    @EventHandler (priority = EventPriority.MONITOR)
    public void onDisconnect(final PlayerQuitEvent event) {
        if (event.getPlayer().isInsideVehicle()) {
            event.getPlayer().getVehicle().eject();
        }
    }

    @EventHandler (priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onFish(final PlayerFishEvent event) {
        final Map<CustomEnchantment, Integer> enchants = Enchantments.getEnchantments(event.getPlayer());
        if (event.getState() == PlayerFishEvent.State.IN_GROUND && enchants.containsKey(Grapple.instance)) {
            Grapple.instance.apply(event.getPlayer(), event.getHook(), enchants.get(Grapple.instance));
        }
        else if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            final ItemStack caught = ((Item)event.getCaught()).getItemStack();
            if (caught.getType() == Material.RAW_FISH) {
                caught.setAmount(enchants.getOrDefault(Angler.instance, 0) + 1);
                if (enchants.containsKey(Fried.instance)) {
                    caught.setType(Material.COOKED_FISH);
                }
            }
        }
    }

    @EventHandler (priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onConsume(final PlayerItemConsumeEvent event) {
        final Map<CustomEnchantment, Integer> enchants = Enchantments.getEnchantments(event.getPlayer());
        if (enchants.containsKey(Rejuvenating.instance)) {
            Rejuvenating.instance.apply(event.getPlayer(), enchants.get(Rejuvenating.instance));
        }
    }

    /**
     * Prevent traps from being broken
     *
     * @param event event details
     */
    @EventHandler (priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBreak(final BlockBreakEvent event) {
        for (final Trap trap : Trap.getTraps()) {
            if (trap.contains(event.getBlock().getLocation())) {
                event.setCancelled(true);
                event.getPlayer().sendMessage("This block is protected by a magical spell...");
            }
        }
    }
}