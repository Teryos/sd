package org.teryos.ckits.misc;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.teryos.ckits.Main;

public class WarpInventory {

	Main plugin;
	public static Inventory inv;
	String name;
	String prefix;
	public static ItemStack ffa;
	public static ItemStack anvil;
	public static ItemStack redrover;
	public static ItemStack rules;
	public static ItemStack spawn;
	
	public WarpInventory(Main instance){
		plugin = instance;
	}
	
	public void init(){
		name = "§a§l       -=Warp menu=-";
		prefix = "§b";
		inv = Bukkit.createInventory(null, 9, name);
		
		ffa = new ItemStack(Material.DIAMOND_SWORD);
		anvil = new ItemStack(Material.ANVIL);
		redrover = new ItemStack(Material.RED_ROSE);
		rules = new ItemStack(Material.BOOK);
		spawn = new ItemStack(Material.BED);
		
		ItemMeta fm = ffa.getItemMeta();
		ItemMeta am = anvil.getItemMeta();
		ItemMeta rm = redrover.getItemMeta();
		ItemMeta rum = rules.getItemMeta();
		ItemMeta sm = spawn.getItemMeta();
		
		fm.setDisplayName(prefix + "FFA Arena");
		am.setDisplayName(prefix + "Anvil");
		rm.setDisplayName(prefix + "Redrover");
		rum.setDisplayName(prefix + "Rules");
		sm.setDisplayName(prefix + "Spawn");
		
		ffa.setItemMeta(fm);
		anvil.setItemMeta(am);
		redrover.setItemMeta(rm);
		rules.setItemMeta(rum);
		spawn.setItemMeta(sm);
		
		inv.addItem(ffa,anvil,redrover);
		inv.setItem(inv.getSize()-1, spawn);
		inv.setItem(inv.getSize()-2, rules);
	}
	
}