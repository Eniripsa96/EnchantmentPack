package com.sucy.enchant.trap.enchant;

import com.rit.sucy.player.Protection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public class LaunchTrap extends RedstoneTrap {

    private static final String SPEED = "speed";

    public LaunchTrap() {
        super("Launch Trap", "Places a trap that launches an enemy up");

        settings.set(LIFESPAN, -1, 0);
        settings.set(SPEED, 3, 0.5);

        radius = 3;
        layout = new boolean[][] {
                {  true,  true,  true,  true,  true },
                {  true, false, false, false,  true },
                {  true, false,  true, false,  true },
                {  true, false, false, false,  true },
                {  true,  true,  true,  true,  true }};
    }

    /**
     * Blows up the trap when an enemy enters the trap
     *
     * @param trap   trap walked into
     * @param entity enemy that walked into the trap
     * @param level  enchantment level used for the trap
     */
    @Override
    public boolean onEnter(final Trap trap, final LivingEntity entity, final int level) {
        if (Protection.canAttack(trap.getOwner(), entity)) {
            final Vector velocity = entity.getVelocity();
            velocity.setY(settings.get(SPEED, level));
            entity.setVelocity(velocity);
            return true;
        }
        return false;
    }
}
