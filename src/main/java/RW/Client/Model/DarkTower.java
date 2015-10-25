/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Client.Model;

import org.lwjgl.opengl.GL11;

import RW.Client.Render.EnergyTowerRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

/**
 * DarkTower - Lord_Crystalyx Created using Tabula 4.1.1
 */
public class DarkTower extends ModelBase implements IItemRenderer
{
	public ModelRenderer shape1;
	public ModelRenderer shape2;
	public ModelRenderer shape3;
	public ModelRenderer shape4;
	public ModelRenderer shape5;
	public ModelRenderer shape6;
	public ModelRenderer shape7;
	public ModelRenderer shape8;

	public DarkTower()
	{
		this.textureWidth = 256;
		this.textureHeight = 128;
		this.shape5 = new ModelRenderer(this, 0, 0);
		this.shape5.setRotationPoint(-5.0F, 17.0F, 4.0F);
		this.shape5.addBox(0.0F, 0.0F, 0.0F, 2, 8, 2, 0.0F);
		this.shape3 = new ModelRenderer(this, 0, 35);
		this.shape3.setRotationPoint(-6.0F, 21.0F, -6.0F);
		this.shape3.addBox(0.0F, 0.0F, 0.0F, 24, 2, 24, 0.0F);
		this.shape7 = new ModelRenderer(this, 0, 0);
		this.shape7.setRotationPoint(4.0F, 17.0F, -5.0F);
		this.shape7.addBox(0.0F, 0.0F, 0.0F, 2, 8, 2, 0.0F);
		this.shape6 = new ModelRenderer(this, 0, 0);
		this.shape6.setRotationPoint(-5.0F, 17.0F, -5.0F);
		this.shape6.addBox(0.0F, 0.0F, 0.0F, 2, 8, 2, 0.0F);
		this.shape2 = new ModelRenderer(this, 0, 63);
		this.shape2.setRotationPoint(-7.0F, 22.0F, -7.0F);
		this.shape2.addBox(0.0F, 0.0F, 0.0F, 28, 2, 28, 0.0F);
		this.shape4 = new ModelRenderer(this, 11, 0);
		this.shape4.setRotationPoint(-1.0F, 13.0F, -1.0F);
		this.shape4.addBox(0.0F, 0.0F, 0.0F, 4, 16, 4, 0.0F);
		this.shape1 = new ModelRenderer(this, 0, 94);
		this.shape1.setRotationPoint(-8.0F, 23.0F, -8.0F);
		this.shape1.addBox(0.0F, 0.0F, 0.0F, 32, 2, 32, 0.0F);
		this.shape8 = new ModelRenderer(this, 0, 0);
		this.shape8.setRotationPoint(3.75F, 17.0F, 4.0F);
		this.shape8.addBox(0.0F, 0.0F, 0.0F, 2, 8, 2, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape5.offsetX, this.shape5.offsetY, this.shape5.offsetZ);
		GL11.glTranslatef(this.shape5.rotationPointX * f5, this.shape5.rotationPointY * f5, this.shape5.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-this.shape5.offsetX, -this.shape5.offsetY, -this.shape5.offsetZ);
		GL11.glTranslatef(-this.shape5.rotationPointX * f5, -this.shape5.rotationPointY * f5, -this.shape5.rotationPointZ * f5);
		this.shape5.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape3.offsetX, this.shape3.offsetY, this.shape3.offsetZ);
		GL11.glTranslatef(this.shape3.rotationPointX * f5, this.shape3.rotationPointY * f5, this.shape3.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-this.shape3.offsetX, -this.shape3.offsetY, -this.shape3.offsetZ);
		GL11.glTranslatef(-this.shape3.rotationPointX * f5, -this.shape3.rotationPointY * f5, -this.shape3.rotationPointZ * f5);
		this.shape3.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape7.offsetX, this.shape7.offsetY, this.shape7.offsetZ);
		GL11.glTranslatef(this.shape7.rotationPointX * f5, this.shape7.rotationPointY * f5, this.shape7.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-this.shape7.offsetX, -this.shape7.offsetY, -this.shape7.offsetZ);
		GL11.glTranslatef(-this.shape7.rotationPointX * f5, -this.shape7.rotationPointY * f5, -this.shape7.rotationPointZ * f5);
		this.shape7.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape6.offsetX, this.shape6.offsetY, this.shape6.offsetZ);
		GL11.glTranslatef(this.shape6.rotationPointX * f5, this.shape6.rotationPointY * f5, this.shape6.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-this.shape6.offsetX, -this.shape6.offsetY, -this.shape6.offsetZ);
		GL11.glTranslatef(-this.shape6.rotationPointX * f5, -this.shape6.rotationPointY * f5, -this.shape6.rotationPointZ * f5);
		this.shape6.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape2.offsetX, this.shape2.offsetY, this.shape2.offsetZ);
		GL11.glTranslatef(this.shape2.rotationPointX * f5, this.shape2.rotationPointY * f5, this.shape2.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-this.shape2.offsetX, -this.shape2.offsetY, -this.shape2.offsetZ);
		GL11.glTranslatef(-this.shape2.rotationPointX * f5, -this.shape2.rotationPointY * f5, -this.shape2.rotationPointZ * f5);
		this.shape2.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape4.offsetX, this.shape4.offsetY, this.shape4.offsetZ);
		GL11.glTranslatef(this.shape4.rotationPointX * f5, this.shape4.rotationPointY * f5, this.shape4.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-this.shape4.offsetX, -this.shape4.offsetY, -this.shape4.offsetZ);
		GL11.glTranslatef(-this.shape4.rotationPointX * f5, -this.shape4.rotationPointY * f5, -this.shape4.rotationPointZ * f5);
		this.shape4.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape1.offsetX, this.shape1.offsetY, this.shape1.offsetZ);
		GL11.glTranslatef(this.shape1.rotationPointX * f5, this.shape1.rotationPointY * f5, this.shape1.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-this.shape1.offsetX, -this.shape1.offsetY, -this.shape1.offsetZ);
		GL11.glTranslatef(-this.shape1.rotationPointX * f5, -this.shape1.rotationPointY * f5, -this.shape1.rotationPointZ * f5);
		this.shape1.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(this.shape8.offsetX, this.shape8.offsetY, this.shape8.offsetZ);
		GL11.glTranslatef(this.shape8.rotationPointX * f5, this.shape8.rotationPointY * f5, this.shape8.rotationPointZ * f5);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		GL11.glTranslatef(-this.shape8.offsetX, -this.shape8.offsetY, -this.shape8.offsetZ);
		GL11.glTranslatef(-this.shape8.rotationPointX * f5, -this.shape8.rotationPointY * f5, -this.shape8.rotationPointZ * f5);
		this.shape8.render(f5);
		GL11.glPopMatrix();
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
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
		GL11.glPushMatrix();
		GL11.glRotatef(180F, 0F, 0F, 359F);
		GL11.glTranslatef(0.0F, -2.0F, 0.0F);
		float scale = 1.0F;
		GL11.glScalef(2.0F, 2.0F, 2.0F);
		EnergyTowerRender.model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		Minecraft.getMinecraft().renderEngine.bindTexture(EnergyTowerRender.textures);
		GL11.glPopMatrix();
	}
}
