package games.bevs.parkour.specialblocks;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import games.bevs.parkour.commons.CC;
import games.bevs.parkour.commons.PluginUtils;
import games.bevs.parkour.specialblocks.blocks.CheckpointBlock;
import games.bevs.parkour.specialblocks.blocks.FinishBlock;
import games.bevs.parkour.specialblocks.blocks.StartBlock;
import games.bevs.parkour.specialblocks.listener.SpecialBlockListener;
import games.bevs.parkour.specialblocks.listener.SpecialTriggerListener;
import games.bevs.parkour.specialblocks.type.SpecialBlock;
import games.bevs.parkour.specialblocks.type.SpecialBlockLog;
import games.bevs.parkour.user.UserManager;
import games.bevs.parkour.user.type.User;
import lombok.Getter;
import lombok.NonNull;

public class SpecialBlockManager
{
	private @Getter @NonNull UserManager userManager;
	private @Getter HashMap<Material, SpecialBlock> specialBlocks; 
	
	public SpecialBlockManager(@NonNull UserManager userManager, JavaPlugin plugin)
	{
		this.userManager = userManager;
		this.specialBlocks = new HashMap<>();
		
		PluginUtils.registerListener(new SpecialBlockListener(this, this.getUserManager()), plugin);
		PluginUtils.registerListener(new SpecialTriggerListener(this, this.getUserManager()), plugin);

		populateSpecialBlocks();
	}
	
	public void populateSpecialBlocks()
	{
		this.registerBlock(new CheckpointBlock());
		this.registerBlock(new FinishBlock());
		this.registerBlock(new StartBlock());
	}
	
	public void registerBlock(SpecialBlock block)
	{
		this.getSpecialBlocks().put(block.getMaterial(), block);
	}
	
	public void triggerSpecial(Player player, Block block)
	{
		User user = this.getUserManager().getUser(player);
		
		if(user == null)
		{
			Bukkit.broadcastMessage(CC.bYellow + "Something went very wrong with the user " + player.getName());
			return;
		}
		
		if(user.getLastSpecial() != null 
				&&  user.getLastSpecial() == block)
			return;
		
		Material blockMaterial = block.getType();
		
		user.setLastSpecial(block);
		
		SpecialBlock specialBlock = this.getSpecialBlocks().get(blockMaterial);
		long now = System.currentTimeMillis();
		
		SpecialBlockLog log = new SpecialBlockLog(player, user, block, now);
		specialBlock.specialTrigger(log);
	}
	
	public void triggerStandOn(Player player, Block standingOnBlock)
	{
		User user = this.getUserManager().getUser(player);
		if(user == null)
		{
			Bukkit.broadcastMessage(CC.bYellow + "Something went very wrong with the user " + player.getName());
			return;
		}
		//already check this block
		if(user.getLastStandingOn() != null 
				&& user.getLastStandingOn() == standingOnBlock)
			return;
		
		Material blockMaterial = standingOnBlock.getType();
		
		user.setLastStandingOn(standingOnBlock);
		
		SpecialBlock specialBlock = this.getSpecialBlocks().get(blockMaterial);
		long now = System.currentTimeMillis();
		
		if(specialBlock == null) return;
		
		SpecialBlockLog log = new SpecialBlockLog(player, user, standingOnBlock, now);
		specialBlock.onStand(log);
	}
}
