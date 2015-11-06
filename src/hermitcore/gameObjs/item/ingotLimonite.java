package hermitcore.gameObjs.item;

import hermitcore.HECore;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ingotLimonite extends Item 
{
	public ingotLimonite(String unlocalizedName){
		this.setTextureName(HECore.MODID + ":" + unlocalizedName);
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}


}
