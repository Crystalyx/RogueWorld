/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Utils;

import java.util.Random;

import RW.Common.Misc.WorldPos;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class MathUtils
{
	
	public static Random rand = new Random();
	public static WorldPos getPosByAngle(WorldPos pos,double range,double yaw,double pitch)
	{
		double nx,rad,pt,mod,ny,nz=1;
		double ang;
		ang=pitch;
		rad=-Math.toRadians(pitch);
		ny= range*Math.sin(rad);
		mod= range*Math.cos(rad);
		
		rad=-Math.toRadians(ang);
		nx= mod * range*Math.cos(rad);
		nz= mod * range*Math.sin(rad);
		pt=Math.sqrt(nx*nx+nz*nz);
		
		return new WorldPos(pos.getX()+nx,pos.getY()+ny,pos.getZ()+nz);		
	}
	
	public static int getIntInRange(int range)
	{
		int ret= rand.nextInt(range);
		if(rand.nextBoolean())
		{
			ret *=-1;
		}
		return ret;
	}
	
	public static WorldPos getMotionsByEntity(double f,Entity to)
	{
		to.motionX = (double)(-MathHelper.sin(to.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(to.rotationPitch / 180.0F *(float)Math.PI) * f);
		to.motionZ = (double)(MathHelper.cos(to.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(to.rotationPitch / 180.0F *(float)Math.PI) * f);
		to.motionY = (double)(-MathHelper.sin((to.rotationPitch + 0) / 180.0F * (float)Math.PI) * f);
		WorldPos ret = new WorldPos((int)to.motionX,(int)to.motionY,(int)to.motionZ);
		return ret;

	}
}
