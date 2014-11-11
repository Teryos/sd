package org.teryos.ckits;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.teryos.ckits.command.CompassCommand;
import org.teryos.ckits.command.SpawnCommand;
import org.teryos.ckits.command.StatsCommand;
import org.teryos.ckits.events.CompassClick;
import org.teryos.ckits.events.DeathEvent;
import org.teryos.ckits.events.InventoryClick;
import org.teryos.ckits.events.KitEvents;
import org.teryos.ckits.events.SoupEat;
import org.teryos.ckits.misc.KitInventory;
import org.teryos.ckits.misc.WarpInventory;
import org.teryos.ckits.util.Manager;

public class Main extends JavaPlugin{
	
	public static String prefix = "§7[§bcKits§7]§e ";
	
	public void onEnable(){
		
		KitInventory ki = new KitInventory(this);
		ki.init();
		WarpInventory wi = new WarpInventory(this);
		wi.init();
		Manager manager = new Manager(this);
		
		this.getCommand("stats").setExecutor(new StatsCommand());
		this.getCommand("compass").setExecutor(new CompassCommand());
		this.getCommand("kit").setExecutor(new CompassCommand());
		this.getCommand("kits").setExecutor(new CompassCommand());
		
		Bukkit.getPluginManager().registerEvents(new InventoryClick(this), this);
		Bukkit.getPluginManager().registerEvents(new CompassClick(), this);
		Bukkit.getPluginManager().registerEvents(new DeathEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new KitEvents(this), this);
		Bukkit.getPluginManager().registerEvents(new SoupEat(this), this);
		
	}
	
	public void onDisable(){
		saveConfig();
	}

}
