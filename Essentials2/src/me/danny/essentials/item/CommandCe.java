package me.danny.essentials.item;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.netty.handler.codec.ReplayingDecoder;
import net.minecraft.server.v1_15_R1.Material;



public class CommandCe implements CommandExecutor {

	public boolean isNumberInRange(int min, int max, int number) {
		if (number >= min && number <= max)
			return true;
		return false;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) 
		{
			Player player = (Player) sender;
			 if(args.length > 1) 
			{
				 if(args[0].equalsIgnoreCase("lore"))
				 {
						ItemStack pitem = player.getInventory().getItemInMainHand();
						ItemMeta mitem = pitem.getItemMeta();
						if(mitem == null || mitem.equals(Material.AIR)) 
						{
							player.sendMessage(ChatColor.RED + "Justmine: " +ChatColor.DARK_RED + "You have no item to do this !");
						}
						else 
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
//												lore.remove(index);
//												mitem.setLore(lore);
//												pitem.setItemMeta(mitem);
//												player.getInventory().setItemInMainHand(pitem);
//												player.sendMessage(ChatColor.RED + "Justmine: " + ChatColor.DARK_RED + "The lore you want to remove successed !");
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
				 }
				 else if(args[0].equalsIgnoreCase("name")){
					 
				 }
			}
			 else 
			 {
		 		  player.sendMessage(ChatColor.RED + "[Justmine]: " + ChatColor.AQUA + "Please use /help ce to know more information !");
			 }
			
		}
		return true;
	}
		
}
