/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Blocks;

import RW.Common.Misc.WorldPos;
import RW.Common.Registry.MiscRegistry;
import RW.Common.Tile.TileEntityPillar;
import RW.Common.Tile.TileEntityReactorCore;
import RW.Utils.MiscUtils;
import RW.Utils.StructurePoses;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class AltarBasePillar extends BlockContainer
{

	public AltarBasePillar(Material mat)
	{
		super(mat);
		this.setBlockBounds(0.29F, 0.0F, 0.29F, 0.71F, 1.0F, 0.71F);
		this.setCreativeTab(MiscRegistry.modTab);
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean isNormalCube()
	{
		return false;
	}

	public static IIcon t_top;
	public static IIcon t_side;

	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (side != ForgeDirection.DOWN.ordinal() && side != ForgeDirection.UP.ordinal())
			return t_side;
		else
			return t_top;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_)
	{
		t_top = p_149651_1_.registerIcon("rogueWorld:pillar/altar_base_pil_top");
		t_side = p_149651_1_.registerIcon("rogueWorld:pillar/altar_base_pil");
	}

	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int meta, float px, float py, float pz)
	{
		if (!p.isSneaking())
		{
			if (p.getCurrentEquippedItem() != null)
			{
				if (w.getTileEntity(x, y, z) instanceof TileEntityPillar)
				{
					if (((TileEntityPillar) w.getTileEntity(x, y, z)).getStackInSlot(0) == null)
					{
						ItemStack item = p.getCurrentEquippedItem().copy();
						item.stackSize = 1;
						((TileEntityPillar) w.getTileEntity(x, y, z)).setInventorySlotContents(0, item);
						WorldPos[] poses = StructurePoses.poses.get(0);
						WorldPos pos = poses[((TileEntityPillar)w.getTileEntity(x, y, z)).getIndex()].flip();
						int[] i = pos.toIntArray();
						if (w.getTileEntity(x+i[0], y+i[1]+4, z+i[2]) instanceof TileEntityReactorCore)
						{
							((TileEntityReactorCore) w.getTileEntity(x+i[0], y+i[1]+4, z+i[2])).setInventorySlotContents(((TileEntityPillar)w.getTileEntity(x, y, z)).getIndex(), item);
						}
						--p.getCurrentEquippedItem().stackSize;
					}
				}
			}
		} else
		{
			if (w.getTileEntity(x, y, z) instanceof TileEntityPillar)
			{
				MiscUtils.addItemStack(p,((IInventory)w.getTileEntity(x, y, z)).getStackInSlot(0));
				((TileEntityPillar) w.getTileEntity(x, y, z)).setInventorySlotContents(0, null);
				WorldPos[] poses = StructurePoses.poses.get(0);
				WorldPos pos = poses[((TileEntityPillar)w.getTileEntity(x, y, z)).getIndex()].flip();
				int[] i = pos.toIntArray();
				if (w.getTileEntity(x+i[0], y+i[1]+4, z+i[2]) instanceof TileEntityReactorCore)
				{
					((TileEntityReactorCore) w.getTileEntity(x+i[0], y+i[1]+4, z+i[2])).setInventorySlotContents(((TileEntityPillar)w.getTileEntity(x, y, z)).getIndex(), null);
				}
			}
		}
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntityPillar();
	}

	public int getRenderType()
	{
		return 0;
	}
}
