package games.bevs.parkour.specialblocks.blocks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import games.bevs.parkour.specialblocks.type.SpecialBlock;
import games.bevs.parkour.specialblocks.type.SpecialBlockLog;
import games.bevs.parkour.user.type.User;

public class FinishBlock extends SpecialBlock
{

	public FinishBlock() {
		super(Material.WOOL);
	}

	@Override
	public void onStand(SpecialBlockLog log)
	{
		Player player = log.player;
		User user = log.user;
		
		Location newCheckpoint = log.block.getLocation();
		
		if(user.hasFinished()) return;
		
		user.setCheckpoint(newCheckpoint);
		user.setFinishTime(log.timestamp);
		Bukkit.broadcastMessage(player.getName() + " has finished in " + user.getFormatedTimeTaken() + ".");
	}
	
}
