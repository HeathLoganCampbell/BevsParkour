package games.bevs.parkour;

import java.util.stream.Stream;

import org.bukkit.plugin.java.JavaPlugin;

import games.bevs.parkour.commandframework.CommandModule;
import games.bevs.parkour.commands.ParkourCommands;
import games.bevs.parkour.commons.PluginUtils;
import games.bevs.parkour.listener.ChatListener;
import games.bevs.parkour.listener.JoinListener;
import games.bevs.parkour.listener.SpawnListener;
import games.bevs.parkour.specialblocks.SpecialBlockManager;
import games.bevs.parkour.user.UserManager;

public class ParkourPlugin extends JavaPlugin
{
	public void registerListeners(UserManager userManager)
	{
		Stream.of(
					new SpawnListener(userManager),
					new JoinListener(userManager),
					new ChatListener(userManager)
				)
			    .forEach(listener -> 
			    	PluginUtils.registerListener(listener, this));
	}
	
	public void registerCommands(UserManager userManager)
	{
		CommandModule commandframework = new CommandModule(this);
		
		Stream.of(
					new ParkourCommands(userManager)
				 ).forEach(command -> 
				 	commandframework.registerCommands(command));
	}
	
	@Override
	public void onEnable()
	{
		UserManager userManager = new UserManager(this);
		/*  SpecialBlockManager specialBlockManager = */new SpecialBlockManager(userManager, this);
		
		registerListeners(userManager);
	}
	
	@Override
	public void onDisable()
	{
		
	}
}
