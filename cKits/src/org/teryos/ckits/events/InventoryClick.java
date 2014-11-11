package org.teryos.ckits.events;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.teryos.ckits.Main;
import org.teryos.ckits.misc.KitInventory;
import org.teryos.ckits.misc.WarpInventory;
import org.teryos.ckits.util.Manager;

public class InventoryClick implements Listener{
	
	Main plugin;
	
	public InventoryClick(Main instance){
		plugin = instance;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e){
		Inventory inv = e.getInventory();
		Player player = (Player) e.getWhoClicked();
		ItemStack item = e.getCurrentItem();
		
		//KIT MENU
		if(inv.getName() == KitInventory.inv.getName()){
			e.setCancelled(true);
			if(item == null || item.getType() == Material.AIR) return;
			String name = item.getItemMeta().getDisplayName().replace("§a§l", "");
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1, 1);
			
			if(name.contains("PvP")){
				Manager.setKit(player, "pvp");
				player.closeInventory();
			}
			if(name.contains("Archer")){
				Manager.setKit(player, "archer");
				player.closeInventory();
			}
			if(name.contains("Cheetah")){
				if(player.hasPermission("ckits.grass")){
					Manager.setKit(player, "cheetah");
					player.closeInventory();
				}else{
					player.sendMessage(Main.prefix+"§cYou don't have permission for that kit.");
				}
			}
			if(name.contains("Frog")){
				if(player.hasPermission("ckits.grass")){
					Manager.setKit(player, "frog");
					player.closeInventory();
				}else{
					player.sendMessage(Main.prefix+"§cYou don't have permission for that kit.");
				}
			}
			if(name.contains("Poseidon")){
				if(player.hasPermission("ckits.grass")){
					Manager.setKit(player, "poseidon");
					player.closeInventory();
				}else{
					player.sendMessage(Main.prefix+"§cYou don't have permission for that kit.");
				}
			}
			if(name.contains("Hades")){
				if(player.hasPermission("ckits.stone")){
					Manager.setKit(player, "hades");
					player.closeInventory();
				}else{
					player.sendMessage(Main.prefix+"§cYou don't have permission for that kit.");
				}
			}
			if(name.contains("Snowgolem")){
				if(player.hasPermission("ckits.stone")){
					Manager.setKit(player, "snowgolem");
					player.closeInventory();
				}else{
					player.sendMessage(Main.prefix+"§cYou don't have permission for that kit.");
				}
			}
			if(name.contains("Fisherman")){
				if(player.hasPermission("ckits.stone")){
					Manager.setKit(player, "fisherman");
					player.closeInventory();
				}else{
					player.sendMessage(Main.prefix+"§cYou don't have permission for that kit.");
				}
			}
			if(name.contains("Croucher")){
				if(player.hasPermission("ckits.gold")){
					Manager.setKit(player, "croucher");
					player.closeInventory();
				}else{
					player.sendMessage(Main.prefix+"§cYou don't have permission for that kit.");
				}
			}
			if(name.contains("Troller")){
				if(player.hasPermission("ckits.gold")){
					Manager.setKit(player, "troller");
					player.closeInventory();
				}else{
					player.sendMessage(Main.prefix+"§cYou don't have permission for that kit.");
				}
			}
			if(name.contains("Netherlord")){
				if(player.hasPermission("ckits.gold")){
					Manager.setKit(player, "netherlord");
					player.closeInventory();
				}else{
					player.sendMessage(Main.prefix+"§cYou don't have permission for that kit.");
				}
			}
		}
		
		//WARP MENU
		if(inv.getName() == WarpInventory.inv.getName()){
			e.setCancelled(true);
			if(item == null || item.getType() == Material.AIR) return;
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1, 1);
			String name = item.getItemMeta().getDisplayName();
			if(name.contains("FFA")){
				player.closeInventory();
				player.performCommand("warp ffa");
			}
			if(name.contains("Spawn")){
				player.closeInventory();
				player.performCommand("spawn");
			}
			if(name.contains("Redrover")){
				player.closeInventory();
				player.performCommand("warp redrover");
			}
			if(name.contains("Anvil")){
				player.closeInventory();
				player.performCommand("warp anvil");
			}
			if(name.contains("Rules")){
				player.closeInventory();
				player.performCommand("warp rules");
			}
		}
	}

}
