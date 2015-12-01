package hermitcore.gameObjs.item;

import hermitcore.HECore;
import hermitcore.library.HermitRegistry;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.ResourceLocation;

public class record_Chipstuff extends ItemRecord {

	public record_Chipstuff(String unlocalizedName) 
	{
		super(unlocalizedName);
		this.setUnlocalizedName(unlocalizedName);
		this.setTextureName(HECore.MODID + ":" + unlocalizedName);
		this.setCreativeTab(HermitRegistry.recordTab);
		this.maxStackSize = 1;
	}
	
	public ResourceLocation getRecordResource(String name)
	{
		return new ResourceLocation("hermitcore", "recordChipstuff");
	}
	

}
