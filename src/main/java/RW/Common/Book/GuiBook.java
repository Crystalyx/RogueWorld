/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Book;

import org.lwjgl.opengl.GL11;

import RW.Client.Gui.GuiTestButton;
import RW.Common.Pages.Page;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public class GuiBook extends GuiScreen
{
	public ResourceLocation texture = new ResourceLocation("rogueWorld:textures/misc/gui/gui_book.png");
	public ResourceLocation recipe1 = new ResourceLocation("rogueWorld:textures/misc/gui/gui_book_recipe_1.png");
	public ResourceLocation recipe2 = new ResourceLocation("rogueWorld:textures/misc/gui/gui_book_recipe_2.png");
	public ResourceLocation recipe3 = new ResourceLocation("rogueWorld:textures/misc/gui/gui_book_recipe_3.png");

	private int xSize = 512 / 2;
	private int ySize = 512 / 2;

	/**
	 * Draws the screen and all the components in it.
	 */
	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
	{
		this.drawDefaultBackground();
		this.drawBackground(0);
		byte b0 = 75;
		byte b1 = 0;
		FontRenderer fontrenderer = this.fontRendererObj;
		int i1 = this.width / 2 - 150;
		int l1 = b1 + 1;
		
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		
		ChapterPage pg = new ChapterPage(PageRegistry.Chapters);
		
		pg.drawPage(k, l,this.fontRendererObj);
		
		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	@Override
	public void initGui()
	{
		this.buttonList.clear();
		this.buttonList.add(button);
	}

	public GuiTestButton button = new GuiTestButton(1, 60, 60, 20, 20, "Test");
	public static IIcon[] recipeIcons = new IIcon[3];

	/**
	 * Draws the background (i is always 0 as of 1.2.2)
	 */
	@Override
	public void drawBackground(int layer)
	{
		Tessellator tess = Tessellator.instance;
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		
		GL11.glPushMatrix();
		this.mc.getTextureManager().bindTexture(texture);
		this.drawTexturedModalRect(k, l, 0, 0, 512 / 2, 512 / 2);
		GL11.glPopMatrix();		
		
	}
}
