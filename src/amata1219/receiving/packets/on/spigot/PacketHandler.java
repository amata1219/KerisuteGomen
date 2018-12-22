package amata1219.receiving.packets.on.spigot;

import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

public class PacketHandler extends ChannelDuplexHandler{

	private Player p;

	public PacketHandler(final Player p) {
		this.p = p;
	}

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		super.write(ctx, msg, promise);
	}

	@Override
	public void channelRead(ChannelHandlerContext c, Object m) throws Exception {
		if(m.getClass().getSimpleName().equalsIgnoreCase("PacketPlayInCustomPayload")){
			String text = Reflection.getFieldValue(m, "a").toString();
			System.out.println(text);
			Object obj = Reflection.getFieldValue(m, "b");
			ByteBuf buf = (ByteBuf) Reflection.getFieldValue(obj, "a");
			if(buf.hasArray()){
				ByteArrayDataInput in = ByteStreams.newDataInput(buf.array());
				while(true){
					try{
						System.out.println(in.readUTF());
					}catch(Exception e){
						System.out.println("break");
						break;
					}
				}
			}else{
				System.out.println("nothing");
			}

		}
		super.channelRead(c, m);
	}

	public Player getPlayer(){
		return p;
	}

}
