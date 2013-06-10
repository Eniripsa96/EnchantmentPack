package com.rit.sucy;

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
        if (!getConfig().contains("Toss")) {
            ArrayList<String> list = new ArrayList<String>();
            list.add("Toss");
            getConfig().set("Toss", list);
        }

        for (Player player : getServer().getOnlinePlayers()) {
            NightVisionEnchantment.initializePlayer(player);
            JumpEnchantment.initializePlayer(player);
        }

        getCommand("applyenchant").setExecutor(this);
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
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command is for players only!");
        }
        else if (args.length == 0) {
            sender.sendMessage("Invalid number of arguments: expected at least 1");
        }
        else {
            String name = args[0];
            int difference = 0;
            int level = 1;
            try {
                level = Integer.parseInt(args[args.length - 1]);
                difference = 1;
            }
            catch (Exception e) {
                // Level is not provided
            }

            for (int i = 1; i < args.length - difference; i++) name += " " + args[i];
            Player player = (Player)sender;
            CustomEnchantment enchantment = EnchantmentAPI.getEnchantment(name);
            if (enchantment == null) {
                sender.sendMessage(name + " is not a registered enchantment!");
            }
            else {
                        player.setItemInHand(enchantment.addToItem(player.getItemInHand(), level));
                player.sendMessage("Enchantment has been applied.");
            }
        }

        return true;
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
