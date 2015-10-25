package RW.Common.Tile;

import RW.Api.IStructurePart;
import RW.Common.Misc.WorldPos;
import RW.Utils.PositionedWorld;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityIOPort extends IStructurePart implements ISidedInventory
{
	public ItemStack[] inventory = new ItemStack[1];
	public WorldPos core;
	public PositionedWorld world;

	public TileEntityIOPort()
	{
		this.world = new PositionedWorld(this.worldObj);
	}

	@Override
	public int getSizeInventory()
	{
		return 1;
	}

	public void updateEntity()
	{

	}
	
	@Override
	public void performAction(int x, int y, int z)
	{
		if (this.core != null)
		{
			TileEntity tile = this.world.getTileEntity(this.core);
			if (tile != null)
			{
				if (tile instanceof TileEntityReactorCore)
				{
					TileEntityReactorCore rcore = (TileEntityReactorCore) tile;
					if (this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord) < 4)
					{
						if (rcore.canAddIStack())
						{
							this.inventory[0] = null;
							rcore.addItemStack(this.inventory[0]);
						}
					}

					if (this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord) >= 4)
					{
						if (this.inventory[0] == null)
						{
							this.inventory[0] = rcore.consumeItemStack();
						}
					}
				}
				else
					this.core = null;
			}
			else
				this.core = null;
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
			}
			else
			{
				ItemStack ret = new ItemStack(this.inventory[slot].getItem(), count, this.inventory[slot].getItemDamage());
				this.inventory[slot].stackSize -= count;
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
		return "ioPort";
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
		return false;
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
	public boolean isItemValidForSlot(int slot, ItemStack p_94041_2_)
	{
		return true;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side)
	{
		int meta = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
		int[] i = new int[] { 2, 5, 3, 4 };
		int k = 8, l = 8;
		if (meta < 4)
		{
			l = i[meta];
		}
		if (meta >= 4)
		{
			k = i[meta - 4];
		}
		if (side == k || side == l)
			return new int[] { 0 };
		return new int[] {0};
	}

	@Override
	public boolean canInsertItem(int side, ItemStack i, int p_102007_3_)
	{
		int meta = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
		int[] l = new int[] { 2, 5, 3, 4 };
		int k = 8;
		if (meta >= 4)
		{
			k = l[meta - 4];
		}
		return side == k;
	}

	@Override
	public boolean canExtractItem(int side, ItemStack i, int p_102008_3_)
	{
		int meta = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
		int[] in = new int[] { 2, 5, 3, 4 };
		int k = 8, l = 8;
		if (meta < 4)
		{
			l = in[meta];
		}
		return side == l;
	}

}
