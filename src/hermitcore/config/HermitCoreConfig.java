package hermitcore.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraft.item.Item;

import java.io.File;

import hermitcore.HECore;
import hermitcore.utils.HELogger;

@SuppressWarnings("unused")
public final class HermitCoreConfig 
{
	public static boolean enableDebugLog;
	public static String toDelete[];
	public static String recordName[];
	public static int GUITopLeftYOffset;
    	public static int GUITopLeftXOffset;
	public static int totalRecords;

	public static void init(File configFile)
	{
		
		Configuration config = new Configuration(configFile);
		
		try
		{
			config.load();
			
			enableDebugLog = config.getBoolean("debugLogging", "misc", false, "Enable a more verbose debug logging");
		
            		GUITopLeftXOffset = config.get("rendering", "Rendering.GUITopLeftXOffset", 0).getInt(0);
            		GUITopLeftYOffset = config.get("rendering", "Rendering.GUITopLeftYOffset", 0).getInt(0);
			
			toDelete = config.get("removeItems", "recipes", new String[] {"minecraft:iron_hoe",  "minecraft:iron_pickaxe", "minecraft:iron_shovel", "minecraft:iron_sword", "minecraft:iron_axe", "minecraft:stone_hoe",  "minecraft:stone_pickaxe", "minecraft:stone_shovel", "minecraft:stone_sword", "minecraft:stone_axe", "minecraft:golden_hoe",  "minecraft:golden_pickaxe", "minecraft:golden_shovel", "minecraft:golden_sword", "minecraft:golden_axe", "minecraft:diamond_hoe",  "minecraft:diamond_pickaxe", "minecraft:diamond_shovel", "minecraft:diamond_sword", "minecraft:diamond_axe"}, "Put Items to remove crafting recipes here. Format: modname:itemname").getStringList();
			
			recordName = config.get("recordNames", "customRecords", new String[] {"PinguTrap"}, "on new line put name of song Ex: For Pingu Trap Remix, PinguTrap").getStringList();
			//totalRecords = config.get("totalRecords", "customRecords", )
			
			HELogger.logInfo("Loaded configuration file.");
		}
		catch (Exception e)
		{
			HELogger.logFatal("Caught exception while loading config file!");
			e.printStackTrace();
		}
		finally
		{
			if(config.hasChanged())
			{
				config.save();
			}
		}
	}
    public static File configFile(String fileName) { return new File(HECore.CONFIG_DIR, fileName); }
}

