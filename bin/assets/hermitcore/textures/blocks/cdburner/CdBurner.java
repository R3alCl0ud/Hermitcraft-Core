package net.minecraft.src;
//Exported java file
//Keep in mind that you still need to fill in some blanks
// - ZeuX

public class CdBurner extends ModelBase
{
	public CdBurner()
	{
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
		legbackleft.addBox(0F, 0F, 0F, 1, 10, 1, 0F);
		legbackleft.setRotationPoint(7F, 11F, 7F);
		legbackleft.rotateAngleX = 0F;
		legbackleft.rotateAngleY = 0F;
		legbackleft.rotateAngleZ = 0F;
		legbackleft.mirror = false;
		legbackright = new ModelRenderer(this, 18, 19);
		legbackright.addBox(0F, 0F, 0F, 1, 10, 1, 0F);
		legbackright.setRotationPoint(-8F, 11F, 7F);
		legbackright.rotateAngleX = 0F;
		legbackright.rotateAngleY = 0F;
		legbackright.rotateAngleZ = 0F;
		legbackright.mirror = false;
		lasertop = new ModelRenderer(this, 23, 19);
		lasertop.addBox(-2F, 0F, -2F, 4, 2, 4, 0F);
		lasertop.setRotationPoint(0F, 11F, 0F);
		lasertop.rotateAngleX = 0F;
		lasertop.rotateAngleY = 0F;
		lasertop.rotateAngleZ = 0F;
		lasertop.mirror = false;
		laserbottom = new ModelRenderer(this, 35, 19);
		laserbottom.addBox(-0.5F, 0F, -0.5F, 1, 2, 1, 0F);
		laserbottom.setRotationPoint(0F, 13F, 0.3F);
		laserbottom.rotateAngleX = 0F;
		laserbottom.rotateAngleY = 0F;
		laserbottom.rotateAngleZ = 0F;
		laserbottom.mirror = false;
		LeftIn = new ModelRenderer(this, 0, 32);
		LeftIn.addBox(0F, 0F, 0F, 1, 1, 14, 0F);
		LeftIn.setRotationPoint(1F, 10F, -7F);
		LeftIn.rotateAngleX = 0F;
		LeftIn.rotateAngleY = 0F;
		LeftIn.rotateAngleZ = 0F;
		LeftIn.mirror = false;
		RightIn = new ModelRenderer(this, 0, 32);
		RightIn.addBox(0F, 0F, 0F, 1, 1, 14, 0F);
		RightIn.setRotationPoint(-2F, 10F, -7F);
		RightIn.rotateAngleX = 0F;
		RightIn.rotateAngleY = 0F;
		RightIn.rotateAngleZ = 0F;
		RightIn.mirror = false;
		FrontIn = new ModelRenderer(this, 0, 48);
		FrontIn.addBox(0F, 0F, 0F, 14, 1, 1, 0F);
		FrontIn.setRotationPoint(-7F, 10F, 1F);
		FrontIn.rotateAngleX = 0F;
		FrontIn.rotateAngleY = 0F;
		FrontIn.rotateAngleZ = 0F;
		FrontIn.mirror = false;
		BackIn = new ModelRenderer(this, 0, 48);
		BackIn.addBox(0F, 0F, 0F, 14, 1, 1, 0F);
		BackIn.setRotationPoint(-7F, 10F, -2F);
		BackIn.rotateAngleX = 0F;
		BackIn.rotateAngleY = 0F;
		BackIn.rotateAngleZ = 0F;
		BackIn.mirror = false;
		LeftOut = new ModelRenderer(this, 0, 32);
		LeftOut.addBox(0F, 0F, 0F, 1, 1, 14, 0F);
		LeftOut.setRotationPoint(7F, 10F, -7F);
		LeftOut.rotateAngleX = 0F;
		LeftOut.rotateAngleY = 0F;
		LeftOut.rotateAngleZ = 0F;
		LeftOut.mirror = false;
		RightOut = new ModelRenderer(this, 0, 32);
		RightOut.addBox(0F, 0F, 0F, 1, 1, 14, 0F);
		RightOut.setRotationPoint(-8F, 10F, -7F);
		RightOut.rotateAngleX = 0F;
		RightOut.rotateAngleY = 0F;
		RightOut.rotateAngleZ = 0F;
		RightOut.mirror = false;
		BackOut = new ModelRenderer(this, 0, 51);
		BackOut.addBox(0F, 0F, 0F, 16, 1, 1, 0F);
		BackOut.setRotationPoint(-8F, 10F, 7F);
		BackOut.rotateAngleX = 0F;
		BackOut.rotateAngleY = 0F;
		BackOut.rotateAngleZ = 0F;
		BackOut.mirror = false;
		FrontOut = new ModelRenderer(this, 0, 54);
		FrontOut.addBox(0F, 0F, 0F, 16, 1, 1, 0F);
		FrontOut.setRotationPoint(-8F, 10F, -8F);
		FrontOut.rotateAngleX = 0F;
		FrontOut.rotateAngleY = 0F;
		FrontOut.rotateAngleZ = 0F;
		FrontOut.mirror = false;
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		base.render(f5);
		legfrontright.render(f5);
		legfrontleft.render(f5);
		legbackleft.render(f5);
		legbackright.render(f5);
		lasertop.render(f5);
		laserbottom.render(f5);
		LeftIn.render(f5);
		RightIn.render(f5);
		FrontIn.render(f5);
		BackIn.render(f5);
		LeftOut.render(f5);
		RightOut.render(f5);
		BackOut.render(f5);
		FrontOut.render(f5);
	}
	
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5);
	}
	
	//fields
	public ModelRenderer base;
	public ModelRenderer legfrontright;
	public ModelRenderer legfrontleft;
	public ModelRenderer legbackleft;
	public ModelRenderer legbackright;
	public ModelRenderer lasertop;
	public ModelRenderer laserbottom;
	public ModelRenderer LeftIn;
	public ModelRenderer RightIn;
	public ModelRenderer FrontIn;
	public ModelRenderer BackIn;
	public ModelRenderer LeftOut;
	public ModelRenderer RightOut;
	public ModelRenderer BackOut;
	public ModelRenderer FrontOut;
}
