/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Items;

import RW.Common.Registry.MiscRegistry;
import net.minecraft.item.Item;

/**
 * @author Lord_Crystalyx
 */
public class AltersteelIngot extends Item
{
	public AltersteelIngot()
	{
		this.setCreativeTab(MiscRegistry.modTab);
		this.setTextureName("rogueWorld:altersteel");
		this.setUnlocalizedName("rw.altersteelingot");
	}
}
