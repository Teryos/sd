package org.teryos.ckits.command;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.teryos.ckits.misc.KitInventory;

public class CompassCommand implements CommandExecutor{
	
	public boolean onCommand(CommandSender sender, Command cmd, String name, String args[]){
		Player player = (Player)sender;
		player.openInventory(KitInventory.inv);
		player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1, 1);
		return true;
	}

}
