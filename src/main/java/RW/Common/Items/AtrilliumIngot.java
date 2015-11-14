/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Items;

import RW.Common.Registry.MiscRegistry;
import net.minecraft.item.Item;

/**
 * @author Lord_Crystalyx
 */
public class AtrilliumIngot extends Item
{
	public AtrilliumIngot()
	{
		this.setCreativeTab(MiscRegistry.modTab);
		this.setTextureName("rogueWorld:atrilliumingot");
		this.setUnlocalizedName("rw.atrilliumingot");
	}
}
