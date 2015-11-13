package hermitcore.tcon.tools;

import static net.minecraft.util.EnumChatFormatting.*;
import tconstruct.library.TConstructRegistry;
import net.minecraft.init.Blocks;
import mantle.pulsar.pulse.Handler;
import mantle.pulsar.pulse.Pulse;
import hermitcore.HECore;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;


@ObjectHolder(HECore.MODID)
@Pulse(id = "Hermitcraft Core' Tools", description = "The main core of the mod! All of the tools, the tables, and the patterns are here.", forced = true)

public class HermitTools {


	
	@Handler
	public void preInit(FMLPreInitializationEvent event)
	{
	


        
        registerMaterials();
        
        
	}
    @Handler
    public void init (FMLInitializationEvent event)
    {

    }

    @Handler
    public void postInit (FMLPostInitializationEvent evt)
    {

    }
    
    void registerMaterials ()
    {
    	TConstructRegistry.addToolMaterial(MaterialID.Limonite, "Limonite", 5, 1000, 1000, 7, 1.3F, 2, 0f, YELLOW.toString(), 0xFF8040);
    	TConstructRegistry.addToolMaterial(MaterialID.Sapphire, "Sapphire", 4, 925, 900, 6, 1.3F, 2, 0f, BLUE.toString(), 0x2554C7);
    	TConstructRegistry.addToolMaterial(MaterialID.Amethyst, "Amethyst", 4, 750, 900, 9, 1.3F, 2, 0f, BLUE.toString(), 0x6C2DC7);
    	TConstructRegistry.addToolMaterial(MaterialID.Rosite, "Rosite", 5, 750, 1500, 12, 1.3F, 2, 0f, RED.toString(), 0xF62217);
    	TConstructRegistry.addToolMaterial(MaterialID.Varsium, "Varsium", 6, 1550, 900, 15, 1.3F, 2, 0f, GRAY.toString(), 0xFF8050);
    	TConstructRegistry.addToolMaterial(MaterialID.Lyon, "Lyon", 6, 750, 1550, 16, 1.3F, 2, 0f, GRAY.toString(), 0x7E3517);
    	TConstructRegistry.addToolMaterial(MaterialID.Mystite, "Mystite", 7, 750, 2000, 20, 1.3F, 2, 0f, AQUA.toString(), 0x93FFE8);
    	TConstructRegistry.addToolMaterial(MaterialID.Skeletal, "Skeletal", 7, 750, 2000, 24, 1.3F, 3, 0f, GRAY.toString(), 0xEAE9E3);
    	TConstructRegistry.addToolMaterial(MaterialID.Jade, "Jade", 7, 750, 2000, 11, 1.3F, 1, 1f, GRAY.toString(), 0x5EFB6E);
    	TConstructRegistry.addToolMaterial(MaterialID.Emberstone, "Emberstone", 7, 750, 2000, 26, 1.3F, 3, 0f, RED.toString(), 0x800517);
    	
    	TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Limonite);
    	TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Sapphire);
    	TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Amethyst);
    	TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Rosite);
    	TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Varsium);
    	TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Lyon);
    	TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Mystite);
    	TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Skeletal);
    	TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Jade);
    	TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Emberstone);
    	
    }
    
    public static final class MaterialID
    {        
        public static final int Limonite = 40;
        public static final int Sapphire = 41;
        public static final int Amethyst = 42;
        public static final int Rosite = 43;
        public static final int Varsium = 44;
        public static final int Lyon = 45;
        public static final int Mystite = 46;
        public static final int Skeletal = 47;
        public static final int Jade = 48;
        public static final int Emberstone = 49;
    }
    
}
