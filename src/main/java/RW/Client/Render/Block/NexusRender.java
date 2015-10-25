package RW.Client.Render.Block;

import org.lwjgl.opengl.GL11;

import RW.Client.Model.SeekerAltar;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBook;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

public class NexusRender implements ISimpleBlockRenderingHandler
{

    private ModelBook book = new ModelBook();
    public SeekerAltar model = new SeekerAltar();
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		RenderHelper.disableStandardItemLighting();

		GL11.glPushMatrix();
		ResourceLocation texture = new ResourceLocation("rogueWorld:textures/misc/model/SeekerAltar-texture.png");
		GL11.glTranslatef(0.0F, 1.0F, 0.0F);
		float scale = 0.0625F;
		GL11.glScalef(scale, scale, scale);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		this.model.render((Entity) null, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		ResourceLocation booktext = new ResourceLocation("textures/entity/enchanting_table_book.png");;
		GL11.glTranslatef(0.0F, 1.0F, 0.0F);
		float bookscale = 0.0625F;
		GL11.glScalef(bookscale, bookscale, bookscale);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(270F, 0.0F, 1.0F, 0.0F);
		
		
		
		Minecraft.getMinecraft().renderEngine.bindTexture(booktext);
        GL11.glEnable(GL11.GL_CULL_FACE);
		this.book.render((Entity) null, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		
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
		return 0x8982;
	}

}
