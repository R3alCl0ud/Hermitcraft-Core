package r3alcl0ud.hermitcore.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraft.item.Item;

import java.io.File;

import r3alcl0ud.hermitcore.utils.HELogger;

public final class HermitCoreConfig 
{
	public static boolean enableDebugLog;
	public static String RemoveIHoe;
	public static String RemoveIPick;
	public static String RemoveISpade;
	public static String RemoveIAxe;
	public static String RemoveISword;
	public static String RemoveGHoe;
	public static String RemoveGPick;
	public static String RemoveGSpade;
	public static String RemoveGAxe;
	public static String RemoveGSword;
	public static String RemoveWHoe;
	public static String RemoveWPick;
	public static String RemoveWSpade;
	public static String RemoveWAxe;
	public static String RemoveWSword;
	public static String RemoveDHoe;
	public static String RemoveDPick;
	public static String RemoveDSpade;
	public static String RemoveDAxe;
	public static String RemoveDSword;
	public static String RemoveSHoe;
	public static String RemoveSPick;
	public static String RemoveSSpade;
	public static String RemoveSAxe;
	public static String RemoveSSword;
	public static String toDelete[];

	
	public static void init(File configFile)
	{
		Configuration config = new Configuration(configFile);
		
		try
		{
			config.load();
			
			
			enableDebugLog = config.getBoolean("debugLogging", "misc", true, "Enable a more verbose debug logging");
			RemoveIHoe = config.getString("removeIHoe", "recipes", "minecraft:iron_hoe", "Removes Iron Hoe");
			RemoveIPick = config.getString("removeIPick", "recipes", "minecraft:iron_pickaxe", "Removes Iron Pickaxe");
			RemoveISpade = config.getString("removeISpade", "recipes", "minecraft:iron_shovel", "Removes Iron Spade");
			RemoveIAxe = config.getString("removeIAxe", "recipes", "minecraft:iron_axe", "Removes Iron Axe");
			RemoveISword = config.getString("removeISword", "recipes", "minecraft:iron_sword", "Removes Iron Sword");
			
			RemoveDHoe = config.getString("removeDHoe", "recipes", "minecraft:diamond_hoe", "Removes Diamond Hoe");
			RemoveDPick = config.getString("removeDPick", "recipes", "minecraft:diamond_pickaxe", "Removes Diamond Pickaxe");
			RemoveDSpade = config.getString("removeDSpade", "recipes", "minecraft:diamond_shovel", "Removes Diamond Spade");
			RemoveDAxe = config.getString("removeDAxe", "recipes", "minecraft:diamond_axe", "Removes Diamond Axe");
			RemoveDSword = config.getString("removeDSword", "recipes", "minecraft:diamond_sword", "Removes Diamond Sword");
			
			RemoveGHoe = config.getString("removeGHoe", "recipes", "minecraft:golden_hoe", "Removes Golden Hoe");
			RemoveGPick = config.getString("removeGPick", "recipes", "minecraft:golden_pickaxe", "Removes Iron Pickaxe");
			RemoveGSpade = config.getString("removeGSpade", "recipes", "minecraft:golden_shovel", "Removes Iron Spade");
			RemoveGAxe = config.getString("removeGAxe", "recipes", "minecraft:golden_axe", "Removes Iron Axe");
			RemoveGSword = config.getString("removeGSword", "recipes", "minecraft:golden_sword", "Removes Iron Sword");
			
			RemoveSHoe = config.getString("removeSHoe", "recipes", "minecraft:stone_hoe", "Removes Stone Hoe");
			RemoveSPick = config.getString("removeSPick", "recipes", "minecraft:stone_pickaxe", "Removes Stone Pickaxe");
			RemoveSSpade = config.getString("removeSSpade", "recipes", "minecraft:stone_shovel", "Removes Stone Spade");
			RemoveSAxe = config.getString("removeSAxe", "recipes", "minecraft:stone_axe", "Removes Stone Axe");
			RemoveSSword = config.getString("removeSSword", "recipes", "minecraft:stone_sword", "Removes Stone Sword");
			
			RemoveWHoe = config.getString("removeWHoe", "recipes", "minecraft:wooden_hoe", "Removes Wooden Hoe");
			RemoveWPick = config.getString("removeWPick", "recipes", "minecraft:wooden_pickaxe", "Removes Wooden Pickaxe");
			RemoveWSpade = config.getString("removeWSpade", "recipes", "minecraft:wooden_shovel", "Removes Wooden Spade");
			RemoveWAxe = config.getString("removeWAxe", "recipes", "minecraft:wooden_axe", "Removes Wooden Axe");
			RemoveWSword = config.getString("removeWSword", "recipes", "minecraft:wooden_sword", "Removes Wooden Sword");
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
}
