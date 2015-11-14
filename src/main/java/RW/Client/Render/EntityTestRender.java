/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Client.Render;

import RW.Client.Model.Altar;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

/**
 * @author Lord_Crystalyx Created using Tabula 4.1.1
 */
public class EntityTestRender extends RenderLiving
{
	public static final Altar model = new Altar();
	public static final ResourceLocation textures = new ResourceLocation("rogueWorld:textures/misc/altar.png");

	public EntityTestRender()
	{
		super(model, 1.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		return textures;
	}

}
