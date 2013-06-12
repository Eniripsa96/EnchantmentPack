package com.rit.sucy;

import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

/**
 * Places an ice trap on the ground that contains all enemies within it
 */
public class IceTrapEnchantment extends TrapEnchantment {

    /**
     * Constructor
     * @param plugin plugin reference
     */
    public IceTrapEnchantment(Plugin plugin) {
        super(plugin, EnchantDefaults.ICE_TRAP, 4);
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
    public void onLeave(Trap trap, LivingEntity entity, int level) {
        if (entity != trap.owner) {
            Vector direction = trap.center.toVector().subtract(entity.getLocation().toVector());
            direction = direction.multiply(1 / direction.length());
            entity.setVelocity(direction);
        }
    }
}
