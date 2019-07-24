package games.bevs.parkour.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import games.bevs.parkour.commons.CC;
import games.bevs.parkour.user.UserManager;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor	
public class ChatListener implements Listener
{
	private @Getter @NonNull UserManager userManager;
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e)
	{
		Player player = e.getPlayer();
		String nameColour = player.isOp() ? CC.red : CC.gray;
		e.setFormat(nameColour + player.getName() + CC.white + ": " + e.getMessage());
	}
}
