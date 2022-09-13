package dev.nanologic.dndcraft.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRoll implements CommandExecutor {
    private int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    private void sendRoll(Player player, int max) {
        int roll = randomWithRange(1, max);
        player.sendMessage(ChatColor.GRAY + "(" + ChatColor.BOLD + ChatColor.GREEN + "Roll" + ChatColor.RESET + ChatColor.GRAY + ")" + ChatColor.WHITE + " D" + max + ChatColor.GRAY + " -> " + ChatColor.WHITE + roll);
    }

    private void sendInvalidFormat(Player player) {
        player.sendMessage(ChatColor.GRAY + "(" + ChatColor.BOLD + ChatColor.RED + "Error" + ChatColor.RESET + ChatColor.GRAY + ") " + ChatColor.RED + "Invalid Format!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            String rawDiceString = args[0].toLowerCase();

            if (rawDiceString.startsWith("d")) {
                try {
                    int diceValue = Integer.parseInt(args[0].replace("d", ""));
                    sendRoll(player, diceValue);
                } catch (NumberFormatException ex) {
                    sendInvalidFormat(player);
                }
            } else {
                sendInvalidFormat(player);
            }
        }

        return true;
    }
}
