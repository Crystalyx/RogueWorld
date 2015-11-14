package RW.Client.Gui;

import org.lwjgl.opengl.GL11;

import RW.Common.Container.ContainerAdvSphere;
import RW.Common.Player.SphereInventory;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * @author Lord_Crystalyx
 */
@SideOnly(Side.CLIENT)
public class GuiAdvSphere extends GuiContainer
{
	private static final ResourceLocation text = new ResourceLocation("textures/gui/container/generic_54.png");

	public GuiAdvSphere(InventoryPlayer ip, SphereInventory tileE)
	{
		super(new ContainerAdvSphere(ip, tileE));
		this.xSize = 200;
		this.ySize = 222;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(text);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}

}
