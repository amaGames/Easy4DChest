package com.amagames.easy4dchest.scheduler;

import com.amagames.easy4dchest.Easy4DChest;
import com.amagames.easy4dchest.chest.PublishedChest;
import com.amagames.easy4dchest.config.ResourceLib;
import com.amagames.easy4dchest.core.ConfigHandler;
import com.amagames.easy4dchest.core.DataHandler;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.concurrent.Callable;

public class BackupTask {

	public synchronized static void saveChestData() {
		ResourceLib.set(".PublishChest", null, ConfigHandler.CHEST_CONFIG);
		if (!(DataHandler.ID_MAP.values().isEmpty())) {
			for (PublishedChest chest : DataHandler.ID_MAP.values()) {
				ResourceLib.set(".PublishChest." + chest.getName() + ".id", chest.getName(), ConfigHandler.CHEST_CONFIG);
				Inventory inventory = chest.getInventory();
				for (int a = 0; a < 54; a = a + 1) {
					ResourceLib.set(".PublishChest." + chest.getName() + "." + a, inventory.getItem(a), ConfigHandler.CHEST_CONFIG);
				}
			}
		}
		ConfigHandler.CHEST_CONFIG.reloadConfiguration();
		ConfigHandler.CHEST_CONFIG.saveConfiguration();
	}

	public static void startBackUpTask() {
		Bukkit.getScheduler().runTaskTimerAsynchronously(Easy4DChest.getInstance(), new Runnable() {

			@Override
			public void run() {
				BackupTask.saveChestData();
				Bukkit.broadcastMessage(ChatColor.BLUE + "Save the chest data was successfull!");
			}

		}, 0L, 6000L);
	}

}
