package RW.Common.Blocks.UCS;

import java.util.List;

import RW.Api.IUCSPart;
import RW.Api.IUSCModule;
import RW.Common.Misc.WorldPos;
import RW.Common.Registry.MiscRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class InhibitorModule extends Block implements IUCSPart, IUSCModule
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
	public void performAction(World w, int x, int y, int z,int corex,int corey,int corez)
	{
		int tx = corex;
		int ty = y;
		int tz = corez;
		
		double radius = 40;
		List<Entity> ents = w.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(x - radius, y - radius, z - radius,x + radius, y + radius, z + radius));
		for (Entity entl : ents)
		{
			if (!(entl instanceof EntityPlayer))
			{
				WorldPos entpos = new WorldPos(entl);
				WorldPos centpos = new WorldPos(tx,ty,tz);
				
				WorldPos vec = centpos.substruct(entpos);
				
				entl.setVelocity((double) 10/vec.getX(), (double) 10/vec.getY(), (double) 10/vec.getZ());
				
			} else
			{
				
			}
		}
	}
}
