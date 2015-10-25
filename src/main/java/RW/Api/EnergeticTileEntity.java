/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Api;

import RW.Client.FX.BindingBeam;
import RW.Common.Items.MetaItem;
import RW.Common.Misc.WorldPos;
import RW.Common.Registry.ItemRegistry;
import RW.Core.RogueWorldCore;
import RW.Utils.MiscUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class EnergeticTileEntity extends TileEntity implements IInventory
{
	public WorldPos linkedTo;
	public int range = 8;
	public int maxPacketSize = 100;
	public boolean isBound = false;
	public ItemStack[] inv = new ItemStack[4];
	public int energy = 0;
	public int maxEnergy = 1000;
	public BindingBeam beam;
	public int tick = 0;
	public String name;
	public boolean isCreative = false;
	public boolean doSaveInventory = true;
	/**
	 * 0 - for consumers; 1 - for mechanisms like RayTower(EC3) and energy
	 * storages; 2 - for generators;
	 */
	public int type = 1;
	protected String uname;

	public EnergeticTileEntity()
	{
		super();
		this.type = 1;
		this.maxEnergy = 0;
		this.name = "energetic";
	}

	public EnergeticTileEntity(int Type, int maxEnergy, String Name)
	{
		super();
		this.type = Type;
		this.maxEnergy = maxEnergy;
		this.name = Name;
	}

	public void updateEntity()
	{
		if (this.tick >= 200)
		{
			if (this.linkedTo != null)
			{
				if (this.getWorldObj().getTileEntity((int) this.linkedTo.getX(), (int) this.linkedTo.getY(), (int) this.linkedTo.getZ()) == null)
				{
					this.unBound();
				} else
				{
					if (!(this.getWorldObj().getTileEntity((int) this.linkedTo.getX(), (int) this.linkedTo.getY(), (int) this.linkedTo.getZ()) instanceof EnergeticTileEntity))
					{
						this.unBound();
					} else
					{
						if (MiscUtils.getDistance(new WorldPos(this), linkedTo) > this.getRange())
						{
							this.unBound();
						}
					}
				}
			}
			this.tick = 0;
		}
		if (this.isBound)
		{
			EntityLivingBase viewer = Minecraft.getMinecraft().renderViewEntity;
			if (viewer instanceof EntityPlayer)
			{
				if (this.tick % 10 == 0)
				{
					if (this.linkedTo != null)
					{
						if (this.worldObj.isRemote)
						{
							RogueWorldCore.proxy.spawnParticle("energyFX", this.xCoord + 0.5F, this.yCoord + 0.3F, this.zCoord + 0.5F, this.linkedTo.getX() + 0.5, this.linkedTo.getY() + 0.8, this.linkedTo.getZ() + 0.5);
						}
					}
				}
			}
		}

		++this.tick;

		if (this.linkedTo != null)
		{
			EnergeticTileEntity From = (EnergeticTileEntity) this.getWorldObj().getTileEntity((int) this.linkedTo.getX(), (int) this.linkedTo.getY(), (int) this.linkedTo.getZ());
			consumeAndAdd();
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

	public int getRange()
	{
		return this.range + (this.count(new ItemStack(ItemRegistry.metai, 1, 0)) * 4);
	}

	public int getSpeed()
	{
		return this.maxPacketSize + (this.count(new ItemStack(ItemRegistry.metai, 1, 1)) * 25);
	}

	public int count(ItemStack i)
	{
		int count = 0;
		for (int l = 0; l < 4; l++)
		{
			if (this.inv[l] != null)
			{
				if (this.inv[l].getItem() == i.getItem() && this.inv[l].getItemDamage() == i.getItemDamage())
				{
					count += this.inv[l].stackSize;
				}
			}
		}
		return count;
	}

	/**
	 * 
	 * @param count
	 *            Energy
	 * @return - Excess Energy
	 */
	public int addEnergy(int count)
	{
		if (this.energy + count > this.maxEnergy)
		{
			int ret = (this.energy + count) - this.maxEnergy;
			this.energy = this.maxEnergy;
			return ret;
		} else
			this.energy += count;
		return 0;
	}

	/**
	 * 
	 * @param count
	 *            Energy
	 * @return - Deficiency
	 */
	public int consumeEnergy(int count)
	{
		if (this.energy - count < 0)
		{
			int ret = count - this.energy;
			this.energy = 0;
			return ret;
		} else
		{
			int ret = 0;
			this.energy -= count;
			return ret;
		}
	}

	/**
	 * 
	 */
	public void consumeAndAdd()
	{
		if (this.linkedTo != null)
		{
			EnergeticTileEntity From = (EnergeticTileEntity) this.getWorldObj().getTileEntity((int) this.linkedTo.getX(), (int) this.linkedTo.getY(), (int) this.linkedTo.getZ());
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
	 * @param pos
	 *            - Bounding To
	 */
	public void boundTo(WorldPos pos)
	{
		if (pos != null)
		{
			this.linkedTo = pos;
			this.isBound = true;
		}
	}

	/**
	 * 
	 */
	public void unBound()
	{
		this.linkedTo = null;
		this.isBound = false;
	}

	/**
	 * 
	 * @param max
	 *            - Max Energy Value
	 */
	public void setMaxEnergy(int max)
	{
		this.maxEnergy = max;
	}

	/**
	 * 
	 * @param energy
	 *            - Energy Value
	 */
	public void setEnergy(int energy)
	{
		this.maxEnergy = energy;
	}

	@Override
	public int getSizeInventory()
	{
		return 5;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return this.inv[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int c)
	{
		if (this.inv[slot] != null)
		{
			if (this.inv[slot].stackSize - c <= 0)
			{
				ItemStack ret = this.inv[slot];
				this.inv[slot] = null;
				return ret;
			} else
			{
				ItemStack ret = new ItemStack(this.inv[slot].getItem(), c, this.inv[slot].getItemDamage());
				this.inv[slot].stackSize -= c;
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
		if (this.inv[slot] != null)
		{
			ItemStack itemstack = this.inv[slot];
			this.inv[slot] = null;
			return itemstack;
		} else
		{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack i)
	{
		this.inv[slot] = i;

		if (i != null && i.stackSize > this.getInventoryStackLimit())
		{
			i.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName()
	{
		return this.name;
	}

	public String getInventoryUName()
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
		return 16;
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
	public boolean isItemValidForSlot(int slot, ItemStack i)
	{
		if (slot < 4)
			if (i != null)
			{
				if (i.getItem() instanceof MetaItem && i.getItemDamage() <= 0)
				{
					return true;
				}
			}
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

	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		int[] it = tag.getIntArray("linkedTo");
		if (it.length == 3)
			this.linkedTo = new WorldPos(it[0], it[1], it[2]);
		NBTTagList nbttaglist = tag.getTagList("Items", 10);
		this.inv = new ItemStack[this.getSizeInventory()];
		if (this.doSaveInventory)
		{
			for (int i = 0; i < nbttaglist.tagCount(); ++i)
			{
				NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
				byte b0 = nbttagcompound1.getByte("Slot");

				if (b0 >= 0 && b0 < this.inv.length)
				{
					this.inv[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
				}
			}
		}
	}

	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		if (this.linkedTo != null)
			tag.setIntArray("linkedTo", this.linkedTo.toIntArray());
		NBTTagList nbttaglist = new NBTTagList();
		if (this.doSaveInventory)
		{
			for (int i = 0; i < this.inv.length; ++i)
			{
				if (this.inv[i] != null)
				{
					NBTTagCompound nbttagcompound1 = new NBTTagCompound();
					nbttagcompound1.setByte("Slot", (byte) i);
					this.inv[i].writeToNBT(nbttagcompound1);
					nbttaglist.appendTag(nbttagcompound1);
				}
			}
		}
		tag.setTag("Items", nbttaglist);
	}
}
