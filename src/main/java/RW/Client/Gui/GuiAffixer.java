package RW.Client.Gui;

import org.lwjgl.opengl.GL11;

import RW.Common.Container.ContainerAffixer;
import RW.Common.Tile.TileEntityAffixer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * @author Lord_Crystalyx
 */
@SideOnly(Side.CLIENT)
public class GuiAffixer extends GuiContainer
{

	private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("rogueWorld:textures/misc/gui/affixer.png");
	private TileEntityAffixer tile;

	public GuiAffixer(InventoryPlayer p, TileEntityAffixer energeticTileEntity)
	{
		super(new ContainerAffixer(p, energeticTileEntity));
		this.tile = energeticTileEntity;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k - 109, l, 146, 169, 188, 86);
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of
	 * the items)
	 */
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
	{
		this.fontRendererObj.drawString(tile.getInventoryName(), 8 - 106, (int) (this.ySize - 134) - 26, 4210752);
		this.fontRendererObj.drawString("Time: " + this.tile.getMaxBurnTime() + " Ticks", 8 - 106, (int) (this.ySize - 64 - 18 * 5.5 + 2) + 24 - 6, 4210752);
		this.fontRendererObj.drawString("Energy: " + this.tile.energy + " DU", 8 - 106, (int) (this.ySize - 64 - 18 * 5.5 + 2) + 44 - 6, 4210752);

		this.fontRendererObj.drawString(this.tile.Btime + "/" + this.tile.getMaxBurnTime(), 8 + 70, (int) (this.ySize - 64 - 18 * 5.5 + 2) + 64 - 6, 4210752);

	}

}
