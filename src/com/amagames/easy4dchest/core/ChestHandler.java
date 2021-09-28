package com.amagames.easy4dchest.core;

import com.amagames.easy4dchest.Easy4DChest;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;

public class ChestHandler {

	public static boolean is4DChest(Sign sign) {
		return isCompletedSign(sign.getLines());
	}

	public static boolean isCompletedSign(String[] lines) {
		if (lines[0].equalsIgnoreCase(Easy4DChest.LOGO_PREFIX) && isExistId(lines[2])) {
			return true;
		}
		return false;
	}

	public static boolean isExistId(String chestId) {
		return DataHandler.ID_MAP.containsKey(chestId);
	}

}
