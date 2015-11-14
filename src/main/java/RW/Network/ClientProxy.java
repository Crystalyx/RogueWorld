package RW.Network;

import java.util.List;

import RW.Api.EnergeticTileEntity;
import RW.Client.FX.BindingBeam;
import RW.Client.FX.Spark;
import RW.Client.Gui.GuiAdvSphere;
import RW.Client.Gui.GuiAffixer;
import RW.Client.Gui.GuiDUStorage;
import RW.Client.Gui.GuiDarkDeconstructor;
import RW.Client.Gui.GuiEnergetic;
import RW.Client.Gui.GuiExtractor;
import RW.Client.Gui.GuiReactor;
import RW.Client.Gui.GuiTower;
import RW.Common.Book.GuiBook;
import RW.Common.Entity.EntityAdventureSphere;
import RW.Common.Player.SphereInventory;
import RW.Common.Tile.TileEntityAffixer;
import RW.Common.Tile.TileEntityDUStorage;
import RW.Common.Tile.TileEntityDarkDeconstructor;
import RW.Common.Tile.TileEntityEnergyTower;
import RW.Common.Tile.TileEntityExtractor;
import RW.Common.Tile.TileEntityReactorCore;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/**
 * @author Lord_Crystalyx
 */
@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
	@SuppressWarnings("unchecked")
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer p, World world, int x, int y, int z)
	{
		if (ID == 0)
		{
			return new GuiExtractor(p.inventory, (TileEntityExtractor) world.getTileEntity(x, y, z));
		}
		if (ID == 1)
		{
			return new GuiReactor((TileEntityReactorCore) world.getTileEntity(x, y, z), p.inventory);
		}
		if (ID == 2)
		{
			return new GuiEnergetic(p.inventory, (EnergeticTileEntity) world.getTileEntity(x, y, z));
		}
		if (ID == 3)
		{
			return new GuiTower(p.inventory, (TileEntityEnergyTower) world.getTileEntity(x, y, z));
		}
		if (ID == 4)
		{
			return new GuiDarkDeconstructor(p.inventory, (TileEntityDarkDeconstructor) world.getTileEntity(x, y, z));
		}
		if (ID == 5)
		{
			return new GuiAffixer(p.inventory, (TileEntityAffixer) world.getTileEntity(x, y, z));
		}
		if (ID == 6)
		{
			return new GuiDUStorage(p.inventory, (TileEntityDUStorage) world.getTileEntity(x, y, z));
		}
		if (ID == 85951)
		{
			return new GuiBook();
		}
		if (ID == 7)
		{
			List<EntityAdventureSphere> spheres = world.getEntitiesWithinAABB(EntityAdventureSphere.class, AxisAlignedBB.getBoundingBox(x - 1, y - 1, z - 1, x + 1, y + 1, z + 1));
			if (!spheres.isEmpty())
			{
				return new GuiAdvSphere(p.inventory, new SphereInventory(p));
			}
		}
		return null;
	}

	@Override
	public void spawnParticle(String name, double x, double y, double z, double i, double j, double k)
	{
		if (name.equals("energyFX"))
			EnergyFX(i, j, k, x, y, z, 0.0625, 0.0625, 0.0625);
		if (name.equals("spark"))
			SparkFX(x, y, z, i, j, k, 1, 1, 1);
	}

	@Override
	public void openBookGUIForPlayer()
	{
		Minecraft.getMinecraft().displayGuiScreen(new GuiBook());
	}

	@Override
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}

	@Override
	public EntityPlayer getClientPlayer()
	{
		return Minecraft.getMinecraft().thePlayer;
	}

	@Override
	public void EnergyFX(double... ds)
	{
		if (ds.length <= 6)
		{
			Minecraft.getMinecraft().effectRenderer.addEffect(new BindingBeam(getClientWorld(), ds[0], ds[1], ds[2], ds[3], ds[4], ds[5]));
		}
		else
			Minecraft.getMinecraft().effectRenderer.addEffect(new BindingBeam(getClientWorld(), ds[0], ds[1], ds[2], ds[3], ds[4], ds[5], ds[6], ds[7], ds[8]));
	}

	@Override
	public void SparkFX(double... ds)
	{
		if (ds.length <= 6)
		{
			Minecraft.getMinecraft().effectRenderer.addEffect(new Spark(getClientWorld(), ds[0], ds[1], ds[2], ds[3], ds[4], ds[5]));
		}
		else
			Minecraft.getMinecraft().effectRenderer.addEffect(new Spark(getClientWorld(), ds[0], ds[1], ds[2], ds[3], ds[4], ds[5], ds[6], ds[7], ds[8]));
	}
}
