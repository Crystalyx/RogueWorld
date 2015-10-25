package RW.Common.Blocks;

import RW.Common.Registry.MiscRegistry;
import RW.Common.Tile.VoiderTileEntity;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class VoiderBlock extends BlockContainer
{

	public VoiderBlock()
	{
		super(Material.dragonEgg);
		this.setBlockTextureName("rogueWorld:null");
		this.setCreativeTab(MiscRegistry.modTab);
		this.setBlockName("rw.voider");
		this.setResistance(10.0F);
		this.setHardness(10.0F);
	}
	
	/**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        return null;
    }

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new VoiderTileEntity();
	}

}
