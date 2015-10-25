package RW.Common.Blocks;

import RW.Common.Registry.MiscRegistry;
import RW.Common.Tile.AGraviterTileEntity;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class AGraviterBlock extends BlockContainer
{

	public AGraviterBlock()
	{
		super(Material.dragonEgg);
		this.setBlockTextureName("rogueWorld:null");
		this.setCreativeTab(MiscRegistry.modTab);
		this.setBlockName("rw.agraviter");
		this.setResistance(10.0F);
		this.setHardness(10.0F);
	}
	
	/**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z)
    {
        return AxisAlignedBB.getBoundingBox(x, y, z, x+1, y+1, z+1);
    }

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new AGraviterTileEntity();
	}

}
