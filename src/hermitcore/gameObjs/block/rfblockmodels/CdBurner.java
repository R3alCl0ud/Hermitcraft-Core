package hermitcore.gameObjs.block.rfblockmodels;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
//Exported java file
//Keep in mind that you still need to fill in some blanks
// - ZeuX

public class CdBurner extends ModelBase
{
	public CdBurner()
	{
		this.textureWidth = 64;
		this.textureHeight = 64;
		Top = new ModelRenderer(this, 0, 32);
		Top.addBox(0F, 0F, 0F, 16, 1, 16, 0F);
		Top.setRotationPoint(-8F, 11F, -8F);
		Top.rotateAngleX = 0.1396263F;
		Top.rotateAngleY = 0F;
		Top.rotateAngleZ = 0F;
		Top.mirror = false;
		base = new ModelRenderer(this, 0, 0);
		base.addBox(0F, 0F, 0F, 16, 3, 16, 0F);
		base.setRotationPoint(-8F, 21F, -8F);
		base.rotateAngleX = 0F;
		base.rotateAngleY = 0F;
		base.rotateAngleZ = 0F;
		base.mirror = false;
		legfrontright = new ModelRenderer(this, 6, 19);
		legfrontright.addBox(0F, 0F, 0F, 1, 10, 1, 0F);
		legfrontright.setRotationPoint(-8F, 11F, -8F);
		legfrontright.rotateAngleX = 0F;
		legfrontright.rotateAngleY = 0F;
		legfrontright.rotateAngleZ = 0F;
		legfrontright.mirror = false;
		legfrontleft = new ModelRenderer(this, 0, 19);
		legfrontleft.addBox(0F, 0F, 0F, 1, 10, 1, 0F);
		legfrontleft.setRotationPoint(7F, 11F, -8F);
		legfrontleft.rotateAngleX = 0F;
		legfrontleft.rotateAngleY = 0F;
		legfrontleft.rotateAngleZ = 0F;
		legfrontleft.mirror = false;
		legbackleft = new ModelRenderer(this, 12, 19);
		legbackleft.addBox(0F, 0F, 0F, 1, 12, 1, 0F);
		legbackleft.setRotationPoint(7F, 9F, 7F);
		legbackleft.rotateAngleX = 0F;
		legbackleft.rotateAngleY = 0F;
		legbackleft.rotateAngleZ = 0F;
		legbackleft.mirror = false;
		legbackright = new ModelRenderer(this, 18, 19);
		legbackright.addBox(0F, 0F, 0F, 1, 12, 1, 0F);
		legbackright.setRotationPoint(-8F, 9F, 7F);
		legbackright.rotateAngleX = 0F;
		legbackright.rotateAngleY = 0F;
		legbackright.rotateAngleZ = 0F;
		legbackright.mirror = false;
		lasertop = new ModelRenderer(this, 23, 19);
		lasertop.addBox(-1.5F, -0.5F, -1.5F, 3, 2, 3, 0F);
		lasertop.setRotationPoint(0F, 11F, 0F);
		lasertop.rotateAngleX = 0.1396263F;
		lasertop.rotateAngleY = 0F;
		lasertop.rotateAngleZ = 0F;
		lasertop.mirror = false;
		laserbottom = new ModelRenderer(this, 35, 19);
		laserbottom.addBox(-0.5F, -0.5F, -0.5F, 1, 2, 1, 0F);
		laserbottom.setRotationPoint(0F, 13F, 0.3F);
		laserbottom.rotateAngleX = 0.1396263F;
		laserbottom.rotateAngleY = 0F;
		laserbottom.rotateAngleZ = 0F;
		laserbottom.mirror = false;
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		base.render(f5);
		legfrontright.render(f5);
		legfrontleft.render(f5);
		legbackleft.render(f5);
		legbackright.render(f5);
		Top.render(f5);
		lasertop.render(f5);
		laserbottom.render(f5);
	}
	
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
	
	public void renderModel(float f5)
	{
		this.base.render(f5);
		this.legfrontright.render(f5);
		this.legfrontleft.render(f5);
		this.legbackleft.render(f5);
		this.legbackright.render(f5);
		this.Top.render(f5);
		this.lasertop.render(f5);
		this.laserbottom.render(f5);
	}
	
	//fields
	public ModelRenderer base;
	public ModelRenderer legfrontright;
	public ModelRenderer legfrontleft;
	public ModelRenderer legbackleft;
	public ModelRenderer legbackright;
	public ModelRenderer Top;
    public ModelRenderer lasertop;
    public ModelRenderer laserbottom;
}
