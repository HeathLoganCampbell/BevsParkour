package games.bevs.parkour.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import games.bevs.parkour.commandframework.annotations.CommandHandler;
import games.bevs.parkour.commandframework.types.CommandArgs;
import games.bevs.parkour.commons.CC;
import games.bevs.parkour.user.UserManager;
import games.bevs.parkour.user.type.User;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ParkourCommands
{
	private @Getter @NonNull UserManager userManager;
	
	/*
	 * Take player back to their last checkpoint
	 */
	@CommandHandler(name = "parkour.checkpoint", aliases = {"parkour.lastCheckpoint", "p.cp", "parkour.cp"})
	public void onBackToCheckpointCmd(CommandArgs args) 
	{
		Player player = args.getPlayer();
		User user = this.getUserManager().getUser(player);
		
		player.teleport(user.getCheckpoint().add(0, 1.5, 0));
		player.sendMessage(CC.aqua + "Sent back to checkpoint.");
	}
	
	@CommandHandler(name = "parkour.start", permission="parkour.command.start")
	public void onStartCmd(CommandArgs args) 
	{
		Player player = args.getPlayer();
		
		Bukkit.getOnlinePlayers().stream()
								 .filter(otherPlayer -> otherPlayer.getGameMode() != GameMode.CREATIVE)
								 .forEach(otherPlayer -> 
								 {
									 User otherUser = this.getUserManager().getUser(otherPlayer);
									 
									 otherUser.setLastSpecial(null);
									 otherUser.setLastStandingOn(null);
									 otherUser.setStartTime(System.currentTimeMillis());
									 otherUser.setFinishTime(-1);
									 otherUser.setCheckpoint(player.getLocation());
									 
									 otherPlayer.teleport(player);
									 
									 otherPlayer.sendMessage(CC.green + player.getName() + " put you in a new game, GO!!!!");
								 });
		player.sendMessage("Teleported all players");
	}
	
	
}
