package RW.Api;

import net.minecraft.item.ItemStack;

public interface IItemContainsDU
{
	public int addDU(ItemStack i, int du);
	
	public int consumeDU(ItemStack i, int du);
	
	public void setMax(int i);
	
	public int getMax();
}
