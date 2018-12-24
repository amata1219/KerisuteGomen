package amata1219.kerisute.gomen;

import java.util.HashSet;
import java.util.Set;

public abstract class ClientInfoBase {

	private final Set<String> channels = new HashSet<>();

	private final Set<Client> clients = new HashSet<>();
	private final Set<Mod> mods = new HashSet<>();

	public Set<String> getChannels() {
		return channels;
	}

	public Set<Client> getClients() {
		return clients;
	}

	public Set<Mod> getMods() {
		return mods;
	}


}
