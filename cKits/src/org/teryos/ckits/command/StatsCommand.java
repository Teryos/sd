package org.teryos.ckits.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.teryos.ckits.Main;
import org.teryos.ckits.util.Manager;

public class StatsCommand implements CommandExecutor{
	
	public boolean onCommand(CommandSender sender, Command cmd, String name, String args[]){
		Player player = (Player)sender;
		if(args.length < 1){
			player.sendMessage("§7-=[§bYour stats§7]=-");
			player.sendMessage("§bKills§7:§e "+Manager.getKills(player));
			player.sendMessage("§bDeaths§7:§e "+Manager.getDeaths(player));
			player.sendMessage("§bK/D Ratio§7:§e "+Manager.getKills(player) / Manager.getDeaths(player));
			player.sendMessage("§bCurrent killstreak§7:§e "+Manager.getKillstreak(player));
			player.sendMessage("§bHighest killstreak§7:§e "+Manager.getHighestKillstreak(player));
		} else {
			Player p2 = Bukkit.getServer().getPlayer(args[0]);
			if(p2 == null){
				player.sendMessage(Main.prefix+"§cThat player does not exist!");
				return true;
			}else{
				player.sendMessage("§7-=[§b"+p2.getName()+"'s stats§7]=-");
				player.sendMessage("§bKills§7:§e "+Manager.getKills(p2));
				player.sendMessage("§bDeaths§7:§e "+Manager.getDeaths(p2));
				player.sendMessage("§bK/D Ratio§7:§e "+((double)Manager.getKills(p2) / (double)Manager.getDeaths(p2)));
				player.sendMessage("§bCurrent killstreak§7:§e "+Manager.getKillstreak(p2));
				player.sendMessage("§bHighest killstreak§7:§e "+Manager.getHighestKillstreak(p2));
			}
		}
		return true;
	}

}
