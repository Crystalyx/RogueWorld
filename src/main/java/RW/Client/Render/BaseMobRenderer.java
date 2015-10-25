package RW.Client.Render;

import org.lwjgl.opengl.GL11;

import RW.Client.Model.Altar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class BaseMobRenderer extends Render
{

	public BaseMobRenderer(ModelBase mdl, float scl, ResourceLocation text)
	{
		this.model = mdl;
		this.scale = scl;
		this.textures = text;
	}

	public float scale;
	public ModelBase model;
	public static ResourceLocation textures = new ResourceLocation("rogueWorld:textures/misc/altar.png");

	@Override
	public void doRender(Entity e, double x, double y, double z, float rotX, float rotY)
	{
		GL11.glPushMatrix();

		GL11.glTranslatef((float) x, (float) y+2, (float) z);
		float scale = 0.1F;
		GL11.glScalef(scale, scale, scale);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		
		if(this.model != null)
		this.model.render(e, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		
		if(this.textures != null)
		Minecraft.getMinecraft().getTextureManager().bindTexture(this.textures);

		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		return this.textures;
	}

}
