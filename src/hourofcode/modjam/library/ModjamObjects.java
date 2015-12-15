package hourofcode.modjam.library;

import cpw.mods.fml.common.registry.GameRegistry;
import hourofcode.modjam.objects.Program;
import hourofcode.modjam.objects.lineCode;
import net.minecraft.item.Item;

public class ModjamObjects 

{

	
	public static Item lineCode;
	public static Item Program;
	
	public static void register()
	{
		GameRegistry.registerItem(lineCode = new lineCode("lineCode"), "lineCode");
		GameRegistry.registerItem(Program = new Program("Program"), "Program");
	}
	
	
}
