package com.sucy.trap.enchant;

import com.sucy.trap.data.EnchantDefaults;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class WebTrap extends RedstoneTrap {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public WebTrap(Plugin plugin) {
        super(plugin, EnchantDefaults.WEB_TRAP, 3);
        description = "Places a trap that spawns temporary webs";
        layout = new boolean[][] {
                { false, false,  true,  true },
                { false, false,  true, false,  true },
                {  true,  true, false,  true,  true },
                {  true, false,  true },
                { false,  true,  true }};
    }

    /**
     * Creates webs around an entity entering the trap
     *
     * @param trap   trap walked into
     * @param entity enemy that walked into the trap
     * @param level  enchantment level used for the trap
     */
    @Override
    public void onEnter(Trap trap, LivingEntity entity, int level) {
        if (entity != trap.owner && works(entity, trap.owner)) {
            Location loc = entity.getLocation();
            ArrayList<Block> webs = new ArrayList<Block>();
            int count = spawn(level);
            int max = 0;
            while (max * max * max < count)
                max++;
            loc.setX(loc.getX() - max / 2);
            loc.setZ(loc.getZ() - max / 2);
            loc.setY(loc.getY() - max / 2 + 1);
            for (int i = 0; i < count; i++) {
                Location temp = new Location(loc.getWorld(),
                        loc.getX() + i % max,
                        loc.getY() + ((double)i / max) / max,
                        loc.getZ() + ((double)i / max) % max);
                Block block = loc.getWorld().getBlockAt(temp);
                if (block.getType() == Material.AIR || !block.getType().isSolid()) {
                    webs.add(block);
                    block.setType(Material.WEB);
                }
            }
            new WebTask(webs).runTaskLater(plugin, duration(level));
            trap.remove();
        }
    }
}
