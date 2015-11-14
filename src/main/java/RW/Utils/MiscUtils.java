/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Utils;

import java.util.Hashtable;
import java.util.Random;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import RW.Common.Misc.WorldPos;
import RW.Common.Player.RWPlayerData;
import RW.Core.RogueWorldCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemCloth;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

/**
 * @author Lord_Crystalyx
 */
public class MiscUtils
{
	public static Hashtable<String, RWPlayerData> playerData = new Hashtable<String, RWPlayerData>();

	public static void spawnParticle(World w, WorldPos pos, WorldPos tg, String particle)
	{
		double motionX, motionY, motionZ = 0;
		motionX = (tg.getX() - pos.getX()) / 5;
		motionZ = (tg.getY() - pos.getY()) / 5;
		motionY = (tg.getZ() - pos.getZ()) / 5;
		w.spawnParticle(particle, pos.getX(), pos.getY(), pos.getZ(), motionX, motionY, motionZ);

	}

	public static boolean addItemStack(EntityPlayer p, ItemStack i)
	{
		if (i != null)
		{
			for (int l = 0; l < p.inventory.getSizeInventory(); l++)
			{
				if (p.inventory.getStackInSlot(l) != null)
					if (p.inventory.getStackInSlot(l).getItem() == i.getItem() && p.inventory.getStackInSlot(l).getItemDamage() == i.getItemDamage() && p.inventory.getStackInSlot(l).stackSize + i.stackSize <= i.getMaxStackSize())
					{
						if ((i.hasTagCompound() == false && p.inventory.getStackInSlot(l).hasTagCompound() == false) || (i.getTagCompound() == p.inventory.getStackInSlot(l).getTagCompound()))
						{
							ItemStack il = new ItemStack(i.getItem(), p.inventory.getStackInSlot(l).stackSize + i.stackSize, i.getItemDamage());
							p.inventory.setInventorySlotContents(l, il);
							return true;
						}
					}

			}
			for (int l = 0; l < p.inventory.getSizeInventory(); l++)
			{
				if (p.inventory.getStackInSlot(l) == null)
				{
					p.inventory.setInventorySlotContents(l, i);
					return true;
				}
			}
		}
		return false;
	}

	public static IIcon[] getIconArray(Block b, int meta)
	{
		IIcon[] icons = new IIcon[6];
		for (int i = 0; i < 6; i++)
		{
			icons[i] = b.getIcon(i, meta);
		}
		return icons;
	}

	public static int getFirstNotOccupiedSlotFor(Object[] array)
	{
		for (int i = 0; i < array.length; i++)
		{
			if (array[i] == null)
			{
				return i;
			}
		}
		return -1;
	}

	public static Object[] expandArray(Object[] array)
	{
		Object[] ret = new Object[array.length + 1];
		for (int i = 0; i < array.length; i++)
		{
			ret[i] = array[i];
		}
		return ret;
	}

	public static Object[] expandArray(Object[] array, int length)
	{
		Object[] ret = new Object[array.length + length];
		for (int i = 0; i < array.length; i++)
		{
			ret[i] = array[i];
		}
		return ret;
	}

	public static String[] expandArray(String[] array, int length)
	{
		String[] ret = new String[array.length + length];
		for (int i = 0; i < array.length; i++)
		{
			ret[i] = array[i];
		}
		return ret;
	}

	public static int[] expandArray(int[] array, int length)
	{
		int[] ret = new int[array.length + length];
		for (int i = 0; i < array.length; i++)
		{
			ret[i] = array[i];
		}
		return ret;
	}

	public static double getDistance(WorldPos p1, WorldPos p2)
	{
		return Math.sqrt((p2.getX() - p1.getX()) * (p2.getX() - p1.getX()) + (p2.getY() - p1.getY()) * (p2.getY() - p1.getY()) + (p2.getZ() - p1.getZ()) * (p2.getZ() - p1.getZ()));
	}
}
