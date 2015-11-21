package hermitcore.gui.container;

import hermitcore.gameObjs.tile.TileHE;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class ContainerHEBase extends Container 
{

	public TileHE baseTile;

	public ContainerHEBase(InventoryPlayer inventoryPlayer, TileEntity tileEntity)
	{
		if (tileEntity instanceof TileHE)
		{
			this.baseTile = ((TileHE) tileEntity);
		}
		
		this.addPlayerInventory(inventoryPlayer);
	}
	
    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return (this.baseTile != null && this.baseTile.canInteractWith(player));
    }

    protected void addPlayerInventory(InventoryPlayer paramInventoryPlayer)
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                this.addSlotToContainer(new Slot(paramInventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; i++)
            this.addSlotToContainer(new Slot(paramInventoryPlayer, i, 8 + i * 18, 142));
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotID)
    {
        return null;
    }

}
