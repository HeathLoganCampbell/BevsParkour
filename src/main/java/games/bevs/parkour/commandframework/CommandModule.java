package games.bevs.parkour.commandframework;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import games.bevs.parkour.commandframework.annotations.CommandHandler;
import games.bevs.parkour.commandframework.types.CommandArgs;


public class CommandModule implements CommandExecutor {
	private MiniCommandMap miniCommandMap;

	public CommandModule(JavaPlugin plugin) 
	{
		this.miniCommandMap = new MiniCommandMap(plugin, this);
	}
	
	public void registerCommands(Object obj) 
	{
        for (Method method : obj.getClass().getMethods()) 
        {
            if (method.getAnnotation(CommandHandler.class) != null) 
            {
            	CommandHandler command = method.getAnnotation(CommandHandler.class);
                if (method.getParameterTypes().length > 1 || method.getParameterTypes()[0] != CommandArgs.class)
                {
                    System.out.println("Unable to register command " + method.getName() + ". Unexpected method arguments");
                    continue;
                }
                
                List<String> commandLabels = new ArrayList<>(5); 
                String commandLabel = command.name();
                commandLabels.add(commandLabel);
                
                
                
                for(String labelAliases : commandLabels)
                {
	                this.miniCommandMap.registerCommand(command, labelAliases, method, obj);
	                for (String alias : command.aliases()) 
	                {
	                	this.miniCommandMap.registerCommand(command, alias, method, obj);
	                }
                }
            } 
        }
    }
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandName, String[] args) {
		return this.miniCommandMap.commandExecutor(sender, cmd, commandName, args);
	}

}
