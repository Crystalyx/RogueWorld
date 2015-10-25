/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Container;

import RW.Common.Tile.TileEntityDUStorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerDUStorage extends Container
{
	public TileEntityDUStorage tile;

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_)
	{
		return true;
	}

	public ContainerDUStorage(InventoryPlayer ip, TileEntityDUStorage tileE)
	{
		this.tile = tileE;

		int i;

		this.addSlotToContainer(new Slot(tileE, 0, 8 * 19, 6));
		this.addSlotToContainer(new Slot(tileE, 1, 8 * 19, 24));
		this.addSlotToContainer(new Slot(tileE, 2, 8 * 19, 42));
		this.addSlotToContainer(new Slot(tileE, 3, 8 * 19, 60));

		this.addSlotToContainer(new Slot(tileE, 4, 8 * 2 + 17 / 2, 16));
		this.addSlotToContainer(new Slot(tileE, 5, 8 * 2 + 17 / 2, 16 + 32));

		for (i = 0; i < 3; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				this.addSlotToContainer(new Slot(ip, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (i = 0; i < 9; i++)
		{
			this.addSlotToContainer(new Slot(ip, i, 8 + i * 18, 142));
		}
	}

	/**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer p, int tslot)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(tslot);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (tslot < tile.getSizeInventory())
            {
                if (!this.mergeItemStack(itemstack1, tile.getSizeInventory(), this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, tile.getSizeInventory(), false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }
}
