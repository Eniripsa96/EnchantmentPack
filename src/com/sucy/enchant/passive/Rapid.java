package com.sucy.enchant.passive;

import com.sucy.enchant.api.CustomEnchantment;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class Rapid extends CustomEnchantment {

    private static final String SPEED = "speed";

    public Rapid() {
        super("Rapid", "Fire faster projectiles");

        setMaxLevel(5);
        setWeight(5);
        addNaturalItems(Material.BOW);

        settings.set(SPEED, 1.1, 0.1);
    }

    @Override
    public void applyProjectile(final LivingEntity user, final int level, final ProjectileLaunchEvent event) {
        event.getEntity().setVelocity(event.getEntity().getVelocity().multiply(settings.get(SPEED, level)));
    }
}
