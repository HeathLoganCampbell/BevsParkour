package games.bevs.parkour.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInitialSpawnEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import games.bevs.parkour.Parkour;
import games.bevs.parkour.user.UserManager;
import games.bevs.parkour.user.type.User;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SpawnListener implements Listener
{
	private @Getter @NonNull UserManager userManager;
	
	@EventHandler
	public void onSpawn(PlayerRespawnEvent e)
	{
		User user = this.getUserManager().getUser(e.getPlayer());
		if(user.getCheckpoint() == null)
		{
			e.setRespawnLocation(Parkour.getLobbySpawn());
			return;
		}
		e.setRespawnLocation(user.getCheckpoint().add(0, 1.5, 0));
	}
	
	@EventHandler
	public void onFirstSpawn(PlayerInitialSpawnEvent e)
	{
		e.setSpawnLocation(Parkour.getLobbySpawn());
	}
}
