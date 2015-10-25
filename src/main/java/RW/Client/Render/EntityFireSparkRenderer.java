/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Client.Render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import RW.Client.Model.Altar;

public class EntityFireSparkRenderer extends Render
{

	public EntityFireSparkRenderer()
    {
	    super();	    
    }
	
	public static final Altar model = new Altar();
	public static final ResourceLocation textures = new ResourceLocation("rogueWorld:textures/misc/altar.png");


	@Override
    public void doRender(Entity e, double x, double y, double z, float p_76986_8_, float p_76986_9_)
    {    	
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x+0.5F, (float)y, (float)z+0.5F);
        float scale = 1F;
        GL11.glScalef(scale, scale, scale);
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(textures);   
        this.model.render(e, (float)e.posX-0.5F, (float)e.posY-0.5F, (float)e.posZ-0.5F, (float)e.posX+0.5F, (float)e.posY+0.5F, (float)e.posZ+0.5F);
        GL11.glPopMatrix();
        }

	@Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
	    return textures;
    }

}
