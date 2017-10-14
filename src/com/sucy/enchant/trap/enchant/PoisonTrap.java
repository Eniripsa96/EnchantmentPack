package com.sucy.enchant.trap.enchant;

import org.bukkit.potion.PotionEffectType;

/**
 * Places a poison trap that poisons all foes who pass through it
 */
public class PoisonTrap extends PotionTrap {

    public PoisonTrap() {
        super("Poison Trap", "Places a trap that poisons enemies", PotionEffectType.POISON);

        radius = 4;
        layout = new boolean[][] {
                { false, false, false,  true },
                { false, false,  true,  true,  true },
                { false,  true, false, false, false,  true },
                {  true,  true, false,  true, false,  true,  true },
                { false,  true, false, false, false,  true },
                { false, false,  true,  true,  true },
                { false, false, false,  true }};
    }
}