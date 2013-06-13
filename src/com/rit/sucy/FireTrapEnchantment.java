package com.rit.sucy;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

/**
 * Places a fire trap that explodes in flames when an enemy enters it
 */
public class FireTrapEnchantment extends TrapEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public FireTrapEnchantment(Plugin plugin) {
        super(plugin, EnchantDefaults.FIRE_TRAP, 4);
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
    public void onEnter(Trap trap, LivingEntity entity, int level) {
        if (entity != trap.owner && works(entity)) {
            Location loc = entity.getLocation();
            entity.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), power(level), true, false);
            trap.remove();
        }
    }
}