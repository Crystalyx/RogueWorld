package RW.Client.Render.Tessellator;

import org.lwjgl.opengl.GL11;

import RW.Client.RenderUtils.Cube;
import RW.Client.RenderUtils.RenderUtils;
import RW.Common.Misc.WorldPos;
import RW.Common.Registry.BlockRegistry;
import RW.Utils.PositionedWorld;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

/**
 * @author Lord_Crystalyx Created using Tabula 4.1.1
 */
public class TessRender extends TileEntitySpecialRenderer
{

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float p_147500_8_)
	{

		RenderHelper.disableStandardItemLighting();

		GL11.glPushMatrix();

		Tessellator tess = Tessellator.instance;

		GL11.glTranslated(x, y, z);

		ResourceLocation text = new ResourceLocation("rogueWorld:textures/misc/tesselator.png");

		Cube cub = new Cube(0, 0, 0, 64, 64, 64, 0, 0, (float) 1, 64);
		cub.render((ResourceLocation) text);

		// RenderUtils.renderBlock(RenderBlocks.getInstance(),
		// BlockRegistry.altar_base, x, y, z);

		GL11.glPopMatrix();
		RenderHelper.enableStandardItemLighting();
	}

}
