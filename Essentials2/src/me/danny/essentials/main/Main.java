package me.danny.essentials.main;

import org.bukkit.plugin.java.JavaPlugin;

import me.danny.essentials.item.CommandIt;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		getCommand("it").setExecutor(new CommandIt());
	}
	
}
