/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Misc;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Lord_Crystalyx
 */
public class WorldPos
{

	private double x;
	private double y;
	private double z;

	public WorldPos(double px, double py, double pz)
	{
		this.x = px;
		this.y = py;
		this.z = pz;
	}

	public WorldPos(Entity p)
	{
		this.x = p.posX;
		this.y = p.posY;
		this.z = p.posZ;
	}

	public static WorldPos getWorldPos(Entity p)
	{
		if (p != null)
		{
			return new WorldPos(p);
		}
		else
			return null;
	}

	public WorldPos(TileEntity tile)
	{
		this.x = tile.xCoord;
		this.y = tile.yCoord;
		this.z = tile.zCoord;
	}

	public WorldPos centralize()
	{
		this.x += 0.5;
		this.y += 0.5;
		this.z += 0.5;
		return this;
	}

	public double getX()
	{
		return this.x;
	}

	public double getY()
	{
		return this.y;
	}

	public double getZ()
	{
		return this.z;
	}

	public void setX(double i)
	{
		this.x = i;
	}

	public void setY(double i)
	{
		this.y = i;
	}

	public void setZ(double i)
	{
		this.z = i;
	}

	public WorldPos substruct(WorldPos what)
	{
		WorldPos ret = new WorldPos(this.x - what.x, this.y - what.y, this.z - what.z);
		return ret;
	}

	public WorldPos add(WorldPos what)
	{
		WorldPos ret = new WorldPos(this.x + what.x, this.y + what.y, this.z + what.z);
		return ret;
	}

	public double[] toArray()
	{
		return new double[] { this.x, this.y, this.z };
	}

	public int[] toIntArray()
	{
		return new int[] { (int) this.x, (int) this.y, (int) this.z };
	}

	public String toString()
	{
		return "x: " + this.x + " y: " + this.y + " z" + this.z;
	}

	public WorldPos flip()
	{
		WorldPos ret = new WorldPos(-this.x, -this.y, -this.z);
		return ret;
	}
}
