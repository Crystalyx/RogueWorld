/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Tile;

import java.util.Random;

import DummyCore.Utils.Notifier;
import DummyCore.Utils.TileStatTracker;
import RW.Api.EnergeticTileEntity;
import RW.Common.Registry.BlockRegistry;
import RW.Common.Registry.ItemRegistry;
import RW.Core.RogueWorldCore;
import RW.Utils.MiscUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author Lord_Crystalyx
 */
public class TileEntityExtractor extends EnergeticTileEntity implements ISidedInventory
{

	public TileEntityExtractor()
	{
		super(0, 200000, "Dark Extractor");
		this.uname = "extractor";
	}

	public TileEntityExtractor(int type, int maxEnergy, String name)
	{
		super(type, maxEnergy, name);
		this.uname = "extractor";
	}

	public ItemStack[] inv = new ItemStack[8];

	@Override
	public int getSizeInventory()
	{
		return 8;
	}

	private static final int[] slotsTop = new int[] { 0 };
	private static final int[] slotsBottom = new int[] { 2, 3 };
	private static final int[] slotsSides = new int[] { 1 };
	/** The number of ticks that the furnace will keep burning */
	public int furnaceBurnTime = 0;
	public int maxBTime = 200;
	/**
	 * The number of ticks that a fresh copy of the currently-burning item would
	 * keep the furnace burning for
	 */
	public int currentItemBurnTime;
	/** The number of ticks that the current item has been cooking for */
	public int furnaceCookTime = 0;

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return this.inv[slot];
	}

	public int getMaxBurnTime()
	{
		int ret = this.maxBTime - (count(new ItemStack(ItemRegistry.metai, 1, 1)) * 6);
		if (count(new ItemStack(ItemRegistry.metai, 1, 1)) > 30)
		{
			ret = 20;
		}
		return ret;
	}

	@Override
	public ItemStack decrStackSize(int slot, int count)
	{
		if (this.inv[slot] != null)
		{
			if (this.inv[slot].stackSize - count <= 0)
			{
				ItemStack ret = this.inv[slot];
				this.inv[slot] = null;
				return ret;
			}
			else
			{
				ItemStack ret = new ItemStack(this.inv[slot].getItem(), count, this.inv[slot].getItemDamage());
				this.inv[slot].stackSize -= count;
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
		if (this.inv[slot] != null)
		{
			ItemStack itemstack = this.inv[slot];
			this.inv[slot] = null;
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
		this.inv[slot] = i;

		if (i != null && i.stackSize > this.getInventoryStackLimit())
		{
			i.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName()
	{
		return "extractor";
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

	@Override
	public int[] getAccessibleSlotsFromSide(int side)
	{
		if (side == ForgeDirection.UP.ordinal())
		{
			return this.slotsTop;
		}
		if (side != ForgeDirection.DOWN.ordinal() && side != ForgeDirection.UP.ordinal())
		{
			return this.slotsSides;
		}
		if (side == ForgeDirection.DOWN.ordinal())
		{
			return this.slotsBottom;
		}
		return new int[0];
	}

	@Override
	public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_)
	{
		return true;
	}

	@Override
	public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_)
	{
		return true;
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

	/**
	 * Reads a tile entity from NBT.
	 */
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);
		NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items", 10);
		this.inv = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			if (j >= 0 && j < this.inv.length)
			{
				this.inv[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		this.furnaceBurnTime = par1NBTTagCompound.getShort("BurnTime");
		this.furnaceCookTime = par1NBTTagCompound.getShort("CookTime");
		this.currentItemBurnTime = getItemBurnTime(this.inv[1]);

		if (par1NBTTagCompound.hasKey("CustomName"))
		{
			this.name = par1NBTTagCompound.getString("CustomName");
		}
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setShort("BurnTime", (short) this.furnaceBurnTime);
		par1NBTTagCompound.setShort("CookTime", (short) this.furnaceCookTime);
		NBTTagList nbttaglist = new NBTTagList();

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

		par1NBTTagCompound.setTag("Items", nbttaglist);

		if (this.hasCustomInventoryName())
		{
			par1NBTTagCompound.setString("CustomName", this.name);
		}
	}

	public boolean isSmelted = false;
	private int syncTick;

	public void updateEntity()
	{
		if (this.energy > (250 - this.getMaxBurnTime()))
		{
			if (this.furnaceCookTime < this.getMaxBurnTime())
			{
				if (this.inv[0] != null)
				{
					if (this.inv[2] == null || (this.inv[2].getItem() == ItemRegistry.DCryst && this.inv[2].stackSize < 64))
					{

						if (this.inv[0].getItem() == Item.getItemFromBlock(BlockRegistry.SWood))
						{
							++this.furnaceCookTime;
							this.consumeEnergy(250 - this.getMaxBurnTime());
						}
						else
							this.furnaceCookTime = 0;

					}
				}
				else
					this.furnaceCookTime = 0;
			}
		}

		if (this.furnaceCookTime >= this.getMaxBurnTime())
		{
			this.isSmelted = true;
		}

		if (this.isSmelted)
		{
			if (this.inv[0] != null)
			{
				this.decrStackSize(0, 1);
				if (this.inv[2] != null)
				{
					if (this.inv[2].stackSize < 64)
					{
						if (this.inv[2].getItem() == ItemRegistry.DCryst)
						{
							++this.inv[2].stackSize;
						}

						if (r.nextBoolean())
						{
							if (this.inv[3] != null)
							{
								if (this.inv[3].getItem() == Item.getItemFromBlock(Blocks.log))
								{
									++this.inv[3].stackSize;
								}
							}
							else
							{
								this.setInventorySlotContents(3, new ItemStack(Blocks.log, 1));
							}
						}
					}
				}
				else
				{
					this.setInventorySlotContents(2, new ItemStack(ItemRegistry.DCryst));

				}
				this.isSmelted = false;
				this.furnaceCookTime = 0;
			}

		}
		//
		// if (syncTick == 0)
		// {
		// if (this.tracker == null)
		// Notifier.notifyCustomMod("RogueWorld", "[WARNING][SEVERE]TileEntity "
		// + this + " at pos " + this.xCoord + "," + this.yCoord + "," +
		// this.zCoord
		// + " tries to sync itself, but has no TileTracker attached to it! SEND
		// THIS MESSAGE TO THE DEVELOPER OF THE MOD!");
		// else if (!this.worldObj.isRemote && this.tracker.tileNeedsSyncing())
		// {
		// DummyCore.Utils.MiscUtils.sendPacketToAllAround(worldObj,
		// getDescriptionPacket(), xCoord, yCoord, zCoord,
		// this.worldObj.provider.dimensionId, 32);
		// }
		// syncTick = 60;
		// } else
		// --this.syncTick;
		//
		// if (requestSync && this.worldObj.isRemote)
		// {
		// requestSync = false;
		// MiscUtils.requestScheduledTileSync(this,
		// RogueWorldCore.proxy.getClientPlayer());
		// }
		super.updateEntity();
	}

	@Override
	public int count(ItemStack i)
	{
		int count = 0;
		for (int l = 4; l < 8; l++)
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

	public ItemStack getResultItem(ItemStack i)
	{
		if (i != null)
		{
			if (i.getItem() == Item.getItemFromBlock(BlockRegistry.SWood))
			{
				return new ItemStack(ItemRegistry.DCryst, 1);
			}
		}
		return i;
	}

	public Random r = new Random();

	/**
	 * Returns the number of ticks that the supplied fuel item will keep the
	 * furnace burning, or 0 if the item isn't fuel
	 */
	public static int getItemBurnTime(ItemStack i)
	{
		if (i == null)
		{
			return 0;
		}
		else
		{
			if (i.getItem() == Items.diamond)
			{
				return 100;
			}
		}
		return 0;
	}

	public static boolean isItemFuel(ItemStack i)
	{
		return getItemBurnTime(i) > 0;
	}

	public int getCookProgressScaled(int i)
	{
		return this.furnaceCookTime / (this.getSpeed() / 22);
	}

	public static Object getSmeltingResult(ItemStack i)
	{
		if (i != null)
		{
			if (i.getItem() == Item.getItemFromBlock(BlockRegistry.SWood))
			{
				return ItemRegistry.DCryst;
			}
		}
		return null;
	}

}
