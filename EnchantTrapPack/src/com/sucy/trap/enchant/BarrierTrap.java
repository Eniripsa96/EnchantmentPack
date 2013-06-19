package com.sucy.trap.enchant;

import com.sucy.trap.data.EnchantDefaults;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class BarrierTrap extends RedstoneTrap {

    /**
     * Constructor
     * @param plugin plugin reference
     */
    public BarrierTrap(Plugin plugin) {
        super(plugin, EnchantDefaults.BARRIER_TRAP, 3);
        description = "Places a trap that pushes enemies away";
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
    public void onEnter(Trap trap, LivingEntity entity, int level) {
        if (entity != trap.owner && works(entity, trap.owner)) {
            Vector direction = entity.getLocation().toVector().subtract(trap.center.toVector());
            direction = direction.multiply(1 / direction.length());
            entity.setVelocity(direction);
        }
    }
}
