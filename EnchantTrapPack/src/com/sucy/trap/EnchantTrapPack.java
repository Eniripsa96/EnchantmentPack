package com.sucy.trap;

import com.rit.sucy.EnchantPlugin;
import com.rit.sucy.EnchantmentAPI;
import com.sucy.trap.enchant.*;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.HandlerList;

/**
 * A collection of enchantments for the EnchantmentAPI based on potion effects
 */
public class EnchantTrapPack extends EnchantPlugin implements CommandExecutor {

    /**
     * Plugin singleton instance
     */
    static EnchantTrapPack instance;

    /**
     * Task timer for traps
     */
    TrapTask task;

    /**
     * Constructor
     */
    public EnchantTrapPack() {
        instance = this;
    }

    /**
     * onEnable
     */
    @Override
    public void onEnable() {

        // Set up the timer for traps
        task = new TrapTask();
        task.runTaskTimer(this, 0, 2);

        // Listeners
        new ETPListener(this);
    }

    /**
     * onDisable
     */
    @Override
    public void onDisable() {

        // Stop the traps timer
        task.cancel();

        // Remove all traps from the server
        for (Trap trap : Trap.getTraps()) trap.remove();

        // Remove listeners
        HandlerList.unregisterAll(this);
    }

    /**
     * If the config is missing a value, put it there
     *
     * @param path  path to check
     * @param value default value
     */
    public static void put(String path, Object value) {
        if (instance.getConfig().contains(path))
            return;

        instance.getConfig().set(path, value);
    }

    /**
     * Registers all of the custom enchantments
     */
    @Override
    public void registerEnchantments() {

        EnchantmentAPI.registerCustomEnchantments(
                new BarrierTrap(this),
                new BombTrap(this),
                new FireTrap(this),
                new IceTrap(this),
                new LaunchTrap(this),
                new LightningTrap(this),
                new PoisonTrap(this),
                new SlowTrap(this),
                new WeaknessTrap(this),
                new WebTrap(this)
        );

        // Update the config (because EnchantDefaults will have put any missing data)
        saveConfig();
    }

    /**
     * Does nothing when run as .jar
     *
     * @param args not used
     */
    public static void main(String[] args){}
}