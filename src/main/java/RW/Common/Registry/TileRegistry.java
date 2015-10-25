/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Registry;

import RW.Api.EnergeticTileEntity;
import RW.Common.Tile.AGraviterTileEntity;
import RW.Common.Tile.GraviterTileEntity;
import RW.Common.Tile.TessTileEntity;
import RW.Common.Tile.TileEntityAffixer;
import RW.Common.Tile.TileEntityDUStorage;
import RW.Common.Tile.TileEntityDarkDeconstructor;
import RW.Common.Tile.TileEntityEnergyTower;
import RW.Common.Tile.TileEntityExtractor;
import RW.Common.Tile.TileEntityIOPort;
import RW.Common.Tile.TileEntityModificationAnvil;
import RW.Common.Tile.TileEntityNexus;
import RW.Common.Tile.TileEntityPillar;
import RW.Common.Tile.TileEntityReactorCore;
import RW.Common.Tile.TileEntitySynchronizer;
import RW.Common.Tile.VoiderTileEntity;
import cpw.mods.fml.common.registry.GameRegistry;


public class TileRegistry
{
	public static TileRegistry TCore;
	
	public static void register() 
	{
		String id = "rw.";
		GameRegistry.registerTileEntity(TileEntityReactorCore.class,id+ new TileEntityReactorCore().getInventoryName());
		GameRegistry.registerTileEntity(TileEntitySynchronizer.class,id+ "synchronizer");
		GameRegistry.registerTileEntity(TileEntityExtractor.class,id+ new TileEntityExtractor(0, 0, null).getInventoryUName());
		GameRegistry.registerTileEntity(TileEntityModificationAnvil.class,id+ new TileEntityModificationAnvil().getInventoryUName());
		GameRegistry.registerTileEntity(TileEntityPillar.class,id+ new TileEntityPillar().getInventoryName());
		GameRegistry.registerTileEntity(TileEntityEnergyTower.class,id+ new TileEntityEnergyTower().getInventoryUName());
		GameRegistry.registerTileEntity(EnergeticTileEntity.class,id+ "energetic");
		GameRegistry.registerTileEntity(TileEntityDarkDeconstructor.class,id+ new TileEntityDarkDeconstructor().getInventoryUName());
		GameRegistry.registerTileEntity(TileEntityAffixer.class,id+  new TileEntityAffixer().getInventoryUName());
		GameRegistry.registerTileEntity(VoiderTileEntity.class,id+  "voider");
		GameRegistry.registerTileEntity(AGraviterTileEntity.class,id+  "agraviter");
		GameRegistry.registerTileEntity(GraviterTileEntity.class,id+  "rgraviter");
		GameRegistry.registerTileEntity(TessTileEntity.class,id+  "tess");
		GameRegistry.registerTileEntity(TileEntityNexus.class,id+  new TileEntityNexus().getInventoryName());
		GameRegistry.registerTileEntity(TileEntityDUStorage.class,id+  new TileEntityDUStorage().getInventoryUName());
		GameRegistry.registerTileEntity(TileEntityIOPort.class,id+  new TileEntityIOPort().getInventoryName());

	}	
}
