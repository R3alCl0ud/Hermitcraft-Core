package hermitcore.tcon.smeltery.blocks;

import cpw.mods.fml.relauncher.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fluids.*;
import hermitcore.tcon.smeltery.HermitSmeltery;

public class LiquidMetalFinite extends BlockFluidFinite
{

	public LiquidMetalFinite(Fluid fluid, String texture) {
		super(fluid, HermitSmeltery.liquidMetal);
		
	}

}
