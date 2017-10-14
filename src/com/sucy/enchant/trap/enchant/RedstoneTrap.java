package com.sucy.enchant.trap.enchant;

import com.rit.sucy.text.TextFormatter;
import com.sucy.enchant.ConflictGroup;
import com.sucy.enchant.api.Cooldowns;
import com.sucy.enchant.api.CustomEnchantment;
import com.sucy.enchant.api.ItemSet;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Places a trap at the user's feet that goes away by going too far away or right clicking again
 */
public abstract class RedstoneTrap extends CustomEnchantment {

    static final String LIFESPAN = "lifespan";

    private static final String REFUND = "pickup-refund";
    private static final String MESSAGE = "cooldown-message";
    private static final String PLACEMENT = "placement-message";

    protected boolean[][] layout;
    protected int radius;

    public RedstoneTrap(final String name, final String description) {
        super(name, description);

        setGroup(ConflictGroup.TRAP);
        setMaxLevel(5);
        setWeight(1);
        addNaturalItems(ItemSet.SHOVELS.getItems());

        Cooldowns.configure(settings, 20, 0);
        settings.set(LIFESPAN, 5, 1);
        settings.set(REFUND, 5, 0);
        settings.set(MESSAGE, "&6" + getName() + "&4 cooldown - &6{cooldown} seconds &4left");
        settings.set(PLACEMENT, "&4This is not a suitable place for the trap's seal...");
    }

    public int lifespan(final int level) {
        return (int)(settings.get(LIFESPAN, level) * 20);
    }

    /**
     * Places the trap
     *
     * @param user       player with the enchantment
     * @param level enchantment level
     * @param event        event details
     */
    @Override
    public void applyInteractBlock(final Player user, final int level, final PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {

            // Remove a trap when right clicking a second time
            if (Trap.isTrapActive(user, this)) {
                final Trap trap = Trap.getTrap(user, this);
                if (trap.contains(user.getLocation())){
                    trap.destroy();
                    Cooldowns.reduce(this, user, (long)(settings.get(REFUND, level) * 1000));
                    return;
                }
            }

            // Check cool downs
            if (Cooldowns.onCooldown(this, user, settings, level)) {
                final String remaining = Integer.toString(Cooldowns.secondsLeft(this, user, settings, level));
                user.sendMessage(TextFormatter.colorString(settings.getString(MESSAGE).replace("{cooldown}", remaining)));
                return;
            }
            if (Trap.isTrapActive(user, this)) return;

            // Check if the location is suitable
            final Location location = user.getLocation();
            location.setX(location.getX() - layout.length / 2);
            location.setZ(location.getZ() - layout[layout.length / 2].length / 2);
            for (final boolean[] row : layout) {
                for (final boolean spot : row) {
                    if (spot) {
                        final Block nonSolid = user.getWorld().getBlockAt(location);
                        location.setY(location.getY() - 1);
                        final Block solid = user.getWorld().getBlockAt(location);
                        location.setY(location.getY() + 1);
                        if (nonSolid.getType().isSolid() || !solid.getType().isSolid()
                                || nonSolid.getType() == Material.REDSTONE_WIRE) {
                            user.sendMessage(TextFormatter.colorString(settings.getString(PLACEMENT)));
                            return;
                        }
                    }
                    location.setZ(location.getZ() + 1);
                }
                location.setZ(location.getZ() - row.length);
                location.setX(location.getX() + 1);
            }

            // Place the trap
            location.setX(user.getLocation().getX() - layout.length / 2);
            for (final boolean[] row : layout) {
                for (final boolean spot : row) {
                    if (spot) {
                        final BlockState state = user.getWorld().getBlockAt(location).getState();
                        state.setType(Material.REDSTONE_WIRE);
                        state.update(true, false);
                    }
                    location.setZ(location.getZ() + 1);
                }
                location.setZ(location.getZ() - row.length);
                location.setX(location.getX() + 1);
            }

            // Create the trap data
            Trap.createTrap(user, this, user.getLocation(), level);

            // Update the cooldown timer
            Cooldowns.start(this, user);
        }
    }

    /**
     * Removes the redstone of a trap from the world
     *
     * @param center center of the trap
     */
    public void removeTrap(final Location center) {
        center.setX(center.getX() - layout.length / 2);
        center.setZ(center.getZ() - layout[layout.length / 2].length / 2);
        for (final boolean[] row : layout) {
            for (final boolean spot : row) {
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
    public boolean onEnter(Trap trap, LivingEntity entity, int level) { return false; }

    /**
     * Applies effects when enemies leave the trap
     *
     * @param trap   trap that was left
     * @param entity enemy that left the trap
     * @param level  enchantment level
     */
    public boolean onLeave(Trap trap, LivingEntity entity, int level) { return false; }

    /**
     * Applies effects continuously to enemies in the trap
     *
     * @param trap  trap to update
     * @param level enchantment level
     */
    public boolean onUpdate(Trap trap, int level) { return false; }
}