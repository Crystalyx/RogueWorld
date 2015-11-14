/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Items;

import java.util.Collection;

import RW.Common.Registry.MiscRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * @author Lord_Crystalyx
 */
public class SoothingTea extends ItemFood
{
	public SoothingTea()
	{
		super(2, 5F, true);
		this.setTextureName("rogueWorld:dark_tea");
		this.setCreativeTab(MiscRegistry.modTab);
		this.setUnlocalizedName("rw.stea");
		this.setAlwaysEdible();
	}

	int l = 0;

	public void onFoodEaten(ItemStack i, World w, EntityPlayer p)
	{
		Collection coll = p.getActivePotionEffects();
		if (!coll.isEmpty())
		{
			for (int l = 0; l < coll.size(); ++l)
			{
				PotionEffect eff = (PotionEffect) coll.toArray()[l];
				if (Potion.potionTypes[eff.getPotionID()].isBadEffect())
				{
					p.removePotionEffect(eff.getPotionID());
				}
			}
		}
	}

	/**
	 * How long it takes to use or consume an item
	 */
	public int getMaxItemUseDuration(ItemStack p_77626_1_)
	{
		return 40;
	}

	/**
	 * returns the action that specifies what animation to play when the items
	 * is being used
	 */
	public EnumAction getItemUseAction(ItemStack p_77661_1_)
	{
		return EnumAction.drink;
	}
}
