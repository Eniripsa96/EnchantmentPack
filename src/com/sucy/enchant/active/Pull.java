package com.sucy.enchant.active;

import com.rit.sucy.player.Protection;
import com.sucy.enchant.ConflictGroup;
import com.sucy.enchant.api.Cooldowns;
import com.sucy.enchant.api.CustomEnchantment;
import com.sucy.enchant.api.ItemSet;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.util.Vector;

/**
 * Pulls a target enemy towards you
 */
public class Pull extends CustomEnchantment {

    private static final String SPEED = "speed";

    public Pull() {
        super("Pull", "Pulls the target enemy closer");

        setGroup(ConflictGroup.FORCE);
        setMaxLevel(5);
        setWeight(3);
        addNaturalItems(ItemSet.PICKAXES.getItems());

        Cooldowns.configure(settings, 10, -1);
        settings.set(SPEED, 0.3, 0);
    }

    /**
     * Pulls the target enemy closer
     *
     * @param player player with the enchantment
     * @param level  enchantment level
     * @param event  event details
     */
    @Override
    public void applyInteractEntity(final Player player, final int level, final PlayerInteractEntityEvent event) {

        // Make sure it is a valid target
        if (!(event.getRightClicked() instanceof LivingEntity) || Protection.isAlly(player, (LivingEntity)event.getRightClicked()))
            return;

        // Check the cooldown
        if (Cooldowns.onCooldown(this, player, settings, level)) return;

        // Pull the target in
        Vector velocity = player.getLocation().subtract(event.getRightClicked().getLocation()).toVector();
        velocity.setY(velocity.getY() / 2);
        event.getRightClicked().setVelocity(velocity.multiply(settings.get(SPEED, level)));

        // Update the cooldown timer
        Cooldowns.start(this, player);
    }
}
