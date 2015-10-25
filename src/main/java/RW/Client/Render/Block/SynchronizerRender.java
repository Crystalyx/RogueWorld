package RW.Client.Render.Block;

import org.lwjgl.opengl.GL11;

import RW.Client.RenderUtils.RenderUtils;
import RW.Common.Registry.BlockRegistry;
import RW.Utils.MiscUtils;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.client.renderers.block.BlockRenderer;

public class SynchronizerRender implements ISimpleBlockRenderingHandler
{

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);
		RenderUtils.drawSides(renderer, block, MiscUtils.getIconArray(block, metadata), true);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess w, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		int index = 0;
		if (w.getBlock(x + 2, y + 1, z - 2) == BlockRegistry.rcore || w.getBlock(x - 2, y + 1, z + 2) == BlockRegistry.rcore)
		{
			index = 0;
		}
		else
			index = 1;
		
		renderer.setOverrideBlockTexture(block.getIcon(ForgeDirection.UP.ordinal(), index));
		block.setBlockBounds(0F, 1.0F, 0F, 1F, 1F, 1F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);
		renderer.clearOverrideBlockTexture();
		
		renderer.setOverrideBlockTexture(block.getIcon(ForgeDirection.EAST.ordinal(), w.getBlockMetadata(x, y, z)));
		block.setBlockBounds(0F, 0.0F, 0F, 1F, 0F, 1F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);
		renderer.clearOverrideBlockTexture();		
		
		renderer.setOverrideBlockTexture(block.getIcon(ForgeDirection.EAST.ordinal(), w.getBlockMetadata(x, y, z)));
		block.setBlockBounds(1F, 0.0F, 0F, 1F, 1F, 1F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);
		renderer.clearOverrideBlockTexture();
		
		renderer.setOverrideBlockTexture(block.getIcon(ForgeDirection.EAST.ordinal(), w.getBlockMetadata(x, y, z)));
		block.setBlockBounds(0F, 0.0F, 1F, 1F, 1F, 1F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);
		renderer.clearOverrideBlockTexture();
		
		renderer.setOverrideBlockTexture(block.getIcon(ForgeDirection.EAST.ordinal(), w.getBlockMetadata(x, y, z)));
		block.setBlockBounds(0F, 0.0F, 0F, 0F, 1F, 1F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);
		renderer.clearOverrideBlockTexture();
		
		renderer.setOverrideBlockTexture(block.getIcon(ForgeDirection.EAST.ordinal(), w.getBlockMetadata(x, y, z)));
		block.setBlockBounds(0F, 0.0F, 0F, 1F, 1F, 0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);
		renderer.clearOverrideBlockTexture();
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
		return 0x8983;
	}

}
