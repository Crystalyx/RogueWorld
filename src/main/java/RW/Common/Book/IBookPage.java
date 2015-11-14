package RW.Common.Book;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

/**
 * @author Lord_Crystalyx
 */
public class IBookPage extends GuiScreen
{

	public ResourceLocation texture;
	public String text = "";

	public IBookPage(ResourceLocation texture, String text)
	{
		this.texture = texture;
		this.text = text;
	}

	public void drawPageTextureLeft(int width, int height)
	{
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_FOG);

		int k = (width - 256) / 2;
		int l = (height - 256) / 2;

		if (this.texture != null)
		{
			Minecraft.getMinecraft().getTextureManager().bindTexture(this.texture);
			this.drawTexturedModalRect(k + 128, l, 128, 0, 512 / 4, 512 / 4);

		}
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();
	}

	protected void drawItemStack(ItemStack p_146982_1_, int p_146982_2_, int p_146982_3_, String p_146982_4_, FontRenderer gui)
	{
		GL11.glTranslatef(0.0F, 0.0F, 32.0F);
		this.zLevel = 200.0F;
		itemRender.zLevel = 200.0F;
		FontRenderer font = gui;
		itemRender.renderItemAndEffectIntoGUI(font, Minecraft.getMinecraft().getTextureManager(), p_146982_1_, p_146982_2_, p_146982_3_);
		itemRender.renderItemOverlayIntoGUI(font, this.mc.getTextureManager(), p_146982_1_, p_146982_2_, p_146982_3_, p_146982_4_);
		this.zLevel = 0.0F;
		itemRender.zLevel = 0.0F;
	}

	public void drawPageTextureRight(int width, int height)
	{
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_FOG);

		int k = (width - 256) / 2;
		int l = (height - 256) / 2;

		if (this.texture != null)
			Minecraft.getMinecraft().getTextureManager().bindTexture(this.texture);
		this.drawTexturedModalRect(k, l, 0, 0, 512 / 4, 512 / 4);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();
	}

	public void drawTextLeft(int width, int height)
	{
		if (text != null)
		{
			int k = (width - 256) / 2;
			int l = (height - 256) / 2;

			GL11.glPushMatrix();
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_FOG);

			this.fontRendererObj.drawString(StatCollector.translateToLocal(this.text), k + 10, l + 30, 0);

			GL11.glPopMatrix();
		}
	}

	public void drawTextRight(int width, int height)
	{
		if (text != null)
		{
			int k = (width - 256) / 2;
			int l = (height - 256) / 2;

			GL11.glPushMatrix();
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_FOG);

			this.fontRendererObj.drawString(StatCollector.translateToLocal(this.text), k + 10, l + 30, 0);

			GL11.glPopMatrix();
		}
	}

	public void drawPage(String text, ResourceLocation tex, int width, int height)
	{
		this.drawPageTextureLeft(width, height);
		this.drawPageTextureRight(width, height);
		this.drawTextLeft(width, height);
		this.drawTextRight(width, height);
	}

}
