/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Items;

import RW.Common.Registry.MiscRegistry;
import net.minecraft.item.Item;

public class AtrilliumCapItem extends Item
{
	public AtrilliumCapItem()
	{
		this.setCreativeTab(MiscRegistry.modTab);
		this.setTextureName("rogueWorld:wand_cap_atrillium");
		this.setUnlocalizedName("rw.atrilliumcap");
	}
}
