package com.sucy.enchant.trap.enchant;

import com.rit.sucy.player.Protection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

/**
 * Places an ice trap on the ground that contains all enemies within it
 */
public class IceTrap extends RedstoneTrap {

    public IceTrap() {
        super("Ice Trap", "Creates a trap that traps enemies");

        radius = 3;
        layout = new boolean[][] {
                {  true,  true, false,  true,  true },
                {  true, false, false, false,  true },
                { false, false,  true, false, false },
                {  true, false, false, false,  true },
                {  true,  true, false,  true,  true }};
    }

    /**
     * Moves enemies back towards the center when they leave
     *
     * @param trap   trap that had an enemy leave
     * @param entity enemy that left the trap
     * @param level  enchantment level used for the trap
     */
    @Override
    public boolean onLeave(final Trap trap, final LivingEntity entity, final int level) {
        if (Protection.canAttack(trap.getOwner(), entity)) {
            final Vector direction = trap.getCenter().toVector().subtract(entity.getLocation().toVector());
            entity.setVelocity(direction.multiply(1 / direction.length()));
        }
        return false;
    }
}
