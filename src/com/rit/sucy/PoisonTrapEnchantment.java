package com.rit.sucy;

import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Places a poison trap that poisons all foes who pass through it
 */
public class PoisonTrapEnchantment extends TrapEnchantment {

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public PoisonTrapEnchantment(Plugin plugin) {
        super(plugin, EnchantDefaults.POISON_TRAP, 4);
        layout = new boolean[][] {
                { false, false, false,  true },
                { false, false,  true,  true,  true },
                { false,  true, false, false, false,  true },
                {  true,  true, false,  true, false,  true,  true },
                { false,  true, false, false, false,  true },
                { false, false,  true,  true,  true },
                { false, false, false,  true }};
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
            if (entity != trap.owner) {
                entity.addPotionEffect(new PotionEffect(PotionEffectType.POISON, duration(level), tier(level)), true);
            }
        }
    }
}