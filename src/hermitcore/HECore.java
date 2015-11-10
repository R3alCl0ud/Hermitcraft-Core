package hermitcore;

import hermitcore.common.IProxy;
import hermitcore.config.HermitCoreConfig;
import hermitcore.gameObjs.ObjHandler;
import hermitcore.library.crafting.LiquidCasting;
import hermitcore.tcon.smeltery.HermitSmeltery;
import hermitcore.tcon.tools.HermitTools;

import java.io.File;
import java.util.Random;

import mantle.pulsar.config.ForgeCFG;
import mantle.pulsar.config.IConfiguration;
import mantle.pulsar.control.PulseManager;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;



@SuppressWarnings("unused")
@Mod(modid = HECore.MODID, name = HECore.MODNAME, version = "${version}",
dependencies = "required-after:TConstruct@[1.7.10-1.8.3,);after:ForgeMultipart@[1.1.1.321,);after:*")
public class HECore {
	public static final String MODID = "hermitcore";
	public static final String MODNAME = "Hermitcraft Core";
	public static final String modVersion = "${version}";

	public static Random random = new Random();
	
	public static File CONFIG_DIR;

	@Instance(MODID)
	public static HECore instance;

	@SidedProxy(clientSide = "hermitcore.common.ClientProxy", serverSide = "hermitcore.common.ServerProxy")
	public static IProxy proxy;
	
    private IConfiguration pulseCFG;
	public static PulseManager pulsar;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		CONFIG_DIR = new File(event.getModConfigurationDirectory(),
				"HermitCore");

		if (!CONFIG_DIR.exists()) {
			CONFIG_DIR.mkdirs();
		}
		
		
        pulseCFG = new PulsarCFG(HermitCoreConfig.configFile("Modules.cfg"), "Tinker's Construct Addon: Hermitcraft Core addon for Tinkers Construct");
        pulseCFG.load();
        pulsar = new PulseManager(MODID, pulseCFG);
		
		
		HermitCoreConfig.init(new File(CONFIG_DIR, "HECore.cfg"));

		
		for (int i = 0; i < (HermitCoreConfig.toDelete.length); i++)
		{
		ObjHandler.removeRecipes(HermitCoreConfig.toDelete[i]);
		}
		ObjHandler.register();
		
		pulsar.registerPulse(new HermitSmeltery());
		pulsar.registerPulse (new HermitTools());
		
        tableCasting = new LiquidCasting();
        basinCasting = new LiquidCasting();
        
        pulsar.preInit(event);

	}

	@EventHandler
	public void Init(FMLInitializationEvent event) {
		
		pulsar.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

		
		
        //proxy.initialize();
        pulsar.postInit(event);
	}
	
    public static LiquidCasting getTableCasting ()
    {
        return tableCasting;
    }

    public static LiquidCasting getBasinCasting ()
    {
        return basinCasting;
    }
    
    
    public static LiquidCasting tableCasting;
    public static LiquidCasting basinCasting;

}
