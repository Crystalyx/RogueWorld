package RW.Client.Render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

/**
 * @author Lord_Crystalyx Created using Tabula 4.1.1
 */
public class SphereRender extends Render
{
	public static final ResourceLocation textures = new ResourceLocation("rogueWorld:textures/misc/model/sphere.png");
	public static final IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation("rogueWorld:models/sphere.obj"));

	@Override
	public void doRender(Entity e, double x, double y, double z, float f1, float f2)
	{

		RenderHelper.disableStandardItemLighting();

		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);
		float scale = 0.25F;
		GL11.glScalef(scale, scale, scale);
		GL11.glRotatef(e.getRotationYawHead(), 0.0F, 1.0F, 0.0F);

		Minecraft.getMinecraft().getTextureManager().bindTexture(textures);

		model.renderAll();

		GL11.glPopMatrix();

		RenderHelper.enableStandardItemLighting();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		return null;
	}

}
