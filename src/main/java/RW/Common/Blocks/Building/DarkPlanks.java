/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Blocks.Building;

import RW.Common.Registry.MiscRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class DarkPlanks extends Block
{

	public DarkPlanks(Material mat) 
	{
		super(mat);
		this.setCreativeTab(MiscRegistry.modTab);
		this.setHardness(1.0F);
		this.setBlockTextureName("rogueWorld:dark_planks");
	}

}
