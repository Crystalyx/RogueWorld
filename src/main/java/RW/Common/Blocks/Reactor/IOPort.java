package RW.Common.Blocks.Reactor;

import RW.Common.Tile.TileEntityIOPort;
import RW.Core.RogueWorldCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author Lord_Crystalyx
 */
public class IOPort extends ReactorElement
{
	public IOPort()
	{
		super(Material.iron, "rw.ioport", 1F, 1F, RogueWorldCore.core, false);
	}

	@SideOnly(Side.CLIENT)
	protected IIcon in;
	@SideOnly(Side.CLIENT)
	protected IIcon out;
	@SideOnly(Side.CLIENT)
	protected IIcon side;

	public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase p, ItemStack i)
	{
		int dir = MathHelper.floor_double((double) ((p.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		w.setBlockMetadataWithNotify(x, y, z, dir, 0);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_)
	{
		this.side = p_149651_1_.registerIcon("rogueWorld:reactor/casing");
		this.in = p_149651_1_.registerIcon("rogueWorld:reactor/port_in");
		this.out = p_149651_1_.registerIcon("rogueWorld:reactor/port_out");
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntityIOPort();
	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		int[] i = new int[] { 2, 5, 3, 4 };
		int k = 8, l = 8;
		if (meta < 4)
		{
			l = i[meta];
		}
		if (meta >= 4)
		{
			k = i[meta - 4];
		}
		return side == k ? this.in : (side == l ? this.out : this.side);
	}
}
