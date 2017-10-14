package com.sucy.enchant;

import com.sucy.enchant.active.Angler;
import com.sucy.enchant.active.Dash;
import com.sucy.enchant.active.Fireball;
import com.sucy.enchant.active.Fried;
import com.sucy.enchant.active.Grapple;
import com.sucy.enchant.active.Heal;
import com.sucy.enchant.active.Pull;
import com.sucy.enchant.active.Rejuvenating;
import com.sucy.enchant.active.Repulse;
import com.sucy.enchant.active.Toss;
import com.sucy.enchant.active.Vortex;
import com.sucy.enchant.api.EnchantPlugin;
import com.sucy.enchant.api.EnchantmentRegistry;
import com.sucy.enchant.api.Tasks;
import com.sucy.enchant.passive.Forceful;
import com.sucy.enchant.passive.Gravity;
import com.sucy.enchant.passive.Knockup;
import com.sucy.enchant.passive.Life;
import com.sucy.enchant.passive.Lifesteal;
import com.sucy.enchant.passive.Lightning;
import com.sucy.enchant.passive.Rapid;
import com.sucy.enchant.passive.Regenerative;
import com.sucy.enchant.passive.ShadowShift;
import com.sucy.enchant.potion.damaged.absorb.Adrenaline;
import com.sucy.enchant.potion.damaged.absorb.Lively;
import com.sucy.enchant.potion.damaged.absorb.Phantom;
import com.sucy.enchant.potion.damaged.reflect.Brilliance;
import com.sucy.enchant.potion.damaged.reflect.Cursed;
import com.sucy.enchant.potion.damaged.reflect.Demoralizing;
import com.sucy.enchant.potion.damaged.reflect.Frost;
import com.sucy.enchant.potion.damaged.reflect.Molten;
import com.sucy.enchant.potion.damaged.reflect.Toxic;
import com.sucy.enchant.potion.hit.inflict.Blind;
import com.sucy.enchant.potion.hit.inflict.Poison;
import com.sucy.enchant.potion.hit.inflict.Slow;
import com.sucy.enchant.potion.hit.inflict.Weakness;
import com.sucy.enchant.potion.hit.inflict.Wither;
import com.sucy.enchant.potion.hit.steal.Berserking;
import com.sucy.enchant.potion.hit.steal.Distortion;
import com.sucy.enchant.potion.hit.steal.Fervor;
import com.sucy.enchant.potion.passive.Gears;
import com.sucy.enchant.potion.passive.Jump;
import com.sucy.enchant.potion.passive.NightVision;
import com.sucy.enchant.trap.enchant.BarrierTrap;
import com.sucy.enchant.trap.enchant.BombTrap;
import com.sucy.enchant.trap.enchant.FireTrap;
import com.sucy.enchant.trap.enchant.IceTrap;
import com.sucy.enchant.trap.enchant.LaunchTrap;
import com.sucy.enchant.trap.enchant.LightningTrap;
import com.sucy.enchant.trap.enchant.PoisonTrap;
import com.sucy.enchant.trap.enchant.SlowTrap;
import com.sucy.enchant.trap.enchant.Trap;
import com.sucy.enchant.trap.enchant.WeaknessTrap;
import com.sucy.enchant.trap.enchant.WebTrap;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

/**
 * EnchantmentPack © 2017
 * com.sucy.enchant.EnchantmentPack
 */
public class EnchantmentPack extends JavaPlugin implements EnchantPlugin {

    private BukkitTask trapTask;

    @Override
    public void onEnable() {
        new EnchantsListener(this);
        trapTask = Tasks.schedule(Trap::tickTraps, 2, 2);
    }

    @Override
    public void onDisable() {
        trapTask.cancel();
        Trap.clearTraps();
        HandlerList.unregisterAll(this);
    }

    @Override
    public void registerEnchantments(final EnchantmentRegistry enchantmentRegistry) {
        enchantmentRegistry.register(

                // Active
                new Angler(),
                new Dash(),
                new Fireball(),
                new Fried(),
                new Grapple(),
                new Heal(),
                new Pull(),
                new Rejuvenating(),
                new Repulse(),
                new Toss(),
                new Vortex(),

                // Passive
                new Forceful(),
                new Gravity(),
                new Knockup(),
                new Life(),
                new Lifesteal(),
                new Lightning(),
                new Rapid(),
                new Regenerative(),
                new ShadowShift(),

                // Potion Absorb
                new Adrenaline(),
                new Lively(),
                new Phantom(),

                // Potion Reflect
                new Brilliance(),
                new Cursed(),
                new Demoralizing(),
                new Frost(),
                new Molten(),
                new Toxic(),

                // Potion Inflict
                new Blind(),
                new Poison(),
                new Slow(),
                new Weakness(),
                new Wither(),

                // Potion Steal
                new Berserking(),
                new Distortion(),
                new Fervor(),

                // Potion Passive
                new Gears(),
                new Jump(),
                new NightVision(),

                // Traps
                new BarrierTrap(),
                new BombTrap(),
                new FireTrap(),
                new IceTrap(),
                new LaunchTrap(),
                new LightningTrap(),
                new PoisonTrap(),
                new SlowTrap(),
                new WeaknessTrap(),
                new WebTrap()
        );
    }
}
