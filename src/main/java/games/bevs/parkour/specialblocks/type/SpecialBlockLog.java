package games.bevs.parkour.specialblocks.type;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import games.bevs.parkour.user.type.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SpecialBlockLog 
{
	public Player player;
	public User user;
	public Block block;
	public long timestamp;
}
