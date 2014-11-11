package org.teryos.ckits.events;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.teryos.ckits.Main;

public class SoupEat implements Listener{
	
	Main plugin;
	
	public SoupEat(Main instance){
		plugin = instance;
	}
	
	@EventHandler
	public void onEat(PlayerInteractEvent e){
		Player pl = e.getPlayer();
	    if (((e.getAction() != Action.RIGHT_CLICK_AIR) && (e.getAction() != Action.RIGHT_CLICK_BLOCK)) || 
	      (pl.getItemInHand().getType() != Material.MUSHROOM_SOUP)) return;
	    if (pl.getHealth() == 20.0D) {
	    	if(pl.getFoodLevel() < 20.0){
	    		pl.setFoodLevel(pl.getFoodLevel() + 7);
	    		pl.getItemInHand().setType(Material.BOWL);
	    	}
	    } else {
	      pl.setHealth((pl.getHealth() + 7.0D > pl.getMaxHealth()) ? pl.getMaxHealth() : pl.getHealth() + 7.0D);
	      pl.getItemInHand().setType(Material.BOWL);
	    }
	}
	
	@EventHandler
	public void onEat(ItemSpawnEvent e){
		if(e.getEntity().getType() == EntityType.DROPPED_ITEM){
			ItemStack i = e.getEntity().getItemStack();
			if(i.getType() == Material.BOWL) e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onEat(PlayerPickupItemEvent e){
		ItemStack i = e.getItem().getItemStack();
		if(i.getType().toString().toLowerCase().contains("iron")
				|| i.getType().toString().toLowerCase().contains("leather")
				|| i.getType().toString().toLowerCase().contains("diamond")
				||i.getType().toString().toLowerCase().contains("chain")
				|| i.getType().toString().toLowerCase().contains("bowl")
				|| i.getType().toString().toLowerCase().contains("gold")
				|| i.getType().toString().toLowerCase().contains("mushroom"))
			e.setCancelled(true);
	}
	
	@EventHandler
	public void onEat(PlayerDropItemEvent e){
			ItemStack i = e.getItemDrop().getItemStack();
			if(i.getType().toString().toLowerCase().contains("sword") || i.getType().toString().toLowerCase().contains("soup")) e.setCancelled(true);
	}

}
