package RW.Common.Blocks.UCS;

import RW.Api.IUCSPart;
import RW.Api.IUSCModule;
import RW.Common.Registry.MiscRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandTime;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class DayCallerModule extends Block implements IUCSPart, IUSCModule
{
	public DayCallerModule()
	{
		super(Material.iron);
		this.setBlockTextureName("rogueWorld:day_caller");
		this.setCreativeTab(MiscRegistry.modTab);
		this.setBlockName("rw.uscDayCaller");
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
		//if (w.canBlockSeeTheSky(corex, corey, corez))
		{
			CommandTime comm = new CommandTime();
			comm.processCommand(Minecraft.getMinecraft().thePlayer, new String[]
			{ "set", "day" });
		}
	}
}
