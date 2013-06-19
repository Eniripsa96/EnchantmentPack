package com.sucy.passive.data;

import com.sucy.passive.EnchantPassivePack;
import com.sucy.passive.data.ConfigValues;

import java.util.Arrays;
import java.util.Hashtable;

/**
 * Default data for enchantments
 *
 * EnchantName, Attributes<name, value>
 */
public enum EnchantDefaults {

    FORCEFUL ("Forceful", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 6);
        put(ConfigValues.CHANCE_BASE.getKey(), 100);
        put(ConfigValues.CHANCE_BONUS.getKey(), 0);
        put(ConfigValues.RANGE_BASE.getKey(), 5);
        put(ConfigValues.RANGE_BONUS.getKey(), 1);
        put(ConfigValues.DMG_BASE.getKey(), 1);
        put(ConfigValues.DMG_BONUS.getKey(), 0);
        put(ConfigValues.SPD_BASE.getKey(), 1);
        put(ConfigValues.SPD_BONUS.getKey(), 0);
    }}),

    GRAVITY ("Gravity", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 6);
        put(ConfigValues.CHANCE_BASE.getKey(), 100);
        put(ConfigValues.CHANCE_BONUS.getKey(), 0);
        put(ConfigValues.RANGE_BASE.getKey(), 5);
        put(ConfigValues.RANGE_BONUS.getKey(), 1);
        put(ConfigValues.DMG_BASE.getKey(), 1);
        put(ConfigValues.DMG_BONUS.getKey(), 0);
        put(ConfigValues.SPD_BASE.getKey(), 0.1);
        put(ConfigValues.SPD_BONUS.getKey(), 0);
    }}),

    KNOCKUP ("Knockup", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 4);
        put(ConfigValues.CHANCE_BASE.getKey(), 5);
        put(ConfigValues.CHANCE_BONUS.getKey(), 2.5);
        put(ConfigValues.SPD_BASE.getKey(), 0.8);
        put(ConfigValues.SPD_BONUS.getKey(), 0);
    }}),

    LIFE ("Life", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 6);
        put(ConfigValues.HP_BASE.getKey(), 5);
        put(ConfigValues.HP_BONUS.getKey(), 5);
    }}),

    LIFESTEAL ("Lifesteal", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CHANCE_BASE.getKey(), 100);
        put(ConfigValues.CHANCE_BONUS.getKey(), 0);
        put(ConfigValues.HP_BASE.getKey(), 1);
        put(ConfigValues.HP_BONUS.getKey(), 1);
        put(ConfigValues.CD_BASE.getKey(), 5);
        put(ConfigValues.CD_BONUS.getKey(), 0);
    }}),

    LIGHTNING ("Lightning", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CHANCE_BASE.getKey(), 4);
        put(ConfigValues.CHANCE_BONUS.getKey(), 4);
    }}),

    RAPID ("Rapid", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.SPD_BASE.getKey(), 1.1);
        put(ConfigValues.SPD_BONUS.getKey(), 0.1);
    }}),

    REFLECTION ("Reflection", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 10);
        put(ConfigValues.CHANCE_BASE.getKey(), 100);
        put(ConfigValues.CHANCE_BONUS.getKey(), 0);
        put(ConfigValues.PCT_BASE.getKey(), 10);
        put(ConfigValues.PCT_BONUS.getKey(), 10);
    }}),

    REGENERATIVE ("Regenerative", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 3);
        put(ConfigValues.CD_BASE.getKey(), 5);
        put(ConfigValues.CD_BONUS.getKey(), 1);
        put(ConfigValues.HP_BASE.getKey(), 1);
        put(ConfigValues.HP_BONUS.getKey(), 0);
    }}),

    SHADOW_SHIFT ("Shadow Shift", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CHANCE_BASE.getKey(), 5);
        put(ConfigValues.CHANCE_BONUS.getKey(), 5);
        put(ConfigValues.TIER_BASE.getKey(), 2);
        put(ConfigValues.TIER_BONUS.getKey(), 0);
        put(ConfigValues.DUR_BASE.getKey(), 3);
        put(ConfigValues.DUR_BONUS.getKey(), 0);
        put(ConfigValues.RAD_BASE.getKey(), 5);
        put(ConfigValues.RAD_BONUS.getKey(), 0);
        put(ConfigValues.RANGE_BASE.getKey(), 5);
        put(ConfigValues.RANGE_BONUS.getKey(), 0);
        put(ConfigValues.CD_BASE.getKey(), 3);
        put(ConfigValues.CD_BONUS.getKey(), 0);
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
        EnchantPassivePack.put(getPath() + ConfigValues.PVP.getKey(), true);
        EnchantPassivePack.put(getPath() + ConfigValues.PVE.getKey(), true);
        String[] names = values.keySet().toArray(new String[values.size()]);
        Arrays.sort(names);
        for (String key : names) {
            EnchantPassivePack.put(getPath() + key, values.get(key));
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
