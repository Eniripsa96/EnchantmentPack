package com.sucy.enchant.passive;

import com.rit.sucy.player.Protection;
import com.sucy.enchant.ConflictGroup;
import com.sucy.enchant.api.CustomEnchantment;
import com.sucy.enchant.api.ItemSet;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

public class Forceful extends CustomEnchantment {

    private static final String CHANCE = "chance";
    private static final String RADIUS = "range";
    private static final String DAMAGE = "damage";
    private static final String SPEED = "speed";

    public Forceful() {
        super("Forceful", "Damages and knocks away enemies on attack");

        setGroup(ConflictGroup.FORCE);
        setMaxLevel(6);
        setWeight(1);
        addNaturalItems(ItemSet.AXES.getItems());

        settings.set(CHANCE, 100, 0);
        settings.set(RADIUS, 2, 1);
        settings.set(DAMAGE, 1, 0);
        settings.set(SPEED, 1, 0);
    }

    /**
     * Pulls in nearby enemies on hit
     *
     * @param user   player with the enchantment
     * @param target enemy that was hit
     * @param level  enchantment level
     * @param event  event details
     */
    @Override
    public void applyOnHit(LivingEntity user, LivingEntity target, int level, EntityDamageByEntityEvent event) {
        if (Math.random() * 100 > settings.get(CHANCE, level)) return;

        final double range = settings.get(RADIUS, level);
        final double damage = settings.get(DAMAGE, level);
        final double speed = settings.get(SPEED, level);
        for (final Entity entity : user.getNearbyEntities(range, range, range)) {
            if (!(entity instanceof LivingEntity) || Protection.isAlly(user, (LivingEntity)entity)) continue;

            ((LivingEntity) entity).damage(damage, user);
            final Vector distance = entity.getLocation().subtract(user.getLocation()).toVector();
            entity.setVelocity(distance.multiply(speed / distance.lengthSquared()));
        }
    }
}
