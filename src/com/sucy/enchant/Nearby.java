package com.sucy.enchant;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Fetches nearby entities by going through possible chunks
 * instead of all entities in a world
 */
public class Nearby
{
    /**
     * Fetches entities nearby a location using a given radius
     *
     * @param loc    location centered around
     * @param radius radius to get within
     *
     * @return nearby entities
     */
    public static List<LivingEntity> getLivingNearby(final Location loc, final double radius)
    {
        List<LivingEntity> result = new ArrayList<LivingEntity>();

        int minX = (int) (loc.getX() - radius) >> 4;
        int maxX = (int) (loc.getX() + radius) >> 4;
        int minZ = (int) (loc.getZ() - radius) >> 4;
        int maxZ = (int) (loc.getZ() + radius) >> 4;

        final double rSq = radius * radius;

        for (int i = minX; i <= maxX; i++)
            for (int j = minZ; j <= maxZ; j++)
                for (Entity entity : loc.getWorld().getChunkAt(i, j).getEntities())
                    if (entity instanceof LivingEntity && entity.getLocation().distanceSquared(loc) < rSq)
                        result.add((LivingEntity) entity);

        return result;
    }
}
