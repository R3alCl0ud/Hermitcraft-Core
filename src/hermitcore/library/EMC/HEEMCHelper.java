package hermitcore.library.EMC;

import net.minecraft.item.ItemStack;

public class HEEMCHelper {
	
	public static int getKleinStarMaxEmc(ItemStack stack)
	{
		return HEConstants.MAX_KLEIN_EMC[stack.getItemDamage()];
	}

}
