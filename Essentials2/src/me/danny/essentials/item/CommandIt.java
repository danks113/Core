package me.danny.essentials.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.server.v1_15_R1.Material;



public class CommandIt implements CommandExecutor {

	public boolean isNumberInRange(int min, int max, int number) {
		if (number >= min && number <= max)
			return true;
		return false;
	}
	
	public Enchantment getEnchantmentInMap(Map<Enchantment, Integer> enchants, int index) {
		int count = 0;
		for (Enchantment enchant : enchants.keySet()) {
			if (count == index) {
				return enchant;
			}
			count++;
		}
		return null;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) 
		{
			Player player = (Player) sender;
			 if(args.length > 1) 
			{
					ItemStack pitem = player.getInventory().getItemInMainHand();
					ItemMeta mitem = pitem.getItemMeta();
					if(mitem == null || mitem.equals(Material.AIR))
					{
						player.sendMessage(ChatColor.RED + "Justmine: " +ChatColor.DARK_RED + "You have no item to do this !");
					}
					else 
					{
						 if(args[0].equalsIgnoreCase("lore"))
						 {
									String str = "";
									switch(args[1]) {
										case "add":
											if (args.length < 3) 
											{
												player.sendMessage(ChatColor.RED + "Justmine: " + ChatColor.DARK_RED + "No lore to add !");
											}
											else
											{
													if(mitem.hasLore() == false) 
												{
														List<String> lore = new ArrayList<String>();
														for(int i = 2; i < args.length; i++) 
												{
														str += " " + args[i].replaceAll("&", "§");
												}
												lore.add(str);
												mitem.setLore(lore);
												pitem.setItemMeta(mitem);
												player.getInventory().setItemInMainHand(pitem);
												}
													else 
												{
														List<String> lore = mitem.getLore();
														for(int i = 2; i < args.length; i++) 
												{
															str += " " + args[i].replaceAll("&", "§");
												}
													lore.add(str);
													mitem.setLore(lore);
													pitem.setItemMeta(mitem);
													player.getInventory().setItemInMainHand(pitem);
												}
													player.sendMessage(ChatColor.RED + "Justmine: " +ChatColor.DARK_RED + "The lore you want to add successed !");
											}
											break;
										case "remove":
											if (args.length < 3) 
											{
												player.sendMessage(ChatColor.RED + "Justmine: " + ChatColor.AQUA + "No lore to remove !");
											}
											else
											{
												if(mitem.hasLore() == false) {
													player.sendMessage(ChatColor.RED + "Justmine: " + ChatColor.DARK_RED + "The item you want to remove have no lore !");
												}
												else 
												{
													List<String> lore = mitem.getLore();
													try 
													{
														int index = Integer.parseInt(args[2]);
														if (isNumberInRange(1, lore.size(), index)) {
															lore.remove(--index);
															mitem.setLore(lore);
															pitem.setItemMeta(mitem);
															player.getInventory().setItemInMainHand(pitem);
															player.sendMessage(ChatColor.RED + "Justmine: " + ChatColor.DARK_RED + "The lore you want to remove successed !");
														}
														else 
														{
															throw new Exception("out of range");
														}
//														lore.remove(index);
//														mitem.setLore(lore);
//														pitem.setItemMeta(mitem);
//														player.getInventory().setItemInMainHand(pitem);
//														player.sendMessage(ChatColor.RED + "Justmine: " + ChatColor.DARK_RED + "The lore you want to remove successed !");
													}
													catch (Exception e) 
													{
														if (e.getLocalizedMessage().equals("out of range")) {
															player.sendMessage(ChatColor.RED + "Justmine: " + ChatColor.DARK_RED + "The value is in 1 " + " to " + lore.size() + "!");
														}
														else {
															player.sendMessage(ChatColor.RED + "Justmine: " + ChatColor.DARK_RED + "The value your entered is wrong type !");
			
														}												
													}
												}
											}
											break;
										default:
											player.sendMessage(ChatColor.RED + "Justmine: " + ChatColor.DARK_RED + "Invaled Command please try again !");
											break;
									}			
						 }
						 else if(args[0].equalsIgnoreCase("name"))
						 {
									 if(args[1].equalsIgnoreCase("set")) 
									 {
											String name = "";
											for (int i =2; i < args.length ; i++) 
											{
												name += "" + args[i].replaceAll("&", "§");
											}
											mitem.setDisplayName(name);
											pitem.setItemMeta(mitem);
											player.getInventory().setItemInMainHand(pitem);
											player.sendMessage(ChatColor.RED + "Justmine: " + ChatColor.DARK_RED + "The name you want to set succed !");
									 }
									 else 
									 {
										player.sendMessage(ChatColor.RED + "Justmine: " + ChatColor.DARK_RED + "Invaled Command please try again !");
									 }
						 }
						 else if(args[0].equalsIgnoreCase("en"))
						 {
							 switch(args[1]) {
							 	case "add":
							 		if(args[2] == null && args[3] == null) 
									 {
										 player.sendMessage(ChatColor.RED + "Justmine: " + ChatColor.DARK_RED + "Nothing to add !");
									 }
									 else 
									 {
										 try 
										 {
											 mitem.addEnchant(Enchantment.getByKey(NamespacedKey.minecraft(args[2].toLowerCase())), Integer.parseInt(args[3]) , true);
											 pitem.setItemMeta(mitem);
//											 Bukkit.getServer().broadcastMessage(mitem.getEnchants().toString());
											 player.getInventory().setItemInMainHand(pitem);
											 player.sendMessage(ChatColor.RED + "Justmine: " + ChatColor.DARK_RED + "The Enchant you want to add successed !");
										 }
										 catch (Exception e)
										 {
											 player.sendMessage(ChatColor.RED + "Justmine: " + ChatColor.DARK_RED + "Invaled name of Enchantment you want to add !");
										 }
			
									 }
							 		break;
							 	case "remove":
									if (args.length < 2 && mitem.hasEnchants() == false) 
									{
										player.sendMessage(ChatColor.RED + "Justmine: " + ChatColor.AQUA + "No Enchant to remove  !");
									}
									else 
									{
										Map<Enchantment, Integer> enchantsList = mitem.getEnchants();
										try 
										 {
//											 mitem.addEnchant(Enchantment.getByKey(NamespacedKey.minecraft(args[2].toLowerCase())), 0, true);
											int index = Integer.parseInt(args[2]);
											 if (isNumberInRange(0, enchantsList.size(), index)) {
//												 mitem.removeEnchant(Enchantment.getByKey(NamespacedKey.minecraft(args[2].toLowerCase())));
//												 pitem.setItemMeta(mitem);
//												 player.getInventory().setItemInMainHand(pitem);
												 index -= 1;
												 Enchantment enchant = getEnchantmentInMap(enchantsList, index);
												 if (enchant != null) {
													 mitem.removeEnchant(enchant);
													 pitem.setItemMeta(mitem);
													 player.getInventory().setItemInMainHand(pitem);
													 player.sendMessage(ChatColor.RED + "Justmine: " + ChatColor.DARK_RED + "The Enchant you want to remove successed !");
												 }
												 else {
													 throw new Exception("null pointer");
												 }
											 }
											 else {
												 throw new Exception("out of range");
											 }
										 }
										 catch (Exception e)
										 {
											 if (e.getLocalizedMessage().equals("null pointer")) {
												 player.sendMessage(ChatColor.RED + "Justmine: " + ChatColor.DARK_RED + "Invalid number to remove !");
											 }
											 else if (e.getLocalizedMessage().equals("out of range")) {
												 player.sendMessage(ChatColor.RED + "Justmine: " + ChatColor.DARK_RED + "Please input number in range [0, " + enchantsList.size() + "]");
											 }
											 else player.sendMessage(ChatColor.RED + "Justmine: " + ChatColor.DARK_RED + "Invalid name of Enchantment you want to remove !");
										 }
										
									}
							 		break;
					
							 }
							 

						 }
					}

			}
			 else 
			 {
		 		  player.sendMessage(ChatColor.RED + "[Justmine]: " + ChatColor.AQUA + "Please use /help ce to know more information !");
			 }
			
		}
		else 
		{
            sender.sendMessage(ChatColor.RED +"Console cannot do this!");
		}
		return true;
	}
		
}
