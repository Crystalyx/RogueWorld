/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Blocks.Reactor;

import RW.Common.Registry.ItemRegistry;
import RW.Common.Registry.MiscRegistry;
import RW.Common.Tile.TileEntityReactorCore;
import RW.Core.RogueWorldCore;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockReactorCore extends BlockContainer
{

	public BlockReactorCore()
	{
		super(Material.iron);
		this.setBlockTextureName("rogueWorld:reactor/reactorcore");
		this.setCreativeTab(MiscRegistry.modTab);
		this.setBlockName("rw.reactorcore");
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntityReactorCore();
	}

	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int side, float px, float py, float pz)
	{
		if (p.getCurrentEquippedItem() != null)
		{
			if(p.getCurrentEquippedItem().getItem() == ItemRegistry.wrench)
			{
				return false;
			}
		}
		p.openGui(RogueWorldCore.core, 1, w, x, y, z);
		return true;
	}

}
