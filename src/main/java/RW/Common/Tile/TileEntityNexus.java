package RW.Common.Tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityNexus extends TileEntity implements IInventory
{

	private ItemStack[] inv = new ItemStack[3];
	private String customName;

	public TileEntityNexus()
	{
		
	}
	
	@Override
	public int getSizeInventory()
	{
		return 3;
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
			} else
			{
				ItemStack ret = new ItemStack(this.inv[slot].getItem(), count, this.inv[slot].getItemDamage());
				this.inv[slot].stackSize -= count;
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
//		if (this.inv[slot] != null)
//		{
//			ItemStack itemstack = this.inv[slot];
//			this.inv[slot] = null;
//			return itemstack;
//		} else
//		{
			return null;
//		}
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
		return "nexus";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 1;
	}
	
	 public void setCustomName(String par1Str)
	    {
	        this.customName = par1Str;
	    }

	    /**
	     * If this returns false, the inventory name will be used as an unlocalized name, and translated into the player's
	     * language. Otherwise it will be used directly.
	     */
	    public boolean isInvNameLocalized()
	    {
	        return this.customName != null;
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
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_)
	{
		return true;
	}
	
	 /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items",10);
        this.inv = new ItemStack[this.getSizeInventory()];
        
        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.inv.length)
            {
                this.inv[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        if (par1NBTTagCompound.hasKey("CustomName"))
        {
            this.customName = par1NBTTagCompound.getString("CustomName");
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = new NBTTagList();
        
        for (int i = 0; i < this.inv.length; ++i)
        {
            if (this.inv[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.inv[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1NBTTagCompound.setTag("Items", nbttaglist);

        if (this.isInvNameLocalized())
        {
            par1NBTTagCompound.setString("CustomName", this.customName);
        }
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
