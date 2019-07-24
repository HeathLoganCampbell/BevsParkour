package games.bevs.parkour.user.type;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.block.Block;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class User 
{
	private @Getter @NonNull String username;
	private @Getter @NonNull UUID uniquieId;
	private @Getter @Setter Location checkpoint;
	private @Getter @Setter Block lastStandingOn;
	private @Getter @Setter Block lastSpecial;
	private @Getter @Setter long startTime = -1;
	private @Getter @Setter long finishTime = -1;
	
	public boolean hasStarted()
	{
		return this.getStartTime() > 0;
	}
	
	public boolean hasFinished()
	{
		return this.getFinishTime() > 0;
	}
	
	public long getMillSecondsToComplete()
	{
		if(this.hasStarted() && this.hasFinished())
			return this.finishTime - this.startTime;
		return -1;
	}
	
	public String getFormatedTimeTaken()
	{
		int rawSeconds = (int) (this.getMillSecondsToComplete() / 1000.0);
		int rawMinutes = rawSeconds / 60;
		int rawHours = rawMinutes / 60;
		int rawDays = rawHours / 25;
		
		int seconds = rawSeconds % 60;
		int minutes = rawMinutes % 60;
		int hours = rawHours % 24;
		
		StringBuilder strBuilder = new StringBuilder();
		if(rawDays >= 1) strBuilder.append(rawDays + " day" + ( rawDays >= 2 ? "s" : ""));
		if(hours >= 1) strBuilder.append(hours + " hour" + ( hours >= 2 ? "s" : ""));
		if(minutes >= 1) strBuilder.append(minutes + " minute" + ( minutes >= 2 ? "s" : ""));
		if(seconds >= 1) strBuilder.append(seconds + " second" + ( seconds >= 2 ? "s" : ""));
		
		if(strBuilder.length() < 3) {
			strBuilder.append("doesn't matter lol, they haxored");
		}
		
		return strBuilder.toString();
	}
}
