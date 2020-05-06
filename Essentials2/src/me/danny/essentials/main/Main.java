package me.danny.essentials.main;

import org.bukkit.plugin.java.JavaPlugin;

import me.danny.essentials.item.CommandCe;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		getCommand("ce").setExecutor(new CommandCe());
	}
	
}
