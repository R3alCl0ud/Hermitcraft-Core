package hermitcore;

import com.google.common.base.Throwables;
import com.google.common.collect.Lists;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import hermitcore.common.ServerProxy;
import hermitcore.config.HermitCoreConfig;
import hermitcore.gameObjs.ObjHandler;
import hermitcore.tcon.smeltery.HermitSmeltery;
import hermitcore.common.IProxy;
import mantle.pulsar.config.ForgeCFG;
import mantle.pulsar.control.PulseManager;

import java.io.File;

import tconstruct.smeltery.TinkerSmeltery;

@SuppressWarnings("unused")
@Mod(modid = HECore.MODID, name = HECore.MODNAME, version = "${version}")
public class HECore {
	public static final String MODID = "HermitcraftCore";
	public static final String MODNAME = "Hermitcraft Core";
	public static final String modVersion = "${version}";

	public static File CONFIG_DIR;

	@Instance(MODID)
	public static HECore instance;

	@SidedProxy(clientSide = "hermitcore.common.ClientProxy", serverSide = "hermitcore.common.ServerProxy")
	public static IProxy proxy;
	
	public static PulseManager pulsar = new PulseManager(MODID, new ForgeCFG("TinkersModules", "Modules: Disabling these will disable a chunk of the mod"));

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		CONFIG_DIR = new File(event.getModConfigurationDirectory(),
				"HermitCore");

		if (!CONFIG_DIR.exists()) {
			CONFIG_DIR.mkdirs();
		}
		HermitCoreConfig.init(new File(CONFIG_DIR, "HECore.cfg"));

		
		for (int i = 0; i < (HermitCoreConfig.toDelete.length); i++)
		{
		ObjHandler.removeRecipes(HermitCoreConfig.toDelete[i]);
		}
		ObjHandler.register();
		pulsar.registerPulse(new HermitSmeltery());

	}

	@EventHandler
	public void load(FMLPreInitializationEvent event) {
		
	}

	@EventHandler
	public void postInit(FMLPreInitializationEvent event) {

	}

}
