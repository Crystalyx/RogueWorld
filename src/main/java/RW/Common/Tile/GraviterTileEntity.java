package RW.Common.Tile;

import java.util.List;

import RW.Common.Misc.WorldPos;
import RW.Common.Registry.ItemRegistry;
import RW.Utils.Logger;
import RW.Utils.MathUtils;
import RW.Utils.MiscUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class GraviterTileEntity extends TileEntity
{
	public int cooldown = 0;

	public GraviterTileEntity()
	{
		super();
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

		double radius = 42;
		List<Entity> ents = this.worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(this.xCoord - radius, this.yCoord - radius, this.zCoord - radius, this.xCoord + radius, this.yCoord + radius, this.zCoord + radius));
		for (Entity entl : ents)
		{
			if (!entl.onGround)
			{
				if (!(entl instanceof EntityPlayer))
				{
					entl.motionX *=5;
					entl.motionZ *=5;
					entl.motionY = 0.00005;
				} else
				{
					EntityPlayer p = (EntityPlayer) entl;
					if (p.getCurrentArmor(3) != null)
					{
						if (/**p.getCurrentArmor(3).getItem() == ItemRegistry.AtrArmor[0] ||**/ MiscUtils.playerData.get(p.getCommandSenderName()).playerIsSeeker)
						{
							
						} else
						{
							entl.motionY = 0.00005;
						}
					} else
					{
						entl.motionY = 0.00005;
					}
					if (p.isSneaking())
					{
						p.motionY = -0.5;
					}
					if (GameSettings.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindJump) && Minecraft.getMinecraft().inGameHasFocus)
					{
						p.motionY = 0.5;
					}

				}

				if (entl instanceof EntityItem)
				{
					entl.motionX *= 0;
					entl.motionY = 0.04;
					entl.motionZ *= 0;
				}

				if (entl instanceof EntityXPOrb)
				{
					entl.motionX *= 0;
					entl.motionY = 0.03;
					entl.motionZ *= 0;
				}
				entl.fallDistance=0;
			}

			// entl.setVelocity(this.xCoord-entl.posX+0.5,
			// this.yCoord-entl.posY+0.5, this.zCoord-entl.posZ+0.5);
		}

	}
}
