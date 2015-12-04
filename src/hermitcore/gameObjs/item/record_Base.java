package hermitcore.gameObjs.item;


import java.util.List;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import hermitcore.HECore;
import hermitcore.config.HermitCoreConfig;
import hermitcore.library.HermitRegistry;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class record_Base extends ItemRecord 
{
	
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	public record_Base(String unlocalizedName)
	{
		super(unlocalizedName);
		this.setUnlocalizedName(unlocalizedName);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setNoRepair();
		this.setCreativeTab(HermitRegistry.recordTab);
	}
	
	/**
	 * Adds unlocalized names to new records as defined in the config file
	 */
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		if (stack.getItemDamage() > HermitCoreConfig.recordName.length)
		{
			return "pe.debug.metainvalid";
		}

		return super.getUnlocalizedName()+ "_" + (stack.getItemDamage() + 1);
	}
	

	/**
	 * Adds new records as defined in the config file
	 * 
	 * @param item
	 * @param cTab
	 * @param list
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs cTab, List list)
	{
		for (int i = 0; i < HermitCoreConfig.recordName.length; ++i)
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
		icons = new IIcon[HermitCoreConfig.recordName.length];
		
		for (int i = 0; i < HermitCoreConfig.recordName.length; i++)
		{
			icons[i] = register.registerIcon(this.getTexture("records", HermitCoreConfig.recordName[i]+(i + 1)));
		}
	}
	public String getTexture(String name)
	{
		return ("hermitcore:" + name);
	}
	
	public String getTexture(String folder, String name)
	{
		return ("hermitcore:" + folder + "/" + name);
	}
	public ResourceLocation getRecordResource(String name)
	{				
		return new ResourceLocation(HECore.MODID, "/sounds");
	}
	

	
}
