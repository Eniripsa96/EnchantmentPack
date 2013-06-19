package com.sucy.active;

import com.rit.sucy.CustomEnchantment;
import com.rit.sucy.enchanting.EListener;
import com.sucy.active.data.ConfigValues;
import com.sucy.active.data.ConflictGroup;
import com.sucy.active.data.EnchantDefaults;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;

import java.util.Hashtable;

/**
 * Base class for configurable enchantments
 */
public class ConfigurableEnchantment extends CustomEnchantment {

    /**
     * The list of attributes for the enchantment
     */
    private Hashtable<String, Object> attributes = new Hashtable<String, Object>();

    /**
     * Cooldown timers for the enchantment
     */
    protected Hashtable<String, Long> timers = new Hashtable<String, Long>();

    /**
     * Plugin reference
     */
    protected Plugin plugin;

    /**
     * Constructor
     *
     * @param plugin  plugin reference
     * @param enchant enchantment type
     * @param items   natural items
     */
    public ConfigurableEnchantment(Plugin plugin, EnchantDefaults enchant, Material[] items) {
        this(plugin, enchant, items, 5, ConflictGroup.DEFAULT);
    }

    /**
     * Constructor
     *
     * @param plugin  plugin reference
     * @param enchant enchantment type
     * @param items   natural items
     * @param weight  enchantment weight
     */
    public ConfigurableEnchantment(Plugin plugin, EnchantDefaults enchant, Material[] items, int weight) {
        this(plugin, enchant, items, weight, ConflictGroup.DEFAULT);
    }

    /**
     * Constructor
     *
     * @param plugin  plugin reference
     * @param enchant enchantment type
     * @param items   natural items
     * @param group   conflicting group
     */
    public ConfigurableEnchantment(Plugin plugin, EnchantDefaults enchant, Material[] items, ConflictGroup group) {
        this(plugin, enchant, items, 5, group);
    }

    /**
     * Constructor
     *
     * @param plugin  plugin reference
     * @param enchant enchantment type
     * @param items   natural items
     * @param weight  enchantment weight
     * @param group   conflicting group
     */
    public ConfigurableEnchantment(Plugin plugin, EnchantDefaults enchant, Material[] items, int weight, ConflictGroup group) {
        super(enchant.getName(), items, group.getGroup(), weight);

        this.plugin = plugin;

        // Attributes are saved to the config before enchantments are initialized
        // Therefore, we can load values straight from the config
        for (String attribute : enchant.getValues().keySet())
            attributes.put(attribute, plugin.getConfig().get(enchant.getPath() + attribute));
        attributes.put(ConfigValues.PVP.getKey(), plugin.getConfig().get(enchant.getPath() + ConfigValues.PVP.getKey()));
        attributes.put(ConfigValues.PVE.getKey(), plugin.getConfig().get(enchant.getPath() + ConfigValues.PVE.getKey()));
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
     * Gets a double value from the enchantment attributes
     *
     * @param key attribute key
     * @return    double value of the attribute
     */
    double getDouble(String key) {
        return Double.parseDouble(attributes.get(key).toString());
    }

    /**
     * Gets a boolean value from the enchantment attributes
     *
     * @param key attribute key
     * @return    boolean value of the attribute
     */
    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(attributes.get(key).toString());
    }

    /**
     * Calcualtes the cooldown for a given enchantment level
     *
     * @param level enchantment level
     * @return      cooldown in milliseconds
     */
    public long cooldown(int level) {
        return (long)(1000 * (getDouble(ConfigValues.CD_BASE.getKey()) + getDouble(ConfigValues.CD_BONUS.getKey()) * level));
    }

    /**
     * Checks if the enchantment is on cooldown
     *
     * @param level      enchantment level
     * @param playerName name of user
     * @return           true if on cooldown
     */
    public boolean cooldown(int level, String playerName) {
        if (!timers.containsKey(playerName)) {
            timers.put(playerName, 0l);
        }

        if (System.currentTimeMillis() - timers.get(playerName) < cooldown(level)) {
            plugin.getServer().getPlayer(playerName).sendMessage(ChatColor.GOLD + name()
                    + ChatColor.DARK_RED + " cooldown - "
                    + ChatColor.GOLD + ((cooldown(level) - System.currentTimeMillis() + timers.get(playerName)) / 1000 + 1)
                    + ChatColor.DARK_RED + " seconds left");
            return true;
        }

        return false;
    }

    /**
     * Randomly determines whether or not an effect should take place
     *
     * @param level enchantment level
     * @return      true if effect should take place, false otherwise
     */
    public boolean roll(int level) {
        return Math.random() * 100 < getDouble(ConfigValues.CHANCE_BASE.getKey())+ getDouble(ConfigValues.CHANCE_BONUS.getKey()) * level;
    }

    /**
     * Calculates the range of an enchantment based on the enchantment level
     *
     * @param level enchantment level
     * @return      enchantment range
     */
    public int range(int level) {
        return getInt(ConfigValues.RANGE_BASE.getKey()) + getInt(ConfigValues.RANGE_BONUS.getKey()) * level;
    }

    /**
     * Calcualtes the radius of an effect based on the enchantment level
     *
     * @param level enchantment level
     * @return      enchantment effect radius
     */
    public int radius(int level) {
        return getInt(ConfigValues.RAD_BASE.getKey()) + getInt(ConfigValues.RAD_BONUS.getKey()) * level;
    }

    /**
     * Calculates the damage to be done by an enchantment at a given enchantment level
     *
     * @param level enchantment level
     * @return      enchantment damage
     */
    public int damage(int level) {
        return getInt(ConfigValues.DMG_BASE.getKey()) + getInt(ConfigValues.DMG_BONUS.getKey()) * level;
    }

    /**
     * Calculates the speed at which to launch an entity given an enchantment level
     *
     * @param level enchantment level
     * @return      enchantment launch speed
     */
    public double speed(int level) {
        return getDouble(ConfigValues.SPD_BASE.getKey()) + getDouble(ConfigValues.SPD_BONUS.getKey()) * level;
    }

    /**
     * Calculates the percentage of an effect that should be applied
     *
     * @param level enchantment level
     * @return      enchantment effect percentage
     */
    public double percent(int level) {
        return (getDouble(ConfigValues.PCT_BASE.getKey()) + getDouble(ConfigValues.PCT_BONUS.getKey()) * level) / 100;
    }

    /**
     * Calculates the amount of health that should be affected by an enchantment
     *
     * @param level enchantment level
     * @return      health quantity
     */
    public int health(int level) {
        return getInt(ConfigValues.HP_BASE.getKey()) + getInt(ConfigValues.HP_BONUS.getKey()) * level;
    }

    /**
     * Calculates the tier of a potion that should be applied for an enchantment
     *
     * @param level enchantment level
     * @return      enchantment potion effect tier
     */
    public int tier(int level) {
        return getInt(ConfigValues.TIER_BASE.getKey()) + getInt(ConfigValues.TIER_BONUS.getKey()) * level - 1;
    }

    /**
     * Calculates the duration of a potion effect for an enchantment
     *
     * @param level enchantment level
     * @return      enchantment potion effect duration
     */
    public int duration(int level) {
        return (int)(20 * (getDouble(ConfigValues.DUR_BASE.getKey()) + getDouble(ConfigValues.DUR_BONUS.getKey()) * level));
    }

    /**
     * Calculates the lifespan of the enchantment
     * @param level enchantment level
     * @return      lifespan of the enchantment
     */
    public int lifespan(int level) {
        return (int)(10 * (getDouble(ConfigValues.LIFE_BASE.getKey()) + getDouble(ConfigValues.LIFE_BONUS.getKey()) * level));
    }

    /**
     * Calculates the power of an explosion for an enchantment
     *
     * @param level enchantment level
     * @return      enchantment explosion power
     */
    public float power(int level) {
        return (float)(getDouble(ConfigValues.PWR_BASE.getKey()) + getDouble(ConfigValues.PWR_BONUS.getKey()) * level);
    }

    /**
     * Checks if the entity can be affected by this enchantment
     *
     * @param entity target entity
     * @return       true if can be used, false otherwise
     */
    public boolean works(Entity entity, Entity entity2) {
        if (entity == null) return false;
        else if (!(entity instanceof LivingEntity)) return false;
        if (entity2 == null) return true;
        else if (entity instanceof Player) {
            if (!getBoolean(ConfigValues.PVP.getKey())) return false;
            return canAttack((LivingEntity)entity2, (LivingEntity)entity);
        }
        else if (!getBoolean(ConfigValues.PVE.getKey())) return false;
        return true;
    }

    protected boolean canAttack(LivingEntity attacker, LivingEntity target) {
        EntityDamageByEntityEvent event = new EntityDamageByEntityEvent(attacker, target, EntityDamageEvent.DamageCause.ENTITY_ATTACK, 1);
        EListener.excuse = true;
        plugin.getServer().getPluginManager().callEvent(event);
        return !event.isCancelled() && event.getDamage() != 0;
    }
}
