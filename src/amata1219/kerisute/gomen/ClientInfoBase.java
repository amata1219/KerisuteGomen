package amata1219.kerisute.gomen;


import java.util.HashSet;
import java.util.Set;

public class ClientInfoBase {

	private final Set<String> channels = new HashSet<>();

	private final Set<Client> clients = new HashSet<>();
	private final Set<Mod> mods = new HashSet<>();

	public void addChannel(String channel){
		channels.add(channel);
	}

	public boolean hasChannel(String channel){
		return channels.contains(channel);
	}

	public void addClient(Client client){
		clients.add(client);
	}

	public boolean hasClient(String clientName){
		return clients.contains(clientName);
	}

	public void addMod(Mod mod){
		mods.add(mod);
	}

	public boolean hasMod(String modId){
		return mods.contains(modId);
	}

}
