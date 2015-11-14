package RW.Api;

import RW.Common.Misc.WorldPos;
import RW.Core.RogueWorldCore;
import RW.Utils.MathUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

/**
 * @author Lord_Crystalyx
 */
public class IStructurePart extends TileEntity
{
	private WorldPos core;

	public void setCore(WorldPos pos)
	{
		this.core = pos;
		if (this.worldObj.isRemote)
		{
			int[] i = pos.toIntArray();
			this.performAction(i[0], i[1], i[2]);
		}
	}

	public WorldPos getCore()
	{
		return this.core;
	}

	public void performAction(int x, int y, int z)
	{

	}
}
