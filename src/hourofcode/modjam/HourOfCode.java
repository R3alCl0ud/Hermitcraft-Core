package hourofcode.modjam;

import java.io.File;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import hermitcore.common.IProxy;
import hourofcode.modjam.config.HOCConfig;
import hourofcode.modjam.library.ModjamObjects;

@Mod(modid = HourOfCode.MODID, name = "Hour of Code, Modjam", version = "1.0.0")
public class HourOfCode 
{
	public static final String MODID = "hcmodjam";
	
	public static File CONFIG_DIR;
	
	
	
	@Instance(MODID)
	public static HourOfCode instance;
	
	@SidedProxy(clientSide = "hourofcode.modjam.common.ClientProxy", serverSide = "hourofcode.modjam.common.ServerProxy")
	public static IProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		CONFIG_DIR = new File(event.getModConfigurationDirectory(), "HourOfCode");
		
		if(!CONFIG_DIR.exists())
		{
			CONFIG_DIR.mkdirs();
		}
		
		HOCConfig.init(new File(CONFIG_DIR, "HourOfCode.cfg"));
		
		ModjamObjects.register();
	}
	
	public void Init(FMLInitializationEvent event)
	{
		
	}
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
