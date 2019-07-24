package games.bevs.parkour.specialblocks.blocks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import games.bevs.parkour.commons.CC;
import games.bevs.parkour.specialblocks.type.SpecialBlock;
import games.bevs.parkour.specialblocks.type.SpecialBlockLog;
import games.bevs.parkour.user.type.User;

public class CheckpointBlock extends SpecialBlock
{

	public CheckpointBlock() {
		super(Material.GOLD_BLOCK);
	}

	@Override
	public void onStand(SpecialBlockLog log)
	{
		Player player = log.player;
		User user = log.user;
		
		Location newCheckpoint = log.block.getLocation();
		
		if(user.hasFinished()) 
		{
			player.sendMessage(CC.aqua + "You have already finished!");
			return;
		}
		
		//Don't want to be spammed checkpoint ever time you respawn
		if(user.getCheckpoint().getBlock() == log.block) return;
		
		user.setCheckpoint(newCheckpoint);
		player.sendMessage(CC.green + "Checkpoint!");
	}

}
