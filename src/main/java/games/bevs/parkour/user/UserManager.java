package games.bevs.parkour.user;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import games.bevs.parkour.commons.PluginUtils;
import games.bevs.parkour.user.listener.UserListener;
import games.bevs.parkour.user.type.User;
import lombok.Getter;

public class UserManager
{
	private @Getter HashMap<UUID, User> onlineUsers;
	
	public UserManager(JavaPlugin plugin)
	{
		this.onlineUsers = new HashMap<>();
		
		PluginUtils.registerListener(new UserListener(this), plugin);
	}
	
	public User registerUser(Player player)
	{
		User user = new User(player.getName(), player.getUniqueId());
		this.onlineUsers.put(player.getUniqueId(), user);
		return user;
	}
	
	public User getUser(Player player)
	{
		return this.getUser(player.getUniqueId());
	}
	
	public User getUser(UUID uniqueId)
	{
		return this.getOnlineUsers().get(uniqueId);
	}
	
	public void unregisterUser(Player player)
	{
		this.unregisterUser(player.getUniqueId());
	} 
	
	public void unregisterUser(UUID uuid)
	{
		this.onlineUsers.remove(uuid);
	}
}
