package com.rit.sucy;

import org.bukkit.plugin.Plugin;

import java.util.Hashtable;

/**
 * Base class for configurable enchantments
 */
public class ConfigurableEnchantment extends CustomEnchantment {

    /**
     * The list of attributes for the enchantment
     */
    Hashtable<String, Object> attributes = new Hashtable<String, Object>();

    /**
     * Cooldown timers for the enchantment
     */
    Hashtable<String, Long> timers = new Hashtable<String, Long>();

    /**
     * Plugin reference
     */
    Plugin plugin;

    /**
     * Constructor
     *
     * @param plugin  plugin reference
     * @param enchant enchantment type
     * @param items   natural items
     */
    public ConfigurableEnchantment(Plugin plugin, EnchantDefaults enchant, String[] items) {
        this(plugin, enchant, items, 5);
    }

    /**
     * Constructor
     *
     * @param plugin  plugin reference
     * @param enchant enchantment type
     * @param items   natural items
     * @param weight  enchantment weight
     */
    public ConfigurableEnchantment(Plugin plugin, EnchantDefaults enchant, String[] items, int weight) {
        super(enchant.getName(), items, weight);

        this.plugin = plugin;

        // Attributes are saved to the config before enchantments are initialized
        // Therefore, we can load values straight from the config
        for (String attribute : enchant.getValues().keySet())
            attributes.put(attribute, plugin.getConfig().get(enchant.getPath() + attribute));
    }

    /**
     * Calculates an enchantment level based on the experience level a player used
     *
     * @param expLevel experience cost used by a player
     * @return         enchantment level
     */
    @Override
    public int getEnchantmentLevel(int expLevel) {

        // Max value is 49 so this is a somewhat accurate way of applying the max value
        // Max levels of at least 50 end up not actually being able to reach the maximum
        // This method is still close and simple enough where it is worth the small error
        return expLevel * getInt(ConfigValues.MAX.getKey()) / 50 + 1;
    }

    /**
     * Gets an integer value from the enchantment attributes
     *
     * @param key attribute key
     * @return    integer value of the attribute
     */
    int getInt(String key) {
        return Integer.parseInt(attributes.get(key).toString());
    }

    /**
     * Gets a ouble value from the enchantment attributes
     *
     * @param key attribute key
     * @return    double value of the attribute
     */
    double getDouble(String key) {
        return Double.parseDouble(attributes.get(key).toString());
    }

    /**
     * Calcualtes the cooldown for a given enchantment level
     *
     * @param level enchantment level
     * @return      cooldown in milliseconds
     */
    long cooldown(int level) {
        return (long)(1000 * (getDouble(ConfigValues.CD_BASE.getKey()) + getDouble(ConfigValues.CD_BONUS.getKey()) * level));
    }

    /**
     * Randomly determines whether or not an effect should take place
     *
     * @param level enchantment level
     * @return      true if effect should take place, false otherwise
     */
    boolean roll(int level) {
        return Math.random() * 100 < getDouble(ConfigValues.CHANCE_BASE.getKey())+ getDouble(ConfigValues.CHANCE_BONUS.getKey()) * level;
    }

    /**
     * Calculates the range of an enchantment based on the enchantment level
     *
     * @param level enchantment level
     * @return      enchantment range
     */
    int range(int level) {
        return getInt(ConfigValues.RANGE_BASE.getKey()) + getInt(ConfigValues.RANGE_BONUS.getKey()) * level;
    }

    /**
     * Calcualtes the radius of an effect based on the enchantment level
     *
     * @param level enchantment level
     * @return      enchantment effect radius
     */
    int radius(int level) {
        return getInt(ConfigValues.RAD_BASE.getKey()) + getInt(ConfigValues.RAD_BONUS.getKey()) * level;
    }

    /**
     * Calculates the damage to be done by an enchantment at a given enchantment level
     *
     * @param level enchantment level
     * @return      enchantment damage
     */
    int damage(int level) {
        return getInt(ConfigValues.DMG_BASE.getKey()) + getInt(ConfigValues.DMG_BONUS.getKey()) * level;
    }

    /**
     * Calculates the speed at which to launch an entity given an enchantment level
     *
     * @param level enchantment level
     * @return      enchantment launch speed
     */
    double speed(int level) {
        return getDouble(ConfigValues.SPD_BASE.getKey()) + getDouble(ConfigValues.SPD_BONUS.getKey()) * level;
    }

    /**
     * Calculates the percentage of an effect that should be applied
     *
     * @param level enchantment level
     * @return      enchantment effect percentage
     */
    double percent(int level) {
        return (getDouble(ConfigValues.PCT_BASE.getKey()) + getDouble(ConfigValues.PCT_BONUS.getKey()) * level) / 100;
    }

    /**
     * Calculates the amount of health that should be affected by an enchantment
     *
     * @param level enchantment level
     * @return      health quantity
     */
    int health(int level) {
        return getInt(ConfigValues.HP_BASE.getKey()) + getInt(ConfigValues.HP_BONUS.getKey()) * level;
    }

    /**
     * Calculates the tier of a potion that should be applied for an enchantment
     *
     * @param level enchantment level
     * @return      enchantment potion effect tier
     */
    int tier(int level) {
        return getInt(ConfigValues.TIER_BASE.getKey()) + getInt(ConfigValues.TIER_BONUS.getKey()) * level - 1;
    }

    /**
     * Calculates the duration of a potion effect for an enchantment
     *
     * @param level enchantment level
     * @return      enchantment potion effect duration
     */
    int duration(int level) {
        return (int)(20 * (getDouble(ConfigValues.DUR_BASE.getKey()) + getDouble(ConfigValues.DUR_BONUS.getKey()) * level));
    }

    /**
     * Calculates the power of an explosion for an enchantment
     *
     * @param level enchantment level
     * @return      enchantment explosion power
     */
    float power(int level) {
        return (float)(getDouble(ConfigValues.PWR_BASE.getKey()) + getDouble(ConfigValues.PWR_BONUS.getKey()) * level);
    }
}
