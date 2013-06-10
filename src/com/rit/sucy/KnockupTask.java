package com.rit.sucy;

import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class KnockupTask extends BukkitRunnable {

    Vector LAUNCH_SPEED;

    LivingEntity entity;

    public KnockupTask(LivingEntity entity, float speed) {
        this.entity = entity;
        LAUNCH_SPEED = new Vector(0, speed, 0);
    }

    public void run() {
        entity.setVelocity(LAUNCH_SPEED);
    }
}
