package com.sucy.passive;

import com.rit.sucy.EnchantPlugin;
import com.rit.sucy.EnchantmentAPI;
import com.rit.sucy.Version;
import com.sucy.passive.enchants.*;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

/**
 * A collection of enchantments for the EnchantmentAPI based on potion effects
 */
public class EnchantPassivePack extends EnchantPlugin implements CommandExecutor {

    /**
     * Plugin singleton instance
     */
    static EnchantPassivePack instance;

    Regenerative regen;

    /**
     * Constructor
     */
    public EnchantPassivePack() {
        instance = this;
    }

    @Override
    public void onEnable() {
        for (Player player : Version.getOnlinePlayers()) {
            regen.initializePlayer(player);
        }
    }

    @Override
    public void onDisable() {
        ((Regenerative)EnchantmentAPI.getEnchantment("Regenerative")).clearTasks();
        regen.clearTasks();
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

        regen = new Regenerative(this);
        EnchantmentAPI.registerCustomEnchantments(
                new Forceful(this),
                new Gravity(this),
                new Knockup(this),
                new Life(this),
                new Lifesteal(this),
                new Lightning(this),
                new Rapid(this),
                new Reflection(this),
                new ShadowShift(this),
                regen
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