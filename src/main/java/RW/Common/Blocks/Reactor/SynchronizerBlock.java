/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Blocks.Reactor;

import java.util.Random;

import RW.Common.Registry.BlockRegistry;
import RW.Common.Tile.TileEntitySynchronizer;
import RW.Core.RogueWorldCore;
import RW.Utils.Logger;
import RW.Utils.MiscUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author Lord_Crystalyx
 */
public class SynchronizerBlock extends ReactorElement
{

	private IIcon top_bot2;

	public SynchronizerBlock()
	{
		super(Material.iron, "rw.sinch", 1F, 1F, RogueWorldCore.core, false);
		this.setTickRandomly(true);
	}

	public static IIcon top_bot;
	public static IIcon side;

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntitySynchronizer();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister ir)
	{
		this.top_bot = ir.registerIcon(RogueWorldCore.ModId + ":reactor/synchcronizer");
		this.top_bot2 = ir.registerIcon(RogueWorldCore.ModId + ":reactor/synchcronizer_90");

		this.side = ir.registerIcon(RogueWorldCore.ModId + ":reactor/casing");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta)
	{
		if (side == ForgeDirection.UP.ordinal())
			if (meta == 0)
				return this.top_bot2;
			else
				return this.top_bot;
		return this.side;
	}

	public void updateTick(World w, int x, int y, int z, Random r)
	{
		int index = 0;
		if (w.getBlock(x + 2, y + 1, z - 2) == BlockRegistry.rcore || w.getBlock(x - 2, y + 1, z + 2) == BlockRegistry.rcore)
		{
			index = 0;
		}
		else
			index = 1;

		w.setBlockMetadataWithNotify(x, y, z, index, 2);
	}

	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int side, float px, float py, float pz)
	{
		MiscUtils.playerData.get(p.getCommandSenderName()).playerIsSeeker = true;
		Logger.info(MiscUtils.playerData.get(p.getCommandSenderName()).playerIsSeeker);
		return false;
	}

	public int getRenderType()
	{
		return 0x8983;
	}

}
