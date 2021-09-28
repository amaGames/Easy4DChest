package com.amagames.easy4dchest.chest;

import org.bukkit.inventory.Inventory;

public class PublishedChest {

	private String chestId;
	private Inventory inventory;

	public PublishedChest(String chestId, Inventory inventory) {
		this.chestId = chestId;
		this.inventory = inventory;
	}

	public Inventory getInventory() {
		return this.inventory;
	}

	public String getName() {
		return this.chestId;
	}

}
