package hermitcore.gameObjs.item.rf;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cofh.api.energy.IEnergyContainerItem;
import hermitcore.gameObjs.block.ItemBlockBasic;
import hermitcore.gameObjs.tile.TileCdBurner;

public class ItemCdBurner extends ItemBlockBasic implements IEnergyContainerItem
{

	public ItemCdBurner(Block block) 
	{
		super(block);
		
		
	}
	
    public void checkAndSetDefaultTag(ItemStack stack)
    {
        if (stack.stackTagCompound == null)
        {
            stack.setTagCompound(new NBTTagCompound());
            TileCdBurner.writeDefaultTag(stack.stackTagCompound);
        }
    }

    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate)
    {
        this.checkAndSetDefaultTag(container);

        int energy = container.stackTagCompound.getInteger("Energy");
        int energyReceived = Math.min(this.getMaxEnergyStored(container) - energy, Math.min(TileCdBurner.RECEIVE[container.getItemDamage()], maxReceive));

        if (!simulate)
        {
            energy += energyReceived;
            container.stackTagCompound.setInteger("Energy", energy);
        }

        return energyReceived;
    }
    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate)
    {
        return 0;
    }

	@Override
	public int getEnergyStored(ItemStack container) 
	{
        if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy"))
        {
            return 0;
        }
        return container.stackTagCompound.getInteger("Energy");
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) 
	{
		return TileCdBurner.CAPACITY[container.getItemDamage()];
	}

}
