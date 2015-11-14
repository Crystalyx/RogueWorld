/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Items;

import RW.Common.Registry.MiscRegistry;
import net.minecraft.item.Item;

/**
 * @author Lord_Crystalyx
 */
public class AltersteelRodItem extends Item
{
	public AltersteelRodItem()
	{
		this.setCreativeTab(MiscRegistry.modTab);
		this.setTextureName("rogueWorld:altersteel_rod");
		this.setUnlocalizedName("rw.altersteelrod");
	}
}
