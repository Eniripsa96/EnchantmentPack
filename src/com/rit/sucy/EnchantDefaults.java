package com.rit.sucy;

import java.util.Arrays;
import java.util.Hashtable;

/**
 * Default data for enchantments
 *
 * EnchantName, Attributes<name, value>
 */
public enum EnchantDefaults {

    ADRENALINE ("Adrenaline", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CHANCE_BASE.getKey(), 100);
        put(ConfigValues.CHANCE_BONUS.getKey(), 0);
        put(ConfigValues.TIER_BASE.getKey(), 1);
        put(ConfigValues.TIER_BONUS.getKey(), 0);
        put(ConfigValues.DUR_BASE.getKey(), 1);
        put(ConfigValues.DUR_BONUS.getKey(), 0.5);
    }}),

    BLIND ("Blind", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CHANCE_BASE.getKey(), 100);
        put(ConfigValues.CHANCE_BONUS.getKey(), 0);
        put(ConfigValues.TIER_BASE.getKey(), 2);
        put(ConfigValues.TIER_BONUS.getKey(), 0);
        put(ConfigValues.DUR_BASE.getKey(), 1);
        put(ConfigValues.DUR_BONUS.getKey(), 0.5);
    }}),

    BRILLIANCE ("Brilliance", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CHANCE_BASE.getKey(), 100);
        put(ConfigValues.CHANCE_BONUS.getKey(), 0);
        put(ConfigValues.TIER_BASE.getKey(), 2);
        put(ConfigValues.TIER_BONUS.getKey(), 0);
        put(ConfigValues.DUR_BASE.getKey(), 1);
        put(ConfigValues.DUR_BONUS.getKey(), 0.5);
    }}),

    CURSED ("Cursed", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CHANCE_BASE.getKey(), 100);
        put(ConfigValues.CHANCE_BONUS.getKey(), 0);
        put(ConfigValues.TIER_BASE.getKey(), 2);
        put(ConfigValues.TIER_BONUS.getKey(), 0);
        put(ConfigValues.DUR_BASE.getKey(), 1);
        put(ConfigValues.DUR_BONUS.getKey(), 0.5);
    }}),

    DASH ("Dash", new Hashtable<String, Object>() {{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CD_BASE.getKey(), 5);
        put(ConfigValues.CD_BONUS.getKey(), 0);
        put(ConfigValues.DMG_BASE.getKey(), 1);
        put(ConfigValues.DMG_BONUS.getKey(), 1);
        put(ConfigValues.SPD_BASE.getKey(), 3);
        put(ConfigValues.SPD_BONUS.getKey(), 0);
    }}),

    DEMORALIZING ("Demoralizing", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CHANCE_BASE.getKey(), 100);
        put(ConfigValues.CHANCE_BONUS.getKey(), 0);
        put(ConfigValues.TIER_BASE.getKey(), 1);
        put(ConfigValues.TIER_BONUS.getKey(), 0);
        put(ConfigValues.DUR_BASE.getKey(), 1);
        put(ConfigValues.DUR_BONUS.getKey(), 0.5);
    }}),

    FERVOR ("Fervor", new Hashtable<String, Object>() {{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CHANCE_BASE.getKey(), 100);
        put(ConfigValues.CHANCE_BONUS.getKey(), 0);
        put(ConfigValues.TIER_BASE.getKey(), 2);
        put(ConfigValues.TIER_BONUS.getKey(), 0);
        put(ConfigValues.DUR_BASE.getKey(), 1);
        put(ConfigValues.DUR_BONUS.getKey(), 0.5);
    }}),

    FIRE_TRAP ("Fire Trap", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CD_BASE.getKey(), 20);
        put(ConfigValues.CD_BONUS.getKey(), 0);
        put(ConfigValues.PWR_BASE.getKey(), 2);
        put(ConfigValues.PWR_BONUS.getKey(), 0.5);
        put(ConfigValues.LIFE_BASE.getKey(), -1);
        put(ConfigValues.LIFE_BONUS.getKey(), 0);
    }}),

    FROST ("Frost", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CHANCE_BASE.getKey(), 100);
        put(ConfigValues.CHANCE_BONUS.getKey(), 0);
        put(ConfigValues.TIER_BASE.getKey(), 2);
        put(ConfigValues.TIER_BONUS.getKey(), 0);
        put(ConfigValues.DUR_BASE.getKey(), 1);
        put(ConfigValues.DUR_BONUS.getKey(), 0.5);
    }}),

    GRAVITY ("Gravity", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 6);
        put(ConfigValues.CHANCE_BASE.getKey(), 100);
        put(ConfigValues.CHANCE_BONUS.getKey(), 0);
        put(ConfigValues.RANGE_BASE.getKey(), 5);
        put(ConfigValues.RANGE_BONUS.getKey(), 1);
        put(ConfigValues.DMG_BASE.getKey(), 1);
        put(ConfigValues.DMG_BONUS.getKey(), 0);
    }}),

    ICE_TRAP ("Ice Trap", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CD_BASE.getKey(), 20);
        put(ConfigValues.CD_BONUS.getKey(), 0);
        put(ConfigValues.LIFE_BASE.getKey(), 5);
        put(ConfigValues.LIFE_BONUS.getKey(), 1);
    }}),

    JUMP ("Jump", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 2);
        put(ConfigValues.TIER_BASE.getKey(), 1);
        put(ConfigValues.TIER_BONUS.getKey(), 1);
    }}),

    KNOCKUP ("Knockup", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 4);
        put(ConfigValues.CHANCE_BASE.getKey(), 5);
        put(ConfigValues.CHANCE_BONUS.getKey(), 2.5);
        put(ConfigValues.SPD_BASE.getKey(), 0.8);
        put(ConfigValues.SPD_BONUS.getKey(), 0);
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

    LIVELY ("Lively", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CHANCE_BASE.getKey(), 100);
        put(ConfigValues.CHANCE_BONUS.getKey(), 0);
        put(ConfigValues.TIER_BASE.getKey(), 2);
        put(ConfigValues.TIER_BONUS.getKey(), 0);
        put(ConfigValues.DUR_BASE.getKey(), 1);
        put(ConfigValues.DUR_BONUS.getKey(), 0.5);
    }}),

    MOLTEN ("Molten", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CHANCE_BASE.getKey(), 100);
        put(ConfigValues.CHANCE_BONUS.getKey(), 0);
        put(ConfigValues.TIER_BASE.getKey(), 2);
        put(ConfigValues.TIER_BONUS.getKey(), 0);
        put(ConfigValues.DUR_BASE.getKey(), 1);
        put(ConfigValues.DUR_BONUS.getKey(), 0.5);
    }}),

    NIGHT_VISION ("Night Vision", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 2);
        put(ConfigValues.TIER_BASE.getKey(), 1);
        put(ConfigValues.TIER_BONUS.getKey(), 1);
    }}),

    PHANTOM ("Phantom", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CHANCE_BASE.getKey(), 100);
        put(ConfigValues.CHANCE_BONUS.getKey(), 0);
        put(ConfigValues.TIER_BASE.getKey(), 2);
        put(ConfigValues.TIER_BONUS.getKey(), 0);
        put(ConfigValues.DUR_BASE.getKey(), 1);
        put(ConfigValues.DUR_BONUS.getKey(), 0.5);
    }}),

    POISON ("Poison", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CHANCE_BASE.getKey(), 100);
        put(ConfigValues.CHANCE_BONUS.getKey(), 0);
        put(ConfigValues.TIER_BASE.getKey(), 2);
        put(ConfigValues.TIER_BONUS.getKey(), 0);
        put(ConfigValues.DUR_BASE.getKey(), 1);
        put(ConfigValues.DUR_BONUS.getKey(), 0.5);
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
        put(ConfigValues.LIFE_BONUS.getKey(), 1);
    }}),

    PULL ("Pull", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CD_BASE.getKey(), 10);
        put(ConfigValues.CD_BONUS.getKey(), 1);
        put(ConfigValues.SPD_BASE.getKey(), 0.3);
        put(ConfigValues.SPD_BONUS.getKey(), 0);
    }}),

    REFLECTION ("Reflection", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 10);
        put(ConfigValues.CHANCE_BASE.getKey(), 100);
        put(ConfigValues.CHANCE_BONUS.getKey(), 0);
        put(ConfigValues.PCT_BASE.getKey(), 10);
        put(ConfigValues.PCT_BONUS.getKey(), 10);
    }}),

    REPULSE ("Repulse", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CD_BASE.getKey(), 10);
        put(ConfigValues.CD_BONUS.getKey(), 1);
        put(ConfigValues.RANGE_BASE.getKey(), 10);
        put(ConfigValues.RANGE_BONUS.getKey(), 0);
        put(ConfigValues.SPD_BASE.getKey(), 8);
        put(ConfigValues.SPD_BONUS.getKey(), 0);
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
    }}),

    SLOWING ("Slowing", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CHANCE_BASE.getKey(), 100);
        put(ConfigValues.CHANCE_BONUS.getKey(), 0);
        put(ConfigValues.TIER_BASE.getKey(), 2);
        put(ConfigValues.TIER_BONUS.getKey(), 0);
        put(ConfigValues.DUR_BASE.getKey(), 1);
        put(ConfigValues.DUR_BONUS.getKey(), 0.5);
    }}),

    TOSS ("Toss", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CD_BASE.getKey(), 15);
        put(ConfigValues.CD_BONUS.getKey(), 0);
        put(ConfigValues.SPD_BASE.getKey(), 1);
        put(ConfigValues.SPD_BONUS.getKey(), 0.5);
    }}),

    TOXIC ("Toxic", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CHANCE_BASE.getKey(), 100);
        put(ConfigValues.CHANCE_BONUS.getKey(), 0);
        put(ConfigValues.TIER_BASE.getKey(), 2);
        put(ConfigValues.TIER_BONUS.getKey(), 0);
        put(ConfigValues.DUR_BASE.getKey(), 1);
        put(ConfigValues.DUR_BONUS.getKey(), 0.5);
    }}),

    VORTEX ("Vortex", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CD_BASE.getKey(), 10);
        put(ConfigValues.CD_BONUS.getKey(), 1);
        put(ConfigValues.RANGE_BASE.getKey(), 10);
        put(ConfigValues.RANGE_BONUS.getKey(), 0);
        put(ConfigValues.SPD_BASE.getKey(), 0.3);
        put(ConfigValues.SPD_BONUS.getKey(), 0);
    }}),

    WEAKNESS ("Weakness", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CHANCE_BASE.getKey(), 100);
        put(ConfigValues.CHANCE_BONUS.getKey(), 0);
        put(ConfigValues.TIER_BASE.getKey(), 1);
        put(ConfigValues.TIER_BONUS.getKey(), 0);
        put(ConfigValues.DUR_BASE.getKey(), 1);
        put(ConfigValues.DUR_BONUS.getKey(), 0.5);
    }}),

    WITHER ("Wither", new Hashtable<String, Object>(){{
        put(ConfigValues.MAX.getKey(), 5);
        put(ConfigValues.CHANCE_BASE.getKey(), 100);
        put(ConfigValues.CHANCE_BONUS.getKey(), 0);
        put(ConfigValues.TIER_BASE.getKey(), 2);
        put(ConfigValues.TIER_BONUS.getKey(), 0);
        put(ConfigValues.DUR_BASE.getKey(), 1);
        put(ConfigValues.DUR_BONUS.getKey(), 0.5);
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
        EnchantmentPack.put(getPath() + ConfigValues.PVP.getKey(), true);
        EnchantmentPack.put(getPath() + ConfigValues.PVE.getKey(), true);
        String[] names = values.keySet().toArray(new String[values.size()]);
        Arrays.sort(names);
        for (String key : names) {
            EnchantmentPack.put(getPath() + key, values.get(key));
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
