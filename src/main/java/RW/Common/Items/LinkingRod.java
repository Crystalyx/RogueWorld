/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Items;

import java.util.List;

import RW.Api.EnergeticTileEntity;
import RW.Common.Misc.WorldPos;
import RW.Common.Registry.MiscRegistry;
import RW.Common.Tile.TileEntityDUStorage;
import RW.Core.RogueWorldCore;
import RW.Utils.MiscUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * @author Lord_Crystalyx
 */
public class LinkingRod extends Item
{
	public LinkingRod()
	{
		this.setCreativeTab(MiscRegistry.modTab);
		this.setUnlocalizedName("rw.linkingrod");
		this.setHasSubtypes(true);

	}

	public static IIcon green;
	public static IIcon red;
	public static IIcon blue;

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister ir)
	{
		this.green = ir.registerIcon(RogueWorldCore.ModId + ":lrod_green");
		this.red = ir.registerIcon(RogueWorldCore.ModId + ":lrod_red");
		this.blue = ir.registerIcon(RogueWorldCore.ModId + ":lrod_blue");
	}

	public void getSubItems(Item i, CreativeTabs tab, List l)
	{
		l.add(new ItemStack(i, 1, 0));
		l.add(new ItemStack(i, 1, 1));
	}

	public IIcon getIconFromDamage(int meta)
	{
		if (meta >= 1)
			return this.green;
		return this.blue;
	}

	public ItemStack onItemRightClick(ItemStack i, World w, EntityPlayer p)
	{
		p.setItemInUse(i, Integer.MAX_VALUE);
		return i;
	}

	public boolean onItemUse(ItemStack i, EntityPlayer p, World w, int x, int y, int z, int side, float px, float py, float pz)
	{
		if (!p.isSneaking())
		{
			if (i != null)
			{
				if (w.getTileEntity(x, y, z) instanceof EnergeticTileEntity)
					if (i.getItemDamage() == 0)
					{
						if (i.getTagCompound() != null)
						{
							i.getTagCompound().setInteger("BlockX", x);
							i.getTagCompound().setInteger("BlockY", y);
							i.getTagCompound().setInteger("BlockZ", z);
							i.getTagCompound().setInteger("Dim", p.dimension);

						}
					}
					else
					{

						if (w.getTileEntity(x, y, z) instanceof TileEntityDUStorage)
							if (i.getTagCompound() != null)
							{
								WorldPos linkingTo = new WorldPos(i.getTagCompound().getInteger("BlockX"), i.getTagCompound().getInteger("BlockY"), i.getTagCompound().getInteger("BlockZ"));
								TileEntityDUStorage tile = (TileEntityDUStorage) w.getTileEntity(x, y, z);
								tile.boundTo(MiscUtils.getFirstNotOccupiedSlotFor(tile.linkedTo), linkingTo);
								i.getTagCompound().removeTag("BlockX");
								i.getTagCompound().removeTag("BlockY");
								i.getTagCompound().removeTag("BlockZ");
								i.getTagCompound().removeTag("Dim");
							}

						if (w.getTileEntity(x, y, z) instanceof EnergeticTileEntity)
							if (i.getTagCompound() != null)
							{
								WorldPos linkingTo = new WorldPos(i.getTagCompound().getInteger("BlockX"), i.getTagCompound().getInteger("BlockY"), i.getTagCompound().getInteger("BlockZ"));
								EnergeticTileEntity tile = (EnergeticTileEntity) w.getTileEntity(x, y, z);
								tile.boundTo(linkingTo);
								i.getTagCompound().removeTag("BlockX");
								i.getTagCompound().removeTag("BlockY");
								i.getTagCompound().removeTag("BlockZ");
								i.getTagCompound().removeTag("Dim");
							}
					}
			}
		}
		else
		{
			i.getTagCompound().removeTag("BlockX");
			i.getTagCompound().removeTag("BlockY");
			i.getTagCompound().removeTag("BlockZ");
			i.getTagCompound().removeTag("Dim");

		}
		return true;
	}

	public static int getFirstNotOccupiedSlotFor(int[] array)
	{
		for (int i = 0; i < array.length; i++)
		{
			if (array[i] == Integer.MIN_VALUE)
			{
				return i;
			}
		}
		return -1;
	}

	public void onUpdate(ItemStack i, World w, Entity e, int interesting, boolean isInHand)
	{
		if (i.getTagCompound() == null)
		{
			NBTTagCompound tag = new NBTTagCompound();
			i.setTagCompound(tag);
		}

		if (i.getTagCompound().hasKey("BlockX") && i.getItemDamage() == 0)
		{
			i.setItemDamage(1);
		}
		if (!i.getTagCompound().hasKey("BlockX") && i.getItemDamage() == 1)
		{
			i.setItemDamage(0);
		}
	}

	public void addInformation(ItemStack i, EntityPlayer p, List l, boolean in)
	{
		if (i.hasTagCompound())
		{
			if (i.getTagCompound().hasKey("BlockX"))
			{
				l.add("Bound to: X = " + i.getTagCompound().getInteger("BlockX") + "; Y = " + i.getTagCompound().getInteger("BlockY") + "; Z = " + i.getTagCompound().getInteger("BlockZ") + ";");
				l.add("In Dimension: " + i.getTagCompound().getInteger("Dim"));
			}
			else
				l.add("Not Bound");
		}
	}
}
