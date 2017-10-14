package com.sucy.enchant.trap.enchant;

import com.rit.sucy.player.Protection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionTrap extends RedstoneTrap {

    private static final String TIER = "tier";
    private static final String DURATION = "duration";

    private final PotionEffectType type;

    public PotionTrap(final String name, final String description, final PotionEffectType type) {
        super(name, description);
        this.type = type;

        settings.set(LIFESPAN, 5, 2);
        settings.set(TIER, 2, 0);
        settings.set(DURATION, 7, 0);
    }

    /**
     * Applies poison to all enemies in the trap
     *
     * @param trap  trap being updated
     * @param level enchantment level used for the trap
     */
    @Override
    public boolean onUpdate(final Trap trap, final int level) {
        final int duration = (int)(settings.get(DURATION, level) * 20);
        final int tier = (int)settings.get(TIER, level);

        for (final LivingEntity entity : trap.getTrappedEntities()) {
            if (Protection.canAttack(trap.getOwner(), entity)) {
                entity.addPotionEffect(new PotionEffect(type, duration, tier), false);
            }
        }

        return false;
    }
}
