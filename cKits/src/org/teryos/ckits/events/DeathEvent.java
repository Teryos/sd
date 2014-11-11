package org.teryos.ckits.events;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.util.Vector;
import org.teryos.ckits.Main;
import org.teryos.ckits.misc.KitInventory;
import org.teryos.ckits.util.Manager;

public class DeathEvent implements Listener{
	
	Main plugin;
	
	HashMap<Player,Boolean>inAir = new HashMap<>();
	
	public DeathEvent(Main instance){
		plugin = instance;
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e){
		e.setDeathMessage("");
		e.getDrops().clear();
		plugin.getConfig().set(e.getEntity().getName()+".killstreak", 0);
		plugin.getConfig().set(e.getEntity().getName()+".deaths", plugin.getConfig().getInt(e.getEntity().getName()+".deaths")+1);
		if(e.getEntity().getKiller() instanceof Player){
			Player player = e.getEntity();
			Player killer = player.getKiller();
			
			plugin.getConfig().set(killer.getName()+".kills", plugin.getConfig().getInt(killer.getName()+".kills")+1);
			plugin.getConfig().set(killer.getName()+".killstreak", plugin.getConfig().getInt(killer.getName()+".killstreak")+1);
			
			int kills = plugin.getConfig().getInt(killer.getName()+".kills");
			int killstreak = plugin.getConfig().getInt(killer.getName()+".killstreak");
			
			if(plugin.getConfig().getInt(killer.getName()+".killstreak") > plugin.getConfig().getInt(killer.getName()+".highest-killstreak"))
				plugin.getConfig().set(killer.getName()+".highest-killstreak",plugin.getConfig().getInt(killer.getName()+".killstreak"));
			
			killer.sendMessage(Main.prefix +"You killed §b"+player.getName()+"§e!");
			player.sendMessage(Main.prefix +"You got killed by §b"+killer.getName()+"§e!");
			
			if(killstreak == 5 || killstreak == 10 || killstreak == 15 || killstreak == 20 || killstreak == 25 || killstreak == 30){
				Bukkit.getServer().broadcastMessage(Main.prefix + "§b" +killer.getName()+"§e has a killstreakt of §b"+killstreak+"§e!");
			}
		}
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e){
		e.getPlayer().getInventory().clear();
		e.getPlayer().getInventory().setItem(0, KitInventory.kits);
		e.getPlayer().getInventory().setItem(4, KitInventory.star);
		Manager.setKit(e.getPlayer(), null);
	}
	
	@EventHandler
	public void onRespawn(PlayerMoveEvent e){
		Player player = e.getPlayer();
		Block block = e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN);
		
		if(block.getType() == Material.SPONGE){
			if(block.getRelative(BlockFace.DOWN).getType() == Material.IRON_BLOCK){
				player.setVelocity(new Vector(0,2,0));
				inAir.put(player, true);
			}
			if(block.getRelative(BlockFace.DOWN).getType() == Material.GOLD_BLOCK){
				player.setVelocity(new Vector(0,4,0));
				inAir.put(player, true);
			}
			if(block.getRelative(BlockFace.DOWN).getType() == Material.DIAMOND_BLOCK){
				player.setVelocity(new Vector(0,7,0));
				inAir.put(player, true);
			}
		}
		if(!player.isOnGround()){
			if(inAir.get(player) != null && inAir.get(player) == true){
			if(block.getType() != Material.AIR){
				inAir.put(player, false);
			}
		}
		}
	}
	@EventHandler
	public void onRespawn(EntityDamageEvent e){
		if(e.getEntity() instanceof Player){
			Player player = (Player) e.getEntity();
			if(e.getCause() == DamageCause.FALL){
				if(inAir.get(player) != null){
					if(inAir.get(player) == true){
						e.setCancelled(true);
						inAir.put(player, false);
					}
				}
			}
		}
	}
}
