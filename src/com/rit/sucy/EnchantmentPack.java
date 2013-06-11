package com.rit.sucy;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import java.util.ArrayList;

public class EnchantmentPack extends EnchantPlugin implements CommandExecutor {

    TrapTask task;

    @Override
    public void onEnable() {
        task = new TrapTask();
        task.runTaskTimer(this, 0, 2);
        new EPListener(this);

        saveDefaultConfig();
        reloadConfig();

        for (Player player : getServer().getOnlinePlayers()) {
            NightVisionEnchantment.initializePlayer(player);
            JumpEnchantment.initializePlayer(player);
        }
    }

    @Override
    public void onDisable() {
        task.cancel();
        for (Trap trap : Trap.traps.values()) trap.remove();
        NightVisionEnchantment.clearPlayers();
        JumpEnchantment.clearPlayers();
        HandlerList.unregisterAll(this);
    }

    @Override
    public void registerEnchantments() {
        EnchantmentAPI.registerCustomEnchantment(new LifestealEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new LightningEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new ReflectEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new FireTrapEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new IceTrapEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new PoisonTrapEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new PoisonEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new SlowEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new FervorEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new PullEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new RepulseEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new DashEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new KnockupEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new JumpEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new NightVisionEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new TossEnchantment(this));
        EnchantmentAPI.registerCustomEnchantment(new VortexEnchantment(this));
    }

    public static void main(String[] args){}
}
