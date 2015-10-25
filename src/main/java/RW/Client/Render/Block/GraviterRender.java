package RW.Client.Render.Block;

import org.lwjgl.opengl.GL11;

import RW.Client.Model.Graviter;
import RW.Core.RogueWorldCore;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

public class GraviterRender implements ISimpleBlockRenderingHandler
{
	public ResourceLocation texture = new ResourceLocation("rogueWorld:textures/misc/model/graviter.png");
	public Graviter model = new Graviter();
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		RenderHelper.disableStandardItemLighting();

		GL11.glPushMatrix();
		GL11.glTranslatef(0, -1.0F, 0);
		float scale = 0.0625F;
		GL11.glScalef(scale, scale, scale);
		texture = new ResourceLocation("rogueWorld:textures/misc/model/graviter.png");
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		model.render(null, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		
		GL11.glPopMatrix();
		
		RenderHelper.enableStandardItemLighting();

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
		return 0x8981;
	}

}
