package r3alcl0ud.hermitcore.common;

import net.minecraft.entity.player.EntityPlayer;

public interface IProxy 
{
    void registerKeyBinds();
    void registerRenderers();
    void registerClientOnlyEvents();
	EntityPlayer getClientPlayer();
	boolean isJumpPressed();
}
