package RW.Common.Blocks.UCS;

import RW.Api.IUCSPart;
import RW.Api.IUCSModule;
import RW.Common.Misc.WorldPos;
import RW.Common.Registry.MiscRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandTime;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

/**
 * @author Lord_Crystalyx
 */
public class NightCallerModule extends Block implements IUCSPart, IUCSModule
{
	public NightCallerModule()
	{
		super(Material.iron);
		this.setBlockTextureName("rogueWorld:night_caller");
		this.setCreativeTab(MiscRegistry.modTab);
		this.setBlockName("rw.ucsNightCaller");
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
	public void performAction(World w, WorldPos bpos, WorldPos corePos)
	{
		if (w.getWorldTime() > 18000 || w.getWorldTime() < 14000)
		{
			w.setWorldTime(15000);
		}
	}
}
