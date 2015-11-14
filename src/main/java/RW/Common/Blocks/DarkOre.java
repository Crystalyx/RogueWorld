/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Blocks;

import java.util.Random;

import RW.Common.Registry.ItemRegistry;
import RW.Common.Registry.MiscRegistry;
import net.minecraft.block.BlockOre;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;

/**
 * @author Lord_Crystalyx
 */
public class DarkOre extends BlockOre
{
	public DarkOre()
	{
		super();
		this.setCreativeTab(MiscRegistry.modTab);
	}

	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return ItemRegistry.DCryst;
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(Random p_149745_1_)
	{
		return 2;
	}

	/**
	 * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i'
	 * (inclusive).
	 */
	public int quantityDroppedWithBonus(int level, Random r)
	{
		if (level > 0)
			return r.nextInt(level) + 2;
		else
			return 2;
	}

	private Random rand = new Random();

	@Override
	public int getExpDrop(IBlockAccess p_149690_1_, int p_149690_5_, int p_149690_7_)
	{
		return 20;
	}
}
