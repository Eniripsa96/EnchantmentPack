package com.sucy.enchant.passive;

import com.rit.sucy.player.Protection;
import com.sucy.enchant.api.Cooldowns;
import com.sucy.enchant.api.CustomEnchantment;
import com.sucy.enchant.api.ItemSet;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Has a chance to teleport and blind enemies when hit
 */
public class ShadowShift extends CustomEnchantment {

    private static final String CHANCE = "chance";
    private static final String TIER = "tier";
    private static final String DURATION = "duration";
    private static final String RADIUS = "radius";
    private static final String DISTANCE = "distance";

    public ShadowShift() {
        super("Shadow Shift", "Chance to blind enemies and teleport when hit");

        setMaxLevel(5);
        setWeight(1);
        addNaturalItems(ItemSet.LEGGINGS.getItems());
    }

    /**
     * Teleports and blinds when hit
     *
     * @param user   player with the enchantment
     * @param target enemy that hit the player
     * @param level  enchantment level
     * @param event  event details
     */
    @Override
    public void applyDefense(final LivingEntity user, final LivingEntity target, final int level, final EntityDamageEvent event) {
        if (Cooldowns.onCooldown(this, user, settings, level)) return;

        if (Math.random() * 100 > settings.get(CHANCE, level)) return;

        final double distance = settings.getDouble(DISTANCE, level);
        final double radius = settings.get(RADIUS, level);
        final int duration = (int)(settings.get(DURATION, level) * 20);
        final int tier = (int)settings.get(TIER, level);

        final double angle = Math.random() * Math.PI * 2;
        final double x = user.getLocation().getX() + Math.cos(angle) * distance;
        final double z = user.getLocation().getZ() + Math.sin(angle) * distance;
        final double y = user.getLocation().getY();
        user.teleport(new Location(user.getWorld(), x, y, z, user.getLocation().getYaw(), user.getLocation().getPitch()));

        for (Entity entity : user.getNearbyEntities(distance, distance, distance)) {
            if (entity instanceof LivingEntity && Protection.canAttack(user, (LivingEntity) entity)) {
                ((LivingEntity) entity).addPotionEffect(
                        new PotionEffect(PotionEffectType.BLINDNESS, duration, tier),
                        true);
            }
        }

        Cooldowns.start(this, user);
    }
}
