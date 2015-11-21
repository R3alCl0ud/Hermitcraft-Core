package hermitcore.gameObjs.block.rf;

import java.util.ArrayList;

import hermitcore.gameObjs.tile.TileBurner;
import hermitcore.library.HermitRegistry;
import hermitcore.utils.helper.TextureHelper;
import cofh.api.block.IDismantleable;
import cofh.core.render.IconRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;


public abstract class rfCd_Burner extends BlockHErf implements ITileEntityProvider, IDismantleable 
{
	public rfCd_Burner()
	{
		super(Material.iron);
		this.setCreativeTab(HermitRegistry.recordTab);
        setHardness(10.0f);
        setResistance(20.0f);
	}
	
    public IIcon getPrimaryIcon(int meta, TileBurner tile)
    {
        return tile.isActive ? this.getActiveIcon(meta) : this.getInactiveIcon(meta);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        ForgeDirection orientation = ForgeDirection.getOrientation(side);
        if (orientation == ForgeDirection.UP) return this.getTopIcon(meta);
        else if (orientation == ForgeDirection.DOWN) return this.getBottomIcon(meta);
        else if (orientation == ForgeDirection.SOUTH) return this.getInactiveIcon(meta);

        return this.getSideIcon(meta);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int iSide)
    {
        TileEntity tileEntity = blockAccess.getTileEntity(x, y, z);
        int meta = blockAccess.getBlockMetadata(x, y, z);
        if (!(tileEntity instanceof TileBurner)) return this.getSideIcon(meta);

        TileBurner tile = (TileBurner) tileEntity;
        ForgeDirection out = tile.getOrientation();
        ForgeDirection side = ForgeDirection.getOrientation(iSide);

        if (out == side) return this.getPrimaryIcon(meta, tile);

        if (side == ForgeDirection.UP) return this.getTopIcon(meta);
        else if (side == ForgeDirection.DOWN) return this.getBottomIcon(meta);
        else return this.getSideIcon(meta);
    }
    
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof TileBurner)
        {
            int direction = -1;

            if (MathHelper.abs((float) entityLiving.posX - (float) x) < 2.0F && MathHelper.abs((float) entityLiving.posZ - (float) z) < 2.0F)
            {
                double d0 = entityLiving.posY + 1.82D - (double) entityLiving.yOffset;
                if (d0 - (double) y > 2.0D)
                {
                    direction = ForgeDirection.UP.ordinal();
                }
                if ((double) y - d0 > 0.0D)
                {
                    direction = ForgeDirection.DOWN.ordinal();
                }
            }

            TileBurner tile = (TileBurner) tileEntity;
            if (direction == -1) super.onBlockPlacedBy(world, x, y, z, entityLiving, itemStack);
            else tile.setOrientation(direction);

            NBTTagCompound nbtTagCompound = itemStack.stackTagCompound;
            if (nbtTagCompound == null) nbtTagCompound = new NBTTagCompound();

            tile.readStateFromNBT(nbtTagCompound);
        }
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int faceHit, float par7, float par8, float par9)
    {
        return super.onBlockActivated(world, x, y, z, player, faceHit, par7, par8, par9);
    }

    @Override
    public ArrayList<ItemStack> dismantleBlock(EntityPlayer player, World world, int x, int y, int z, boolean returnDrops)
    {
        return BlockHErf.dismantleBlockInWorld(player, world, x, y, z, returnDrops);
    }

    @Override
    public boolean canDismantle(EntityPlayer player, World world, int x, int y, int z)
    {
        return true;
    }

    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z)
    {
        return false;
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }
    
    @Override
    public boolean rotateBlock(World worldObj, int x, int y, int z, ForgeDirection axis)
    {
        TileEntity tile = worldObj.getTileEntity(x, y, z);
        if (!(tile instanceof TileBurner)) return false;

        TileBurner TilePad = (TileBurner) tile;
        return TilePad.setFacing(axis.ordinal());
    }

    @Override
    public ForgeDirection[] getValidRotations(World worldObj, int x, int y, int z)
    {
        return ForgeDirection.VALID_DIRECTIONS;
    }
    
    public abstract IIcon getActiveIcon(int meta);

    public abstract IIcon getInactiveIcon(int meta);

    public IIcon getTopIcon(int meta)
    {
        return IconRegistry.getIcon("Machine_" + TextureHelper.metaToType(meta) + "_Top");
    }

    public IIcon getBottomIcon(int meta)
    {
        return IconRegistry.getIcon("Machine_" + TextureHelper.metaToType(meta) + "_Bottom");
    }

    public IIcon getSideIcon(int meta)
    {
        return IconRegistry.getIcon("Machine_" + TextureHelper.metaToType(meta) + "_Side");
    }

}