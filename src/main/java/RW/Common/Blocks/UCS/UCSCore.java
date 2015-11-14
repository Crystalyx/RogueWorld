package RW.Common.Blocks.UCS;

import RW.Api.IUCSPart;
import RW.Common.Registry.MiscRegistry;
import RW.Common.Tile.TileEntityUCS;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
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
public class UCSCore extends BlockContainer implements IUCSPart
{

	public UCSCore()
	{
		super(Material.iron);
		this.setCreativeTab(MiscRegistry.modTab);
		this.setBlockName("rw.ucscore");
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntityUCS();
	}

	public IIcon top;
	public IIcon side;
	public IIcon bot;

	@Override
	public IIcon getIcon(int bside, int meta)
	{
		if (bside == ForgeDirection.UP.ordinal())
			return top;
		if (bside == ForgeDirection.DOWN.ordinal())
			return bot;
		else
			return side;
	}

	@Override
	public void registerBlockIcons(IIconRegister ir)
	{
		top = ir.registerIcon("rogueWorld:ucs_core_top");
		bot = ir.registerIcon("rogueWorld:ucs_core_bot");
		side = ir.registerIcon("rogueWorld:ucs_core_side");
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
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int side, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	{
		return false;
	}
}
