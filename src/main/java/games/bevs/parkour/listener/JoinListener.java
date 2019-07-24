package games.bevs.parkour.listener;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import games.bevs.parkour.Parkour;
import games.bevs.parkour.user.UserManager;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor	
public class JoinListener implements Listener
{
	private @Getter @NonNull UserManager userManager;
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		Player player = e.getPlayer();
		player.setGameMode(GameMode.ADVENTURE);
		player.teleport(Parkour.getLobbySpawn());
	}
}
