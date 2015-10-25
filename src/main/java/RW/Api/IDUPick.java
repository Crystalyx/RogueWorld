package RW.Api;

import java.util.List;

import RW.Utils.Logger;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class IDUPick extends ItemPickaxe implements IItemContainsDU
{
	public int maxdu = 20000;

	public IDUPick(ToolMaterial mat,int max)
	{
		super(mat);
		this.maxdu = max;
	}

	public IDUPick(ToolMaterial mat)
	{
		super(mat);
	}
	@Override
	public int addDU(ItemStack i, int du)
	{
		if (i.hasTagCompound())
		{
			if (this.getEnergy(i) + du > this.maxdu)
			{
				int ret = this.getEnergy(i) + du - this.maxdu;
				this.setEnergy(i, this.maxdu);
				Logger.info("DUAdded");
				return ret;
			} else
			{
				this.setEnergy(i, this.getEnergy(i) + du);
				Logger.info("DUAdded");
				return 0;
			}
		}
		return du;
	}

	@Override
	public int consumeDU(ItemStack i, int du)
	{
		if (i.hasTagCompound())
		{
			if (this.getEnergy(i) < du)
			{
				int ret = du - this.getEnergy(i);
				this.setEnergy(i,0);
				Logger.info("DUConsumed");
				return ret;
			} else
			{
				int ret = 0;
				this.setEnergy(i, this.getEnergy(i) - du);
				Logger.info("DUConsumed");
				return ret;
			}
		}
		return du;
	}
	
	public void setEnergy(ItemStack i,int e)
	{
		i.getTagCompound().setInteger("Energy",e);
	}
	
	public int getEnergy(ItemStack i)
	{
		return i.getTagCompound().getInteger("Energy");
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

	@Override
	public void setMax(int i)
	{
		this.maxdu=i;
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
	public int getMax()
	{
		return this.maxdu;
	}

}
