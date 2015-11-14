/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Client.Gui;

import org.lwjgl.opengl.GL11;

import RW.Common.Container.ContainerReactor;
import RW.Common.Tile.TileEntityReactorCore;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * @author Lord_Crystalyx
 */
public class GuiReactor extends GuiContainer
{

	public GuiReactor(TileEntityReactorCore tile, InventoryPlayer player)
	{
		super(new ContainerReactor(tile, player));
	}

	private static final ResourceLocation textures = new ResourceLocation("rogueWorld:textures/misc/gui/reactor.png");

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(textures);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, 6 * 18 + 17);
		this.drawTexturedModalRect(k, l + 6 * 18 + 17, 0, 126, this.xSize, 96);
	}

}
