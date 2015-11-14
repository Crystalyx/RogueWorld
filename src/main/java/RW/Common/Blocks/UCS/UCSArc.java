package RW.Common.Blocks.UCS;

import RW.Api.IUCSPart;
import RW.Common.Registry.MiscRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

/**
 * @author Lord_Crystalyx
 */
public class UCSArc extends Block implements IUCSPart
{
	public UCSArc()
	{
		super(Material.iron);
		this.setBlockTextureName("rogueWorld:ucs_arc");
		this.setCreativeTab(MiscRegistry.modTab);
		this.setBlockName("rw.ucsarc");
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
}
