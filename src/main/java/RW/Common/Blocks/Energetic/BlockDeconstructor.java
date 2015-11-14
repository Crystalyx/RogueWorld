package RW.Common.Blocks.Energetic;

import RW.Api.EnergeticTileEntity;
import RW.Common.Registry.ItemRegistry;
import RW.Common.Registry.MiscRegistry;
import RW.Common.Tile.TileEntityDarkDeconstructor;
import RW.Core.RogueWorldCore;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author Lord_Crystalyx
 */
public class BlockDeconstructor extends BlockContainer
{

	public BlockDeconstructor()
	{
		super(Material.anvil);
		this.setCreativeTab(MiscRegistry.modTab);
		this.setBlockName("rw.deconstructor");
		this.setBlockTextureName("rogueWorld:null");
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntityDarkDeconstructor();
	}

	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int side, float fx, float fy, float fz)
	{
		if (!p.isSneaking())
		{
			if (p.getCurrentEquippedItem() != null)
			{
				if (p.getCurrentEquippedItem().getItem() == ItemRegistry.linkingRod)
				{
					return false;
				}
			}
			p.openGui(RogueWorldCore.core, 4, w, x, y, z);
		}
		return true;
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
		return 0x8978;
	}

}
