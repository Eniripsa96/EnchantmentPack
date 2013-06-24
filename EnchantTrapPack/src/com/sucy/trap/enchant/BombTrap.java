package com.sucy.trap.enchant;

import com.rit.sucy.service.SuffixGroups;
import com.sucy.trap.data.EnchantDefaults;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class BombTrap extends RedstoneTrap {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public BombTrap(Plugin plugin) {
        super(plugin, EnchantDefaults.BOMB_TRAP, 4);
        description = "Places a trap that explodes when triggered";
        suffixGroups.add(SuffixGroups.EXPLOSIONS.getKey());
        layout = new boolean[][] {
                { false, false,  true, false,  true },
                { false,  true, false, false, false,  true },
                {  true, false,  true,  true,  true, false,  true },
                { false, false,  true, false,  true, false, false },
                {  true, false,  true,  true,  true, false,  true },
                { false,  true, false, false, false,  true },
                { false, false,  true, false,  true }};
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
            entity.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), power(level), false, false);
            trap.remove();
        }
    }
}
