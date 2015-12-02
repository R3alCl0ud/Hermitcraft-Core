package hermitcore.gameObjs.item;

import hermitcore.HECore;
import hermitcore.library.HermitRegistry;
import net.minecraft.item.Item;

public class Schematic_Tbtn extends Item 
{
	public Schematic_Tbtn (String unlocalizedName)
	{
		this.setUnlocalizedName(unlocalizedName);
		this.setTextureName(HECore.MODID + ":" + "schematic");
		this.setCreativeTab(HermitRegistry.recordTab);
	}

}
