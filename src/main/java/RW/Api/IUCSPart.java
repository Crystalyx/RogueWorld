package RW.Api;

import net.minecraft.world.World;

public interface IUCSPart
{
	public IUCSPart getPartRelatively(World w,int blockX,int blockY,int blockZ ,int relativeX,int relativeY,int relativeZ);
}
