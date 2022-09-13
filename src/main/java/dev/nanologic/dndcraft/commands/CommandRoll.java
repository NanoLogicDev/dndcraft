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
        player.sendMessage(ChatColor.GRAY + "(" + ChatColor.GREEN + ChatColor.BOLD + "Roll" + ChatColor.RESET + ChatColor.GRAY + ")" + ChatColor.WHITE + " D" + max + ChatColor.GRAY + " -> " + ChatColor.WHITE + roll);
    }

    private void sendRolls(Player player, int[] diceArray) {

    }
    private void sendInvalidFormat(Player player) {
        player.sendMessage(ChatColor.GRAY + "(" + ChatColor.RED + ChatColor.BOLD + "Error" + ChatColor.RESET + ChatColor.GRAY + ") " + ChatColor.RED + "Invalid Format!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            String rawDiceString = args[0].toLowerCase();

            if (rawDiceString.contains("d")) {
                if (rawDiceString.startsWith("d")) {
                    try {
                        int diceValue = Integer.parseInt(args[0].replace("d", ""));
                        if (diceValue <= 100) {
                            sendRoll(player, diceValue);
                        } else {
                            sendInvalidFormat(player);
                        }
                    } catch (NumberFormatException ex) {
                        sendInvalidFormat(player);
                    }
                } else {
                    try {
                        int amountOfDice = Integer.parseInt(args[0].split("d")[0]);
                        int diceValue = Integer.parseInt(args[0].split("d")[1]);

                        if (amountOfDice <= 10 && diceValue <= 100) {
                            int total = 0;
                            StringBuilder message = new StringBuilder();
                            message.append(ChatColor.GRAY + "(" + ChatColor.GREEN + ChatColor.BOLD + "Roll" + ChatColor.RESET + ChatColor.GRAY + ") " + ChatColor.WHITE + rawDiceString.toUpperCase() + "\n");

                            for (int i = 0; i < amountOfDice; i++) {
                                int roll = randomWithRange(1, diceValue);
                                total += roll;
                                message.append(ChatColor.WHITE + String.valueOf(i + 1) + ". D" + diceValue + ChatColor.GRAY + " -> " + ChatColor.WHITE + roll + "\n");
                            }

                            message.append(ChatColor.GOLD + "Total: " + ChatColor.YELLOW + total);

                            player.sendMessage(message.toString());
                        } else {
                            sendInvalidFormat(player);
                        }
                    } catch (NumberFormatException ex) {
                        sendInvalidFormat(player);
                    }
                }
            } else {
                sendInvalidFormat(player);
            }

        }

        return true;
    }
}
