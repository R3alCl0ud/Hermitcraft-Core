package hourofcode.modjam.objects;

import hourofcode.modjam.HourOfCode;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class Program extends Item 
{

	public Program(String unlocalizedName)
	{
		this.setUnlocalizedName(unlocalizedName);
		this.setTextureName(HourOfCode.MODID + ":" + unlocalizedName);
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}
}
