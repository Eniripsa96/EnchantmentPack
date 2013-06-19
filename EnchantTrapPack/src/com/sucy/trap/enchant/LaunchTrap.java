package com.sucy.trap.enchant;

import com.sucy.trap.data.EnchantDefaults;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class LaunchTrap extends RedstoneTrap {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public LaunchTrap(Plugin plugin) {
        super(plugin, EnchantDefaults.LAUNCH_TRAP, 3);
        description = "Places a trap that launches an enemy up";
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
    public void onEnter(Trap trap, LivingEntity entity, int level) {
        if (entity != trap.owner && works(entity, trap.owner)) {
            Location loc = entity.getLocation();
            Vector velocity = entity.getVelocity();
            velocity.setY(speed(level));
            entity.setVelocity(velocity);
            trap.remove();
        }
    }
}
