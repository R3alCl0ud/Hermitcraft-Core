package hermitcore.gui.container;

import cofh.lib.gui.slot.SlotEnergy;
import hermitcore.gameObjs.tile.TileCdBurner;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

public class ContainerCdBurner extends ContainerHEBase
{
	
	TileCdBurner tileCdBurner;
	
	public ContainerCdBurner(InventoryPlayer inventoryPlayer, TileEntity tileEntity) 
	{
		super(inventoryPlayer, tileEntity);
		
		this.tileCdBurner = ((TileCdBurner) tileEntity);
		this.addSlotToContainer(new SlotEnergy(this.tileCdBurner, this.tileCdBurner.getChargeSlot(), 8, 53));
	}

}
