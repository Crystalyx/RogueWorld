/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Misc;

import RW.Common.Registry.ItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * @author Lord_Crystalyx
 */
public class CreativeTabRogueWorld extends CreativeTabs
{

	public CreativeTabRogueWorld(String p_i1853_2_)
	{
		super(p_i1853_2_);
	}

	@Override
	public Item getTabIconItem()
	{
		return ItemRegistry.DSword;
	}

}
