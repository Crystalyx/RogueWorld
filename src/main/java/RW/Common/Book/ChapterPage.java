package RW.Common.Book;

import java.util.Hashtable;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ChapterPage extends IBookPage
{
	public Hashtable<Integer, Chapter> chapters;

	public ChapterPage(Hashtable<Integer, Chapter> chap)
	{
		super(null, "");
		this.chapters = chap;
	}

	public void drawPageTextureLeft(int width, int height, FontRenderer gui)
	{
		//super.drawPageTextureLeft(width, height);

		int k = (width - 256) / 2;
		int l = (height - 256) / 2;

		if (!this.chapters.isEmpty())
			for (int i = 0; i < this.chapters.size() / 6+1; i++)
			{
				for (int j = 0; j < 4; j++)
				{
					GL11.glPushMatrix();

					Chapter chap = this.chapters.get(i * 6 + j);

					if (chap.Icon instanceof Item)
					{
						this.drawItemStack(new ItemStack((Item) chap.Icon), k + 20, l + 20, this.text,gui);
						// this.drawTexturedModalRect(k+20+20*i, l+20+20*j, 0,
						// 0, (int) ((Item) chap.Icon).getIcon(new
						// ItemStack((Item)chap.Icon), 0).getMaxU(), (int) (int)
						// ((Item) chap.Icon).getIcon(new
						// ItemStack((Item)chap.Icon), 0).getMaxV());
						Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("rogueWorld:textures/items/dark_book.png"));

					}
					GL11.glPopMatrix();
				}
			}
	}

	public void drawPage(int width, int height, FontRenderer gui)
	{
		this.drawPageTextureLeft(width, height,gui);
		this.drawHeader(width, height, gui);
	}

	public void drawHeader(int width, int height, FontRenderer gui)
	{
		if (text != null)
		{
			int k = (width - 256) / 2;
			int l = (height - 256) / 2;

			GL11.glPushMatrix();
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_FOG);

			gui.drawString("Index", k + 10, l + 10, 4210752);

			GL11.glPopMatrix();
		}
	}

}
