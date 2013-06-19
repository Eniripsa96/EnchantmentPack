package com.sucy.trap.data;

/**
 * Keys for configuration attributes
 */
public enum ConfigValues {

    /**
     * Maximum enchantment level
     */
    MAX ("max"),

    /**
     * If it effects players
     */
    PVP ("pvp"),

    /**
     * If it effects mobs
     */
    PVE ("pve"),

    /**
     * Enchantment cooldown at level 1
     */
    CD_BASE ("cooldownBase"),

    /**
     * Cooldown reduction per enchantment level
     */
    CD_BONUS ("cooldownBonus"),

    /**
     * Potion effect tier at level 1
     */
    TIER_BASE ("tierBase"),

    /**
     * Potion effect tier gain per enchantment level
     */
    TIER_BONUS ("tierBonus"),

    /**
     * Probability for an enchantment to occur at level 1
     */
    CHANCE_BASE ("chanceBase"),

    /**
     * Probability gain for an enchantment per enchantment level
     */
    CHANCE_BONUS ("chanceBonus"),

    /**
     * Duration of an enchantment's potion effect at level 1
     */
    DUR_BASE ("durationBase"),

    /**
     * Additional duration of a potion effect per enchantment level
     */
    DUR_BONUS ("durationBonus"),

    /**
     * Health quantity to be modified at level 1
     */
    HP_BASE ("healthBase"),

    /**
     * Additional health to be modified per enchantment level
     */
    HP_BONUS ("healthBonus"),

    /**
     * Range of an enchantment at level 1
     */
    RANGE_BASE ("rangeBase"),

    /**
     * Additional range of an enchantment per level
     */
    RANGE_BONUS ("rangeBonus"),

    /**
     * Launch speed for an enchantment at level 1
     */
    SPD_BASE ("speedBase"),

    /**
     * Additional launch speed per enchantment level
     */
    SPD_BONUS ("speedBonus"),

    /**
     * How many blocks or entities to spawn at level 1
     */
    SPAWN_BASE ("spawnBase"),

    /**
     * How many more blocks or entities to spawn per level
     */
    SPAWN_BONUS ("spawnBonus"),

    /**
     * Damage of an enchantment at level 1
     */
    DMG_BASE ("damageBase"),

    /**
     * Additional damage per enchantment level
     */
    DMG_BONUS ("damageBonus"),

    /**
     * Explosion power for an enchantment at level 1
     */
    PWR_BASE ("powerBase"),

    /**
     * Additional explosion power per enchantment level
     */
    PWR_BONUS ("powerBonus"),

    /**
     * Effect radius for an enchantment at level 1
     */
    RAD_BASE ("radiusBase"),

    /**
     * Additional effect radius per enchantment level
     */
    RAD_BONUS ("radiusBonus"),

    /**
     * Effect percentage for an enchantment at level 1
     */
    PCT_BASE ("percentBase"),

    /**
     * Additional effect percentage per enchantment level
     */
    PCT_BONUS ("percentBonus"),

    /**
     * Lifespan of the enchantment at level 1
     */
    LIFE_BASE ("lifespanBase"),

    /**
     * Additional lifespan of the enchantment per enchant level
     */
    LIFE_BONUS ("lifespanBonus");

    /**
     * Attribute key
     */
    String key;

    /**
     * Constructor
     *
     * @param key attribute key
     */
    private ConfigValues(String key) {
        this.key = key;
    }

    /**
     * @return attribute key
     */
    public String getKey() {
        return key;
    }
}
