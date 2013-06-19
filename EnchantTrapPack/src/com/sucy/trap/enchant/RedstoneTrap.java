package com.sucy.trap.enchant;

import com.sucy.trap.ConfigurableEnchantment;
import com.sucy.trap.data.ConflictGroup;
import com.sucy.trap.data.EnchantDefaults;
import com.sucy.trap.data.ItemSets;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

/**
 * Places a trap at the user's feet that goes away by going too far away or right clicking again
 */
public abstract class RedstoneTrap extends ConfigurableEnchantment {

    /**
     * Redstone layout
     */
    protected boolean[][] layout;

    /**
     * Effect radius
     */
    protected int radius;

    /**
     * Constructor
     *
     * @param plugin  plugin reference
     * @param enchant enchantment type
     * @param radius  effect radius
     */
    public RedstoneTrap(Plugin plugin, EnchantDefaults enchant, int radius) {
        super (plugin, enchant, ItemSets.SHOVELS.getItems(), 1, ConflictGroup.TRAP);
        this.radius = radius;
    }

    /**
     * Places the trap
     *
     * @param player       player with the enchantment
     * @param enchantLevel enchantment level
     * @param event        event details
     */
    @Override
    public void applyMiscEffect(Player player, int enchantLevel, PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {

            // Remove a trap when right clicking a second time
            if (Trap.isTrapActive(player.getName(), this)) {
                Trap trap = Trap.getTrap(player.getName(), this);
                if (trap.contains(player.getLocation())){
                    trap.remove();
                    if (timers.containsKey(player.getName()))
                        timers.put(player.getName(), timers.get(player.getName()) - cooldown(enchantLevel) / 4);
                    return;
                }
            }

            // Check cool downs
            if (timers.get(player.getName()) == null) timers.put(player.getName(), 0l);
            if (System.currentTimeMillis() - timers.get(player.getName()) < cooldown(enchantLevel)){
                player.sendMessage(ChatColor.GOLD + name()
                        + ChatColor.DARK_RED + " cooldown - "
                        + ChatColor.GOLD + ((cooldown(enchantLevel) - System.currentTimeMillis() + timers.get(player.getName())) / 1000 + 1)
                        + ChatColor.DARK_RED + " seconds left");
                return;
            }
            if (Trap.isTrapActive(player.getName(), this)) return;

            // Check if the location is suitable
            Location location = player.getLocation();
            location.setX(location.getX() - layout.length / 2);
            location.setZ(location.getZ() - layout[layout.length / 2].length / 2);
            for (boolean[] row : layout) {
                for (boolean spot : row) {
                    if (!spot) {
                        location.setZ(location.getZ() + 1);
                        continue;
                    }
                    Block nonSolid = player.getWorld().getBlockAt(location);
                    location.setY(location.getY() - 1);
                    Block solid = player.getWorld().getBlockAt(location);
                    location.setY(location.getY() + 1);
                    if (nonSolid.getType().isSolid() || !solid.getType().isSolid()
                            || nonSolid.getType() == Material.REDSTONE_WIRE) {
                        player.sendMessage(ChatColor.DARK_RED + "This is not a suitable place for the trap's seal...");
                        return;
                    }
                    location.setZ(location.getZ() + 1);
                }
                location.setZ(location.getZ() - row.length);
                location.setX(location.getX() + 1);
            }

            // Place the trap
            location.setX(player.getLocation().getX() - layout.length / 2);
            for (boolean[] row : layout) {
                for (boolean spot : row) {
                    if (!spot) {
                        location.setZ(location.getZ() + 1);
                        continue;
                    }
                    player.getWorld().getBlockAt(location).setType(Material.REDSTONE_WIRE);
                    location.setZ(location.getZ() + 1);
                }
                location.setZ(location.getZ() - row.length);
                location.setX(location.getX() + 1);
            }

            // Create the trap data
            Trap.createTrap(player, this, player.getLocation(), radius, enchantLevel);

            // Update the cooldown timer
            timers.put(player.getName(), System.currentTimeMillis());
        }
    }

    /**
     * Removes the redstone of a trap from the world
     *
     * @param center center of the trap
     */
    public void removeTrap(Location center) {
        center.setX(center.getX() - layout.length / 2);
        center.setZ(center.getZ() - layout[layout.length / 2].length / 2);
        for (boolean[] row : layout) {
            for (boolean spot : row) {
                if (!spot || center.getWorld().getBlockAt(center).getType() != Material.REDSTONE_WIRE) {
                    center.setZ(center.getZ() + 1);
                    continue;
                }
                center.getWorld().getBlockAt(center).setType(Material.AIR);
                center.setZ(center.getZ() + 1);
            }
            center.setZ(center.getZ() - row.length);
            center.setX(center.getX() + 1);
        }
    }

    /**
     * Applies effects when enemies enter the trap
     *
     * @param trap   trap that was entered
     * @param entity enemy that entered the trap
     * @param level  enchantment level
     */
    public void onEnter(Trap trap, LivingEntity entity, int level) {}

    /**
     * Applies effects when enemies leave the trap
     *
     * @param trap   trap that was left
     * @param entity enemy that left the trap
     * @param level  enchantment level
     */
    public void onLeave(Trap trap, LivingEntity entity, int level) {}

    /**
     * Applies effects continuously to enemies in the trap
     *
     * @param trap  trap to update
     * @param level enchantment level
     */
    public void onUpdate(Trap trap, int level) {}
}