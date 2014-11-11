package org.teryos.ckits.util;

import java.util.HashMap;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.teryos.ckits.Main;

public class Manager {
	
	static Main plugin;
	
	public Manager(Main instance){
		plugin = instance;
	}
	
	static HashMap<Player, String> kit = new HashMap<>();
	
	public static void setKit(Player player, String string){
		if(string == null){ kit.put(player, string); return;}
		if(kit.get(player) != null){
			player.sendMessage(Main.prefix+"You can only choose one kit per life.");
			return;
		}
		kit.put(player, string);
		if(string == "pvp"){
			giveSoup(player);
			Manager.giveKit(player, ItemList.ihelmet, ItemList.ichestplate, ItemList.ileggings, ItemList.iboots, ItemList.dsword, null,1);
		}
		if(string == "archer"){
			giveSoup(player);
			Manager.giveKit(player, ItemList.chelmet, ItemList.cchestplate, ItemList.lleggings, ItemList.cboots, ItemList.ssword, ItemList.bow,1);
			player.getInventory().setItem(20,new ItemStack(Material.ARROW,64));
		}
		if(string == "cheetah"){
			giveSoup(player);
			Manager.giveKit(player, ItemList.chelmet, ItemList.cchestplate, ItemList.cleggings, ItemList.cboots, ItemList.isword, ItemList.sugar,5);
		}
		if(string == "frog"){
			giveSoup(player);
			ItemStack h = new ItemStack(ItemList.lhelmet.getType());
			ItemStack c = new ItemStack(ItemList.lchestplate.getType());
			ItemStack l = new ItemStack(ItemList.lleggings.getType());
			ItemStack b = new ItemStack(ItemList.lboots.getType());
			LeatherArmorMeta hM = (LeatherArmorMeta) h.getItemMeta();
			LeatherArmorMeta cM = (LeatherArmorMeta) c.getItemMeta();
			LeatherArmorMeta lM = (LeatherArmorMeta) l.getItemMeta();
			LeatherArmorMeta bM = (LeatherArmorMeta) b.getItemMeta();
			hM.setColor(Color.GREEN);
			cM.setColor(Color.GREEN);
			lM.setColor(Color.GREEN);
			bM.setColor(Color.GREEN);
			h.setItemMeta(hM);
			c.setItemMeta(cM);
			l.setItemMeta(lM);
			b.setItemMeta(cM);
			Manager.giveKit(player, h, c, l, b, ItemList.ssword, new ItemStack(Material.EMERALD),5);
		}
		if(string == "poseidon"){
			giveSoup(player);
			ItemStack sword = new ItemStack(Material.WOOD_SWORD);
			sword.addEnchantment(Enchantment.DURABILITY, 3);
			Manager.giveKit(player, ItemList.lhelmet, ItemList.lchestplate, ItemList.lleggings, ItemList.lboots, sword, null,1);
		}
		if(string == "hades"){
			giveSoup(player);
			Manager.giveKit(player, ItemList.lhelmet, ItemList.ichestplate, ItemList.lleggings, ItemList.lboots, ItemList.wsword, null,1);
		}
		if(string == "snowgolem"){
			giveSoup(player);
			Manager.giveKit(player, ItemList.chelmet, ItemList.cchestplate, ItemList.cleggings, ItemList.cboots, ItemList.isword, ItemList.snowball,16);
		}
		if(string == "fisherman"){
			giveSoup(player);
			Manager.giveKit(player, ItemList.lhelmet, ItemList.lchestplate, ItemList.lleggings, ItemList.lboots, ItemList.ssword, ItemList.fishingrod,1);
		}
		if(string == "croucher"){
			giveSoup(player);
			Manager.giveKit(player, ItemList.chelmet, ItemList.cchestplate, ItemList.cleggings, ItemList.cboots, ItemList.isword, null,1);
		}
		if(string == "troller"){
			giveSoup(player);
			Manager.giveKit(player, ItemList.chelmet, ItemList.ichestplate, ItemList.cleggings, ItemList.iboots, ItemList.isword, null,1);
		}
		if(string == "netherlord"){
			giveSoup(player);
			Manager.giveKit(player, ItemList.chelmet, ItemList.ichestplate, ItemList.cleggings, ItemList.dboots, ItemList.isword, null,1);
		}
		
		if(string != null)
		player.sendMessage(Main.prefix + "You have equipped the §a"+string+"§e kit.");
	}
	
	public static String getKit(Player player){
		return kit.get(player);
	}
	
	public static void giveKit(Player player, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots, ItemStack sword, ItemStack special, Integer am){
		player.getInventory().setHelmet(helmet);
		player.getInventory().setChestplate(chestplate);
		player.getInventory().setLeggings(leggings);
		player.getInventory().setBoots(boots);
		
		if(special != null){
			special.setAmount(am);
			player.getInventory().setItem(0, sword);
			player.getInventory().setItem(1, special);
		}else{
			player.getInventory().setItem(0, sword);
		}
	}
	
	public static void giveSoup(Player player){
		for(int i = 0; i<player.getInventory().getSize(); i++){
			player.getInventory().setItem(i, new ItemStack(Material.MUSHROOM_SOUP));
		}
	}

	public static void removeKit(Player player){
		kit.put(player, null);
	}
	
	public static boolean hasKit(Player player){
		return kit.containsKey(player);
	}
	
	public static int getKills(Player player){
		return plugin.getConfig().getInt(player.getName()+".kills");
	}
	
	public static int getDeaths(Player player){
		return plugin.getConfig().getInt(player.getName()+".deaths");
	}
	
	public static int getKillstreak(Player player){
		return plugin.getConfig().getInt(player.getName()+".killstreak");
	}
	
	public static int getHighestKillstreak(Player player){
		return plugin.getConfig().getInt(player.getName()+".highest-killstreak");
	}

}
