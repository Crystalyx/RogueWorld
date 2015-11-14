package RW.Common.Blocks;

import RW.Common.Registry.MiscRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * @author Lord_Crystalyx
 */
public class TuberBlock extends Block
{
	public TuberBlock()
	{
		super(Material.iron);
		this.setBlockTextureName("rogueWorld:tuber");
		this.setCreativeTab(MiscRegistry.modTab);
		this.setBlockName("rw.tuber");
	}

	public int getRenderType()
	{
		return 0x8976;
	}

	public boolean isNormalCube()
	{
		return false;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}
}
