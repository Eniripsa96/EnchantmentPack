package com.sucy.enchant.trap.enchant;

import com.rit.sucy.player.Protection;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

/**
 * Places a fire trap that explodes in flames when an enemy enters it
 */
public class FireTrap extends RedstoneTrap {

    private static final String POWER = "power";

    public FireTrap() {
        super("Fire Trap", "Places a trap that creates fire when triggered");

        settings.set(LIFESPAN, -1, 0);
        settings.set(POWER, 1.5, 0.25);

        radius = 4;
        layout = new boolean[][] {
                { false, false,  true,  true,  true },
                { false, false, false,  true },
                {  true, false, false, false, false, false,  true },
                {  true,  true, false,  true, false,  true,  true },
                {  true, false, false, false, false, false,  true },
                { false, false, false,  true },
                { false, false,  true,  true,  true }};
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
            final Location loc = entity.getLocation();
            final float power = (float)settings.get(POWER, level);
            entity.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), power, true, false);
            return true;
        }
        return false;
    }
}