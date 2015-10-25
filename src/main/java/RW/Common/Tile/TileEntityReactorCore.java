/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Tile;

import RW.Api.IStructurePart;
import RW.Common.Blocks.Reactor.IReactorPart;
import RW.Common.Blocks.Reactor.ReactorElement;
import RW.Common.Misc.WorldPos;
import RW.Common.Registry.BlockRegistry;
import RW.Utils.Logger;
import RW.Utils.PositionedWorld;
import RW.Utils.StructurePoses;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;

public class TileEntityReactorCore extends TileEntity implements IInventory
{
	public ItemStack[] inv = new ItemStack[9];
	private int syncTick;
	public PositionedWorld world;

	public TileEntityReactorCore()
	{
		super();
		this.world = new PositionedWorld(this.worldObj);
	}

	public boolean structureComplete = false;
	private WorldPos[] poses;

	public void completeStructure()
	{
		this.world = new PositionedWorld(this.worldObj);
		WorldPos tilepos = new WorldPos(this);
		this.poses = StructurePoses.poses.get(1);
		boolean complete = true;
		for (int i = 0; i < this.poses.length; i++)
		{
			if (this.world.getBlock(this.poses[i].add(tilepos)) != null)
			{
				if (this.world.getBlock(this.poses[i].add(tilepos)) instanceof IReactorPart)
				{
					if (this.world.getBlock(this.poses[i].add(tilepos)) instanceof ReactorElement)
					{
						Logger.info("Completion of Structure");
						((IStructurePart) this.world.getTileEntity(this.poses[i].add(tilepos))).performAction(this.xCoord, this.yCoord, this.zCoord);
					}
				}
				else
				{
					Logger.info("Structure incomplete " + this.poses[i].add(tilepos).toString());
					complete = false;
				}
			}
			else
			{
				Logger.info("Structure incomplete " + this.poses[i].add(tilepos).toString());
				complete = false;
			}
		}
		this.structureComplete = complete;
		if (this.structureComplete)
		{
			Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("Structure Complete"));
			for (int i = 0; i < this.poses.length; i++)
			{
				if (this.world.getBlock(this.poses[i].add(tilepos)) != null)
				{
					if (this.world.getBlock(this.poses[i].add(tilepos)) instanceof ReactorElement)
					{
						((IStructurePart) this.world.getTileEntity(this.poses[i].add(tilepos))).setCore(tilepos);
					}
				}
			}
		}
	}

	@Override
	public void updateEntity()
	{
		WorldPos tilepos = new WorldPos(this);

		super.updateEntity();
		this.poses = StructurePoses.poses.get(0);
		for (int i = 0; i < 9; i++)
		{
			if (this.worldObj.getTileEntity((int) (this.xCoord + poses[i].getX()), (int) (this.yCoord + poses[i].getY() - 4), (int) (this.zCoord + poses[i].getZ())) != null)
			{
				if (this.worldObj.getTileEntity((int) (this.xCoord + poses[i].getX()), (int) (this.yCoord + poses[i].getY() - 4), (int) (this.zCoord + poses[i].getZ())) instanceof TileEntityPillar)
				{
					TileEntityPillar tile = (TileEntityPillar) this.worldObj.getTileEntity((int) (this.xCoord + poses[i].getX()), (int) (this.yCoord + poses[i].getY() - 4), (int) (this.zCoord + poses[i].getZ()));
					tile.inventory[0] = this.getStackInSlot(i);
					tile.setIndex(i);
				}
			}
		}

		if (this.structureComplete)
		{
			this.poses = StructurePoses.poses.get(1);
			for (int i = 0; i < this.poses.length; i++)
			{
				if (this.world.getBlock(this.poses[i].add(tilepos)) != null)
				{
					if (this.world.getBlock(this.poses[i].add(tilepos)) instanceof ReactorElement)
					{
						((IStructurePart) this.world.getTileEntity(this.poses[i].add(tilepos))).performAction(this.xCoord, this.yCoord, this.zCoord);
					}
				}
			}
		}
	}

	public void function(int x, int y, int z)
	{
		if (this.worldObj.getBlock(x, y, z) == BlockRegistry.ioport)
		{

		}
	}

	public boolean addItemStack(ItemStack i)
	{
		for (int l = 0; l < this.inv.length; l++)
		{
			if (this.inv[l] == null)
			{
				this.inv[l] = i;
				return true;
			}
		}
		return false;
	}

	public boolean canAddIStack()
	{
		for (int l = 0; l < this.inv.length; l++)
		{
			if (this.inv[l] == null)
			{
				return true;
			}
		}
		return false;
	}

	public ItemStack consumeItemStack()
	{
		for (int l = this.inv.length - 1; l > -1; l++)
		{
			if (this.inv[l] != null)
			{
				this.inv[l] = null;
				return this.inv[l];
			}
		}
		return null;
	}

	@Override
	public int getSizeInventory()
	{
		return 9;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return this.inv[slot];
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
		return "reactor";
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

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack i)
	{
		return true;
	}

	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		NBTTagList nbttaglist = tag.getTagList("Items", 10);
		this.inv = new ItemStack[this.getSizeInventory()];

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

	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
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

		tag.setTag("Items", nbttaglist);
	}

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
}
