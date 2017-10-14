package com.sucy.enchant.passive;

import com.sucy.enchant.api.CustomEnchantment;
import com.sucy.enchant.api.Tasks;
import com.sucy.enchant.api.ItemSet;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitTask;

import java.util.Hashtable;
import java.util.UUID;

public class Regenerative extends CustomEnchantment {

    private static final String FREQUENCY = "frequency";
    private static final String HEALTH = "health";

    private Hashtable<UUID, BukkitTask> tasks = new Hashtable<>();

    public Regenerative() {
        super("Regenerative", "Restores health slowly over time");

        setMaxLevel(3);
        setWeight(2);
        addNaturalItems(ItemSet.CHESTPLATES.getItems());

        settings.set(FREQUENCY, 5, -1);
        settings.set(HEALTH, 1, 0);
    }

    @Override
    public void applyEquip(final LivingEntity user, final int level) {
        final double health = settings.get(HEALTH, level);
        final int frequency = (int)(settings.get(FREQUENCY, level) * 20);
        tasks.put(user.getUniqueId(), Tasks.schedule(
                () -> user.setHealth(Math.min(user.getMaxHealth(), user.getHealth() + health)),
                frequency,
                frequency));
    }

    @Override
    public void applyUnequip(LivingEntity user, int level) {
        tasks.remove(user.getUniqueId()).cancel();
    }
}
