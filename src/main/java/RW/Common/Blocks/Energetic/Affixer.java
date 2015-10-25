package RW.Common.Blocks.Energetic;

import RW.Api.EnergeticTileEntity;
import RW.Common.Registry.ItemRegistry;
import RW.Common.Registry.MiscRegistry;
import RW.Common.Tile.TileEntityAffixer;
import RW.Core.RogueWorldCore;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class Affixer extends BlockContainer
{

	private IIcon bottom;
	private IIcon top;
	private IIcon side;

	public Affixer()
	{
		super(Material.anvil);
		this.setCreativeTab(MiscRegistry.modTab);
		this.setBlockName("rw.affixer");
		this.setLightOpacity(0);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube? This determines whether
	 * or not to render the shared face of two adjacent blocks and also whether
	 * the player can attach torches, redstone wire, etc to this block.
	 */
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntityAffixer();
	}

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
		top = ir.registerIcon("rogueWorld:affixer/affixer_top");
		side = ir.registerIcon("rogueWorld:affixer/affixer_side");
		bottom = ir.registerIcon("rogueWorld:affixer/affixer_bottom");
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
			p.openGui(RogueWorldCore.core, 5, w, x, y, z);
		} else
		{
			EnergeticTileEntity From = (EnergeticTileEntity) w.getTileEntity(x, y, z);
			From.unBound();
		}
		return true;
	}

}
