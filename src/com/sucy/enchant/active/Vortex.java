package com.sucy.enchant.active;

import com.rit.sucy.player.Protection;
import com.sucy.enchant.ConflictGroup;
import com.sucy.enchant.api.Cooldowns;
import com.sucy.enchant.api.CustomEnchantment;
import com.sucy.enchant.api.ItemSet;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

/**
 * Drags all nearby enemies towards you
 */
public class Vortex extends CustomEnchantment {

    private static final String RANGE = "range";
    private static final String SPEED = "speed";

    public Vortex() {
        super("Vortex", "Pulls in all nearby enemies");

        setGroup(ConflictGroup.FORCE);
        setMaxLevel(5);
        setWeight(1);
        addNaturalItems(ItemSet.PICKAXES.getItems());

        Cooldowns.configure(settings, 10, -1);
        settings.set(RANGE, 10, 0);
        settings.set(SPEED, 0.3, 0);
    }

    /**
     * Drags all nearby enemies closer on right click
     *
     * @param player player with the enchantment
     * @param level  enchantment level
     * @param event  event details
     */
    @Override
    public void applyInteractBlock(final Player player, final int level, final PlayerInteractEvent event) {

        // Make sure it is a right click
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {

            // Check the cooldown
            if (Cooldowns.onCooldown(this, player, settings, level)) return;

            // Drag all nearby enemies closer
            final double range = settings.get(RANGE, level);
            for (final Entity entity : player.getNearbyEntities(range, range, range)) {
                if (!(entity instanceof LivingEntity) || Protection.isAlly(player, (LivingEntity) entity)) continue;

                final Vector velocity = player.getLocation().subtract(entity.getLocation()).toVector();
                velocity.setY(velocity.getY() / 2);
                entity.setVelocity(velocity.multiply(settings.get(SPEED, level)));

                Cooldowns.start(this, player);
            }
        }
    }
}
