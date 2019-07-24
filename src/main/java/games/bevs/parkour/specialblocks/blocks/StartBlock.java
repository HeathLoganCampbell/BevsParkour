package games.bevs.parkour.specialblocks.blocks;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import games.bevs.parkour.commons.CC;
import games.bevs.parkour.specialblocks.type.SpecialBlock;
import games.bevs.parkour.specialblocks.type.SpecialBlockLog;
import games.bevs.parkour.user.type.User;

public class StartBlock extends SpecialBlock
{

	public StartBlock() {
		super(Material.STONE_PLATE);
	}

	@Override
	public void onStand(SpecialBlockLog log) {}
	
	@Override
	public void specialTrigger(SpecialBlockLog log) 
	{
		Player player = log.player;
		User user = log.user;
		
		if(user.hasStarted() && !user.hasFinished()) return;
		
		user.setStartTime(log.timestamp);
		user.setCheckpoint(log.block.getLocation());
		user.setFinishTime(-1);
		
		player.sendMessage(CC.green + "GO!!!!");
	}

}
