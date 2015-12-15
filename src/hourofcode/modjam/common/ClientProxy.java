package hourofcode.modjam.common;

import cpw.mods.fml.client.FMLClientHandler;
import hermitcore.common.IProxy;
import net.minecraft.entity.player.EntityPlayer;

public class ClientProxy implements IProxy{

	@Override
	public void registerKeyBinds() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerRenderers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerClientOnlyEvents() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public EntityPlayer getClientPlayer()
	{
		return FMLClientHandler.instance().getClientPlayerEntity();
	}

	@Override
	public boolean isJumpPressed() {
		// TODO Auto-generated method stub
		return false;
	}

}
