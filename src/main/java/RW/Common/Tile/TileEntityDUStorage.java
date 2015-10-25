package RW.Common.Tile;

import RW.Api.EnergeticTileEntity;
import RW.Api.IDUItem;
import RW.Api.IItemContainsDU;
import RW.Common.Misc.WorldPos;
import RW.Common.Registry.ItemRegistry;
import RW.Core.RogueWorldCore;
import RW.Utils.Logger;
import RW.Utils.MiscUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileEntityDUStorage extends EnergeticTileEntity
{
	public WorldPos[] linkedTo = new WorldPos[8];
	public ItemStack[] inventory = new ItemStack[6];
	public boolean[] isBound = new boolean[8];

	public TileEntityDUStorage()
	{
		super(1, 200000, "DUStorage");
		this.uname = "dustorage";
		this.doSaveInventory = false;
	}

	public void updateEntity()
	{

		if (this.inventory[5] != null)
		{
			if (this.inventory[5].hasTagCompound())
			{
				if (this.inventory[5].getItem() instanceof IItemContainsDU)
				{
					if (this.inventory[5].getTagCompound().getInteger("Energy") >= this.maxPacketSize)
					{
						if (this.energy <=this.maxEnergy - this.maxPacketSize)
						{
							int exc = ((IItemContainsDU) this.inventory[5].getItem()).consumeDU(this.inventory[5], this.maxPacketSize);
							int def = this.addEnergy(this.maxPacketSize - exc);
							if(def>0)
							((IItemContainsDU) this.inventory[5].getItem()).addDU(this.inventory[5], def);
						}
					}
				}
			}
		}

		if (this.inventory[4] != null)
		{
			if (this.inventory[4].hasTagCompound())
			{
				if (this.inventory[4].getItem() instanceof IItemContainsDU)
				{
					if (this.inventory[4].getTagCompound().getInteger("Energy") <= ((IItemContainsDU) this.inventory[4].getItem()).getMax())
					{
						if (this.energy >= this.maxPacketSize)
						{
							int exc = ((IItemContainsDU) this.inventory[4].getItem()).addDU(this.inventory[4], this.maxPacketSize);
							int def = this.consumeEnergy(this.maxPacketSize - exc);
							if(def>0)
							((IItemContainsDU) this.inventory[4].getItem()).consumeDU(this.inventory[4], def);
						}
					}
				}
			}
		}

		for (int i = 0; i < 8; i++)
		{
			if (Minecraft.getMinecraft().getSystemTime() % 200 == 0)
			{
				if (this.linkedTo[i] != null)
				{
					if (this.getWorldObj().getTileEntity((int) this.linkedTo[i].getX(), (int) this.linkedTo[i].getY(), (int) this.linkedTo[i].getZ()) == null)
					{
						this.unBound(i);
						Logger.info("UnBound - Null TileEntity");
					} else
					{
						if (!(this.getWorldObj().getTileEntity((int) this.linkedTo[i].getX(), (int) this.linkedTo[i].getY(), (int) this.linkedTo[i].getZ()) instanceof EnergeticTileEntity))
						{
							this.unBound(i);
							Logger.info("UnBound - Non Energetic TileEntity");
						} else
						{
							if (MiscUtils.getDistance(new WorldPos(this), linkedTo[i]) > this.getRange())
							{
								this.unBound(i);
								Logger.info("UnBound - too much distance");
							}
						}
					}
				}
				this.tick = 0;
			}

			if (this.isBound[i])
			{
				EntityLivingBase viewer = Minecraft.getMinecraft().renderViewEntity;
				if (viewer instanceof EntityPlayer)
				{
					if (Minecraft.getMinecraft().getSystemTime() % 10 == 0)
					{
						if (this.linkedTo[i] != null)
						{
							RogueWorldCore.proxy.spawnParticle("energyFX", this.xCoord + 0.5F, this.yCoord + 0.3F, this.zCoord + 0.5F, this.linkedTo[i].getX() + 0.5, this.linkedTo[i].getY() + 0.8, this.linkedTo[i].getZ() + 0.5);
						}
					}
				}
			}

			++this.tick;

			if (this.linkedTo[i] != null)
			{
				consumeAndAdd();
			}
		}
		if (count(new ItemStack(Items.rotten_flesh)) > 0)
		{
			this.energy = 0;
		}

		if (count(new ItemStack(Items.apple)) > 0 || this.isCreative)
		{
			this.energy = this.maxEnergy;
		}

	}

	/**
	 * 
	 */
	public void consumeAndAdd()
	{
		for (int i = 0; i < 8; i++)
			if (this.linkedTo[i] != null)
			{
				EnergeticTileEntity From = (EnergeticTileEntity) this.getWorldObj().getTileEntity((int) this.linkedTo[i].getX(), (int) this.linkedTo[i].getY(), (int) this.linkedTo[i].getZ());
				if (From != null)
				{
					int cons = this.getSpeed() - From.consumeEnergy(this.getSpeed());
					int ecxs = this.addEnergy(cons);

					From.addEnergy(ecxs);
				}
			}
	}

	/**
	 * 
	 */
	public void unBound(int index)
	{
		this.linkedTo[index] = null;
		this.isBound[index] = false;
	}

	/**
	 * 
	 */
	public void unBoundFull()
	{
		for (int i = 0; i < 8; i++)
		{
			this.linkedTo[i] = null;
			this.isBound[i] = false;
		}
	}

	/**
	 * 
	 * @param pos
	 *            - Bounding To
	 */
	public void boundTo(int index, WorldPos pos)
	{
		if (index >= 0)
			if (pos != null)
			{
				this.linkedTo[index] = pos;
				this.isBound[index] = true;
			}
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return this.inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int count)
	{
		if (this.inventory[slot] != null)
		{
			if (this.inventory[slot].stackSize - count <= 0)
			{
				ItemStack ret = this.inventory[slot];
				this.inventory[slot] = null;
				return ret;
			} else
			{
				ItemStack ret = new ItemStack(this.inventory[slot].getItem(), count, this.inventory[slot].getItemDamage());
				this.inventory[slot].stackSize -= count;
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
		if (this.inventory[slot] != null)
		{
			ItemStack itemstack = this.inventory[slot];
			this.inventory[slot] = null;
			return itemstack;
		} else
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
	public int getSizeInventory()
	{
		return 6;
	}

	@Override
	public String getInventoryName()
	{
		return "DU Storage";
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
		super.isItemValidForSlot(slot, i);
		if (slot < 4)
		{
			if (i != null)
			{
				if (i.getItem() == ItemRegistry.metai && i.getItemDamage() < 2)
				{
					return true;
				} else
					return false;
			}
		}
		return true;
	}

	public int getEnergyScaled()
	{
		return this.energy * 70 / this.maxEnergy;
	}

	/**
	 * Reads a tile entity from NBT.
	 */
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		for (int i = 0; i < 8; i++)
		{
			int[] integ = tag.getIntArray("linkedTo" + i);
			if (integ.length == 3)
				this.linkedTo[i] = new WorldPos(integ[0], integ[1], integ[2]);
		}

		NBTTagList nbttaglist = tag.getTagList("Items", 10);
		this.inventory = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			if (j >= 0 && j < this.inventory.length)
			{
				this.inventory[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		if (tag.hasKey("CustomName"))
		{
			this.name = tag.getString("CustomName");
		}
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < 8; i++)
		{
			if (this.linkedTo[i] != null)
			{
				int[] integ = this.linkedTo[i].toIntArray();
				tag.setIntArray("linkedTo" + i, integ);
			}
		}
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

		if (this.hasCustomInventoryName())
		{
			tag.setString("CustomName", this.name);
		}
	}
}
