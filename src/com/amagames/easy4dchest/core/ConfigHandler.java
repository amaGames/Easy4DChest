package com.amagames.easy4dchest.core;

import com.amagames.easy4dchest.chest.PublishedChest;
import com.amagames.easy4dchest.config.ResourceLib;
import com.amagames.easy4dchest.config.RewriteConfig;
import com.amagames.easy4dchest.scheduler.BackupTask;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ConfigHandler {

	public static final RewriteConfig CHEST_CONFIG = new RewriteConfig();

	public static void onEnable() {
		CHEST_CONFIG.initializeInstance("ChestData.yml", "ChestData");
		CHEST_CONFIG.reloadConfiguration();
		CHEST_CONFIG.saveConfiguration();
		ConfigurationSection section = ResourceLib.getConfigurationSection(".PublishChest", CHEST_CONFIG);
		if (section != null) {
			for (String chestId : section.getKeys(false)) {
				String id = chestId;
				Inventory inventory = Bukkit.createInventory(null, 54, ChatColor.BLUE + "4D-Chest <id: " + id + ">");
				for (int a = 0; a < 54; a = a + 1) {
					inventory.setItem(a, (ItemStack) ResourceLib.get(".PublishChest." + id + "." + a, CHEST_CONFIG, new ItemStack(Material.AIR, 1)));
				}
				DataHandler.ID_MAP.put(id, new PublishedChest(id, inventory));
			}
		}
		BackupTask.startBackUpTask();
	}

	public static void onDisable() {
		BackupTask.saveChestData();
		CHEST_CONFIG.saveConfiguration();
	}

}
