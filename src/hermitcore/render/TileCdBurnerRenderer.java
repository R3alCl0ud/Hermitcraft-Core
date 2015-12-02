package hermitcore.render;

import hermitcore.gameObjs.tile.TileCdBurner;
import hermitcore.render.model.CdBurner;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

public class TileCdBurnerRenderer extends TileEntitySpecialRenderer implements IItemRenderer 
{
	private final ResourceLocation CdBurner = new ResourceLocation("hermitcore:textures/blocks/cdburner/CdBurner.png");
	private final CdBurner model = new CdBurner();
	private RenderItem ghostItemRenderer;
	
	
	public TileCdBurnerRenderer ()
	{
		ghostItemRenderer = new RenderItem() {
			@Override
			public boolean shouldBob()
			{
				return false;
			}
		};
		ghostItemRenderer.setRenderManager(RenderManager.instance);
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return true;
	}

	
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		switch (type)
		{
			case ENTITY:
				renderAModelAt(0.0F, 1.0F, 0.0F, 0);
				break;
			case EQUIPPED:
				renderAModelAt(0.0F, 1.15F, 1.0F, 0);
				break;
			case EQUIPPED_FIRST_PERSON:
				renderAModelAt(-1.0F, 1.6F, -1.0F, 0);
				break;
			case INVENTORY:
				renderAModelAt(0.0F, 1.0F, 0.0F, 0);
				break;
			default:
				break;
		}
	}
	
	public void renderAModelAt(float x, float y, float z, int meta)
	{
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(CdBurner);
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(180, 1, 0, 0);
		GL11.glRotatef(-90, 0, 1, 0);
		model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float par8) 
	{
		if(tileEntity instanceof TileCdBurner)
		{
		TileCdBurner burner = ((TileCdBurner) tileEntity);
		
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glPushMatrix();
		bindTexture(CdBurner);
		GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslatef((float)x + 0.0F, (float)y + 0.0F, (float)z + 0.0F);
		GL11.glRotatef(90.0F, 0.0F, 0.0F, 90.0F);
		if (burner.getItemStack() != null)
		{
			EntityItem hover = new EntityItem(burner.getWorldObj());
			hover.hoverStart = 0.0F;
			hover.rotationPitch = 20F;
			hover.setEntityItemStack(burner.getItemStack());
			ghostItemRenderer.doRender(hover, 0.2, -0.625, 0.5, 0, 94.3F);

			
		}
		
		GL11.glPopMatrix();
		GL11.glEnable(GL11.GL_CULL_FACE);
						
		}
	}

}
