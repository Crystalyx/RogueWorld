/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Client.Gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class GuiTestButton extends GuiButton
{

	public GuiTestButton(int p_i1021_1_, int p_i1021_2_, int p_i1021_3_,int p_i1021_4_, int p_i1021_5_, String p_i1021_6_)
	{
		super(p_i1021_1_, p_i1021_2_, p_i1021_3_, p_i1021_4_, p_i1021_5_, p_i1021_6_);
	}
	public ResourceLocation texture = new ResourceLocation("rogueWorld:textures/misc/gui/gui_book.png");
	private int xSize=42;
	private int ySize=42;
	int k = (this.width - this.xSize) / 2;
    int l = (this.height - this.ySize) / 2;
    
	@Override
	public boolean mousePressed(Minecraft minecraft, int x, int y)
	{
		return true;		
	}
}
