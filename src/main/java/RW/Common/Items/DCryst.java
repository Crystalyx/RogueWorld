/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Items;

import RW.Common.Registry.MiscRegistry;
import net.minecraft.item.Item;

public class DCryst extends Item
{
	public DCryst()
	{
		super();
		this.setTextureName("rogueWorld:dark_crystal");
		this.setUnlocalizedName("rw.dcrystal");
		this.setCreativeTab(MiscRegistry.modTab);
	}
}
