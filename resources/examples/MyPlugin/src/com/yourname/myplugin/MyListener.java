package com.yourname.myplugin;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Bukkit;
import org.bukkit.Material;

public class MyListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		Player player = event.getPlayer();
		String name = player.getName();
		
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			p.sendMessage(name + " has joined the server!");
		}
		
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onDeath(EntityDeathEvent event) {
		
		Entity killer = event.getDamageSource().getCausingEntity();
		Entity target = event.getEntity();
		boolean skelePK = killer instanceof Player && target instanceof Skeleton;
		
		if (skelePK) {
			Player player = (Player) killer;
			ItemStack item = new ItemStack(Material.DIAMOND, 1);
			player.getInventory().addItem(item);
		}
		
	}
	
}