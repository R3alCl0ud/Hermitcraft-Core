package r3alcl0ud.hermitcore.common;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy implements IProxy
{

	/*@Override
	public void registerKeyBinds()
	{
		ClientKeyHelper.registerMCBindings();
	}*/
	@Override
	public EntityPlayer getClientPlayer()
	{
		return FMLClientHandler.instance().getClientPlayerEntity();
	}

	@Override
	public boolean isJumpPressed()
	{
		return FMLClientHandler.instance().getClient().gameSettings.keyBindJump.getIsKeyPressed();
	}

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

}