package amata1219.kerisute.gomen;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.scheduler.BukkitRunnable;

public class DataRemoveTask extends BukkitRunnable{

	private HashMap<UUID, ClientData> clients;
	private UUID uuid;

	public DataRemoveTask(HashMap<UUID, ClientData> clients, UUID uuid){
		this.clients = clients;
		this.uuid = uuid;
	}

	@Override
	public void run(){
		clients.remove(uuid);
	}

}
