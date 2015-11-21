package hermitcore.gameObjs.block.rf;

import hermitcore.gameObjs.tile.TileCdBurner;
import hermitcore.reference.Textures;
import hermitcore.utils.helper.TextureHelper;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cofh.core.render.IconRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCdBurner extends rfCd_Burner
{
	public static ItemStack itemCdBurnerCreative;
	public static ItemStack itemCdBurnerResonant;
	public static ItemStack itemCdBurnerRedstone;
	
	
	public BlockCdBurner()
	{
		super();
		
		this.setBlockName("CdBurner");
	}
	
	public void init()
	{
		TileCdBurner.init();
		
		itemCdBurnerCreative = new ItemStack(this, 1, 0);
		itemCdBurnerResonant = new ItemStack(this, 1, 2);
		itemCdBurnerRedstone = new ItemStack(this, 1, 1);
	}

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileCdBurner();
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List blockList)
    {
        blockList.add(new ItemStack(this, 1, 0));
        blockList.add(new ItemStack(this, 1, 2));
        blockList.add(new ItemStack(this, 1, 1));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        String[] types = {"Creative", "Redstone", "Resonant"};
        for (String type : types)
        {
            IconRegistry.addIcon("CdBurner_" + type + "_Active", Textures.CD_BURNER_BASE + type + "_Active", iconRegister);
            IconRegistry.addIcon("CdBurner_" + type + "_Inactive", Textures.CD_BURNER_BASE + type + "_Inactive", iconRegister);
        	//IconRegistry.
        }
    }

    public int getRenderType()
    {
    	return -1;
    }
    
    public boolean isOpaqueCube()
    {
    	return false;
    }
    
    public boolean renderAsNormalBlock()
    {
    	return false;
    }

    public void onBlockPlacedBy(World world, int i, int l, int k, EntityLiving entityLiving)
    {
    	int rotation = MathHelper.floor_double((double)((entityLiving.rotationYaw * 4F) / 360F) + 2.5D) & 3;
    }
    
    @Override
    public IIcon getActiveIcon(int meta)
    {
        return IconRegistry.getIcon("CdBurner_" + TextureHelper.metaToType(meta) + "_Active");
    }

    @Override
    public IIcon getInactiveIcon(int meta)
    {
        return IconRegistry.getIcon("CdBurner_" + TextureHelper.metaToType(meta) + "_Inactive");
    }
    public static enum Types
    {
        CREATIVE, REDSTONE, RESONANT;
    }

}
