package RW.Client.Render.Block;

import org.lwjgl.opengl.GL11;

import RW.Common.Blocks.BaseBlock;
import RW.Common.Registry.BlockRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import thaumcraft.client.renderers.block.BlockRenderer;

public class TuberRender implements ISimpleBlockRenderingHandler
{

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		block.setBlockBounds(Cx, Cx, Cx, Cy, Cy, Cy);
		renderer.setRenderBoundsFromBlock(block);
		BlockRenderer.drawFaces(renderer, block, BlockRegistry.tuber.getIcon(0, 0), true);

		block.setBlockBounds(Tx, Ty, Tx, Ty, 1.0F, Ty);
		renderer.setRenderBoundsFromBlock(block);
		BlockRenderer.drawFaces(renderer, block, BlockRegistry.tuber.getIcon(0, 0), true);

		block.setBlockBounds(Tx, 0.0F, Tx, Ty, Tx, Ty);
		renderer.setRenderBoundsFromBlock(block);
		BlockRenderer.drawFaces(renderer, block, BlockRegistry.tuber.getIcon(0, 0), true);

		block.setBlockBounds(Cx, Cx, Cx, Cy, Cy, Cy);
	}

	float Cx = 0.4F;
	float Cy = 0.6F;

	float Tx = 0.4375F;
	float Ty = 0.5625F;

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		GL11.glPushMatrix();

		GL11.glScaled(2.0D, 2.0D, 2.0D);
		renderer.overrideBlockTexture = BlockRegistry.tuber.getIcon(0, 0);

		block.setBlockBounds(Cx, Cx, Cx, Cy, Cy, Cy);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(BlockRegistry.tuber, x, y, z);

		if (world.getBlock(x, y + 1, z) == BlockRegistry.tuber || world.getBlock(x, y + 1, z) instanceof BaseBlock)
		{
			block.setBlockBounds(Tx, Ty, Tx, Ty, 1.0F, Ty);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(BlockRegistry.tuber, x, y, z);
		}

		if (world.getBlock(x, y - 1, z) == BlockRegistry.tuber|| world.getBlock(x, y - 1, z)instanceof BaseBlock)
		{
			block.setBlockBounds(Tx, 0.0F, Tx, Ty, Tx, Ty);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(BlockRegistry.tuber, x, y, z);
		}
		

		if (world.getBlock(x+1, y, z) == BlockRegistry.tuber|| world.getBlock(x+1, y, z) instanceof BaseBlock)
		{
			block.setBlockBounds(Ty, Tx, Tx, 1.0F, Ty, Ty);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(BlockRegistry.tuber, x, y, z);
		}

		if (world.getBlock(x-1, y, z) == BlockRegistry.tuber|| world.getBlock(x-1, y, z) instanceof BaseBlock)
		{
			block.setBlockBounds(0.0F, Tx, Tx, Tx, Ty, Ty);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(BlockRegistry.tuber, x, y, z);
		}		

		if (world.getBlock(x, y, z+1) == BlockRegistry.tuber|| world.getBlock(x, y, z+1) instanceof BaseBlock)
		{
			block.setBlockBounds(Tx, Tx, Ty, Ty, Ty, 1.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(BlockRegistry.tuber, x, y, z);
		}

		if (world.getBlock(x, y, z-1) == BlockRegistry.tuber|| world.getBlock(x, y, z-1) instanceof BaseBlock)
		{
			block.setBlockBounds(Tx, Tx, 0.0F, Ty, Ty, Tx);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(BlockRegistry.tuber, x, y, z);
		}
		
		renderer.field_152631_f = true;

		renderer.clearOverrideBlockTexture();
		block.setBlockBounds(Cx, Cx, Cx, Cy, Cy, Cy);
		renderer.setRenderBoundsFromBlock(block);

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
		return 0x8976;
	}

}
