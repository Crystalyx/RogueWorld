package RW.Client.Render.Block;

import org.lwjgl.opengl.GL11;

import RW.Client.Model.DarkGenerator;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

/**
 * @author Lord_Crystalyx Created using Tabula 4.1.1
 */
public class DarkDeconstructorRender implements ISimpleBlockRenderingHandler
{

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		RenderHelper.disableStandardItemLighting();

		GL11.glPushMatrix();

		GL11.glTranslatef(0, 1.0F, 0);
		GL11.glRotatef(180, 0, 0, 1);
		GL11.glScalef(0.0625F, 0.0625F, 0.0625F);
		Minecraft.getMinecraft().renderEngine.bindTexture(textures);
		model.render((Entity) null, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);

		GL11.glPopMatrix();

		RenderHelper.enableStandardItemLighting();
	}

	public static DarkGenerator model = new DarkGenerator();
	public static final ResourceLocation textures = new ResourceLocation("rogueWorld:textures/misc/model/DarkGenerator.png");

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return 0x8978;
	}

}
