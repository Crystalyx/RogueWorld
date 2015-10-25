/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Client.Gui;

import org.lwjgl.opengl.GL11;

import RW.Common.Container.ContainerDUStorage;
import RW.Common.Tile.TileEntityDUStorage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GuiDUStorage extends GuiContainer
{

	private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("rogueWorld:textures/misc/gui/gui_storage.png");
	private TileEntityDUStorage tile;

	public GuiDUStorage(InventoryPlayer p, TileEntityDUStorage energeticTileEntity)
	{
		super(new ContainerDUStorage(p, energeticTileEntity));
		this.tile = energeticTileEntity;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_,int p_146976_2_, int p_146976_3_)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		int i1 = tile.getEnergyScaled();
         this.drawTexturedModalRect(k + 134, l + 76 - i1, 176, 77 - i1, 14, i1);
    }

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of
	 * the items)
	 */
	protected void drawGuiContainerForegroundLayer(int p_146979_1_,	int p_146979_2_)
	{
		this.fontRendererObj.drawString(tile.getInventoryName(), 8,(int) (this.ySize - 134)-26, 4210752);
		this.fontRendererObj.drawString(this.tile.energy+" DU", 108,(int) (this.ySize - 64 - 18 * 5.5 + 2)+44-6-36, 4210752);

	}

}
