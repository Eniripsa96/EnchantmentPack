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
 * Pushes all nearby enemies away
 */
public class Repulse extends CustomEnchantment {

    private static final String RANGE = "range";
    private static final String SPEED = "speed";

    public Repulse() {
        super("Repulse", "Pushes all enemies away");

        setGroup(ConflictGroup.FORCE);
        setMaxLevel(5);
        setWeight(1);
        addNaturalItems(ItemSet.SWORDS.getItems());

        settings.set(RANGE, 10, 0);
        settings.set(SPEED, 8, 0);
    }

    /**
     * Pushes all enemies away on right click
     *
     * @param player player with the enchantment
     * @param level  enchantment level
     * @param event  event details
     */
    @Override
    public void applyInteractBlock(final Player player, final int level, final PlayerInteractEvent event) {

        // Make sure it was a right click
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR)
            return;

        // Check the cooldown
        if (Cooldowns.onCooldown(this, player, settings, level)) return;

        // Pull in nearby enemies
        double range = settings.get(RANGE, level);
        for (Entity entity : player.getNearbyEntities(range, range, range)) {
            if (!(entity instanceof LivingEntity) || !Protection.isAlly(player, (LivingEntity) entity)) continue;

            final Vector velocity = entity.getLocation().subtract(player.getLocation()).toVector();
            velocity.setY(velocity.getY() / 3);
            entity.setVelocity(velocity.multiply(settings.get(SPEED, level) / (1 + velocity.lengthSquared())));

            Cooldowns.start(this, player);
        }
    }
}
