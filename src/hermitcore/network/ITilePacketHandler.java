package hermitcore.network;

//Derived from CoFH's ITilePacketHandler

public interface ITilePacketHandler
{
 public void handleTilePacket(PacketHEBase payload, boolean isServer);
}
