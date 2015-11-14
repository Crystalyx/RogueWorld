/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Blocks;

import RW.Common.Registry.MiscRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * @author Lord_Crystalyx
 */
public class AltersteelBlock extends Block
{

	public AltersteelBlock()
	{
		super(Material.iron);
		this.setHardness(3.0F);
		this.setCreativeTab(MiscRegistry.modTab);
		this.setBlockName("rw.altersteelblock");
		this.setBlockTextureName("rogueWorld:block_altersteel");
	}

}
