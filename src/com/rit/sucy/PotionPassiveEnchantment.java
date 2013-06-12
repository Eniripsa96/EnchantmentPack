package com.rit.sucy;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Hashtable;
import java.util.Map;

/**
 * Applies a potion effect passively
 */
public abstract class PotionPassiveEnchantment extends ConfigurableEnchantment implements PotionEnchantment {

    /**
     * Timer tasks for this enchantment
     */
    Hashtable<String, PotionRunnable> tasks = new Hashtable<String, PotionRunnable>();

    /**
     * Constructor
     * @param plugin  plugin reference
     * @param enchant enchantment type
     * @param items   natural items
     */
    public PotionPassiveEnchantment(Plugin plugin, EnchantDefaults enchant, String[] items) {
        super(plugin, enchant, items);
    }

    /**
     * Begins the effect when the enchantment is equipped
     *
     * @param player player with the enchantment
     * @param level  enchantment level
     */
    @Override
    public void applyEquipEffect(Player player, int level) {

        // Don't apply it if its already applied
        if (tasks.containsKey(player.getName())) return;

        // Apply the effect and start a timer to keep it on
        PotionRunnable runnable = new PotionRunnable(player, type(), tier(level));
        runnable.runTaskTimer(plugin, 0, 95);
        tasks.put(player.getName(), runnable);
    }

    /**
     * Remove the potion when the enchantment is unequipped
     *
     * @param player       player with the enchantment
     * @param enchantLevel enchantment level
     */
    @Override
    public void applyUnequipEffect(Player player, int enchantLevel) {

        // If there is no timer, there's nothing to remove
        if (!tasks.containsKey(player.getName())) return;

        // Remove the potion effect and the timer for it
        tasks.get(player.getName()).cancel();
        tasks.remove(player.getName());
        player.removePotionEffect(type());
    }

    /**
     * Checks to see if the player has this enchantment equipped
     *
     * @param player player to check
     */
    public void initializePlayer(Player player) {
        for (ItemStack item : player.getEquipment().getArmorContents()) {
            for (Map.Entry<CustomEnchantment, Integer> entry : EnchantmentAPI.getEnchantments(item).entrySet())
                if (entry.getKey().name().equalsIgnoreCase(name()))
                    entry.getKey().applyEquipEffect(player, entry.getValue());
        }
    }

    /**
     * Removes a player from the data
     *
     * @param playerName name of the player to clear
     */
    public void clearPlayer(String playerName) {
        if (tasks.containsKey(playerName)) {
            tasks.get(playerName).cancel();
            tasks.remove(playerName);
        }
    }

    /**
     * Clears all player data (for when the plugin is disabled)
     */
    public void clearPlayers() {
        for (String player : tasks.keySet()) {
            clearPlayer(player);
        }
    }
}
