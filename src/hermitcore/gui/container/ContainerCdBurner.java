package hermitcore.gui.container;

import cofh.lib.gui.slot.*;
import hermitcore.gameObjs.tile.TileCdBurner;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class ContainerCdBurner extends ContainerHEBase
{
	
	TileCdBurner tileCdBurner;
	
	public ContainerCdBurner(InventoryPlayer inventoryPlayer, TileEntity tileEntity) 
	{
		super(inventoryPlayer, tileEntity);
		
		this.tileCdBurner = ((TileCdBurner) tileEntity);
		this.addSlotToContainer(new SlotEnergy(this.tileCdBurner, this.tileCdBurner.getChargeSlot(), 8, 53));
		//this.addSlotToContainer(new SlotSpecificItem(this.tileCdBurner, this.tileCdBurner.getSchematicSlot(), 16, 24, new ItemStack(Items.beef)));
	}

}
