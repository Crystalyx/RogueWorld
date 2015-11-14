package RW.Client.Render.Block;

import RW.Client.RenderUtils.Cube;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

/**
 * @author Lord_Crystalyx Created using Tabula 4.1.1
 */
public class TessBlockRender implements ISimpleBlockRenderingHandler
{

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		Cube cub = new Cube(0, 0, 0, 64, 64, 64, 0, 0, 1 / 3, block.getIcon(0, metadata).getIconHeight());

		cub.render((ResourceLocation) null);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		return false;
	}

	@Override
	public int getRenderId()
	{
		return 0x8985;
	}

}
