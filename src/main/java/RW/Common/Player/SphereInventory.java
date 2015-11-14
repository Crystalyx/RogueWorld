package RW.Common.Player;

import RW.Utils.MiscUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class SphereInventory implements IInventory
{
	public EntityPlayer p;

	public SphereInventory(EntityPlayer p)
	{
		this.p = p;
	}

	@Override
	public int getSizeInventory()
	{
		return 54;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		if (MiscUtils.playerData.get(this.p) != null)
			return MiscUtils.playerData.get(this.p).sphInv[slot];
		return null;
	}

	@Override
	public ItemStack decrStackSize(int slot, int count)
	{
		if (MiscUtils.playerData.get(this.p) != null)
			if (MiscUtils.playerData.get(this.p).sphInv[slot] != null)
			{
				if (MiscUtils.playerData.get(this.p).sphInv[slot].stackSize - count <= 0)
				{
					MiscUtils.playerData.get(this.p).sphInv[slot] = null;
				}
				else
				{
					MiscUtils.playerData.get(this.p).sphInv[slot].stackSize -= count;
				}
				return MiscUtils.playerData.get(this.p).sphInv[slot];
			}
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		if (MiscUtils.playerData.get(this.p) != null)

			if (MiscUtils.playerData.get(this.p).sphInv[slot] != null)
			{
				ItemStack itemstack = MiscUtils.playerData.get(this.p).sphInv[slot];
				MiscUtils.playerData.get(this.p).sphInv[slot] = null;
				return itemstack;
			}
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack is)
	{
		if (MiscUtils.playerData.get(this.p) != null)
			MiscUtils.playerData.get(this.p).sphInv[slot] = is;
	}

	@Override
	public String getInventoryName()
	{
		return "rw.advSph.inv.name";
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
	public void markDirty()
	{

	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p)
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
	public boolean isItemValidForSlot(int slot, ItemStack is)
	{
		return true;
	}

}
