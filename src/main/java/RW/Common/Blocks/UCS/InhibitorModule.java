package RW.Common.Blocks.UCS;

import java.util.List;

import RW.Api.IUCSPart;
import RW.Api.IUCSModule;
import RW.Common.Misc.WorldPos;
import RW.Common.Registry.MiscRegistry;
import RW.Utils.Logger;
import RW.Utils.MiscUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/**
 * @author Lord_Crystalyx
 */
public class InhibitorModule extends Block implements IUCSPart, IUCSModule
{
	public InhibitorModule()
	{
		super(Material.iron);
		this.setBlockTextureName("rogueWorld:inhibitor");
		this.setCreativeTab(MiscRegistry.modTab);
		this.setBlockName("rw.uscInhibitor");
	}

	@Override
	public IUCSPart getPartRelatively(World w, int blockX, int blockY, int blockZ, int relativeX, int relativeY, int relativeZ)
	{
		Block b = w.getBlock(blockX + relativeX, blockY + relativeY, blockZ + relativeZ);
		if (b instanceof IUCSPart)
		{
			return (IUCSPart) b;
		}
		return null;
	}

	@Override
	public void performAction(World w, WorldPos bpos, WorldPos corePos)
	{
		int x = (int) corePos.getX();
		int y = (int) bpos.getY();
		int z = (int) corePos.getZ();

//		double gravConst = 1;//bpos.getY()-corePos.getY();
//		
//		double radius = 40;
//		List<Entity> ents = w.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(x - radius, y - radius, z - radius, x + radius, y + radius, z + radius));
//		for (Entity entl : ents)
//		{
//			if (!(entl instanceof EntityPlayer))
//			{
//				WorldPos entpos = new WorldPos(entl);
//				WorldPos centpos = new WorldPos(x, y, z);
//
//				WorldPos vec = centpos.substruct(entpos);
//
//				entl.setVelocity((double) gravConst / (vec.getX()*Math.abs(vec.getX())), (double) gravConst / (vec.getY()*Math.abs(vec.getY())), (double) gravConst / (vec.getZ()*Math.abs(vec.getZ())));
//
//			}
//			else
//			{
//
//			}
//		}
		double radius = 40;
		List<Entity> ents = w.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(x - radius, y - radius, z - radius, x + radius, y + radius, z + radius));
		for (Entity entl : ents)
		{
			if (!(entl instanceof EntityPlayer))
			{
				double var20 = ((double) x + 0.5D - entl.posX) / 15.0D;
				double var22 = ((double) y + 0.5D - entl.posY) / 15.0D;
				double var25 = ((double) z + 0.5D - entl.posZ) / 15.0D;
				double var9 = Math.sqrt(var20 * var20 + var22 * var22 + var25 * var25);
				double var11 = 1.0D - var9;
				if (var11 > 0.0D)
				{
					var11 *= var11;
					entl.motionX += var20 / var9 * var11 * 0.15D;
					entl.motionY += var22 / var9 * var11 * 0.25D;
					entl.motionZ += var25 / var9 * var11 * 0.15D;
				}

				if (entl.posX >= x && entl.posX <= x + 1.0F)
				{
					entl.motionX = 0;
				}

				if (entl.posY >= y && entl.posY <= y + 1.0F)
				{
					entl.motionY = 0;
				}

				if (entl.posZ >= z && entl.posZ <= z + 1.0F)
				{
					entl.motionZ = 0;
				}

			}
			else
			{
				EntityPlayer p = (EntityPlayer) entl;
				if (p.getCurrentArmor(3) != null)
				{
					if (!MiscUtils.playerData.get(p.getCommandSenderName()).playerIsSeeker)
					{
						double var20 = ((double) x + 0.5D - entl.posX) / 15.0D;
						double var22 = ((double) y + 0.5D - entl.posY) / 15.0D;
						double var25 = ((double) z + 0.5D - entl.posZ) / 15.0D;
						double var9 = Math.sqrt(var20 * var20 + var22 * var22 + var25 * var25);
						double var11 = 1.0D - var9;
						if (var11 > 0.0D)
						{
							var11 *= var11;
							entl.motionX += var20 / var9 * var11 * 0.15D;
							entl.motionY += var22 / var9 * var11 * 0.25D;
							entl.motionZ += var25 / var9 * var11 * 0.15D;
						}
					}
				}
				else
				{
					double var20 = ((double) x + 0.5D - entl.posX) / 15.0D;
					double var22 = ((double) y + 0.5D - entl.posY) / 15.0D;
					double var25 = ((double) z + 0.5D - entl.posZ) / 15.0D;
					double var9 = Math.sqrt(var20 * var20 + var22 * var22 + var25 * var25);
					double var11 = 1.0D - var9;
					if (var11 > 0.0D)
					{
						var11 *= var11;
						entl.motionX += var20 / var9 * var11 * 0.15D;
						entl.motionY += var22 / var9 * var11 * 0.25D;
						entl.motionZ += var25 / var9 * var11 * 0.15D;
					}
				}
			}
			// entl.setVelocity(x-entl.posX+0.5,
			// y-entl.posY+0.5, z-entl.posZ+0.5);
		}
	}
}
