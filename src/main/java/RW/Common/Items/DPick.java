/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Items;

import java.util.List;

import RW.Api.IDUPick;
import RW.Common.Registry.MiscRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

/**
 * @author Lord_Crystalyx
 */
public class DPick extends IDUPick
{

	public DPick()
	{
		super(MiscRegistry.darkMat);
		this.setTextureName("rogueWorld:dark_pick");
		this.setUnlocalizedName("rw.darkpick");
		this.setCreativeTab(MiscRegistry.modTab);
	}

	public NBTTagCompound tag = new NBTTagCompound();

	public ItemStack onItemRightClick(ItemStack i, World w, EntityPlayer p)
	{
		if (p.isSneaking())
		{
			if (!i.hasTagCompound())
			{
				tag.setFloat("Exp", 0);
				tag.setInteger("Level", 1);
				i.setTagCompound(tag);
			}
		}
		return i;
	}

	public void onUpdate(ItemStack i, World w, Entity e, int p_77663_4_, boolean inhand)
	{
		if (i.getTagCompound() == null)
		{
			tag.setFloat("Expirience", 0);
			tag.setInteger("Level", 1);
			i.setTagCompound(tag);
		}
		else
		{
			this.setHarvestLevel("pickaxe", i.getTagCompound().getInteger("Level") / 3 + 1);
			this.setHarvestLevel("shovel", i.getTagCompound().getInteger("Level") / 3 + 1);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack i, EntityPlayer p, List l, boolean bool)
	{
		if (i.getTagCompound() != null)
		{
			float Dam = (int) i.getTagCompound().getFloat("Exp");
			l.add(EnumChatFormatting.AQUA + "Expirience: " + (int) Dam);
			float lev = i.getTagCompound().getInteger("Level");
			l.add(EnumChatFormatting.BLUE + "Level: " + lev);
			l.add("");
		}
		super.addInformation(i, p, l, bool);
	}
}
