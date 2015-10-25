/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Integration;

import java.util.Iterator;

import cpw.mods.fml.common.Optional;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.IWandRodOnUpdate;

public class TestUpd implements IWandRodOnUpdate
{
	public int tick;
	@Override
	public void onUpdate(ItemStack wandstack, EntityPlayer player)
	{
		if(this.tick >= 0)
		{
			if (wandstack.getTagCompound() != null)
			{
				Iterator<Aspect> iter = Aspect.getPrimalAspects().iterator();
				while (iter.hasNext())
				{
					Aspect a = iter.next();
					if(wandstack.getTagCompound().getInteger(a.getTag()) < 75000)
					wandstack.getTagCompound().setFloat(a.getTag(), wandstack.getTagCompound().getFloat(a.getTag()) + 100);
				}
			}
			this.tick=0;
		}
		++this.tick;
	}

}
