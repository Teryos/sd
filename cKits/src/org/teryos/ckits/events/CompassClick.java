package org.teryos.ckits.events;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.teryos.ckits.misc.KitInventory;
import org.teryos.ckits.misc.WarpInventory;

public class CompassClick implements Listener{
	
	@EventHandler
	public void onClick(PlayerInteractEvent e){
		Player player = e.getPlayer();
		if(e.getPlayer().getItemInHand().getType() == Material.COMPASS){
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1, 1);
			e.setCancelled(true);
			e.getPlayer().openInventory(KitInventory.inv);
		}
		if(e.getPlayer().getItemInHand().getType() == Material.NETHER_STAR){
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1, 1);
			e.setCancelled(true);
			e.getPlayer().openInventory(WarpInventory.inv);
		}
	}

}
