package amata1219.kerisute.gomen.old;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ClientLoginEventOld extends Event{

	private static final HandlerList  handlers = new HandlerList();
	private ClientData data;
	private boolean isInvalid;

	public ClientLoginEventOld(ClientData data){
		this.data = data;
	}

	public Player getPlayer(){
		return data.getPlayer();
	}

	public ClientData getClientData(){
		return data;
	}

	public void setInvalid(boolean isInvalid){
		this.isInvalid = isInvalid;
	}

	public boolean isInvalid(){
		return isInvalid;
	}

	public HandlerList getHandlers(){
		return handlers;
	}

	public static HandlerList getHandlerList(){
		return handlers;
	}

}