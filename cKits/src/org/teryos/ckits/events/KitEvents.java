package org.teryos.ckits.events;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.teryos.ckits.Main;
import org.teryos.ckits.util.ItemList;
import org.teryos.ckits.util.Manager;

public class KitEvents implements Listener{
	
	Main plugin;
	
	ArrayList<Player>cooldown = new ArrayList<>();
	ArrayList<Player>abilityTime = new ArrayList<>();
	
	public KitEvents(Main instance){
		plugin = instance;
	}
	
	HashMap<Player,Projectile>proj=new HashMap<>();
	
	//CHEETAH KIT
	@EventHandler
	public void cheetah(PlayerInteractEvent e){
		if(Manager.getKit(e.getPlayer()) == "cheetah"){
			if(e.getPlayer().getItemInHand().getType() == Material.SUGAR){
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 2));
				if(e.getPlayer().getItemInHand().getAmount() > 1){
					e.getPlayer().getItemInHand().setAmount(e.getPlayer().getItemInHand().getAmount()-1);
				}else{
					e.getPlayer().setItemInHand(new ItemStack(Material.AIR));
				}
			}
		}
	}
	
	//FROG KIT
	@EventHandler
	public void frog(PlayerInteractEvent e){
		if(Manager.getKit(e.getPlayer()) == "frog"){
			if(e.getPlayer().getItemInHand().getType() == Material.EMERALD){
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100, 2));
				if(e.getPlayer().getItemInHand().getAmount() > 1){
					e.getPlayer().getItemInHand().setAmount(e.getPlayer().getItemInHand().getAmount()-1);
				}else{
					e.getPlayer().setItemInHand(new ItemStack(Material.AIR));
				}
			}
		}
	}
	
	//POSEIDON KIT
	@EventHandler
	public void posiedon(PlayerMoveEvent e){
		if(Manager.getKit(e.getPlayer()) == "poseidon"){
			if(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.SELF).getType() == Material.STATIONARY_WATER&& e.getPlayer().getLocation().getBlock().getRelative(BlockFace.SELF).isLiquid()){
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20, 0));
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 20, 2));
			}
		}
	}
	
	//HADES KIT && FEED
	@EventHandler
	public void hades(PlayerMoveEvent e){
		if(Manager.getKit(e.getPlayer()) == "hades"){
			if(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.SELF).isLiquid() && e.getPlayer().getLocation().getBlock().getRelative(BlockFace.SELF).getType() == Material.STATIONARY_LAVA){
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20, 0));
			}
		}
		if(e.getPlayer().getFoodLevel() < 20) e.getPlayer().setFoodLevel(20);
	}
	@EventHandler
	public void hades(EntityDamageEvent e){
		if(!(e.getEntity() instanceof Player)) return;
		Player player = (Player) e.getEntity();
		if(Manager.getKit(player) == "hades"){
			if(e.getCause() == DamageCause.LAVA)
				e.setCancelled(true);
			if(e.getCause() == DamageCause.FIRE)
				e.setCancelled(true);
			if(e.getCause() == DamageCause.FIRE_TICK)
				e.setCancelled(true);
		}
	}
	
	//SNOWGOLM KIT
	@EventHandler
	public void snowgolem(ProjectileLaunchEvent e){
		if(Manager.getKit((Player)e.getEntity().getShooter()) == "snowgolem"){
			proj.put((Player)e.getEntity().getShooter(), e.getEntity());
		}
	}
	@EventHandler
	public void snowgolem(EntityDamageByEntityEvent e){
		HashMap<Player,Player>swish=new HashMap<>();
		if(e.getEntity() instanceof Player){
		if(e.getCause() == DamageCause.PROJECTILE){
			if(e.getDamager().getType() == EntityType.SNOWBALL){
				if(Manager.getKit((Player) ((Projectile)e.getDamager()).getShooter()) == "snowgolem"){
			if(proj.containsKey(((Projectile)e.getDamager()).getShooter())){
				swish.put((Player)e.getEntity(), (Player)((Projectile)e.getDamager()).getShooter());
				Location f = e.getEntity().getLocation();
				Location c = ((Player)((Projectile)e.getDamager()).getShooter()).getLocation();
				
				e.getEntity().teleport(c);
				((Player)((Projectile)e.getDamager()).getShooter()).teleport(f);
				
				((Player)e.getEntity()).sendMessage(Main.prefix+"You switched places with "+((Player)((Projectile)e.getDamager()).getShooter()).getName()+"§e.");
				((Player)e.getDamager()).sendMessage(Main.prefix+"You switched places with "+((Player)e.getEntity()).getName()+"§e.");
			}
			}
			}
		}
		}
	}
	
	//FISHERMAN KIT
		@EventHandler
		public void fisherman(PlayerFishEvent e){
			if(Manager.getKit(e.getPlayer()) == "fisherman"){
				if(e.getCaught() != null){
					e.getCaught().teleport(e.getPlayer());
				}
			}
		}
		
		//TROLLER KIT
		@EventHandler
		public void fisherman(PlayerInteractEntityEvent e){
			if(Manager.getKit(e.getPlayer()) == "troller"){
				if(e.getRightClicked() instanceof Player){
				final Player p1 = e.getPlayer();
				final Player p2 = (Player) e.getRightClicked();
					if(!cooldown.contains(p1)){
						final ItemStack i = p2.getItemInHand();
						final Material is = i.getType();
						p2.getItemInHand().setType(Material.BROWN_MUSHROOM);
						cooldown.add(p1);
						p1.sendMessage(Main.prefix+"You trolled §s"+p2.getName()+"§e!");
						this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
					          public void run() {
					        	 p2.setItemInHand(new ItemStack(is)); 
					          }
					       }, 100);
						this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
					          public void run() {
					        		cooldown.remove(p1);
									p2.sendMessage(Main.prefix+"You can use your ability again!");
					          }
					       }, 500);
					}else{
						p2.sendMessage(Main.prefix+"§cYou can't use your ability yet!");
					}
				}
			}
		}
		
		//NetherLord KIT
		@EventHandler
		public void netherLord(final PlayerInteractEvent e){
			if(Manager.getKit(e.getPlayer()) == "netherlord"){
				if(e.getAction() == Action.RIGHT_CLICK_AIR){
				if(!cooldown.contains(e.getPlayer())) {
					Projectile bullet = e.getPlayer().launchProjectile(Snowball.class);
					proj.put(e.getPlayer(), bullet);
					e.getPlayer().sendMessage(Main.prefix+"Enabling cooldown.");
					cooldown.add(e.getPlayer());
					this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
				          public void run() {
				        	 cooldown.remove(e.getPlayer());
								e.getPlayer().sendMessage(Main.prefix+"You can use your ability again!");
				          }
				       }, 300);
				}else{
					e.getPlayer().sendMessage(Main.prefix+"§cYou can't use your ability yet!");
				}
				}
			}
		}
		@EventHandler
		public void netherLord(EntityDamageByEntityEvent e){
			if(e.getCause() == DamageCause.PROJECTILE){
				if(Manager.getKit((Player) ((Projectile)e.getDamager()).getShooter()) == "netherlord"){
					if(e.getDamager().getType() == EntityType.SNOWBALL){
						((Player) e.getEntity()).damage(2.0);
						((Player) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 150, 1));
						((Player) e.getEntity()).sendMessage(Main.prefix + "You've been blinded by a NetherLord.");
					}
				}
			}
		}
		
		//CROUCHER KIT
		@EventHandler
		public void croucher(final PlayerToggleSneakEvent e){
			if(Manager.getKit(e.getPlayer()) == "croucher"){
				if(!e.getPlayer().isSneaking()){
					if(!cooldown.contains(e.getPlayer())){
						e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 500, 1));
						e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 500, 2));
						e.getPlayer().getInventory().setHelmet(new ItemStack(Material.AIR));
						e.getPlayer().getInventory().setChestplate(new ItemStack(Material.AIR));
						e.getPlayer().getInventory().setLeggings(new ItemStack(Material.AIR));
						e.getPlayer().getInventory().setBoots(new ItemStack(Material.AIR));
						if(!abilityTime.contains(e.getPlayer())){
							abilityTime.add(e.getPlayer());
						this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
					          public void run() {
					        	  abilityTime.remove(e.getPlayer());
					        	  e.getPlayer().sendMessage(Main.prefix + "Your ability time is now over. Cooldown initiated.");
									e.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
									e.getPlayer().removePotionEffect(PotionEffectType.SPEED);
									 Manager.giveKit(e.getPlayer(), ItemList.chelmet, ItemList.cchestplate, ItemList.cleggings, ItemList.cboots, ItemList.isword, null,1);
					        	  cooldown.add(e.getPlayer());
					          }
					       }, 500);
							this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
						          public void run() {
						        	 cooldown.remove(e.getPlayer());
						        	 e.getPlayer().sendMessage(Main.prefix + "You can use your ability again.");
						          }
						       }, 800);
						}
					}else{
			        	  if(!e.getPlayer().hasPotionEffect(PotionEffectType.INVISIBILITY)){
								e.getPlayer().sendMessage(Main.prefix + "§cYou cannot use your ability yet!");
			        	  }
					}
				}else{
					if(e.getPlayer().getInventory().getHelmet() == null){
						e.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
						e.getPlayer().removePotionEffect(PotionEffectType.SPEED);
						 Manager.giveKit(e.getPlayer(), ItemList.chelmet, ItemList.cchestplate, ItemList.cleggings, ItemList.cboots, ItemList.isword, null,1);
					}
				}
			}
		}
}
