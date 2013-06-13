package com.rit.sucy;

import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * A collection of enchantments thought of and created by the Bukkit community
 *
 * Main Author: Eniripsa96
 * Contributors:
 *     - JefferiesTube
 *     - Tokesho
 *     - CeramicTitan
 */
public class EnchantmentPack extends EnchantPlugin implements CommandExecutor {

    /**
     * Plugin singleton instance
     */
    static EnchantmentPack instance;

    /**
     * Task timer for traps
     */
    TrapTask task;

    /**
     * Jump enchantment reference for updating player data
     */
    JumpEnchantment jump;

    /**
     * Night vision enchantment reference for updating player data
     */
    NightVisionEnchantment night;

    /**
     * Constructor
     */
    public EnchantmentPack() {
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
        new EPListener(this);

        // Apply equip effects for the passive enchantments
        for (Player player : getServer().getOnlinePlayers()) {
            night.initializePlayer(player);
            jump.initializePlayer(player);
        }
    }

    /**
     * onDisable
     */
    @Override
    public void onDisable() {

        // Stop the traps timer
        task.cancel();

        // Remove all traps from the server
        for (Trap trap : Trap.traps.values()) trap.remove();

        // Clear effects and data for passive enchantments
        night.clearPlayers();
        jump.clearPlayers();

        // Remove listeners
        HandlerList.unregisterAll(this);
    }

    /**
     * If the config is missing a value, put it there
     *
     * @param path  path to check
     * @param value default value
     */
    static void put(String path, Object value) {
        if (instance.getConfig().contains(path))
            return;

        instance.getConfig().set(path, value);
    }

    /**
     * Registers all of the custom enchantments
     */
    @Override
    public void registerEnchantments() {
        night = new NightVisionEnchantment(this);
        jump = new JumpEnchantment(this);

        EnchantmentAPI.registerCustomEnchantment(new AdrenalineEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new BlindEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new BrillianceEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new CursedEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new DashEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new DemoralizingEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new FervorEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new FireTrapEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new FrostEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new GravityEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new IceTrapEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(jump);
        EnchantmentAPI.registerCustomEnchantment(new KnockupEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new LifestealEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new LightningEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new LivelyEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new MoltenEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(night);
        EnchantmentAPI.registerCustomEnchantment(new PhantomEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new PoisonEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new PoisonTrapEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new PullEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new ReflectEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new RepulseEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new ShadowShiftEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new SlowEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new TossEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new ToxicEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new VortexEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new WeaknessEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new WitherEnchantment(this));

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
