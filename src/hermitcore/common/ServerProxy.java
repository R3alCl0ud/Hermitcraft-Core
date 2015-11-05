package hermitcore.common;

import net.minecraft.entity.player.EntityPlayer;


public class ServerProxy implements IProxy
{
	public void registerKeyBinds() {} 
	public void registerRenderers() {}
	public void registerClientOnlyEvents() {}
	public EntityPlayer getClientPlayer() { return null;}
	public boolean isJumpPressed() { return false;}

}
