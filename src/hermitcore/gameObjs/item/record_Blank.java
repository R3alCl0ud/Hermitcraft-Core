package hermitcore.gameObjs.item;

import hermitcore.HECore;
import hermitcore.library.HermitRegistry;
import net.minecraft.item.Item;

public class record_Blank extends Item 
{
	public record_Blank (String unlocalizedName)
	{
		this.setUnlocalizedName(unlocalizedName);
		this.setTextureName(HECore.MODID + ":" + unlocalizedName);
		this.setCreativeTab(HermitRegistry.recordTab);
	}
}
