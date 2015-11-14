package RW.Client.Render;

import org.lwjgl.opengl.GL11;

import RW.Client.Model.Altar;
import RW.Core.RogueWorldCore;
import RW.Utils.MathUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

/**
 * @author Lord_Crystalyx Created using Tabula 4.1.1
 */
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
		RogueWorldCore.proxy.spawnParticle("spark", x, y, z, (float) MathUtils.getIntInRange(10) / 10, (float) MathUtils.getIntInRange(10) / 10, (float) MathUtils.getIntInRange(10) / 10);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		return this.textures;
	}

}
