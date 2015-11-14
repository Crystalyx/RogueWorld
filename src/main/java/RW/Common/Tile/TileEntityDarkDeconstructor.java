/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Tile;

import RW.Api.EnergeticTileEntity;
import RW.Common.Items.MetaItem;
import RW.Common.Misc.WorldPos;
import RW.Common.Registry.ItemRegistry;
import RW.Utils.Logger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

/**
 * @author Lord_Crystalyx
 */
public class TileEntityDarkDeconstructor extends EnergeticTileEntity implements ISidedInventory
{
	public ItemStack[] inventory = new ItemStack[5];

	public TileEntityDarkDeconstructor()
	{
		super(2, 50000, "Dark Deconstructor");
		this.uname = "deconstructor";
		this.doSaveInventory = false;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack i)
	{
		if (slot > 0)
		{
			if (i != null)
				if (i.getItem() instanceof MetaItem && (i.getItemDamage() == 2 || i.getItemDamage() == 3))
				{
					return true;
				}
		}
		return false;
	}

	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		int[] it = tag.getIntArray("linkedTo");
		if (it.length == 3)
			this.linkedTo = new WorldPos(it[0], it[1], it[2]);
		NBTTagList nbttaglist = tag.getTagList("Items", 10);
		this.inventory = new ItemStack[this.getSizeInventory()];
		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.inventory.length)
			{
				this.inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}

	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		if (this.linkedTo != null)
			tag.setIntArray("linkedTo", this.linkedTo.toIntArray());
		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < this.inventory.length; ++i)
		{
			if (this.inventory[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.inventory[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		tag.setTag("Items", nbttaglist);
	}

	public int burnTime = 0;

	public void updateEntity()
	{
		if (this.inventory[0] != null && this.inventory[0].getItem() == ItemRegistry.DCryst)
		{
			if (this.burnTime < getMaxBurnTime() && (this.energy + getOutput()) < this.maxEnergy)
			{
				++this.burnTime;
			}
			if (this.burnTime >= getMaxBurnTime())
			{
				if (this.inventory[0].stackSize > 1)
					--this.inventory[0].stackSize;
				else
					this.inventory[0] = null;
				this.burnTime = 0;
				this.energy += getOutput();
			}
		}
	}

	public int count(ItemStack i)
	{
		int count = 0;
		for (int l = 1; l < 5; l++)
		{
			if (this.inventory[l] != null)
			{
				if (this.inventory[l].isItemEqual(i))
				{
					count += this.inventory[l].stackSize;
				}
			}
		}
		return count;
	}

	public int getMaxBurnTime()
	{
		int ret = 200 - (count(new ItemStack(ItemRegistry.metai, 1, 3)) * 6);
		if (ret < 20)
		{
			ret = 20;
		}
		return ret;
	}

	public int getOutput()
	{
		int ret = 2000 + count(new ItemStack(ItemRegistry.metai, 1, 2)) * 20;
		return ret;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return this.inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int c)
	{
		if (this.inventory[slot] != null)
		{
			if (this.inventory[slot].stackSize - c <= 0)
			{
				ItemStack ret = this.inventory[slot];
				this.inventory[slot] = null;
				return ret;
			}
			else
			{
				ItemStack ret = new ItemStack(this.inventory[slot].getItem(), c, this.inventory[slot].getItemDamage());
				this.inventory[slot].stackSize -= c;
				return ret;
			}
		}
		else
		{
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		if (this.inventory[slot] != null)
		{
			ItemStack itemstack = this.inventory[slot];
			this.inventory[slot] = null;
			return itemstack;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack i)
	{
		this.inventory[slot] = i;

		if (i != null && i.stackSize > this.getInventoryStackLimit())
		{
			i.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName()
	{
		return this.uname;
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_)
	{
		return true;
	}

	@Override
	public void openInventory()
	{

	}

	@Override
	public void closeInventory()
	{

	}

	@Override
	public int[] getAccessibleSlotsFromSide(int p_94128_1_)
	{
		return new int[] { 0 };
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack i, int side)
	{
		if (slot == 0 && i != null && (this.inventory[0] == null || i.getItem() == this.inventory[0].getItem()))
			return true;
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack i, int side)
	{
		if (slot == 0 && i != null && (this.inventory[0] == null || i.getItem() == this.inventory[0].getItem()))
			return true;
		return false;
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound syncData = new NBTTagCompound();
		this.writeToNBT(syncData);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		readFromNBT(pkt.func_148857_g());
	}
}
