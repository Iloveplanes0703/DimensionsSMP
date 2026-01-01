package com.example.dimensiontp;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class DimensionTP extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("DimensionTP enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("DimensionTP disabled.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            // Command must be run by a player
            if (!(sender instanceof Player)) {
                sender.sendMessage("This command can only be used in-game.");
                return true;
            }

            Player player = (Player) sender;

            // Must provide an argument
            if (args.length != 1) {
                player.sendMessage("Usage: /dimension <overworld|nether|end>");
                return true;
            }

            String dim = args[0].toLowerCase();
            World targetWorld = null;

            switch (dim) {
                case "overworld":
                case "world":
                    targetWorld = Bukkit.getWorld("world");
                    break;

                case "nether":
                    targetWorld = Bukkit.getWorld("world_nether");
                    break;

                case "end":
                case "the_end":
                    targetWorld = Bukkit.getWorld("world_the_end");
                    break;

                default:
                    player.sendMessage("Invalid dimension. Use overworld, nether, or end.");
                    return true;
            }

            if (targetWorld == null) {
                player.sendMessage("Target world is not loaded on this server.");
                return true;
            }

            player.teleport(targetWorld.getSpawnLocation());
            player.sendMessage("Teleported to: " + targetWorld.getName());
            return true;
    }
}