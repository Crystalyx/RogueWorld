/**
 * This Class Created By Lord_Crystalyx.
 */

package RW.Common.Container;

import RW.Common.Tile.TileEntityReactorCore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerReactor extends Container
{
	public TileEntityReactorCore tile;
	public ContainerReactor(TileEntityReactorCore tile,InventoryPlayer player)
	{
		this.tile = tile;
		this.addSlotToContainer(new Slot(tile, 0, 8 + 4 * 18, 33));//00
		this.addSlotToContainer(new Slot(tile, 1, 8 + 4 * 18-28, 33));//-0
		this.addSlotToContainer(new Slot(tile, 2, 8 + 4 * 18+28, 33));//+0
		this.addSlotToContainer(new Slot(tile, 3, 8 + 4 * 18, 33-28));//0-
		this.addSlotToContainer(new Slot(tile, 4, 8 + 4 * 18, 33+28));//0+
		this.addSlotToContainer(new Slot(tile, 5, 8 + 4 * 18-28+7, 33-21));//+-
		this.addSlotToContainer(new Slot(tile, 6, 8 + 4 * 18+28-7, 33+21));//-+
		this.addSlotToContainer(new Slot(tile, 7, 8 + 4 * 18+21, 33-28+7));//++
		this.addSlotToContainer(new Slot(tile, 8, 8 + 4 * 18-21, 33+28-7));//--

		int i = 18;
		int j;
        int k;
        
		for (j = 0; j < 3; ++j)
        {
            for (k = 0; k < 9; ++k)
            {
                this.addSlotToContainer(new Slot(player, k + j * 9 + 9, 8 + k * 18, 121 + j * 18 + i-55));
            }
        }

        for (j = 0; j < 9; ++j)
        {
            this.addSlotToContainer(new Slot(player, j, 8 + j * 18, 179+ i-56));
        }
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_)
	{
		return true;
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
