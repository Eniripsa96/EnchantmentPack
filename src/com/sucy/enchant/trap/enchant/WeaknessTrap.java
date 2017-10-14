package com.sucy.enchant.trap.enchant;

import org.bukkit.potion.PotionEffectType;

public class WeaknessTrap extends PotionTrap {

    public WeaknessTrap() {
        super("Weakness Trap", "Places a trap that lowers the damage of enemies", PotionEffectType.WEAKNESS);

        radius = 3;
        layout = new boolean[][]{
                {  true, false, false, false,  true },
                { false,  true, false,  true },
                { false, false, false, false, false },
                { false,  true, false,  true },
                {  true, false, false, false,  true }};
    }
}
