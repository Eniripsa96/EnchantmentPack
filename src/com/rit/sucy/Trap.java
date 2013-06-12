package com.rit.sucy;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A trap layed in the world
 */
public class Trap {

    /**
     * Maximum distance a player can walk away from a trap before it vanishes
     */
    static final int MAX_DISTANCE = 50;

    /**
     * List of active traps
     */
    static HashMap<String, Trap> traps = new HashMap<String, Trap>();

    /**
     * Entities inside the trap
     */
    List<LivingEntity> inRange;

    /**
     * Owner of the trap
     */
    Player owner;

    /**
     * Enchantment that placed the trap
     */
    TrapEnchantment enchantment;

    /**
     * Center of the trap
     */
    Location center;

    /**
     * Radius containing the trap
     */
    int radius;

    /**
     * Enchantment level used to place this trap
     */
    int level;

    /**
     * Checks if the player has a trap of the given type already placed
     *
     * @param player      player to check
     * @param enchantment trap type
     * @return            true if one is placed, false otherwise
     */
    public static boolean isTrapActive(String player, TrapEnchantment enchantment) {
         return traps.containsKey(player + enchantment.name());
    }

    /**
     * Gets the placed trap for a player
     *
     * @param player      player to search for
     * @param enchantment type of trap
     * @return            placed trap or null if not found
     */
    public static Trap getTrap(String player, TrapEnchantment enchantment) {
        if (traps.containsKey(player + enchantment.name()))
            return traps.get(player + enchantment.name());
        else return null;
    }

    /**
     * Creates a new trap
     *
     * @param player      player placing the trap
     * @param enchantment enchantment that caused the trap
     * @param center      center of the trap
     * @param radius      radius of the trap effect
     * @param level       enchantment level of the trap
     */
    public static void createTrap(Player player, TrapEnchantment enchantment, Location center, int radius, int level) {
        traps.put(player.getName() + enchantment.name(), new Trap(player, enchantment, center, radius, level));
    }

    /**
     * Constructor
     * @param owner       trap owner
     * @param enchantment causing enchantment
     * @param center      trap center
     * @param radius      trap effect radius
     * @param level       enchantment level
     */
    public Trap(Player owner, TrapEnchantment enchantment, Location center, int radius, int level) {
        inRange = new ArrayList<LivingEntity>();
        this.owner = owner;
        this.enchantment = enchantment;
        this.center = center;
        this.radius = radius;
        this.level = level;
    }

    /**
     * Checks if the given location is within the trap boundaries
     *
     * @param location location to check
     * @return         true if within the trap, false otherwise
     */
    public boolean contains(Location location) {
        return location.getWorld() == center.getWorld()
                && location.distanceSquared(center) < radius * radius;
    }

    /**
     * Adds an entity to the active list
     *
     * @param entity entity to add
     */
    public void addEntity(LivingEntity entity) {
        if (owner == null) remove();
        else if (!inRange.contains(entity)) {
            inRange.add(entity);
            enchantment.onEnter(this, entity, level);
        }
    }

    /**
     * Removes an entity from the active list
     *
     * @param entity entity to remove
     */
    public void removeEntity(LivingEntity entity) {
        if (owner == null) remove();
        else if (inRange.contains(entity)) {
            inRange.remove(entity);
            enchantment.onLeave(this, entity, level);
        }
    }

    /**
     * Updates the trap lists
     */
    public void update() {
        enchantment.onUpdate(this, level);
        if (owner == null) remove();
        else if (!owner.isOnline()) remove();
        else if (owner.getLocation().distanceSquared(center) > MAX_DISTANCE * MAX_DISTANCE) remove();
    }

    /**
     * Removes this trap from the world
     */
    public void remove() {
        enchantment.removeTrap(center);
        traps.remove(owner.getName() + enchantment.name());
    }
}