/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Blocks;

import RW.Common.Registry.MiscRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class AtrilliumBlock extends Block
{

	public AtrilliumBlock()
	{
		super(Material.iron);
		this.setHardness(3.0F);
		this.setCreativeTab(MiscRegistry.modTab);
		this.setBlockName("rw.atrilliumblock");
		this.setBlockTextureName("rogueWorld:block_atrillium");
	}

}
