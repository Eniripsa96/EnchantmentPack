package com.rit.sucy;

import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Updates traps every other tick
 */
public class TrapTask extends BukkitRunnable {

    /**
     * Updates all traps
     */
    public void run() {
        for (Trap trap : Trap.traps.values()) {
            for (LivingEntity entity : trap.center.getWorld().getLivingEntities()) {
                if (trap.contains(entity.getLocation())) trap.addEntity(entity);
                else trap.removeEntity(entity);
            }
            trap.update();
        }
    }
}
