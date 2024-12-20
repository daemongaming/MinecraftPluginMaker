import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocSerialization {

	//Takes in a location and returns a completely serialized string
    public static String getStringFromLocation(Location l) {    	
	    if (l == null) {
	        return "";
	    }
	    return l.getWorld().getName() + ":" + l.getX() + ":" + l.getY() + ":" + l.getZ() + ":" + l.getYaw() + ":" + l.getPitch() ;	    
	}
    
	//Takes in a complete string and deserializes it into a location
    public static Location getLocationFromString(String s) {
        if (s == null || s.trim() == "") {
            return null;
        }
        final String[] parts = s.split(":");
        if (parts.length == 6) {
            World w = Bukkit.getServer().getWorld(parts[0]);
            double x = Double.parseDouble(parts[1]);
            double y = Double.parseDouble(parts[2]);
            double z = Double.parseDouble(parts[3]);
            float yaw = Float.parseFloat(parts[4]);
            float pitch = Float.parseFloat(parts[5]);
            return new Location(w, x, y, z, yaw, pitch);
        }
        return null;
    }
    
	//Takes in a location and returns a simply serialized string
    public static String getLiteStringFromLocation(Location l) {    	
	    if (l == null) {
	        return "";
	    }
	    return l.getWorld().getName() + "," + l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ() ;	    
	}
    
	//Takes in a simply serialized string and deserializes it into a location
    public static Location getLiteLocationFromString(String s) {
        if (s == null || s.trim() == "") {
            return null;
        }
        final String[] parts = s.split(",");
        if (parts.length == 4) {
            World w = Bukkit.getServer().getWorld(parts[0]);
            double x = Double.parseDouble(parts[1]);
            double y = Double.parseDouble(parts[2]);
            double z = Double.parseDouble(parts[3]);
            return new Location(w, x, y, z);
        }
        return null;
    }
    
}
