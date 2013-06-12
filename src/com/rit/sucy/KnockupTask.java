package com.rit.sucy;

import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

/**
 * Launches an entity into the air
 */
public class KnockupTask extends BukkitRunnable {

    /**
     * Speed to launch the entity
     */
    Vector LAUNCH_SPEED;

    /**
     * Entity to launch
     */
    LivingEntity entity;

    /**
     * Constructor
     *
     * @param entity entity to launch
     * @param speed  speed to launch the entity
     */
    public KnockupTask(LivingEntity entity, float speed) {
        this.entity = entity;
        LAUNCH_SPEED = new Vector(0, speed, 0);
    }

    /**
     * Launches the entity
     */
    public void run() {
        entity.setVelocity(LAUNCH_SPEED);
    }
}
