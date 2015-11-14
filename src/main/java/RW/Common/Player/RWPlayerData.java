package RW.Common.Player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * @author Lord_Crystalyx
 */
public class RWPlayerData
{
	public boolean playerIsSeeker = false;
	public int patronage = 0;
	public EntityPlayer player;
	public ItemStack[] sphInv = new ItemStack[54];

	public RWPlayerData(EntityPlayer player)
	{
		this.player = player;
	}

	public void setSeeker(boolean set)
	{
		this.playerIsSeeker = set;
	}

	public void addPatronage(int count)
	{
		this.patronage += count;
	}

	public void setPatronage(int count)
	{
		this.patronage = count;
	}

	public void readFromNBT(NBTTagCompound tag)
	{
		this.playerIsSeeker = tag.getBoolean("Seeker");
		this.patronage = tag.getInteger("Patronage");

		NBTTagList nbttaglist = tag.getTagList("Items", 10);
		this.sphInv = new ItemStack[54];

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			if (j >= 0 && j < this.sphInv.length)
			{
				this.sphInv[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}

	public void writeToNBT(NBTTagCompound tag)
	{
		tag.setBoolean("Seeker", this.playerIsSeeker);
		tag.setInteger("Patronage", this.patronage);

		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.sphInv.length; ++i)
		{
			if (this.sphInv[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.sphInv[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		tag.setTag("Items", nbttaglist);
	}
}
