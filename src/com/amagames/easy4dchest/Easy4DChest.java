package com.amagames.easy4dchest;

import com.amagames.easy4dchest.chest.PublishedChest;
import com.amagames.easy4dchest.core.ConfigHandler;
import com.amagames.easy4dchest.core.DataHandler;
import com.amagames.easy4dchest.listeners.PlayerListener;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import javax.xml.crypto.Data;
import java.util.concurrent.ConcurrentHashMap;

public class Easy4DChest extends JavaPlugin {

	private static JavaPlugin easy4DChest;
	public static final String LOGO_PREFIX = "§9[§24DChest§9]";
	public static final String PREFIX = ChatColor.YELLOW + "[" + ChatColor.DARK_AQUA + "4DChest" + ChatColor.YELLOW + "] " + ChatColor.GREEN;

	@Override
	public void onEnable() {
		easy4DChest = this;
		Bukkit.getConsoleSender().sendMessage(PREFIX + "Easy4DChest plugin has been enabled.\nProvided by amaGames.");
		ConfigHandler.onEnable();
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
	}

	@Override
	public void onDisable() {
		ConfigHandler.onDisable();
		Bukkit.getConsoleSender().sendMessage(PREFIX + "Easy4DChest plugin has been disabled.\nThank you for using this.");
	}

	//Syntax: /easy4d create <id>
	// : /easy4d remove <id>
	// : /easy4d list

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("easy4d") && sender.isOp()) {
			if (args.length >= 1) {
				switch (args[0].toLowerCase()) {
					case "list":
						if (DataHandler.ID_MAP.isEmpty()) {
							sender.sendMessage(PREFIX + ChatColor.RED + "Could not find any 4D-Chest.");
							return true;
						}
						StringBuilder stringBuilder = new StringBuilder();
						for (PublishedChest chest : DataHandler.ID_MAP.values()) {
							stringBuilder.append(chest.getName() + ", ");
						}
						stringBuilder.setLength(stringBuilder.length() - 2);
						sender.sendMessage(PREFIX + "The list of 4D-Chest.\n" + stringBuilder);
						return true;
					case "create":
						if (args.length >= 2) {
							if (DataHandler.ID_MAP.containsKey(args[1])) {
								sender.sendMessage(PREFIX + ChatColor.RED + "That name's 4D-Chest has been already created.");
								return true;
							}
							DataHandler.ID_MAP.put(args[1], new PublishedChest(args[1], Bukkit.createInventory(null, 54, ChatColor.BLUE + "4D-Chest <id: " + args[1] + ">")));
							sender.sendMessage(PREFIX + "You have created 4D-Chest named " + args[1] + ".");
							return true;
						}
					case "remove":
						if (args.length >= 2) {
							if (DataHandler.ID_MAP.containsKey(args[1])) {
								sender.sendMessage(PREFIX + "You have delete 4D-Chest.");
								DataHandler.ID_MAP.remove(args[1]);
								return true;
							}
							sender.sendMessage(PREFIX + ChatColor.RED + "Could not find 4D-Chest named " + args[1] + ".");
							return true;
						}
					default:
						break;
				}
			}
			this.sendHelp(sender);
			return true;
		}
		sender.sendMessage(PREFIX + ChatColor.RED + "You are not allowed to execute this command.");
		return true;
	}

	public void sendHelp(CommandSender sender) {
		sender.sendMessage(PREFIX + ChatColor.RED + "You can look command helps.\n/easy4d create <id> : Create a new 4D-Chest.\n/easy4d remove <id> : Remove the 4D-Chest.\n/easy4d list : Show the list of 4D-Chest.");
	}

	public static JavaPlugin getInstance() {
		return easy4DChest;
	}

}
