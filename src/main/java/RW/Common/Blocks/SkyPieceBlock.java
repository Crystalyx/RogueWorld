package RW.Common.Blocks;

import java.util.List;

import RW.Common.Registry.MiscRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

/**
 * @author Lord_Crystalyx
 */
public class SkyPieceBlock extends Block
{
	public SkyPieceBlock()
	{
		super(Material.glass);
		this.setCreativeTab(MiscRegistry.modTab);
		this.setBlockName("rw.skyPiece");
	}

	public static String[] names = new String[] { "", "_magic", "_nether", "_ender" };

	public IIcon[] icons = new IIcon[5];

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		this.icons[0] = ir.registerIcon("rogueWorld:sky_piece");
		this.icons[1] = ir.registerIcon("rogueWorld:sky_piece_magic");
		this.icons[2] = ir.registerIcon("rogueWorld:sky_piece_nether");
		this.icons[3] = ir.registerIcon("rogueWorld:sky_piece_ender");
	}

	public int getBlockMetadata(int i)
	{
		return i;

	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess p_149673_1_, int p_149673_2_, int p_149673_3_, int p_149673_4_, int p_149673_5_)
	{
		return this.getIcon(p_149673_5_, p_149673_1_.getBlockMetadata(p_149673_2_, p_149673_3_, p_149673_4_));
	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return this.icons[meta];
	}

	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood
	 * returns 4 blocks)
	 */
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item i, CreativeTabs tab, List l)
	{
		l.add(new ItemStack(i, 1, 0));
		l.add(new ItemStack(i, 1, 1));
		l.add(new ItemStack(i, 1, 2));
		l.add(new ItemStack(i, 1, 3));
	}

}
