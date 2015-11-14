package RW.Client.Render;

import org.lwjgl.opengl.GL11;

import RW.Client.Model.DarkGenerator;
import RW.Utils.Logger;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

/**
 * @author Lord_Crystalyx Created using Tabula 4.1.1
 */
public class DarkDeconstructorRender extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler
{
	public DarkDeconstructorRender()
	{

	}

	public static DarkGenerator model = new DarkGenerator();
	public static final ResourceLocation textures = new ResourceLocation("rogueWorld:textures/misc/model/DarkGenerator.png");

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float p_147500_8_)
	{
		RenderHelper.disableStandardItemLighting();

		GL11.glPushMatrix();

		GL11.glTranslatef((float) x + 0.5F, (float) y + 2.4F - 0.8975F, (float) z + 0.5F);
		GL11.glRotatef(180, 0, 0, 1);
		GL11.glScalef(0.0625F, 0.0625F, 0.0625F);
		Minecraft.getMinecraft().renderEngine.bindTexture(textures);
		model.render((Entity) null, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);

		GL11.glPopMatrix();

		RenderHelper.enableStandardItemLighting();

	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		renderer.setRenderAllFaces(true);
	}

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
		return 0x141413;
	}

}
