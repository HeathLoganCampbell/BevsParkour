package games.bevs.parkour.user.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import games.bevs.parkour.user.UserManager;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class UserListener implements Listener
{
	private @Getter UserManager userManager;
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		Player player = e.getPlayer();
		this.getUserManager().registerUser(player);
	}
	

	@EventHandler
	public void onDisconnect(PlayerQuitEvent e)
	{
		Player player = e.getPlayer();
		this.getUserManager().unregisterUser(player);
	}
	
	@EventHandler
	public void onDisconnect(PlayerKickEvent e)
	{
		Player player = e.getPlayer();
		this.getUserManager().unregisterUser(player);
	}
}
