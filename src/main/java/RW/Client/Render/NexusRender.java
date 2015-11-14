package RW.Client.Render;

import org.lwjgl.opengl.GL11;

import DummyCore.Utils.DrawUtils;
import RW.Client.Model.SeekerAltar;
import RW.Common.Tile.TileEntityNexus;
import RW.Utils.MiscUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBook;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

/**
 * @author Lord_Crystalyx Created using Tabula 4.1.1
 */
public class NexusRender extends TileEntitySpecialRenderer
{

	private ModelBook book = new ModelBook();
	public SeekerAltar model = new SeekerAltar();

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float p_147500_8_)
	{
		RenderHelper.disableStandardItemLighting();

		int dir = tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord);

		GL11.glPushMatrix();

		ResourceLocation texture = new ResourceLocation("rogueWorld:textures/misc/model/SeekerAltar-texture.png");

		GL11.glTranslatef((float) x + 0.5F, (float) y - 0.5F + 2.0F, (float) z + 0.5F);
		float scale = 0.0625F;
		GL11.glScalef(scale, scale, scale);

		GL11.glRotatef(dir * (-90F), 0F, 1F, 0F);
		GL11.glRotatef(180F, 1, 0, 0);

		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		this.model.render((Entity) null, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);

		GL11.glPopMatrix();

		GL11.glPushMatrix();

		ResourceLocation booktext = new ResourceLocation("textures/entity/enchanting_table_book.png");

		if (dir == 0)
		{
			GL11.glTranslatef((float) x + 0.5F, (float) y + 1.3F, (float) z + 0.85F);
		}

		if (dir == 1)
		{
			GL11.glTranslatef((float) x + 0.15F, (float) y + 1.3F, (float) z + 0.5F);
		}

		if (dir == 2)
		{
			GL11.glTranslatef((float) x + 0.5F, (float) y + 1.3F, (float) z + 0.15F);
		}

		if (dir == 3)
		{
			GL11.glTranslatef((float) x + 0.85F, (float) y + 1.3F, (float) z + 0.5F);
		}

		float bookscale = 0.0625F;
		GL11.glScalef(bookscale, bookscale, bookscale);

		if (dir == 0)
		{
			GL11.glRotatef(210F, 1, 0, 0);
		}

		if (dir == 1)
		{
			GL11.glRotatef(210F, 0, 0, 1);
		}

		if (dir == 2)
		{
			GL11.glRotatef(150F, 1, 0, 0);
		}

		if (dir == 3)
		{
			GL11.glRotatef(150F, 0, 0, 1);
		}

		GL11.glRotatef(dir * (-90F) + 90, 0F, 1F, 0F);
		GL11.glRotatef(180F, 1, 0, 0);
		GL11.glRotatef(180F, 0, 1, 0);

		Minecraft.getMinecraft().renderEngine.bindTexture(booktext);
		this.book.render((Entity) null, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);

		GL11.glPopMatrix();

		TileEntityNexus tilee = (TileEntityNexus) tile;
		float rotation = tilee.getWorldObj().getWorldTime() % 360;

		rotation = rotation + 360F / tilee.getWorldObj().getWorldTime() % 360;
		GL11.glPushMatrix();
		DrawUtils.renderItemStack_Full(tilee.getStackInSlot(0), tilee.xCoord + 0.5D, tilee.yCoord + 2D, tilee.zCoord + 0.5D,x, y + 0.5D, z, rotation, 0F, 1, 1, 1, 0.625F, 0.225F, 0.5F,true);

		GL11.glPopMatrix();

		RenderHelper.enableStandardItemLighting();
	}
}
