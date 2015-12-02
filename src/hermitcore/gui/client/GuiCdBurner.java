package hermitcore.gui.client;

import cofh.lib.gui.element.*;
import hermitcore.gameObjs.tile.TileCdBurner;
import hermitcore.gameObjs.tile.TileHE;
import hermitcore.gui.container.ContainerCdBurner;
import hermitcore.gui.element.ElementIcon;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiCdBurner extends GuiHEBase 
{

    public static final String TEXTURE_PATH = "hermitcore:textures/gui/CdBurner.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);
    public TileCdBurner tileCdBurner;
    private ElementIcon elementChargingIcon;
    private ElementSimpleBox elementSchematicSlot;
    private ElementSimpleBox elementBlankRecordSlot;
	
	public GuiCdBurner(InventoryPlayer inventoryPlayer, TileHE tileEntity) 
	{
		super(new ContainerCdBurner(inventoryPlayer, tileEntity), TEXTURE, tileEntity);
		
		this.tileCdBurner = (TileCdBurner) tileEntity;
		this.name = this.tileCdBurner.getName();
	}
	
	public void initGui()
	{
		super.initGui();
		
        ElementEnergyStored elementEnergyStored = new ElementEnergyStored(this, 8, 8, this.tileCdBurner);
        this.addElement(elementEnergyStored);
        
        elementChargingIcon = new ElementIcon(this, 80, 30);
        this.addElement(elementChargingIcon);
        
        elementSchematicSlot = new ElementSimpleBox(this, 8 , 8, 1);
        this.addElement(elementSchematicSlot);
        
        elementBlankRecordSlot = new ElementSimpleBox(this, 8 , 8, 3);
        this.addElement(elementBlankRecordSlot);
	}
	
    @Override
    protected void updateElementInformation()
    {
        //elementChargingIcon.setIconToDraw(tileCdBurner.getFrontIcon());
    	elementBlankRecordSlot.drawBackground(9, 9, 1F);
    }

}
