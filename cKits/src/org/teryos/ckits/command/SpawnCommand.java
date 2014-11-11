package org.teryos.ckits.command;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.teryos.ckits.misc.KitInventory;

public class SpawnCommand implements CommandExecutor{
	
	public SpawnCommand(){}
	
	public boolean onCommand(CommandSender sender, Command cmd, String name, String args[]){
		Player player = (Player)sender;
		player.getInventory().clear();						
		player.getInventory().setHelmet(new ItemStack(Material.AIR));
		player.getInventory().setChestplate(new ItemStack(Material.AIR));
		player.getInventory().setLeggings(new ItemStack(Material.AIR));
		player.getInventory().setBoots(new ItemStack(Material.AIR));
		player.performCommand("espawn");
		
		player.getInventory().setItem(0, KitInventory.star);
		player.getInventory().setItem(4, KitInventory.kits);
		
		return true;
	}
	
}
