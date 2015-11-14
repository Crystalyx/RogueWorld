package RW.Api;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

/**
 * @author Lord_Crystalyx
 */
public class IDUItem extends Item implements IItemContainsDU
{
	public int maxdu = 20000;

	public IDUItem(int max)
	{
		this.maxdu = max;
	}

	public IDUItem()
	{
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack i, EntityPlayer p, List l, boolean bool)
	{
		if (i.getTagCompound() != null)
		{
			l.add(EnumChatFormatting.GRAY + "" + i.getTagCompound().getInteger("Energy") + "/20000 DU");
		}
	}

	public int addDU(ItemStack i, int du)
	{
		if (i.hasTagCompound())
		{
			if (i.getTagCompound().getInteger("Energy") + du > this.maxdu)
			{
				int ret = i.getTagCompound().getInteger("Energy") + du - this.maxdu;
				i.getTagCompound().setInteger("Energy", this.maxdu);
				return ret;
			}
			else
			{
				i.getTagCompound().setInteger("Energy", i.getTagCompound().getInteger("Energy") + du);
				return 0;
			}
		}
		return du;
	}

	public int consumeDU(ItemStack i, int du)
	{
		if (i.hasTagCompound())
		{
			if (i.getTagCompound().getInteger("Energy") < du)
			{
				int ret = du - i.getTagCompound().getInteger("Energy");
				i.getTagCompound().setInteger("Energy", 0);
				return ret;
			}
			else
			{
				int ret = 0;
				i.getTagCompound().setInteger("Energy", i.getTagCompound().getInteger("Energy") - du);
				return ret;
			}
		}
		return du;
	}

	public void onUpdate(ItemStack i, World w, Entity e, int p_77663_4_, boolean inhand)
	{
		if (i.getTagCompound() == null)
		{
			NBTTagCompound tag = new NBTTagCompound();
			tag.setInteger("Energy", 0);
			i.setTagCompound(tag);
		}
	}

	@Override
	public void setMax(int i)
	{
		this.maxdu = i;
	}

	@Override
	public int getMax()
	{
		return this.maxdu;
	}
}
