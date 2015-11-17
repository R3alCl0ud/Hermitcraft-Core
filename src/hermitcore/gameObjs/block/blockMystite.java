package hermitcore.gameObjs.block;

import hermitcore.HECore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class blockMystite extends Block {

	public blockMystite(String unlocalizedName, Material material) {
		super(material);
		this.setBlockName(unlocalizedName);
		this.setBlockTextureName(HECore.MODID + ":" + unlocalizedName);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(2.0F);
		this.setResistance(6.0F);
		this.setHarvestLevel("pickaxe", 4);
		this.setStepSound(soundTypeMetal);

	}

}
