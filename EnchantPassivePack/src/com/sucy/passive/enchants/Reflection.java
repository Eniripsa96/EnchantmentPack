package com.sucy.passive.enchants;

import com.rit.sucy.service.SuffixGroups;
import com.sucy.passive.ConfigurableEnchantment;
import com.sucy.passive.data.ConflictGroup;
import com.sucy.passive.data.EnchantDefaults;
import com.sucy.passive.data.ItemSets;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;

/**
 * Reflects damage back at attackers
 */
public class Reflection extends ConfigurableEnchantment {

    private static boolean applying = false;

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public Reflection(Plugin plugin) {
        super(plugin, EnchantDefaults.REFLECTION, ItemSets.CHESTPLATES.getItems(), ConflictGroup.POD);
        description = "Reflects damage back at attackers";
        suffixGroups.add(SuffixGroups.STRENGTH.getKey());
    }

    /**
     * Reflects damage when hit
     *
     * @param user   player with the enchantment
     * @param target enemy that hit the player
     * @param level  enchantment level
     * @param event  event details
     */
    @Override
    public void applyDefenseEffect(LivingEntity user, LivingEntity target, int level, EntityDamageEvent event) {
        if (!applying && roll(level) && works(target, user))
        {
            applying = true;
            target.damage((int) (event.getDamage() * percent(level)), user);
            applying = false;
        }
    }
}
