package com.sucy.enchant.trap.enchant;

import com.rit.sucy.player.Protection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public class BarrierTrap extends RedstoneTrap {

    public BarrierTrap() {
        super("Barrier Trap", "Places a trap that pushes enemies away");

        radius = 3;
        layout = new boolean[][] {
                {  true,  true, false,  true,  true },
                {  true, false, false, false,  true },
                { false, false,  true, false, false },
                {  true, false, false, false,  true },
                {  true,  true, false,  true,  true }};
    }

    /**
     * Moves enemies away from the center when they try to enter
     *
     * @param trap   trap that had an enemy enter
     * @param entity enemy that left the trap
     * @param level  enchantment level used for the trap
     */
    @Override
    public boolean onEnter(final Trap trap, final LivingEntity entity, final int level) {
        if (Protection.canAttack(trap.getOwner(), entity)) {
            final Vector direction = entity.getLocation().toVector().subtract(trap.getCenter().toVector());
            entity.setVelocity(direction.multiply(1 / direction.length()));
        }
        return false;
    }
}
