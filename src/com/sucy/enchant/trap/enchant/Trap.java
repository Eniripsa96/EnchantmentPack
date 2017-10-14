package com.sucy.enchant.trap.enchant;

import com.sucy.enchant.Nearby;
import com.sucy.enchant.api.CustomEnchantment;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A trap layed in the world
 */
public class Trap {

    private static final int MAX_DISTANCE = 50;

    private static final HashMap<String, Trap> TRAPS = new HashMap<>();

    /**
     * Checks if the player has a trap of the given type already placed
     *
     * @param user    player to check
     * @param enchant trap type
     * @return true if one is placed, false otherwise
     */
    public static boolean isTrapActive(final LivingEntity user, final RedstoneTrap enchant) {
         return TRAPS.containsKey(makeKey(user, enchant));
    }

    /**
     * Gets the placed trap for a player
     *
     * @param user    player to search for
     * @param enchant type of trap
     * @return placed trap or null if not found
     */
    public static Trap getTrap(final LivingEntity user, final RedstoneTrap enchant) {
        return TRAPS.get(makeKey(user, enchant));
    }

    /**
     * Creates a new trap
     *
     * @param user    player placing the trap
     * @param enchant enchantment that caused the trap
     * @param center  center of the trap
     * @param level   enchantment level of the trap
     */
    public static void createTrap(final Player user, final RedstoneTrap enchant, final Location center, final int level) {
        TRAPS.put(makeKey(user, enchant), new Trap(user, enchant, center, level));
    }

    public static void clearTraps() {
        TRAPS.values().forEach(trap -> trap.enchantment.removeTrap(trap.center));
        TRAPS.clear();
    }

    public static void tickTraps() {
        TRAPS.entrySet().removeIf(e -> {
            final boolean expired = e.getValue().update();
            if (expired) {
                e.getValue().enchantment.removeTrap(e.getValue().center);
            }
            return expired;
        });
    }

    private static String makeKey(final LivingEntity user, final CustomEnchantment enchantment) {
        return user.getUniqueId() + enchantment.getName();
    }

    private final Set<LivingEntity> inRange;

    private final Player owner;
    private final RedstoneTrap enchantment;
    private final Location center;
    private final int level;

    private int lifespan;

    public Trap(final Player owner, final RedstoneTrap enchantment, final Location center, final int level) {
        Objects.requireNonNull(owner, "Owner cannot be null");

        inRange = new HashSet<>();
        this.owner = owner;
        this.enchantment = enchantment;
        this.center = center;
        this.level = level;
        this.lifespan = enchantment.lifespan(level);
    }

    public LivingEntity getOwner() {
        return owner;
    }

    public Location getCenter() {
        return center;
    }

    public Set<LivingEntity> getTrappedEntities() {
        return inRange;
    }

    /**
     * Checks if the given location is within the trap boundaries
     *
     * @param location location to check
     * @return         true if within the trap, false otherwise
     */
    public boolean contains(final Location location) {
        return location.getWorld() == center.getWorld()
                && location.distanceSquared(center) < enchantment.radius * enchantment.radius;
    }

    /**
     * Adds an entity to the active list
     *
     * @param entity entity to add
     */
    public boolean addEntity(final LivingEntity entity) {
        if (!inRange.contains(entity)) {
            inRange.add(entity);
            return enchantment.onEnter(this, entity, level);
        }
        return false;
    }

    /**
     * Removes an entity from the active list
     *
     * @param entity entity to remove
     */
    public boolean removeEntity(final LivingEntity entity) {
        if (inRange.contains(entity)) {
            inRange.remove(entity);
            return enchantment.onLeave(this, entity, level);
        }
        return false;
    }

    /**
     * Updates the trap lists
     */
    public boolean update() {
        if (!owner.isOnline() || owner.getLocation().distanceSquared(center) > MAX_DISTANCE * MAX_DISTANCE)
            return true;

        // Remove entities no longer in the trap
        for (final LivingEntity entity : inRange) {
            if (!contains(entity.getLocation())) {
                if (removeEntity(entity)) return true;
            }
        }

        // Add entities now in the trap
        for (final LivingEntity entity : Nearby.getLivingNearby(center, enchantment.radius)) {
            if (addEntity(entity)) return true;
        }

        return enchantment.onUpdate(this, level) || --lifespan == 0;
    }

    void destroy() {
        enchantment.removeTrap(center);
        TRAPS.remove(makeKey(owner, enchantment));
    }

    /**
     * @return collection of active traps
     */
    public static Collection<Trap> getTraps() {
        return TRAPS.values();
    }
}