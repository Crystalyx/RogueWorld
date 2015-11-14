package RW.Api;

import RW.Common.Misc.WorldPos;
import net.minecraft.world.World;

/**
 * @author Lord_Crystalyx
 */
public interface IUCSModule
{
	public void performAction(World w, WorldPos bpos, WorldPos corePos);
}
