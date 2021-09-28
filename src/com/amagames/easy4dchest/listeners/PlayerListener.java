package com.amagames.easy4dchest.listeners;

import com.amagames.easy4dchest.Easy4DChest;
import com.amagames.easy4dchest.core.ChestHandler;
import com.amagames.easy4dchest.core.DataHandler;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;
import java.util.List;

public class PlayerListener implements Listener {

	@EventHandler
	public void onPrepareChestEvent(SignChangeEvent event) {
		Player player = event.getPlayer();
		//if (player.isOp() && event.getLine(0).equalsIgnoreCase("<4DChest>") && event.getBlock().getType() == Material.BIRCH_WALL_SIGN) {
		if (event.getLine(0).equalsIgnoreCase("<4DChest>") && isAnySign(event.getBlock().getType())) {
			String chestId = event.getLine(1);
			if (ChestHandler.isExistId(chestId)) {
				player.sendMessage(Easy4DChest.PREFIX + "The connection to 4D-Chest was successfull!");
				event.setLine(0, Easy4DChest.LOGO_PREFIX);
				event.setLine(1, ChatColor.DARK_PURPLE + ChatColor.BOLD.toString() + "Chest ID:");
				event.setLine(2, chestId);
			}
			else {
				player.sendMessage(Easy4DChest.PREFIX + ChatColor.RED + "The specified id was not exist!");
			}
		}
	}

	@EventHandler
	public void onDestroy(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		if (isAnySign(block.getType()) && ChestHandler.is4DChest((Sign) block.getState())) {
			//if (player.isOp()) {
			player.sendMessage(Easy4DChest.PREFIX + "You have destroyed 4D-Chest.");
			//}
			//else {
			//	player.sendMessage(Easy4DChest.PREFIX + ChatColor.RED + "You cannot destroy 4D-Chest.");
			//	event.setCancelled(true);
			//}
		}
	}

	@EventHandler
	public void onOpen(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Block block = event.getClickedBlock();
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK && isAnySign(block.getType())) {
			Sign sign = (Sign) block.getState();
			if (ChestHandler.is4DChest(sign)) {
				player.sendMessage(Easy4DChest.PREFIX + "You opened a synchronized chest!");
				player.openInventory(DataHandler.ID_MAP.get(sign.getLine(2)).getInventory());
			}
		}
	}

	/*
	1.17 only
	 */

	private static final List<Material> MATERIALS = Arrays.asList(new Material[] {
			Material.BIRCH_WALL_SIGN,
			Material.SPRUCE_WALL_SIGN,
			Material.WARPED_WALL_SIGN,
			Material.ACACIA_WALL_SIGN,
			Material.DARK_OAK_WALL_SIGN,
			Material.CRIMSON_WALL_SIGN,
			Material.JUNGLE_WALL_SIGN,
			Material.OAK_WALL_SIGN});

	public boolean isAnySign(Material material) {
		return MATERIALS.contains(material);
	}

}
