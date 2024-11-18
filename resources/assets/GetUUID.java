import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class GetUUID extends JavaPlugin {

    @Override
    public void onEnable() {
    	
    	PluginManager pm = getServer().getPluginManager();
      
		//Chat handler
        pm.registerEvents(new Listener() {
        	@EventHandler
        	public void rightClick(PlayerInteractEvent e) {
        		if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
        			for (Entity ent : e.getPlayer().getNearbyEntities(3, 3, 3)) {
        				e.getPlayer().sendMessage(ent.getUniqueId().toString());
        			}
        		}
        	}
        }, this);

    }
}
