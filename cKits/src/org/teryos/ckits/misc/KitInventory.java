package org.teryos.ckits.misc;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffectType;
import org.teryos.ckits.Main;

public class KitInventory {
	
	Main plugin;
	public static Inventory inv;
	String name;
	String prefix;
	public static ItemStack kits;
	public static ItemStack star;
	
	ItemStack pvp;
	ItemStack archer;
	ItemStack cheetah;
	ItemStack frog;
	ItemStack poseidon;
	ItemStack hades;
	ItemStack snowgolem;
	ItemStack fisherman;
	ItemStack croucher;
	ItemStack troller; /////////////
	ItemStack netherlord; /////////////
	
	public KitInventory(Main instance){
		plugin = instance;
	}
	
	public void init(){
		name = "§5§l        -=Kit menu=-";
		prefix = "§b";
		inv = Bukkit.createInventory(null, 27, name);
		kits = new ItemStack(Material.COMPASS);
		star = new ItemStack(Material.NETHER_STAR);
		ItemMeta kitsM = kits.getItemMeta();
		ItemMeta starM = kits.getItemMeta();
		kitsM.setDisplayName("§b§lKits");
		starM.setDisplayName("§a§lWarps");
		kits.setItemMeta(kitsM);
		star.setItemMeta(starM);
		
		Potion pot = new Potion(8270);
		
		pvp = new ItemStack(Material.DIAMOND_SWORD);
		archer = new ItemStack(Material.BOW);
		cheetah = new ItemStack(Material.SUGAR);
		frog = new ItemStack(Material.EMERALD);
		poseidon = new ItemStack(Material.WATER);
		hades = new ItemStack(Material.LAVA);
		snowgolem = new ItemStack(Material.SNOW_BALL);
		fisherman = new ItemStack(Material.FISHING_ROD);
		croucher = new ItemStack(pot.toItemStack(1));
		troller = new ItemStack(Material.STICK);
		netherlord = new ItemStack(Material.SKULL_ITEM);
		
		ItemMeta pvpM = pvp.getItemMeta();
		ItemMeta archM = archer.getItemMeta();
		ItemMeta cheetahM = cheetah.getItemMeta();
		ItemMeta frogM = frog.getItemMeta();
		ItemMeta poseM = poseidon.getItemMeta();
		ItemMeta hadeM = hades.getItemMeta();
		ItemMeta snowM = snowgolem.getItemMeta();
		ItemMeta fishM = fisherman.getItemMeta();
		ItemMeta crouchM = croucher.getItemMeta();
		ItemMeta trollM = troller.getItemMeta();
		ItemMeta netM = netherlord.getItemMeta();
		
		pvpM.setDisplayName(prefix + "PvP Kit");
		setLore(pvpM, "Slain your enemies with your sword made of diamond.", "Default");
		archM.setDisplayName(prefix + "Archer Kit");
		setLore(archM, "Use your bow to attack enemies far away.", "Default");
		cheetahM.setDisplayName(prefix + "Cheetah Kit");
		setLore(cheetahM, "Your enemies wont be able to hit you when you're so fast.", "Grass");
		frogM.setDisplayName(prefix + "Frog Kit");
		setLore(frogM, "Use your emerald to gain a jump boost effect.", "Grass");
		poseM.setDisplayName(prefix + "Poseidon Kit");
		setLore(poseM, "Be the water god, gain strenght and water breathing when in water.", "Grass");
		hadeM.setDisplayName(prefix + "Hades Kit");
		setLore(hadeM, "Get strenght when in lava. Lava and fire can't hurt you.", "Stone");
		snowM.setDisplayName(prefix + "Snowgolem Kit");
		setLore(snowM, "Throw snowballs at enemies to switch places.","Stone");
		fishM.setDisplayName(prefix + "Fisherman Kit");
		setLore(fishM, "Drag enemies towards you.","Stone");
		crouchM.setDisplayName(prefix + "Croucher Kit");
		setLore(crouchM, "Be invisible and gain a speed boost when sneaking.", "Gold");
		trollM.setDisplayName(prefix + "Troller Kit");
		setLore(trollM, "Click people with your stick to turn their weapon inte a mushrrom.","Gold");
		netM.setDisplayName(prefix + "Netherlord Kit");
		setLore(netM, "Shoot nether stars that blind people with your golden sword..", "Gold");
		
		pvp.setItemMeta(pvpM);
		archer.setItemMeta(archM);
		cheetah.setItemMeta(cheetahM);
		frog.setItemMeta(frogM);
		poseidon.setItemMeta(poseM);
		hades.setItemMeta(hadeM);
		snowgolem.setItemMeta(snowM);
		fisherman.setItemMeta(fishM);
		croucher.setItemMeta(crouchM);
		troller.setItemMeta(trollM);
		netherlord.setItemMeta(netM);
		
		
		inv.addItem(pvp,archer,cheetah,frog,poseidon,hades,snowgolem,fisherman,croucher,troller,netherlord);
	}
	
	public void setLore(ItemMeta m, String what, String rank){
		m.setLore(Arrays.asList("§5§o"+what + " §aRequires "+rank+" rank"));
	}

}
