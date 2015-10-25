package RW.Common.Tile;

import RW.Api.EnergeticTileEntity;
import RW.Common.Items.ElementalShard;
import RW.Common.Registry.ItemRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileEntityAffixer extends EnergeticTileEntity
{

	public ItemStack[] inva = new ItemStack[6];
	private int maxBTime = 2000;
	public int Btime=0;
	public TileEntityAffixer()
	{
		super(0, 20000, "Affixer");
		this.uname="affixer";
	}
	
	public void updateEntity()
	{
		if(this.inva[0] != null && this.inva[1] != null)
		{
			if(this.inva[0].getItem() == ItemRegistry.DSword && this.inva[1].getItem() == ItemRegistry.EShard)
			{
				if(this.Btime < this.getMaxBurnTime())
				{
					++this.Btime;
				}
				else
				{
					this.Btime=0;
					if(this.inva[0].hasTagCompound())
					{
						this.inva[0].getTagCompound().setInteger(ElementalShard.names[this.inva[1].getItemDamage()], this.inva[0].getTagCompound().getInteger(ElementalShard.names[this.inva[1].getItemDamage()])+1);
					}
					else
					{
						NBTTagCompound tag = new NBTTagCompound();
						tag.setInteger(ElementalShard.names[this.inva[1].getItemDamage()], this.inva[0].getTagCompound().getInteger(ElementalShard.names[this.inva[1].getItemDamage()])+1);
						this.inva[0].setTagCompound(tag);
					}
					decrStackSize(1, 1);
				}
			}
		}
	}
	
	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return this.inva[slot];
	}
	
	@Override
	public ItemStack decrStackSize(int slot, int count)
	{
		if (this.inva[slot] != null)
		{
			if (this.inva[slot].stackSize - count <= 0)
			{
				ItemStack ret = this.inva[slot];
				this.inva[slot] = null;
				return ret;
			} else
			{
				ItemStack ret = new ItemStack(this.inva[slot].getItem(), count, this.inva[slot].getItemDamage());
				this.inva[slot].stackSize -= count;
				return ret;
			}
		} else
		{
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		if (this.inva[slot] != null)
		{
			ItemStack itemstack = this.inva[slot];
			this.inva[slot] = null;
			return itemstack;
		} else
		{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack i)
	{
		this.inva[slot] = i;

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
	public boolean isItemValidForSlot(int slot, ItemStack i)
	{
		return true;
	}
	
	public int getMaxBurnTime()
	{
		int ret = this.maxBTime  - (count(new ItemStack(ItemRegistry.metai, 1, 1)) * 60);
		if (ret<200)
		{
			ret = 200;
		}
		return ret;
	}
	
	@Override
	public int count(ItemStack i)
	{
		int count = 0;
		for (int l = 2; l < 6; l++)
		{
			if (this.inva[l] != null)
			{
				if (this.inva[l].getItem() == i.getItem() && this.inva[l].getItemDamage() == i.getItemDamage())
				{
					count += this.inva[l].stackSize;
				}
			}
		}
		return count;
	}
	
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		NBTTagList nbttaglist = tag.getTagList("Items", 10);
		this.inva = new ItemStack[6];

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.inva.length)
			{
				this.inva[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}

	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.inva.length; ++i)
		{
			if (this.inva[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.inva[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		tag.setTag("Items", nbttaglist);
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
