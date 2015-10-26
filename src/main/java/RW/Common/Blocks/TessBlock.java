package RW.Common.Blocks;

import RW.Common.Registry.MiscRegistry;
import RW.Common.Tile.TessTileEntity;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TessBlock extends BlockContainer
{

	public TessBlock()
	{
		super(Material.anvil);
		this.setBlockTextureName("rogueWorld:null");
		this.setCreativeTab(MiscRegistry.modTab);
		this.setBlockName("rw.tess");
		this.setResistance(10.0F);
		this.setHardness(10.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TessTileEntity();
	}
	
	/**
	 * Is this block (a) opaque and (b) a full 1m cube? This determines whether
	 * or not to render the shared face of two adjacent blocks and also whether
	 * the player can attach torches, redstone wire, etc to this block.
	 */
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
		return 0;
	}

}
