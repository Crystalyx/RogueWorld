/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Blocks.Energetic;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import RW.Api.EnergeticTileEntity;
import RW.Common.Registry.ItemRegistry;
import RW.Common.Registry.MiscRegistry;
import RW.Common.Tile.TileEntityExtractor;
import RW.Core.RogueWorldCore;

/**
 * @author Lord_Crystalyx
 */
public class DarkExtractor extends BlockContainer
{

	public DarkExtractor(Material mat)
	{
		super(mat);
		this.setCreativeTab(MiscRegistry.modTab);
		this.setBlockName("rogueWorldDExtr");
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntityExtractor(0, 200000, "Dark Extractor");
	}

	public IIcon top;
	public IIcon side;
	public IIcon bottom;

	@Override
	public IIcon getIcon(int bside, int meta)
	{
		if (bside == ForgeDirection.DOWN.ordinal())
			return bottom;
		else if (bside == ForgeDirection.UP.ordinal())
			return top;
		else
			return side;
	}

	@Override
	public void registerBlockIcons(IIconRegister ir)
	{
		top = ir.registerIcon("rogueWorld:extractor/extractor_top");
		side = ir.registerIcon("rogueWorld:extractor/extractor_side");
		bottom = ir.registerIcon("rogueWorld:extractor/extractor_bottom");
	}

	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int meta, float px, float py, float pz)
	{
		if (!p.isSneaking())
		{
			if (p.getCurrentEquippedItem() != null)
			{
				if (p.getCurrentEquippedItem().getItem() == ItemRegistry.linkingRod)
				{
					return false;
				}
			}
			p.openGui(RogueWorldCore.core, 0, w, x, y, z);
		}
		else
		{
			EnergeticTileEntity From = (EnergeticTileEntity) w.getTileEntity(x, y, z);
			From.unBound();
		}
		return true;
	}

	@Override
	public int getRenderBlockPass()
	{
		return 0;
	}

}
