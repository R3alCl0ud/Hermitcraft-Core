package hermitcore.gameObjs.item;

import hermitcore.HECore;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class itemLaser extends Item 
{

	public itemLaser(String unlocalizedName)
	{
		this.setUnlocalizedName(unlocalizedName);
		this.setTextureName(HECore.MODID + ":" + unlocalizedName);
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}
	
}
