package com.sucy.active;

import com.rit.sucy.EnchantPlugin;
import com.rit.sucy.EnchantmentAPI;
import com.sucy.active.enchants.*;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.HandlerList;

/**
 * A collection of enchantments for the EnchantmentAPI based on potion effects
 */
public class EnchantActivePack extends EnchantPlugin implements CommandExecutor {

    /**
     * Plugin singleton instance
     */
    static EnchantActivePack instance;

    /**
     * Constructor
     */
    public EnchantActivePack() {
        instance = this;
    }

    @Override
    public void onEnable() {
        new EPListener(this);
    }

    @Override
    public void onDisable() {
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
                new Angler(this),
                new Dash(this),
                new Fireball(this),
                new Fried(this),
                new Grapple(this),
                new Heal(this),
                new Pull(this),
                new Rejuvenating(this),
                new Repulse(this),
                new Toss(this),
                new Vortex(this)
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