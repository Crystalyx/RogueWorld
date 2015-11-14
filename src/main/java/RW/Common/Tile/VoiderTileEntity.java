package RW.Common.Tile;

import java.util.List;

import RW.Utils.MiscUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author Lord_Crystalyx
 */
public class VoiderTileEntity extends TileEntity
{
	public VoiderTileEntity()
	{
		super();
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

	public void updateEntity()
	{
		double rx = this.worldObj.rand.nextDouble() * 10;
		double ry = this.worldObj.rand.nextDouble() * 10;
		double rz = this.worldObj.rand.nextDouble() * 10;
		if (this.worldObj.rand.nextBoolean())
			rx *= -1;
		if (this.worldObj.rand.nextBoolean())
			ry *= -1;
		if (this.worldObj.rand.nextBoolean())
			rz *= -1;
		// RogueWorldCore.proxy.spawnParticle("energyFX", this.xCoord + 0.5F,
		// this.yCoord - 0.5F, this.zCoord + 0.5F, this.xCoord + rx, this.yCoord
		// + ry, this.zCoord + rz);

		double radius = 40;
		List<Entity> ents = this.worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(this.xCoord - radius, this.yCoord - radius, this.zCoord - radius, this.xCoord + radius, this.yCoord + radius, this.zCoord + radius));
		for (Entity entl : ents)
		{
			if (!(entl instanceof EntityPlayer))
			{
				double var20 = ((double) this.xCoord + 0.5D - entl.posX) / 15.0D;
				double var22 = ((double) this.yCoord + 0.5D - entl.posY) / 15.0D;
				double var25 = ((double) this.zCoord + 0.5D - entl.posZ) / 15.0D;
				double var9 = Math.sqrt(var20 * var20 + var22 * var22 + var25 * var25);
				double var11 = 1.0D - var9;
				if (var11 > 0.0D)
				{
					var11 *= var11;
					entl.motionX += var20 / var9 * var11 * 0.15D;
					entl.motionY += var22 / var9 * var11 * 0.25D;
					entl.motionZ += var25 / var9 * var11 * 0.15D;
				}

				if (entl.posX >= this.xCoord && entl.posX <= this.xCoord + 1.0F)
				{
					entl.motionX = 0;
				}

				if (entl.posY >= this.yCoord && entl.posY <= this.yCoord + 1.0F)
				{
					entl.motionY = 0;
				}

				if (entl.posZ >= this.zCoord && entl.posZ <= this.zCoord + 1.0F)
				{
					entl.motionZ = 0;
				}

			}
			else
			{
				EntityPlayer p = (EntityPlayer) entl;
				if (p.getCurrentArmor(3) != null)
				{
					if (MiscUtils.playerData.get(p.getCommandSenderName()).playerIsSeeker)
					{

					}
					else
					{
						double var20 = ((double) this.xCoord + 0.5D - entl.posX) / 15.0D;
						double var22 = ((double) this.yCoord + 0.5D - entl.posY) / 15.0D;
						double var25 = ((double) this.zCoord + 0.5D - entl.posZ) / 15.0D;
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
					double var20 = ((double) this.xCoord + 0.5D - entl.posX) / 15.0D;
					double var22 = ((double) this.yCoord + 0.5D - entl.posY) / 15.0D;
					double var25 = ((double) this.zCoord + 0.5D - entl.posZ) / 15.0D;
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
			// entl.setVelocity(this.xCoord-entl.posX+0.5,
			// this.yCoord-entl.posY+0.5, this.zCoord-entl.posZ+0.5);
		}

	}
}
