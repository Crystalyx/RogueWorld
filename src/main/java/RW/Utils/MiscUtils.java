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
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemCloth;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;

public class MiscUtils
{
	public static Hashtable<String, RogueData> playerData = new Hashtable<String, RogueData>();

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
	
	public static IIcon[] getIconArray(Block b,int meta)
	{
		IIcon[] icons = new IIcon[6];
		for(int i=0;i<6;i++)
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

	@SideOnly(Side.CLIENT)
	public static void renderItemStack_Full(ItemStack stk, double posX, double posY, double posZ, double screenPosX, double screenPosY, double screenPosZ, float rotation, float rotationZ, float colorRed, float colorGreen, float colorBlue,
			float offsetX, float offsetY, float offsetZ)
	{
		if (stk != null)
		{
			ItemStack itemstack = stk.copy();
			itemstack.stackSize = 1; // Doing this so no weird glitches occur.
			new ResourceLocation("textures/misc/enchanted_item_glint.png");
			RenderBlocks renderBlocksRi = new RenderBlocks();
			Random random = new Random();
			boolean renderWithColor = true;
			if (itemstack != null && itemstack.getItem() != null)
			{
				Minecraft.getMinecraft().renderEngine.bindTexture(Minecraft.getMinecraft().renderEngine.getResourceLocation(stk.getItemSpriteNumber()));
				TextureUtil.func_152777_a(false, false, 1.0F);
				random.setSeed(187L);
				GL11.glPushMatrix();
				float f2 = rotationZ;
				float f3 = rotation;
				byte b0 = 1;

				if (stk.stackSize > 1)
				{
					b0 = 2;
				}

				if (stk.stackSize > 5)
				{
					b0 = 3;
				}

				if (stk.stackSize > 20)
				{
					b0 = 4;
				}

				if (stk.stackSize > 40)
				{
					b0 = 5;
				}

				GL11.glTranslated((float) screenPosX + offsetX, (float) screenPosY + offsetY, (float) screenPosZ + offsetZ);
				GL11.glEnable(GL12.GL_RESCALE_NORMAL);
				float f6;
				float f7;
				int k;
				EntityItem fakeItem = new EntityItem(Minecraft.getMinecraft().theWorld, posX, posY, posZ, stk);
				GL11.glRotatef(f2, 0, 0, 1);
				if (ForgeHooksClient.renderEntityItem(fakeItem, itemstack, f2, f3, random, Minecraft.getMinecraft().renderEngine, renderBlocksRi, b0))
				{
					;
				} else if (itemstack.getItemSpriteNumber() == 0 && itemstack.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack.getItem()).getRenderType()))
				{
					Block block = Block.getBlockFromItem(itemstack.getItem());
					GL11.glRotatef(f3, 0.0F, 1.0F, 0.0F);
					float f9 = 0.25F;
					k = block.getRenderType();

					if (k == 1 || k == 19 || k == 12 || k == 2)
					{
						f9 = 1F;
					}

					if (block.getRenderBlockPass() > 0)
					{
						GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
						GL11.glEnable(GL11.GL_BLEND);
						OpenGlHelper.glBlendFunc(770, 771, 1, 0);
					}

					GL11.glScalef(f9, f9, f9);

					for (int l = 0; l < b0; ++l)
					{
						GL11.glPushMatrix();

						if (l > 0)
						{
							f6 = (random.nextFloat() * 2.0F - 1.0F) * 0.2F / f9;
							f7 = (random.nextFloat() * 2.0F - 1.0F) * 0.2F / f9;
							float f8 = (random.nextFloat() * 2.0F - 1.0F) * 0.2F / f9;
							GL11.glTranslatef(f6, f7, f8);
						}

						renderBlocksRi.renderBlockAsItem(block, itemstack.getItemDamage(), 1.0F);
						GL11.glPopMatrix();
					}

					if (block.getRenderBlockPass() > 0)
					{
						GL11.glDisable(GL11.GL_BLEND);
					}
				} else
				{
					if (itemstack.getItem().requiresMultipleRenderPasses())
					{
						GL11.glScalef(0.5F, 0.5F, 0.5F);
						for (int j = 0; j < itemstack.getItem().getRenderPasses(itemstack.getItemDamage()); ++j)
						{
							random.setSeed(187L);
							itemstack.getItem().getIcon(itemstack, j);
							renderItemStack(stk, posX, posY, posZ, screenPosX, screenPosY, screenPosZ, rotation, colorRed, colorGreen, colorBlue, j, stk.stackSize);
						}
					} else
					{
						if (itemstack != null && itemstack.getItem() instanceof ItemCloth)
						{
							GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
							GL11.glEnable(GL11.GL_BLEND);
							OpenGlHelper.glBlendFunc(770, 771, 1, 0);
						}
						GL11.glScalef(0.5F, 0.5F, 0.5F);
						itemstack.getIconIndex();

						if (renderWithColor)
						{
							renderItemStack(stk, posX, posY, posZ, screenPosX, screenPosY, screenPosZ, rotation, colorRed, colorGreen, colorBlue, 0, stk.stackSize);
						}
						if (itemstack != null && itemstack.getItem() instanceof ItemCloth)
						{
							GL11.glDisable(GL11.GL_BLEND);
						}
					}
				}
				fakeItem = null;
				GL11.glDisable(GL12.GL_RESCALE_NORMAL);
				GL11.glPopMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(Minecraft.getMinecraft().renderEngine.getResourceLocation(stk.getItemSpriteNumber()));
				TextureUtil.func_147945_b();
			}
			itemstack = null; // Again, there is a gc for that, but removing
								// possible leaks is never a bad thing to do...
		}
	}

	@SideOnly(Side.CLIENT)
	public static void renderItemStack(ItemStack stk, double posX, double posY, double posZ, double screenPosX, double screenPosY, double screenPosZ, float rotation, float colorRed, float colorGreen, float colorBlue, int renderPass,
			int itemsAmount)
	{
		final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
		new RenderBlocks();
		Random random = new Random();
		IIcon p_77020_2_ = stk.getItem().getIcon(stk, renderPass);

		Tessellator tessellator = Tessellator.instance;

		if (p_77020_2_ == null)
		{
			TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
			ResourceLocation resourcelocation = texturemanager.getResourceLocation(stk.getItem().getSpriteNumber());
			p_77020_2_ = ((TextureMap) texturemanager.getTexture(resourcelocation)).getAtlasSprite("missingno");
		}

		float f14 = ((IIcon) p_77020_2_).getMinU();
		float f15 = ((IIcon) p_77020_2_).getMaxU();
		float f4 = ((IIcon) p_77020_2_).getMinV();
		float f5 = ((IIcon) p_77020_2_).getMaxV();
		float f6 = 1.0F;
		float f7 = 0.5F;
		float f8 = 0.25F;
		float f10;

		if (Minecraft.getMinecraft().gameSettings.fancyGraphics)
		{
			GL11.glPushMatrix();
			GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
			float f9 = 0.0625F;
			f10 = 0.021875F;
			ItemStack itemstack = stk;
			int j = itemstack.stackSize;
			byte b0;

			if (j < 2)
			{
				b0 = 1;
			} else if (j < 16)
			{
				b0 = 2;
			} else if (j < 32)
			{
				b0 = 3;
			} else
			{
				b0 = 4;
			}

			GL11.glTranslatef(-f7, -f8, -((f9 + f10) * (float) b0 / 2.0F));

			for (int k = 0; k < b0; ++k)
			{
				if (k > 0)
				{
					float x = (random.nextFloat() * 2.0F - 1.0F) * 0.3F / 0.5F;
					float y = (random.nextFloat() * 2.0F - 1.0F) * 0.3F / 0.5F;
					random.nextFloat();
					GL11.glTranslatef(x, y, f9 + f10);
				} else
				{
					GL11.glTranslatef(0f, 0f, f9 + f10);
				}

				if (itemstack.getItemSpriteNumber() == 0)
				{
					Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
				} else
				{
					Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationItemsTexture);
				}

				GL11.glColor4f(colorRed, colorGreen, colorBlue, 1.0F);
				ItemRenderer.renderItemIn2D(tessellator, f15, f4, f14, f5, ((IIcon) p_77020_2_).getIconWidth(), ((IIcon) p_77020_2_).getIconHeight(), f9);

				if (itemstack.hasEffect(renderPass))
				{
					GL11.glDepthFunc(GL11.GL_EQUAL);
					GL11.glDisable(GL11.GL_LIGHTING);
					Minecraft.getMinecraft().renderEngine.bindTexture(RES_ITEM_GLINT);
					GL11.glEnable(GL11.GL_BLEND);
					GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
					float f11 = 0.76F;
					GL11.glColor4f(0.5F * f11, 0.25F * f11, 0.8F * f11, 1.0F);
					GL11.glMatrixMode(GL11.GL_TEXTURE);
					GL11.glPushMatrix();
					float f12 = 0.125F;
					GL11.glScalef(f12, f12, f12);
					float f13 = (float) (Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
					GL11.glTranslatef(f13, 0.0F, 0.0F);
					GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
					ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 255, 255, f9);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					GL11.glScalef(f12, f12, f12);
					f13 = (float) (Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
					GL11.glTranslatef(-f13, 0.0F, 0.0F);
					GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
					ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 255, 255, f9);
					GL11.glPopMatrix();
					GL11.glMatrixMode(GL11.GL_MODELVIEW);
					GL11.glDisable(GL11.GL_BLEND);
					GL11.glEnable(GL11.GL_LIGHTING);
					GL11.glDepthFunc(GL11.GL_LEQUAL);
				}
			}

			GL11.glPopMatrix();
		} else
		{
			for (int l = 0; l < itemsAmount; ++l)
			{
				GL11.glPushMatrix();

				if (l > 0)
				{
					f10 = (random.nextFloat() * 2.0F - 1.0F) * 0.3F;
					float f16 = (random.nextFloat() * 2.0F - 1.0F) * 0.3F;
					float f17 = (random.nextFloat() * 2.0F - 1.0F) * 0.3F;
					GL11.glTranslatef(f10, f16, f17);
				}
				GL11.glColor3f(colorRed, colorGreen, colorBlue);
				tessellator.startDrawingQuads();
				tessellator.setNormal(0.0F, 1.0F, 0.0F);
				tessellator.addVertexWithUV((double) (0.0F - f7), (double) (0.0F - f8), 0.0D, (double) f14, (double) f5);
				tessellator.addVertexWithUV((double) (f6 - f7), (double) (0.0F - f8), 0.0D, (double) f15, (double) f5);
				tessellator.addVertexWithUV((double) (f6 - f7), (double) (1.0F - f8), 0.0D, (double) f15, (double) f4);
				tessellator.addVertexWithUV((double) (0.0F - f7), (double) (1.0F - f8), 0.0D, (double) f14, (double) f4);
				tessellator.draw();
				GL11.glPopMatrix();
			}
		}
	}

	// ======================================String Beginning
	// Indexes===============================================//
	public static final String info = "[INFO]";
	public static final String warning = "[WARNING]";
	public static final String error = "[ERROR]";
	public static final String severe = "[SEVERE]";
	// ======================================String Second
	// Indexes===============================================//
	public static final String dev = "[DEV]";
	public static final String debug = "[DEBUG]";
	public static final String stdout = "[STDOUT]";
	// ======================================String Ending
	// Indexes===============================================//
	public static final String system = "[SYSTEM]";
	public static final String mod = "[RogueWorld]";

	public static final Logger logger = LogManager.getLogger();

	public static void notifyDebug(String s)
	{
		notify("", debug, mod, s);
	}

	public static void notify(String... s)
	{
		String begin = "";
		String mid = "";
		String end = "";
		String mesg = s[3];
		mid = s[1];
		end = s[2];
		String out = buildString(begin, mid, end, mesg);
		publish(out);
	}

	public static String buildString(String... strings)
	{
		return strings[0] + " " + strings[1] + " " + strings[2] + " " + strings[3] + " ";
	}

	public static void publish(String s)
	{
		org.apache.logging.log4j.core.Logger log = (org.apache.logging.log4j.core.Logger) logger;
		log.setLevel(Level.INFO);
		if (RogueWorldCore.doLogging)
			logger.log(Level.INFO, s);
	}

	public static TileEntity getTileEntityAt(World w, WorldPos pos)
	{
		return w.getTileEntity((int) pos.getX(), (int) pos.getY(), (int) pos.getZ());
	}

	public static double getDistance(WorldPos p1, WorldPos p2)
	{
		return Math.sqrt((p2.getX() - p1.getX()) * (p2.getX() - p1.getX()) + (p2.getY() - p1.getY()) * (p2.getY() - p1.getY()) + (p2.getZ() - p1.getZ()) * (p2.getZ() - p1.getZ()));
	}
}
