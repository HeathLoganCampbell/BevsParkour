package games.bevs.parkour;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import lombok.Getter;
import lombok.Setter;

public class Parkour 
{
	@Getter @Setter
	public static Location lobbySpawn = new Location(Bukkit.getWorlds().get(0), 0, 100, 0);
}
