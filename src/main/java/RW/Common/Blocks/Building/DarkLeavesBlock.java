/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Blocks.Building;

import RW.Common.Registry.MiscRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * @author Lord_Crystalyx
 */
public class DarkLeavesBlock extends Block
{

	public DarkLeavesBlock(Material mat)
	{
		super(mat);
		this.setBlockTextureName("rogueWorld:dark_leaves");
		this.setCreativeTab(MiscRegistry.modTab);
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

}
