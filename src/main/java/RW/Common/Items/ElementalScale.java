/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Items;

import RW.Common.Registry.MiscRegistry;
import net.minecraft.item.Item;

public class ElementalScale extends Item
{
	public ElementalScale()
	{
		this.setTextureName("rogueWorld:scale");
		this.setCreativeTab(MiscRegistry.modTab);
		this.setUnlocalizedName("rw.elementalshard");
	}
}
