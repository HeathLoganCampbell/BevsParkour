package games.bevs.parkour.specialblocks.type;

import org.bukkit.Material;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SpecialBlock
{
	private Material material;
	
	public void onStand(SpecialBlockLog log) {}
	public void inStand(SpecialBlockLog log) {}
	public void specialTrigger(SpecialBlockLog log) {}
}
