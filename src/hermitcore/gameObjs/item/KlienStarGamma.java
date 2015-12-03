package hermitcore.gameObjs.item;


import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import hermitcore.HECore;
import hermitcore.library.EMC.HEEMCHelper;
import moze_intel.projecte.api.item.IItemEmc;
import moze_intel.projecte.gameObjs.ObjHandler;
import moze_intel.projecte.gameObjs.items.ItemPE;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class KlienStarGamma extends Item implements IItemEmc
{

	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	public  KlienStarGamma(String unlocalizedName)
	{
		
		this.setUnlocalizedName(unlocalizedName);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setMaxStackSize(1);
		this.setNoRepair();
		//this.setTextureName(HECore.MODID + ":" + unlocalizedName);
		this.setCreativeTab(ObjHandler.cTab);
		
	}
	
	public static double getEmc(ItemStack stack)
	{
		if (stack.stackTagCompound == null)
		{
			stack.stackTagCompound = new NBTTagCompound();
		}
		
		return stack.stackTagCompound.getDouble("StoredEMC");
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack stack)
	{
		return stack.hasTagCompound();
	}
	
	@Override
	public double getDurabilityForDisplay(ItemStack stack)
	{
		double starEmc = this.getEmc(stack);
		
		if (starEmc == 0)
		{
			return 1.0D;
		}
		
		return 1.0D - starEmc / (double) HEEMCHelper.getKleinStarMaxEmc(stack);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) 
	{
		if (!stack.hasTagCompound())
		{
			stack.stackTagCompound = new NBTTagCompound();
		}
	}
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		if (stack.getItemDamage() > 5)
		{
			return "hi";
		}

		return super.getUnlocalizedName()+ "_" + (stack.getItemDamage() + 1);
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs cTab, List list)
	{
		for (int i = 0; i < 2; ++i)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1)
	{
		return icons[MathHelper.clamp_int(par1, 0, 5)];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register)
	{
		icons = new IIcon[2];
		
		for (int i = 0; i < 2; i++)
		{
			icons[i] = register.registerIcon(this.getTexture("stars", "klein_star_"+(i + 1)));
		}
	}
	
	@Override
	public double addEmc(ItemStack stack, double toAdd)
	{
		double add = Math.min(getMaximumEmc(stack) - getStoredEmc(stack), toAdd);
		ItemPE.addEmcToStack(stack, add);
		return add;
	}

	@Override
	public double extractEmc(ItemStack stack, double toRemove)
	{
		double sub = Math.min(getStoredEmc(stack), toRemove);
		ItemPE.removeEmc(stack, sub);
		return sub;
	}

	@Override
	public double getStoredEmc(ItemStack stack)
	{
		return ItemPE.getEmc(stack);
	}

	@Override
	public double getMaximumEmc(ItemStack stack)
	{
		return HEEMCHelper.getKleinStarMaxEmc(stack);
	}
	public String getTexture(String name)
	{
		return ("hermitcore:" + name);
	}
	
	public String getTexture(String folder, String name)
	{
		return ("hermitcore:" + folder + "/" + name);
	}
	

}
