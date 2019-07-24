package games.bevs.parkour.specialblocks.listener;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import games.bevs.parkour.specialblocks.SpecialBlockManager;
import games.bevs.parkour.user.UserManager;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SpecialBlockListener implements Listener {
	private @Getter @NonNull SpecialBlockManager specialBlockManager;
	private @Getter @NonNull UserManager userManager;

	@EventHandler
	public void onStandBlock(PlayerMoveEvent e)
	{
		//if player hasn't moved blocks, ignore
		if(e.getTo().getBlockX() == e.getFrom().getBlockX()
			&& e.getTo().getBlockZ() == e.getFrom().getBlockZ()
			&& e.getTo().getBlockY() == e.getFrom().getBlockY())
			return;
		Player player = e.getPlayer();
		
		Block standingBlock = player.getLocation().subtract(0, 0.5001, 0).getBlock();
		this.getSpecialBlockManager().triggerStandOn(player, standingBlock);
		
	}
}
