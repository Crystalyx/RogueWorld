package RW.Utils;

import RW.Common.Misc.WorldPos;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PositionedWorld
{
	World world;

	public PositionedWorld(World w)
	{
		this.world = w;
	}

	public TileEntity getTileEntity(WorldPos pos)
	{
		return this.world.getTileEntity((int) pos.getX(), (int) pos.getY(), (int) pos.getZ());
	}

	public Block getBlock(WorldPos pos)
	{
		return this.world.getBlock((int) pos.getX(), (int) pos.getY(), (int) pos.getZ());
	}
	
	public void setBlock(WorldPos pos, Block b,int meta)
	{
		this.world.setBlock((int) pos.getX(), (int) pos.getY(), (int) pos.getZ(),b,meta,2);
	}
}
