package hermitcore.gameObjs.block;

import cpw.mods.fml.relauncher.*;

import java.util.List;

import mantle.blocks.MantleBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.*;
import net.minecraft.util.IIcon;

public class HermitBlock extends MantleBlock
{

	
    public String[] textureNames;
    public IIcon[] icons;

    public HermitBlock(Material material, float hardness, String[] tex)
    {
        super(material);
        setHardness(hardness);
        
        textureNames = tex;
    }
    @Override
    public int damageDropped (int meta)
    {
        return meta;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons (IIconRegister iconRegister)
    {
        this.icons = new IIcon[textureNames.length];

        for (int i = 0; i < this.icons.length; ++i)
        {
            this.icons[i] = iconRegister.registerIcon("hermit:" + textureNames[i]);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon (int side, int meta)
    {
        return meta < icons.length ? icons[meta] : icons[0];
    }

    @SideOnly(Side.CLIENT)
    public int getSideTextureIndex (int side)
    {
        if (side == 0)
            return 2;
        if (side == 1)
            return 0;

        return 1;
    }


    @SuppressWarnings("unchecked")
	@Override
    public void getSubBlocks (Item block, CreativeTabs tab, @SuppressWarnings("rawtypes") List list)
    {
        for (int iter = 0; iter < icons.length; iter++)
        {
            list.add(new ItemStack(block, 1, iter));
        }
    }
}
