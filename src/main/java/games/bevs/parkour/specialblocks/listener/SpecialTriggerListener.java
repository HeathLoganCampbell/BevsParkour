package games.bevs.parkour.specialblocks.listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import games.bevs.parkour.specialblocks.SpecialBlockManager;
import games.bevs.parkour.user.UserManager;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SpecialTriggerListener implements Listener {
	private @Getter @NonNull SpecialBlockManager specialBlockManager;
	private @Getter @NonNull UserManager userManager;
	
	@EventHandler
	public void onTriggerPresurePlate(PlayerInteractEvent e)
	{
		Block block = e.getClickedBlock();
		if(e.getAction() == Action.PHYSICAL
				&& block.getType() == Material.STONE_PLATE) 
		{
            Player player = e.getPlayer();
            
            this.getSpecialBlockManager().triggerSpecial(player, block);
	    }
	}

}
