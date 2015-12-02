package hermitcore.gameObjs.item;

import hermitcore.HECore;
import hermitcore.library.HermitRegistry;
import net.minecraft.item.Item;

public class itemVinyl extends Item 
{

	public itemVinyl (String unlocalizedName)
	{
		this.setUnlocalizedName(unlocalizedName);
		this.setTextureName(HECore.MODID + ":" + unlocalizedName);
		this.setCreativeTab(HermitRegistry.recordTab);
	}
}
