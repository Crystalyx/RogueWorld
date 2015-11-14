package RW.Client.Gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import RW.Common.Container.ContainerExtractor;
import RW.Common.Tile.TileEntityExtractor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Lord_Crystalyx
 */
@SideOnly(Side.CLIENT)
public class GuiExtractor extends GuiContainer
{

	private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("rogueWorld:textures/misc/gui/furnace.png");
	private TileEntityExtractor tile;

	public GuiExtractor(InventoryPlayer p, TileEntityExtractor tilee)
	{
		super(new ContainerExtractor(p, tilee));
		this.tile = tilee;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k-109, l, 146, 169, 188, 86);
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

		int i1 = this.tile.getCookProgressScaled(12);
		this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);

	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of
	 * the items)
	 */
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
	{
		this.fontRendererObj.drawString("Dark Extractor", 8-100, (int) (this.ySize - 64 - 18 * 5.5 + 2), 4210752);
		this.fontRendererObj.drawString("Progress: "+this.tile.furnaceCookTime + "/" + this.tile.getMaxBurnTime(), 8-100, (int) ((this.ySize - 64 - 18 * 5.5 + 2)+14), 4210752);
		this.fontRendererObj.drawString("Range: "+this.tile.getRange()+" Blocks", 8-100,(int) (this.ySize - 64 - 18 * 5.5 + 2)+24, 4210752);
		this.fontRendererObj.drawString("Speed: "+this.tile.getSpeed()+" DU/t", 8-100,(int) (this.ySize - 64 - 18 * 5.5 + 2)+34, 4210752);
		this.fontRendererObj.drawString("Energy: "+this.tile.energy+" DU", 8-100,(int) (this.ySize - 64 - 18 * 5.5 + 2)+44, 4210752);
	}

}
