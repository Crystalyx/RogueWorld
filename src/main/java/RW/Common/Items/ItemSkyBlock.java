package RW.Common.Items;

import RW.Common.Blocks.SkyPieceBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * @author Lord_Crystalyx
 */
public class ItemSkyBlock extends ItemBlock
{
	public Block item;

	public ItemSkyBlock(Block b)
	{
		super(b);
		this.item = b;
	}

	public int getMetadata(int i)
	{
		return i;
	}

	public String getUnlocalizedName(ItemStack i)
	{
		return this.item.getUnlocalizedName() + SkyPieceBlock.names[i.getItemDamage()];
	}

}
