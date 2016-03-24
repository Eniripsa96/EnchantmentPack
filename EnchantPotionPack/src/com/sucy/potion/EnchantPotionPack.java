package com.sucy.potion;

import com.rit.sucy.EnchantPlugin;
import com.rit.sucy.EnchantmentAPI;
import com.rit.sucy.Version;
import com.sucy.potion.damaged.absorb.Adrenaline;
import com.sucy.potion.damaged.absorb.Lively;
import com.sucy.potion.damaged.absorb.Phantom;
import com.sucy.potion.damaged.reflect.*;
import com.sucy.potion.hit.steal.Berserking;
import com.sucy.potion.hit.steal.Distortion;
import com.sucy.potion.hit.steal.Fervor;
import com.sucy.potion.hit.inflict.*;
import com.sucy.potion.passive.Gears;
import com.sucy.potion.passive.Jump;
import com.sucy.potion.passive.NightVision;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * A collection of enchantments for the EnchantmentAPI based on potion effects
 */
public class EnchantPotionPack extends EnchantPlugin implements CommandExecutor {

    /**
     * Plugin singleton instance
     */
    static EnchantPotionPack instance;

    /**
     * Jump enchantment reference for updating player data
     */
    Jump jump;

    Gears gears;

    /**
     * Night vision enchantment reference for updating player data
     */
    NightVision night;

    /**
     * Constructor
     */
    public EnchantPotionPack() {
        instance = this;
    }

    /**
     * onEnable
     */
    @Override
    public void onEnable() {

        // Listeners
        new EPPListener(this);

        // Apply equip effects for the passive enchantments
        for (Player player : Version.getOnlinePlayers()) {
            night.initializePlayer(player);
            jump.initializePlayer(player);
            gears.initializePlayer(player);
        }
    }

    /**
     * onDisable
     */
    @Override
    public void onDisable() {

        // Clear effects and data for passive enchantments
        night.clearPlayers();
        jump.clearPlayers();
        gears.clearPlayers();

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
        night = new NightVision(this);
        jump = new Jump(this);
        gears = new Gears(this);

        EnchantmentAPI.registerCustomEnchantments(
                new Adrenaline(this),
                new Berserking(this),
                new Blind(this),
                new Brilliance(this),
                new Cursed(this),
                new Demoralizing(this),
                new Distortion(this),
                new Fervor(this),
                new Frost(this),
                new Lively(this),
                new Molten(this),
                new Phantom(this),
                new Poison(this),
                new Slow(this),
                new Toxic(this),
                new Weakness(this),
                new Wither(this),
                jump, night, gears
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