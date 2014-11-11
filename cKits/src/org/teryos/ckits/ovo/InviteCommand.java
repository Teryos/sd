package org.teryos.ckits.ovo;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.teryos.ckits.misc.KitInventory;

public class InviteCommand implements CommandExecutor{
	
	public InviteCommand(){}
	
	public boolean onCommand(CommandSender sender, Command cmd, String name, String args[]){
		Player player = (Player)sender;
		if(name == "SF") player.sendMessage("");
		return true;
	}
	
}
