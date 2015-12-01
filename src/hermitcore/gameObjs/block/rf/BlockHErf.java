package hermitcore.gameObjs.block.rf;

import hermitcore.gameObjs.tile.TileHE;
import hermitcore.gameObjs.tile.TileInventory;
import hermitcore.library.HermitRegistry;
import hermitcore.utils.helper.WorldHelper;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.lib.util.helpers.ServerHelper;

public class BlockHErf extends Block
{
	public BlockHErf ()
	{
		this(Material.iron);
	}
	

	public BlockHErf(Material material) {
		super(material);
		this.setCreativeTab(HermitRegistry.recordTab);
	}
	
    public static ArrayList<ItemStack> dismantleBlockInWorld(EntityPlayer player, World world, int x, int y, int z, boolean returnDrops)
    {
        Block block = world.getBlock(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);

        ItemStack drop = new ItemStack(block, 1, block.damageDropped(meta));
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        drops.add(drop);

        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof TileInventory)
        {
            TileInventory tileInventory = (TileInventory) tile;

            for (ItemStack itemStack : tileInventory.inventory)
            {
                if (itemStack != null) drops.add(itemStack);
            }

            tileInventory.inventory = new ItemStack[tileInventory.inventory.length];
        }

        if (tile instanceof TileHE)
        {
            TileHE tileHE = (TileHE) tile;

            if (tileHE.hasItemState())
            {
                drop.setTagCompound(new NBTTagCompound());
                tileHE.writeStateToNBT(drop.stackTagCompound);
            }
        }

        world.setBlockToAir(x, y, z);

        if (!returnDrops)
        {
            for (ItemStack itemStack : drops)
            {
                WorldHelper.spawnItemInWorldWithRandomness(itemStack, world, 0.3F, x, y, z, 2);
            }
        }

        return drops;
    }    
    
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
    {
        if (world.getTileEntity(x, y, z) instanceof TileHE)
        {
            int direction = 0;
            int facing = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

            if (facing == 0)
            {
                direction = ForgeDirection.NORTH.ordinal();
            } else if (facing == 1)
            {
                direction = ForgeDirection.EAST.ordinal();
            } else if (facing == 2)
            {
                direction = ForgeDirection.SOUTH.ordinal();
            } else if (facing == 3)
            {
                direction = ForgeDirection.WEST.ordinal();
            }

            ((TileHE) world.getTileEntity(x, y, z)).setOrientation(direction);
        }
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float fx, float fy, float fz)
    {
        TileHE tileHE = (TileHE) world.getTileEntity(x, y, z);
        if (tileHE == null) return false;

        if (tileHE.hasGui())
        {
            if (ServerHelper.isServerWorld(world))
            {
                tileHE.openGui(player);
            }

            return true;
        }

        return false;
    }







}
