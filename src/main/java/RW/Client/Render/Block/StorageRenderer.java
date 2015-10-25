package RW.Client.Render.Block;

import org.lwjgl.opengl.GL11;

import RW.Client.RenderUtils.RenderUtils;
import RW.Utils.MiscUtils;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public class StorageRenderer implements ISimpleBlockRenderingHandler
{

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		block.setBlockBounds(0.0625F, 0.0625F, 0.0625F, 0.9375F, 0.9375F, 0.9375F);
		renderer.setRenderBoundsFromBlock(block);
		RenderUtils.drawSides(renderer, block, MiscUtils.getIconArray(block, metadata), true);

		block.setBlockBounds(0F, 0F, 0F, 0.0625F, 1F, 0.0625F);
		renderer.setRenderBoundsFromBlock(block);
		RenderUtils.drawSides(renderer, block, MiscUtils.getIconArray(block, metadata), true);

		block.setBlockBounds(0F, 0F, 1 - 0.0625F, 0.0625F, 1F, 1);
		renderer.setRenderBoundsFromBlock(block);
		RenderUtils.drawSides(renderer, block, MiscUtils.getIconArray(block, metadata), true);

		block.setBlockBounds(1 - 0.0625F, 0F, 0, 1, 1F, 0.0625F);
		renderer.setRenderBoundsFromBlock(block);
		RenderUtils.drawSides(renderer, block, MiscUtils.getIconArray(block, metadata), true);

		block.setBlockBounds(1 - 0.0625F, 0F, 1 - 0.0625F, 1, 1F, 1);
		renderer.setRenderBoundsFromBlock(block);
		RenderUtils.drawSides(renderer, block, MiscUtils.getIconArray(block, metadata), true);
		///////////////////////////////////////////////////
		block.setBlockBounds(0, 1 - 0.0625F, 0.0625F, 0.0625F, 1F, 1 - 0.0625F);
		renderer.setRenderBoundsFromBlock(block);
		RenderUtils.drawSides(renderer, block, MiscUtils.getIconArray(block, metadata), true);

		block.setBlockBounds(0.0625F, 1 - 0.0625F, 0, 1 - 0.0625F, 1F, 0.0625F);
		renderer.setRenderBoundsFromBlock(block);
		RenderUtils.drawSides(renderer, block, MiscUtils.getIconArray(block, metadata), true);

		block.setBlockBounds(1 - 0.0625F, 1 - 0.0625F, 0.0625F, 1, 1F, 1 - 0.0625F);
		renderer.setRenderBoundsFromBlock(block);
		RenderUtils.drawSides(renderer, block, MiscUtils.getIconArray(block, metadata), true);

		block.setBlockBounds(0.0625F, 1 - 0.0625F, 1 - 0.0625F, 1 - 0.0625F, 1F, 1);
		renderer.setRenderBoundsFromBlock(block);
		RenderUtils.drawSides(renderer, block, MiscUtils.getIconArray(block, metadata), true);
		///////////////////////////////////////////////////
		block.setBlockBounds(0, 0F, 0.0625F, 0.0625F, 0.0625F, 1 - 0.0625F);
		renderer.setRenderBoundsFromBlock(block);
		RenderUtils.drawSides(renderer, block, MiscUtils.getIconArray(block, metadata), true);

		block.setBlockBounds(0.0625F, 0, 0, 1 - 0.0625F, 0.0625F, 0.0625F);
		renderer.setRenderBoundsFromBlock(block);
		RenderUtils.drawSides(renderer, block, MiscUtils.getIconArray(block, metadata), true);

		block.setBlockBounds(1 - 0.0625F, 0, 0.0625F, 1, 0.0625F, 1 - 0.0625F);
		renderer.setRenderBoundsFromBlock(block);
		RenderUtils.drawSides(renderer, block, MiscUtils.getIconArray(block, metadata), true);

		block.setBlockBounds(0.0625F, 0, 1 - 0.0625F, 1 - 0.0625F, 0.0625F, 1);
		renderer.setRenderBoundsFromBlock(block);
		RenderUtils.drawSides(renderer, block, MiscUtils.getIconArray(block, metadata), true);

		block.setBlockBounds(0F, 0, 0F, 1F, 1F, 1);

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{

		GL11.glPushMatrix();

		block.setBlockBounds(0.0625F, 0.0625F, 0.0625F, 0.9375F, 0.9375F, 0.9375F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(0F, 0F, 0F, 0.0625F, 1F, 0.0625F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(0F, 0F, 1 - 0.0625F, 0.0625F, 1F, 1);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(1 - 0.0625F, 0F, 0, 1, 1F, 0.0625F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(1 - 0.0625F, 0F, 1 - 0.0625F, 1, 1F, 1);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);
		///////////////////////////////////////////////////
		block.setBlockBounds(0, 1 - 0.0625F, 0.0625F, 0.0625F, 1F, 1 - 0.0625F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(0.0625F, 1 - 0.0625F, 0, 1 - 0.0625F, 1F, 0.0625F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(1 - 0.0625F, 1 - 0.0625F, 0.0625F, 1, 1F, 1 - 0.0625F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(0.0625F, 1 - 0.0625F, 1 - 0.0625F, 1 - 0.0625F, 1F, 1);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);
		///////////////////////////////////////////////////
		block.setBlockBounds(0, 0F, 0.0625F, 0.0625F, 0.0625F, 1 - 0.0625F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(0.0625F, 0, 0, 1 - 0.0625F, 0.0625F, 0.0625F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(1 - 0.0625F, 0, 0.0625F, 1, 0.0625F, 1 - 0.0625F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(0.0625F, 0, 1 - 0.0625F, 1 - 0.0625F, 0.0625F, 1);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);
		block.setBlockBounds(0F, 0, 0F, 1F, 1F, 1);

		GL11.glPopMatrix();
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return 0x8984;
	}

}
