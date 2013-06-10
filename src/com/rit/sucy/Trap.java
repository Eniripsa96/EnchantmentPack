package com.rit.sucy;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Trap {

    static final int MAX_DISTANCE = 50;

    static HashMap<String, Trap> traps = new HashMap<String, Trap>();

    List<LivingEntity> inRange;
    Player owner;
    TrapEnchantment enchantment;
    Location center;
    int radius;
    int level;

    public static boolean isTrapActive(String player, TrapEnchantment enchantment) {
         return traps.containsKey(player + enchantment.name());
    }

    public static Trap getTrap(String player, TrapEnchantment enchantment) {
        if (traps.containsKey(player + enchantment.name()))
            return traps.get(player + enchantment.name());
        else return null;
    }

    public static void createTrap(Player player, TrapEnchantment enchantment, Location center, int radius, int level) {
        traps.put(player.getName() + enchantment.name(), new Trap(player, enchantment, center, radius, level));
    }

    public Trap(Player owner, TrapEnchantment enchantment, Location center, int radius, int level) {
        inRange = new ArrayList<LivingEntity>();
        this.owner = owner;
        this.enchantment = enchantment;
        this.center = center;
        this.radius = radius;
        this.level = level;
    }

    public boolean contains(Location location) {
        return location.getWorld() == center.getWorld()
                && location.distanceSquared(center) < radius * radius;
    }

    public void addEntity(LivingEntity entity) {
        if (owner == null) remove();
        else if (!inRange.contains(entity)) {
            inRange.add(entity);
            enchantment.onEnter(this, entity, level);
        }
    }

    public void removeEntity(LivingEntity entity) {
        if (owner == null) remove();
        else if (inRange.contains(entity)) {
            inRange.remove(entity);
            enchantment.onLeave(this, entity, level);
        }
    }

    public void update() {
        enchantment.onUpdate(this, level);
        if (owner == null) remove();
        else if (!owner.isOnline()) remove();
        else if (owner.getLocation().distanceSquared(center) > MAX_DISTANCE * MAX_DISTANCE) remove();
    }

    public void remove() {
        enchantment.removeTrap(center);
        traps.remove(owner.getName() + enchantment.name());
    }
}