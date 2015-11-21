package hermitcore.gameObjs.tile;

import org.lwjgl.opengl.GL11;

import hermitcore.gameObjs.block.rfblockmodels.CdBurner;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class TileCdBurnerRenderer extends TileEntitySpecialRenderer 
{
	private static final ResourceLocation CdBurner = new ResourceLocation("/assets/hermitcore/textures/blocks/cdburner/CdBurner.png");
	private final CdBurner model = new CdBurner();
	
	public void renderAModelAt(TileCdBurner par1CdBurner, double par2, double par4, double par6, float par8)
	{
		int metadata = par1CdBurner.getBlockMetadata();
		int rotationAngle = 0;
		if(metadata%4 == 0) 
		{
			rotationAngle = 0;
		}
		if(metadata%4 == 1) 
		{
			rotationAngle = 270;
		}
		if(metadata%4 == 2) 
		{
			rotationAngle = 180;
		}
		if(metadata%4 == 3) 
		{
			rotationAngle = 90;
		}

		GL11.glTranslated((float)par2 + 0.5F, (float)par4 + 1.5F, (float)par6 + 0.5F);
		GL11.glScalef(1.0F, -1F, -1F);
		GL11.glRotatef(rotationAngle*90, 0.0F, 1.0F, 0.0F);
		this.bindTexture(CdBurner);
		this.model.renderModel(0.0625F);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double par2, double par4, double par6, float par8) 
	{
		
		 this.renderAModelAt((TileCdBurner)tileEntity, par2, par4, par6, par8);

	}

}
