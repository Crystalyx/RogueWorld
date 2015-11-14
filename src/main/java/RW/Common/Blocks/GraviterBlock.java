package RW.Common.Blocks;

import RW.Common.Registry.MiscRegistry;
import RW.Common.Tile.GraviterTileEntity;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/**
 * @author Lord_Crystalyx
 */
public class GraviterBlock extends BlockContainer
{

	public GraviterBlock()
	{
		super(Material.dragonEgg);
		this.setBlockTextureName("rogueWorld:null");
		this.setCreativeTab(MiscRegistry.modTab);
		this.setBlockName("rw.graviter");
		this.setResistance(10.0F);
		this.setHardness(10.0F);
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this
	 * box can change after the pool has been cleared to be reused)
	 */
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z)
	{
		return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new GraviterTileEntity();
	}

	public int getRenderType()
	{
		return 0x8981;
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
}
