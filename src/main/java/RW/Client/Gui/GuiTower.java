/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Client.Gui;

import org.lwjgl.opengl.GL11;

import RW.Common.Container.ContainerTower;
import RW.Common.Tile.TileEntityEnergyTower;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GuiTower extends GuiContainer
{

	private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("rogueWorld:textures/misc/gui/tower.png");
	private TileEntityEnergyTower tile;

	public GuiTower(InventoryPlayer p, TileEntityEnergyTower tilee)
	{
		super(new ContainerTower(p, tilee));
		this.tile = tilee;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_,int p_146976_2_, int p_146976_3_)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of
	 * the items)
	 */
	protected void drawGuiContainerForegroundLayer(int p_146979_1_,	int p_146979_2_)
	{
		this.fontRendererObj.drawString(tile.getInventoryName(), 8,(int) (this.ySize - 64 - 18 * 5.5 + 2), 4210752);
		this.fontRendererObj.drawString("Range: "+this.tile.getRange()+" Blocks", 8,(int) (this.ySize - 64 - 18 * 5.5 + 2)+14, 4210752);
		this.fontRendererObj.drawString("Speed: "+this.tile.getSpeed()+" DU/t", 8,(int) (this.ySize - 64 - 18 * 5.5 + 2)+24, 4210752);
		this.fontRendererObj.drawString("Energy: "+this.tile.energy+" DU", 8,(int) (this.ySize - 64 - 18 * 5.5 + 2)+34, 4210752);

	}

}
