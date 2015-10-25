package RW.Common.Book;

import org.lwjgl.opengl.GL11;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public class ArticlePage extends IBookPage
{
	public Chapter chapter;

	public ArticlePage(ResourceLocation texture, Chapter chap)
	{
		super(texture, null);
		this.chapter = chap;
	}

	public void drawPageTextureLeft(int width, int height)
	{
		super.drawPageTextureLeft(width, height);

		int k = (width - 256) / 2;
		int l = (height - 256) / 2;

		if(!this.chapter.Articles.isEmpty())
		for (int i = 0; i < this.chapter.Articles.size() / 6; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				GL11.glPushMatrix();

				Article art = this.chapter.Articles.get(i * 6 + j);

				if (art.Icon instanceof Item)
					this.drawTexturedModalRect(k+20+18*i, l+20+18*j, 0, 0, (int) ((Item) art.Icon).getIcon(new ItemStack((Item)art.Icon), 0).getMaxU(), (int) (int) ((Item) art.Icon).getIcon(new ItemStack((Item)art.Icon), 0).getMaxV());

				GL11.glPopMatrix();
			}
		}
	}
	
	public void drawPage(int width, int height)
	{
		this.drawPageTextureLeft(width, height);
		this.drawHeader(width, height);
	}
	
	public void drawHeader(int width, int height)
	{
		if (text != null)
		{
			int k = (width - 256) / 2;
			int l = (height - 256) / 2;

			GL11.glPushMatrix();
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_FOG);

			this.fontRendererObj.drawString(this.chapter.name, k + 10, l + 10, 0);

			GL11.glPopMatrix();
		}
	}

}
