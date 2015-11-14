/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Client.Render;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import DummyCore.Utils.DrawUtils;
import RW.Client.Model.ShowCase;
import RW.Common.Tile.TileEntityPillar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

/**
 * @author Lord_Crystalyx Created using Tabula 4.1.1
 */
public class TileEntityPillarRender extends TileEntitySpecialRenderer
{
	public static ShowCase model = new ShowCase();
	public static ResourceLocation texture = new ResourceLocation("rogueWorld:textures/misc/model/ShowCase-texture.png");

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float p_147500_8_)
	{
		RenderHelper.disableStandardItemLighting();

		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.65F, (float) z + 0.5F);
		float scale = 0.08F;
		GL11.glScalef(scale, scale, scale);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		this.model.render((Entity) null, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();

		TileEntityPillar tilee = (TileEntityPillar) tile;
		float rotation = tilee.getWorldObj().getWorldTime() % 360;

		rotation = rotation + 360F / tilee.getWorldObj().getWorldTime() % 360;
		GL11.glPushMatrix();

		if (tilee.getStackInSlot(0) != null)
		{
			Color col = new Color(tilee.getStackInSlot(0).getItem().getColorFromItemStack(tilee.getStackInSlot(0), tilee.getStackInSlot(0).getItemDamage()));
			DrawUtils.renderItemStack_Full(Monoficate(tilee.getStackInSlot(0)), tilee.xCoord + 0.5D, tilee.yCoord + 10D, tilee.zCoord + 0.5D, x, y + 1.1D, z, rotation, 0F, (float) col.getRed() / 256, (float) col.getGreen() / 256,
					(float) col.getBlue() / 256, 0.5F, 0.175F, 0.5F, true);
		}
		GL11.glPopMatrix();
		RenderHelper.enableStandardItemLighting();
	}

	public ItemStack Monoficate(ItemStack i)
	{
		ItemStack ret = new ItemStack(i.getItem(), 1, i.getItemDamage());
		ret.setTagCompound(i.getTagCompound());
		return ret;
	}
}
