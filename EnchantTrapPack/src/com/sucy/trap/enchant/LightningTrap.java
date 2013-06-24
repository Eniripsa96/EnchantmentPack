package com.sucy.trap.enchant;

import com.rit.sucy.service.SuffixGroups;
import com.sucy.trap.data.EnchantDefaults;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class LightningTrap extends RedstoneTrap {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public LightningTrap(Plugin plugin) {
        super(plugin, EnchantDefaults.LIGHTNING_TRAP, 3);
        description = "Places a trap that strikes lightning";
        suffixGroups.add(SuffixGroups.LIGHTNING.getKey());
        layout = new boolean[][] {
                { false,  true, false,  true },
                {  true,  true, false,  true,  true },
                { false, false,  true, false, false },
                {  true,  true, false,  true,  true },
                { false,  true, false,  true }};
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
            entity.getWorld().strikeLightning(entity.getLocation());
            trap.remove();
        }
    }
}
