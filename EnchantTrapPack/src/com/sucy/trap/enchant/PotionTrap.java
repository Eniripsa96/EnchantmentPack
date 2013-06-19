package com.sucy.trap.enchant;

import com.sucy.trap.data.EnchantDefaults;
import com.sucy.trap.enchant.RedstoneTrap;
import com.sucy.trap.enchant.Trap;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionTrap extends RedstoneTrap {

    PotionEffectType type;

    /**
     * Constructor
     *
     * @param plugin  plugin reference
     * @param enchant enchantment defaults
     * @param radius  the effect radius of the trap
     * @param type    the type of potion applied
     */
    public PotionTrap(Plugin plugin, EnchantDefaults enchant, int radius, PotionEffectType type) {
        super(plugin, enchant, 4);
        this.type = type;
    }

    /**
     * Applies poison to all enemies in the trap
     *
     * @param trap  trap being updated
     * @param level enchantment level used for the trap
     */
    @Override
    public void onUpdate(Trap trap, int level) {
        for (LivingEntity entity : trap.inRange) {
            if (entity != trap.owner && works(entity, trap.owner)) {
                entity.addPotionEffect(new PotionEffect(type, duration(level), tier(level)), false);
            }
        }
    }
}
