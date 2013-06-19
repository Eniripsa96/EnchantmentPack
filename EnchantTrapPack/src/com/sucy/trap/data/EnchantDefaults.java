package com.sucy.trap.data;

import com.sucy.trap.EnchantTrapPack;

import java.util.Arrays;
import java.util.Hashtable;

/**
 * Default data for enchantments
 *
 * EnchantName, Attributes<name, value>
 */
public enum EnchantDefaults {

    BARRIER_TRAP ("Barrier Trap", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CD_BASE.getKey(), 20);
        put(ConfigValues.CD_BONUS.getKey(), 0);
        put(ConfigValues.LIFE_BASE.getKey(), 5);
        put(ConfigValues.LIFE_BONUS.getKey(), 1);
    }}),

    BOMB_TRAP ("Bomb Trap", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CD_BASE.getKey(), 20);
        put(ConfigValues.CD_BONUS.getKey(), 0);
        put(ConfigValues.PWR_BASE.getKey(), 2);
        put(ConfigValues.PWR_BONUS.getKey(), 0.5);
        put(ConfigValues.LIFE_BASE.getKey(), -1);
        put(ConfigValues.LIFE_BONUS.getKey(), 0);
    }}),

    FIRE_TRAP ("Fire Trap", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CD_BASE.getKey(), 20);
        put(ConfigValues.CD_BONUS.getKey(), 0);
        put(ConfigValues.PWR_BASE.getKey(), 1.5);
        put(ConfigValues.PWR_BONUS.getKey(), 0.25);
        put(ConfigValues.LIFE_BASE.getKey(), -1);
        put(ConfigValues.LIFE_BONUS.getKey(), 0);
    }}),

    ICE_TRAP ("Ice Trap", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CD_BASE.getKey(), 20);
        put(ConfigValues.CD_BONUS.getKey(), 0);
        put(ConfigValues.LIFE_BASE.getKey(), 5);
        put(ConfigValues.LIFE_BONUS.getKey(), 1);
    }}),

    LAUNCH_TRAP ("Launch Trap", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CD_BASE.getKey(), 20);
        put(ConfigValues.CD_BONUS.getKey(), 0);
        put(ConfigValues.LIFE_BASE.getKey(), -1);
        put(ConfigValues.LIFE_BONUS.getKey(), 0);
        put(ConfigValues.SPD_BASE.getKey(), 3);
        put(ConfigValues.SPD_BONUS.getKey(), 0.5);
    }}),

    LIGHTNING_TRAP ("Lightning Trap", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CD_BASE.getKey(), 15);
        put(ConfigValues.CD_BONUS.getKey(), 2);
        put(ConfigValues.LIFE_BASE.getKey(), -1);
        put(ConfigValues.LIFE_BONUS.getKey(), 0);
    }}),

    POISON_TRAP ("Poison Trap", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CD_BASE.getKey(), 20);
        put(ConfigValues.CD_BONUS.getKey(), 0);
        put(ConfigValues.DUR_BASE.getKey(), 7);
        put(ConfigValues.DUR_BONUS.getKey(), 0);
        put(ConfigValues.TIER_BASE.getKey(), 2);
        put(ConfigValues.TIER_BONUS.getKey(), 0);
        put(ConfigValues.LIFE_BASE.getKey(), 5);
        put(ConfigValues.LIFE_BONUS.getKey(), 2);
    }}),

    SLOW_TRAP ("Slow Trap", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CD_BASE.getKey(), 20);
        put(ConfigValues.CD_BONUS.getKey(), 0);
        put(ConfigValues.DUR_BASE.getKey(), 7);
        put(ConfigValues.DUR_BONUS.getKey(), 0);
        put(ConfigValues.TIER_BASE.getKey(), 2);
        put(ConfigValues.TIER_BONUS.getKey(), 0);
        put(ConfigValues.LIFE_BASE.getKey(), 5);
        put(ConfigValues.LIFE_BONUS.getKey(), 2);
    }}),

    WEAKNESS_TRAP ("Weakness Trap", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CD_BASE.getKey(), 20);
        put(ConfigValues.CD_BONUS.getKey(), 0);
        put(ConfigValues.DUR_BASE.getKey(), 7);
        put(ConfigValues.DUR_BONUS.getKey(), 0);
        put(ConfigValues.TIER_BASE.getKey(), 1);
        put(ConfigValues.TIER_BONUS.getKey(), 0);
        put(ConfigValues.LIFE_BASE.getKey(), 5);
        put(ConfigValues.LIFE_BONUS.getKey(), 1);
    }}),

    WEB_TRAP ("Web Trap", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 1);
        put(ConfigValues.CD_BASE.getKey(), 20);
        put(ConfigValues.CD_BONUS.getKey(), 0);
        put(ConfigValues.DUR_BASE.getKey(), 7);
        put(ConfigValues.DUR_BONUS.getKey(), 0);
        put(ConfigValues.LIFE_BASE.getKey(), -1);
        put(ConfigValues.LIFE_BONUS.getKey(), 0);
        put(ConfigValues.SPAWN_BASE.getKey(), 27);
        put(ConfigValues.SPAWN_BONUS.getKey(), 0);
    }});

    /**
     * Enchantment name
     */
    private final String name;

    /**
     * Enchantment attributes
     */
    private final Hashtable<String, Object> values;

    /**
     * Constructor
     *
     * @param name   enchantment name
     * @param values enchantment attributes
     */
    private EnchantDefaults(String name, Hashtable<String, Object> values) {
        this.name = name;
        this.values = values;

        // Make sure the config has at least the default values
        EnchantTrapPack.put(getPath() + ConfigValues.PVP.getKey(), true);
        EnchantTrapPack.put(getPath() + ConfigValues.PVE.getKey(), true);
        String[] names = values.keySet().toArray(new String[values.size()]);
        Arrays.sort(names);
        for (String key : names) {
            EnchantTrapPack.put(getPath() + key, values.get(key));
        }
    }

    /**
     * @return enchantment name
     */
    public String getName() {
        return name;
    }

    /**
     * @return enchantment config path
     */
    public String getPath() {
        return name.replace(" ", "") + ".";
    }

    /**
     * @return enchantment attributes
     */
    public Hashtable<String, Object> getValues() {
        return values;
    }
}
