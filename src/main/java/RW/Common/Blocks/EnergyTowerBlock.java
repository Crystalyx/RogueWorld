/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Blocks;

import java.util.Random;

import RW.Api.EnergeticTileEntity;
import RW.Common.Registry.ItemRegistry;
import RW.Common.Registry.MiscRegistry;
import RW.Common.Tile.TileEntityEnergyTower;
import RW.Core.RogueWorldCore;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class EnergyTowerBlock extends BlockContainer
{	
	private int tick=0;

	public EnergyTowerBlock()
	{
		super(Material.iron);
		this.setBlockTextureName("rogueWorld:null");
		this.setCreativeTab(MiscRegistry.modTab);
		this.setBlockName("rw.etower");
	}
	
	@Override
	public void randomDisplayTick(World w, int x, int y, int z, Random rand)
	{
		
	}
	
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int side, float fx, float fy, float fz)
	{
		if(!p.isSneaking())
		{
			if(p.getCurrentEquippedItem() != null)
			{ 
				if(p.getCurrentEquippedItem().getItem() == ItemRegistry.linkingRod)
				{
					return false;
				}
			}		
			p.openGui(RogueWorldCore.core, 3, w, x, y, z);
		}
		else
		{
			EnergeticTileEntity From = (EnergeticTileEntity) w.getTileEntity(x, y, z);
			From.unBound();
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		
		return new TileEntityEnergyTower();
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	public boolean isNormalCube()
	{
		return false;
	}
	
	public int getRenderType()
	{
		return 0x8979;
	}

}
