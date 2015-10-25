/**
 * This Class Created By Lord_Crystalyx.
 */

package RW.Network;

import RW.Api.EnergeticTileEntity;
import RW.Client.FX.BindingBeam;
import RW.Client.Render.BaseMobRenderer;
import RW.Client.Render.DarkDeconstructorRender;
import RW.Client.Render.EnergyTowerRender;
import RW.Client.Render.GraviterRender;
import RW.Client.Render.ModificationAnvilRender;
import RW.Client.Render.NexusRender;
import RW.Client.Render.TileEntityPillarRender;
import RW.Client.Render.Block.StorageRenderer;
import RW.Client.Render.Block.SynchronizerRender;
import RW.Client.Render.Block.TessBlockRender;
import RW.Client.Render.Block.TuberRender;
import RW.Client.Render.Tessellator.TessRender;
import RW.Common.Container.ContainerAffixer;
import RW.Common.Container.ContainerDUStorage;
import RW.Common.Container.ContainerDarkDeconstructor;
import RW.Common.Container.ContainerEnergetic;
import RW.Common.Container.ContainerExtractor;
import RW.Common.Container.ContainerReactor;
import RW.Common.Container.ContainerTower;
import RW.Common.Entity.EntityFireSpark;
import RW.Common.Tile.GraviterTileEntity;
import RW.Common.Tile.TessTileEntity;
import RW.Common.Tile.TileEntityAffixer;
import RW.Common.Tile.TileEntityDUStorage;
import RW.Common.Tile.TileEntityDarkDeconstructor;
import RW.Common.Tile.TileEntityEnergyTower;
import RW.Common.Tile.TileEntityExtractor;
import RW.Common.Tile.TileEntityModificationAnvil;
import RW.Common.Tile.TileEntityNexus;
import RW.Common.Tile.TileEntityPillar;
import RW.Common.Tile.TileEntityReactorCore;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler
{
	@SuppressWarnings("unchecked")
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) 
	{
		if(ID==0)
		{
			TileEntityExtractor tile = (TileEntityExtractor) world.getTileEntity(x, y, z);
			return new ContainerExtractor(player.inventory, tile);
		}
		if(ID==1)
		{
			return new ContainerReactor((TileEntityReactorCore) world.getTileEntity(x, y, z), player.inventory);
		}
		if(ID==2)
		{
			return new ContainerEnergetic(player.inventory, (EnergeticTileEntity) world.getTileEntity(x, y, z));
		}
		if(ID==3)
		{
			return new ContainerTower(player.inventory, (TileEntityEnergyTower) world.getTileEntity(x, y, z));
		}
		if(ID==4)
		{
			return new ContainerDarkDeconstructor(player.inventory, (TileEntityDarkDeconstructor) world.getTileEntity(x, y, z));
		}
		if(ID==5)
		{
			return new ContainerAffixer(player.inventory, (TileEntityAffixer) world.getTileEntity(x, y, z));
		}
		if(ID==6)
		{
			return new ContainerDUStorage(player.inventory, (TileEntityDUStorage) world.getTileEntity(x, y, z));
		}
		return null;
	}
	
	public void registerSpecialRenderer()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityModificationAnvil.class,new ModificationAnvilRender());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPillar.class,new TileEntityPillarRender());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEnergyTower.class,new EnergyTowerRender());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDarkDeconstructor.class,new DarkDeconstructorRender());
		ClientRegistry.bindTileEntitySpecialRenderer(TessTileEntity.class,new TessRender());
		ClientRegistry.bindTileEntitySpecialRenderer(GraviterTileEntity.class,new GraviterRender());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNexus.class,new NexusRender());
		RenderingRegistry.registerBlockHandler(new TuberRender());//TileEntityNexus
		RenderingRegistry.registerBlockHandler(new RW.Client.Render.Block.ModificationAnvilRender());
		RenderingRegistry.registerBlockHandler(new RW.Client.Render.Block.DarkDeconstructorRender());
		RenderingRegistry.registerBlockHandler(new RW.Client.Render.Block.EnergyTowerRender());
		RenderingRegistry.registerBlockHandler(new RW.Client.Render.Block.PillarRender());
		RenderingRegistry.registerBlockHandler(new RW.Client.Render.Block.GraviterRender());
		RenderingRegistry.registerBlockHandler(new RW.Client.Render.Block.NexusRender());
		RenderingRegistry.registerBlockHandler(new SynchronizerRender());
		RenderingRegistry.registerBlockHandler(new StorageRenderer());
		RenderingRegistry.registerBlockHandler(new TessBlockRender());
 
		RenderingRegistry.registerEntityRenderingHandler(EntityFireSpark.class, new BaseMobRenderer(null,1.0F, null));
	}
	
	public void registerTiles()
	{
		ClientRegistry.registerTileEntity(TileEntityModificationAnvil.class,"rogueWorld:TileModificationAnvil",new ModificationAnvilRender());
		ClientRegistry.registerTileEntity(TileEntityPillar.class,"rogueWorld:TileEntityPillar",new TileEntityPillarRender());
		ClientRegistry.registerTileEntity(TileEntityEnergyTower.class,"rogueWorld:TileEntityEnergyTower",new EnergyTowerRender());
		ClientRegistry.registerTileEntity(TileEntityDarkDeconstructor.class,"rogueWorld:TileEntityDarkDeconstructor",new DarkDeconstructorRender());

	}

	public EntityPlayer getClientPlayer()
	{
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}

	public void spawnParticle(String name, double x, double y, double z, double i, double j, double k)
	{
		if(name.equals("energyFX"))
			Minecraft.getMinecraft().effectRenderer.addEffect(new BindingBeam(getClientWorld(), x, y, z, i, j, k));
		
	}

	public World getClientWorld()
	{
		return null;
	}

	public void EnergyFX(double... ds)
	{
		
	}

	public void SparkFX(double ... ds)
	{
		
	}

	public void openBookGUIForPlayer()
	{
		
	}

}
