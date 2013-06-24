package com.sucy.potion.damaged.reflect;

import com.rit.sucy.service.SuffixGroups;
import com.sucy.potion.ConfigurableEnchantment;
import com.sucy.potion.data.ConflictGroup;
import com.sucy.potion.data.EnchantDefaults;
import com.sucy.potion.data.ItemSets;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;

/**
 * Sets enemies on fire when they hit you
 */
public class Molten extends ConfigurableEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public Molten(Plugin plugin) {
        super(plugin, EnchantDefaults.MOLTEN, ItemSets.CHESTPLATES.getItems(), 2, ConflictGroup.POD);
        description = "Burns enemies when hit";
        suffixGroups.add(SuffixGroups.FIRE.getKey());
    }

    /**
     * Sets attackers on fire
     *
     * @param user   player with the enchantment
     * @param target enemy that hit the player
     * @param level  enchantment level
     * @param event  event details
     */
    @Override
    public void applyDefenseEffect(LivingEntity user, LivingEntity target, int level, EntityDamageEvent event) {
        if (roll(level) && works(target, user))
            target.setFireTicks(duration(level));
    }
}
