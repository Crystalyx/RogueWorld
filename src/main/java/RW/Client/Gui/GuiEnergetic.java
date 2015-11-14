package RW.Client.Gui;

import org.lwjgl.opengl.GL11;

import RW.Api.EnergeticTileEntity;
import RW.Common.Container.ContainerEnergetic;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * @author Lord_Crystalyx
 */
@SideOnly(Side.CLIENT)
public class GuiEnergetic extends GuiContainer
{

	private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("rogueWorld:textures/misc/gui/tower.png");
	private EnergeticTileEntity tile;

	public GuiEnergetic(InventoryPlayer p, EnergeticTileEntity energeticTileEntity)
	{
		super(new ContainerEnergetic(p, energeticTileEntity));
		this.tile = energeticTileEntity;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
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
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
	{
		this.fontRendererObj.drawString(tile.getInventoryName(), 8, (int) (this.ySize - 134) - 28, 4210752);
		this.fontRendererObj.drawString("Range: " + this.tile.getRange() + " Blocks", 8, (int) (this.ySize - 147) + 18, 4210752);
		this.fontRendererObj.drawString("Speed: " + this.tile.getSpeed() + " DU/t", 8, (int) (this.ySize - 157) + 18, 4210752);
		this.fontRendererObj.drawString("Energy: " + this.tile.energy + " DU", 8, (int) (this.ySize - 167 + 18), 4210752);

	}

}
