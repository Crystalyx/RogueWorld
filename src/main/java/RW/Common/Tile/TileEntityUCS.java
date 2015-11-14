package RW.Common.Tile;

import RW.Api.IUCSPart;
import RW.Api.IUCSModule;
import RW.Common.Misc.WorldPos;
import RW.Core.RogueWorldCore;
import RW.Utils.Logger;
import RW.Utils.MathUtils;
import RW.Utils.PositionedWorld;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Lord_Crystalyx
 */
public class TileEntityUCS extends TileEntity
{
	public PositionedWorld world;

	public TileEntityUCS()
	{
		this.world = new PositionedWorld(this.worldObj);
	}

	public WorldPos xpos;
	public WorldPos xneg;
	public WorldPos zpos;
	public WorldPos zneg;

	public void updateEntity()
	{
		this.world = new PositionedWorld(this.worldObj);
		// Logger.info("ucsUpd");
		findModules();
		if (this.xpos != null)
			if (this.world.getBlock(this.xpos) instanceof IUCSModule && this.world.getBlock(this.zpos) instanceof IUCSModule && this.world.getBlock(this.xneg) instanceof IUCSModule && this.world.getBlock(this.zneg) instanceof IUCSModule)
				if (this.xpos.getY() == this.zpos.getY() && this.xpos.getY() == this.xneg.getY() && this.xpos.getY() == this.zneg.getY())
					((IUCSModule) this.world.getBlock(this.xpos)).performAction(this.worldObj, this.xpos, new WorldPos(this));
				else
				{
					this.xpos = null;
					this.xneg = null;
					this.zpos = null;
					this.zneg = null;
				}
		findModules();
	}

	public void findModules()
	{
		if (this.xpos == null)
		{
			WorldPos currPos = new WorldPos(this);
			currPos = new WorldPos(this);
			for (int i = 0; i < 20;)
			{
				if (this.world.getBlock(currPos) instanceof IUCSPart)
				{
					if (this.worldObj.getBlock((int) currPos.getX() + 1, (int) currPos.getY(), (int) currPos.getZ()) instanceof IUCSPart)
					{
						currPos = new WorldPos(currPos.getX() + 1, currPos.getY(), currPos.getZ());
						RogueWorldCore.proxy.spawnParticle("spark", currPos.getX(), currPos.getY(), currPos.getZ(), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2));
					}

					if (this.worldObj.getBlock((int) currPos.getX() + 1, (int) currPos.getY(), (int) currPos.getZ()) instanceof IUCSPart)
					{
						currPos = new WorldPos(currPos.getX() + 1, currPos.getY(), currPos.getZ());
						RogueWorldCore.proxy.spawnParticle("spark", currPos.getX(), currPos.getY(), currPos.getZ(), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2));
					}
					else
					{
						this.xpos = null;
						break;
					}

					if (this.worldObj.getBlock((int) currPos.getX(), (int) currPos.getY() + 1, (int) currPos.getZ()) instanceof IUCSPart)
					{
						currPos = new WorldPos(currPos.getX(), currPos.getY() + 1, currPos.getZ());
						RogueWorldCore.proxy.spawnParticle("spark", currPos.getX(), currPos.getY(), currPos.getZ(), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2));
					}
					else
					{
						this.xpos = null;
						break;
					}

					if (this.worldObj.getBlock((int) currPos.getX(), (int) currPos.getY() + 1, (int) currPos.getZ()) instanceof IUCSPart)
					{
						currPos = new WorldPos(currPos.getX(), currPos.getY() + 1, currPos.getZ());
						RogueWorldCore.proxy.spawnParticle("spark", currPos.getX(), currPos.getY(), currPos.getZ(), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2));
					}
					else
					{
						this.xpos = null;
						break;
					}

					if (this.worldObj.getBlock((int) currPos.getX(), (int) currPos.getY() + 1, (int) currPos.getZ()) instanceof IUCSPart)
					{
						currPos = new WorldPos(currPos.getX(), currPos.getY() + 1, currPos.getZ());
						RogueWorldCore.proxy.spawnParticle("spark", currPos.getX(), currPos.getY(), currPos.getZ(), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2));
					}
					else
					{
						this.xpos = null;
						break;
					}
				}
				if (this.worldObj.getBlock((int) currPos.getX(), (int) currPos.getY() + 1, (int) currPos.getZ()) instanceof IUCSModule)
				{
					currPos = new WorldPos(currPos.getX(), currPos.getY() + 1, currPos.getZ());
					this.xpos = currPos;
					break;
				}
			}
		}
		if (this.xneg == null)
		{
			WorldPos currPos = new WorldPos(this);
			currPos = new WorldPos(this);
			for (int i = 0; i < 20;)
			{
				if (this.world.getBlock(currPos) instanceof IUCSPart)
				{
					if (this.worldObj.getBlock((int) currPos.getX() + 1, (int) currPos.getY(), (int) currPos.getZ()) instanceof IUCSPart)
					{
						currPos = new WorldPos(currPos.getX() + 1, currPos.getY(), currPos.getZ());
						RogueWorldCore.proxy.spawnParticle("spark", currPos.getX(), currPos.getY(), currPos.getZ(), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2));
					}
					else
					{
						this.xneg = null;
						break;
					}

					if (this.worldObj.getBlock((int) currPos.getX() + 1, (int) currPos.getY(), (int) currPos.getZ()) instanceof IUCSPart)
					{
						currPos = new WorldPos(currPos.getX() + 1, currPos.getY(), currPos.getZ());
						RogueWorldCore.proxy.spawnParticle("spark", currPos.getX(), currPos.getY(), currPos.getZ(), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2));
					}
					else
					{
						this.xneg = null;
						break;
					}

					if (this.worldObj.getBlock((int) currPos.getX(), (int) currPos.getY() + 1, (int) currPos.getZ()) instanceof IUCSPart)
					{
						currPos = new WorldPos(currPos.getX(), currPos.getY() + 1, currPos.getZ());
						RogueWorldCore.proxy.spawnParticle("spark", currPos.getX(), currPos.getY(), currPos.getZ(), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2));
					}
					else
					{
						this.xneg = null;
						break;
					}

					if (this.worldObj.getBlock((int) currPos.getX(), (int) currPos.getY() + 1, (int) currPos.getZ()) instanceof IUCSPart)
					{
						currPos = new WorldPos(currPos.getX(), currPos.getY() + 1, currPos.getZ());
						RogueWorldCore.proxy.spawnParticle("spark", currPos.getX(), currPos.getY(), currPos.getZ(), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2));
					}
					else
					{
						this.xneg = null;
						break;
					}

					if (this.worldObj.getBlock((int) currPos.getX(), (int) currPos.getY() + 1, (int) currPos.getZ()) instanceof IUCSPart)
					{
						currPos = new WorldPos(currPos.getX(), currPos.getY() + 1, currPos.getZ());
						RogueWorldCore.proxy.spawnParticle("spark", currPos.getX(), currPos.getY(), currPos.getZ(), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2));
					}
					else
					{
						this.xneg = null;
						break;
					}
				}
				if (this.worldObj.getBlock((int) currPos.getX(), (int) currPos.getY() + 1, (int) currPos.getZ()) instanceof IUCSModule)
				{
					currPos = new WorldPos(currPos.getX(), currPos.getY() + 1, currPos.getZ());
					this.xneg = currPos;
					break;
				}
			}
		}
		if (this.zpos == null)
		{
			WorldPos currPos = new WorldPos(this);
			currPos = new WorldPos(this);
			for (int i = 0; i < 20;)
			{
				if (this.world.getBlock(currPos) instanceof IUCSPart)
				{
					if (this.worldObj.getBlock((int) currPos.getX() + 1, (int) currPos.getY(), (int) currPos.getZ()) instanceof IUCSPart)
					{
						currPos = new WorldPos(currPos.getX() + 1, currPos.getY(), currPos.getZ());
						RogueWorldCore.proxy.spawnParticle("spark", currPos.getX(), currPos.getY(), currPos.getZ(), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2));
					}

					if (this.worldObj.getBlock((int) currPos.getX() + 1, (int) currPos.getY(), (int) currPos.getZ()) instanceof IUCSPart)
					{
						currPos = new WorldPos(currPos.getX() + 1, currPos.getY(), currPos.getZ());
						RogueWorldCore.proxy.spawnParticle("spark", currPos.getX(), currPos.getY(), currPos.getZ(), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2));
					}
					else
					{
						this.zpos = null;
						break;
					}

					if (this.worldObj.getBlock((int) currPos.getX(), (int) currPos.getY() + 1, (int) currPos.getZ()) instanceof IUCSPart)
					{
						currPos = new WorldPos(currPos.getX(), currPos.getY() + 1, currPos.getZ());
						RogueWorldCore.proxy.spawnParticle("spark", currPos.getX(), currPos.getY(), currPos.getZ(), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2));
					}
					else
					{
						this.zpos = null;
						break;
					}

					if (this.worldObj.getBlock((int) currPos.getX(), (int) currPos.getY() + 1, (int) currPos.getZ()) instanceof IUCSPart)
					{
						currPos = new WorldPos(currPos.getX(), currPos.getY() + 1, currPos.getZ());
						RogueWorldCore.proxy.spawnParticle("spark", currPos.getX(), currPos.getY(), currPos.getZ(), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2));
					}
					else
					{
						this.zpos = null;
						break;
					}

					if (this.worldObj.getBlock((int) currPos.getX(), (int) currPos.getY() + 1, (int) currPos.getZ()) instanceof IUCSPart)
					{
						currPos = new WorldPos(currPos.getX(), currPos.getY() + 1, currPos.getZ());
						RogueWorldCore.proxy.spawnParticle("spark", currPos.getX(), currPos.getY(), currPos.getZ(), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2));
					}
					else
					{
						this.zpos = null;
						break;
					}
				}
				if (this.worldObj.getBlock((int) currPos.getX(), (int) currPos.getY() + 1, (int) currPos.getZ()) instanceof IUCSModule)
				{
					currPos = new WorldPos(currPos.getX(), currPos.getY() + 1, currPos.getZ());
					this.zpos = currPos;
					break;
				}
			}
		}
		if (this.zneg == null)
		{
			WorldPos currPos = new WorldPos(this);
			currPos = new WorldPos(this);
			for (int i = 0; i < 20;)
			{
				if (this.world.getBlock(currPos) instanceof IUCSPart)
				{
					if (this.worldObj.getBlock((int) currPos.getX() + 1, (int) currPos.getY(), (int) currPos.getZ()) instanceof IUCSPart)
					{
						currPos = new WorldPos(currPos.getX() + 1, currPos.getY(), currPos.getZ());
						RogueWorldCore.proxy.spawnParticle("spark", currPos.getX(), currPos.getY(), currPos.getZ(), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2));
					}
					else
					{
						this.zneg = null;
						break;
					}

					if (this.worldObj.getBlock((int) currPos.getX() + 1, (int) currPos.getY(), (int) currPos.getZ()) instanceof IUCSPart)
					{
						currPos = new WorldPos(currPos.getX() + 1, currPos.getY(), currPos.getZ());
						RogueWorldCore.proxy.spawnParticle("spark", currPos.getX(), currPos.getY(), currPos.getZ(), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2));
					}
					else
					{
						this.zneg = null;
						break;
					}

					if (this.worldObj.getBlock((int) currPos.getX(), (int) currPos.getY() + 1, (int) currPos.getZ()) instanceof IUCSPart)
					{
						currPos = new WorldPos(currPos.getX(), currPos.getY() + 1, currPos.getZ());
						RogueWorldCore.proxy.spawnParticle("spark", currPos.getX(), currPos.getY(), currPos.getZ(), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2));
					}
					else
					{
						this.zneg = null;
						break;
					}

					if (this.worldObj.getBlock((int) currPos.getX(), (int) currPos.getY() + 1, (int) currPos.getZ()) instanceof IUCSPart)
					{
						currPos = new WorldPos(currPos.getX(), currPos.getY() + 1, currPos.getZ());
						RogueWorldCore.proxy.spawnParticle("spark", currPos.getX(), currPos.getY(), currPos.getZ(), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2));
					}
					else
					{
						this.zneg = null;
						break;
					}

					if (this.worldObj.getBlock((int) currPos.getX(), (int) currPos.getY() + 1, (int) currPos.getZ()) instanceof IUCSPart)
					{
						currPos = new WorldPos(currPos.getX(), currPos.getY() + 1, currPos.getZ());
						RogueWorldCore.proxy.spawnParticle("spark", currPos.getX(), currPos.getY(), currPos.getZ(), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2), MathUtils.getIntInRange(2));
					}
					else
					{
						this.zneg = null;
						break;
					}
				}
				if (this.worldObj.getBlock((int) currPos.getX(), (int) currPos.getY() + 1, (int) currPos.getZ()) instanceof IUCSModule)
				{
					currPos = new WorldPos(currPos.getX(), currPos.getY() + 1, currPos.getZ());
					this.zneg = currPos;
					break;
				}
			}
		}

	}
}
