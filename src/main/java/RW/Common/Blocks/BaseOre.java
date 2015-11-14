package RW.Common.Blocks;

import java.util.Random;

import RW.Core.RogueWorldCore;
import net.minecraft.block.BlockOre;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

/**
 * @author Lord_Crystalyx
 */
public class BaseOre extends BlockOre
{
	public BaseOre(String uName, String texture, float hardness, float resist, RogueWorldCore mod, ItemStack dropped, boolean fortune, int xp)
	{
		this.setBlockName(uName);
		this.setBlockTextureName(mod.ModId + ":" + texture);
		this.setHardness(hardness);
		this.setResistance(resist);
		this.setCreativeTab(mod.MCore.modTab);
		this.drop = dropped;
		this.fortAffect = fortune;
		this.xpdrop = xp;
	}

	public BaseOre(String uName, String texture, float hardness, float resist, RogueWorldCore mod)
	{
		this.setBlockName(uName);
		this.setBlockTextureName(mod.ModId + ":" + texture);
		this.setHardness(hardness);
		this.setResistance(resist);
		this.setCreativeTab(mod.MCore.modTab);
	}

	public ItemStack drop;
	public boolean fortAffect = true;
	public int xpdrop = 2;

	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		if (this.drop != null)
			return this.drop.getItem();
		return Item.getItemFromBlock(this);
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(Random p_149745_1_)
	{
		if (this.drop != null)
			return this.drop.stackSize;
		return 1;
	}

	/**
	 * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i'
	 * (inclusive).
	 */
	public int quantityDroppedWithBonus(int p_149679_1_, Random p_149679_2_)
	{
		if (this.fortAffect)
		{
			if (p_149679_1_ > 0 && Item.getItemFromBlock(this) != this.getItemDropped(0, p_149679_2_, p_149679_1_))
			{
				int j = p_149679_2_.nextInt(p_149679_1_ + 2) - 1;

				if (j < 0)
				{
					j = 0;
				}

				return this.quantityDropped(p_149679_2_) * (j + 1);
			}
			else
			{
				return this.quantityDropped(p_149679_2_);
			}
		}
		return this.quantityDropped(p_149679_2_);

	}

	private Random rand = new Random();

	@Override
	public int getExpDrop(IBlockAccess p_149690_1_, int p_149690_5_, int p_149690_7_)
	{
		if (this.getItemDropped(p_149690_5_, rand, p_149690_7_) != Item.getItemFromBlock(this))
		{
			int j1 = 0;

			j1 = MathHelper.getRandomIntegerInRange(rand, this.xpdrop - 2, this.xpdrop);

			return j1;
		}
		return 0;
	}

	/**
	 * Determines the damage on the item the block drops. Used in cloth and
	 * wood.
	 */
	public int damageDropped(int p_149692_1_)
	{
		if (this.drop != null)
			return this.drop.getItemDamage();
		return 0;
	}
}
