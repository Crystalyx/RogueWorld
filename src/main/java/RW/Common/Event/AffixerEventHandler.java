package RW.Common.Event;

import RW.Common.Items.ElementalShard;
import RW.Common.Registry.ItemRegistry;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class AffixerEventHandler
{
	@SubscribeEvent(priority=EventPriority.NORMAL)
	public void AttackEntityEvent(AttackEntityEvent event)
	{
		if(event.entityPlayer.getCurrentEquippedItem() != null)
		{
			if(event.entityPlayer.getCurrentEquippedItem().getItem() == ItemRegistry.DSword)
			{
				if(event.entityPlayer.getCurrentEquippedItem().getTagCompound() != null)
				{
					if(event.entityPlayer.getCurrentEquippedItem().getTagCompound().getInteger("Fire") > 0)
					{
						event.target.setFire(event.entityPlayer.getCurrentEquippedItem().getTagCompound().getInteger("Fire"));
					}
					if(event.entityPlayer.getCurrentEquippedItem().getTagCompound().getInteger("Earth") > 0)
					{
						event.target.attackEntityFrom(DamageSource.causePlayerDamage(event.entityPlayer), event.entityPlayer.getCurrentEquippedItem().getTagCompound().getInteger("Earth"));
					}
					if(event.entityPlayer.getCurrentEquippedItem().getTagCompound().getInteger("Water") > 0)
					{
						if(event.target.isBurning())
						{
							event.target.extinguish();
						}
					}
					if(event.entityPlayer.getCurrentEquippedItem().getTagCompound().getInteger("Power") > 0)
					{
						event.target.attackEntityFrom(DamageSource.causePlayerDamage(event.entityPlayer), event.entityPlayer.getCurrentEquippedItem().getTagCompound().getInteger("Power"));
					}
					if(event.entityPlayer.getCurrentEquippedItem().getTagCompound().getInteger("Magic") > 0)
					{
						if(!event.target.noClip)
						{
							event.target.noClip=true;
						}
					}
					if(event.entityPlayer.getCurrentEquippedItem().getTagCompound().getInteger("Sky") > 0)
					{
						event.target.setVelocity(0.0D, 1.0D, 0.0D);
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void ItemTooltipEvent(ItemTooltipEvent event)
	{
		if(event.itemStack.getItem() == ItemRegistry.DSword)
		{
			if(event.itemStack.hasTagCompound())
			{
				for(int i = 0; i < 6;i++)
				{
					if(event.itemStack.getTagCompound().getInteger(ElementalShard.names[i])>0)
					event.toolTip.add(ElementalShard.names[i]+": "+event.itemStack.getTagCompound().getInteger(ElementalShard.names[i]));
				}
			}
		}
	}
}
