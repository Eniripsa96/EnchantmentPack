package com.sucy.enchant.active;

import com.sucy.enchant.api.Cooldowns;
import com.sucy.enchant.api.CustomEnchantment;
import com.sucy.enchant.api.Tasks;
import com.sucy.enchant.api.ItemSet;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

/**
 * Dashes forward, dealing damage along the way
 */
public class Dash extends CustomEnchantment {

    private static final String SPEED = "speed";
    private static final String DAMAGE = "damage";

    public Dash() {
        super("Dash", "Dash forward dealing damage to all enemies");

        setMaxLevel(5);
        setWeight(2);
        addNaturalItems(ItemSet.SWORDS.getItems());

        Cooldowns.configure(settings, 5, 0);
        settings.set(DAMAGE, 1, 3);
        settings.set(SPEED, 3, 0);
    }

    /**
     * Applies the enchantment effect upon right click
     *
     * @param player player with the enchantment
     * @param level  enchantment level
     * @param event  event details
     */
    @Override
    public void applyInteractBlock(Player player, int level, PlayerInteractEvent event) {

        // Make sure it was a right click
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {

            // Check the cooldown
            if (Cooldowns.onCooldown(this, player, settings, level)) return;

            // Dash forward
            Vector vector = player.getLocation().getDirection();
            vector.setY(0);
            vector.multiply(settings.get(SPEED, level) / vector.length());
            vector.setY(0.3);
            player.setVelocity(vector);

            // Apply damage while moving
            Tasks.schedule(() -> player.getNearbyEntities(1, 1, 1).forEach(target -> {
                if (target instanceof LivingEntity) {
                    ((LivingEntity) target).damage(settings.get(DAMAGE, level), player);
                }
            }), 0, 4, 4);

            // Update cooldown timer
            Cooldowns.start(this, player);
        }
    }
}
