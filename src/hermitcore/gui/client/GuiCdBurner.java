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
        
        //ElementSimple elementSchematicSlot = new ElementSimple(this, 8 , 8);
        //this.addElement(elementSchematicSlot);
	}
	
    @Override
    protected void updateElementInformation()
    {
        //elementChargingIcon.setIconToDraw(tileCdBurner.getFrontIcon());
    }

}
