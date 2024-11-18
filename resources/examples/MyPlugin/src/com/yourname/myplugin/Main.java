package com.yourname.myplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.Bukkit;

public class Main extends JavaPlugin {
	
	@Override
    public void onEnable() {
		
		PluginManager pm = getServer().getPluginManager();
		MyListener listener = new MyListener();
		pm.registerEvents(listener, this);
		
		int d = 1000;
		int i = 200;
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
			//Do something...
		}, d, i);
		
    }
    
    //MCGS command handling
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        
    	Player player = Bukkit.getServer().getPlayerExact("krakenmyboy");
    	String command = cmd.getName();
    	boolean isPlayer = sender instanceof Player;
    	
    	if (isPlayer) {
    		player = (Player) sender;
    	}
    	
    	if (command.toLowerCase().equals("myfirstcommand")) {
    		player.sendMessage("Yo, nice! You did it! Have a cookie, on the house.");
    	}
    	
    	return true;
    }
    
}
