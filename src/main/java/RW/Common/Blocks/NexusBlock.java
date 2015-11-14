package RW.Common.Blocks;

import RW.Common.Registry.ItemRegistry;
import RW.Common.Registry.MiscRegistry;
import RW.Common.Tile.TileEntityModificationAnvil;
import RW.Common.Tile.TileEntityNexus;
import RW.Utils.MiscUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * @author Lord_Crystalyx
 */
public class NexusBlock extends BlockContainer
{

	public NexusBlock()
	{
		super(Material.iron);
		this.setBlockTextureName("rogueWorld:null");
		this.setCreativeTab(MiscRegistry.modTab);
		this.setBlockName("rw.nexus");
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntityNexus();
	}

	public int getRenderType()
	{
		return 0x8982;
	}

	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int p_149727_6_, float px, float py, float pz)
	{
		if (w.getTileEntity(x, y, z) != null)
			if (w.getTileEntity(x, y, z) instanceof TileEntityNexus)
			{
				TileEntityNexus tile = (TileEntityNexus) w.getTileEntity(x, y, z);
				if (!p.isSneaking())
				{
					if (p.getCurrentEquippedItem() != null)
					{
						if (p.getCurrentEquippedItem().getItem() != ItemRegistry.RiteBook && p.getCurrentEquippedItem().getItem() != ItemRegistry.linkingRod)
						{
							ItemStack item = p.getCurrentEquippedItem().copy();
							item.stackSize = 1;
							for (int i = 0; i < 3; i++)
							{
								if (tile.getStackInSlot(i) == null)
								{
									tile.setInventorySlotContents(i, item);
									p.inventory.decrStackSize(p.inventory.currentItem, 1);
									break;
								}
							}
						}
					}
				}
				else
				{
					if (p.isSneaking())
					{
						for (int i = 2; i >= 0; i--)
						{
							if (tile.getStackInSlot(i) != null)
							{
								MiscUtils.addItemStack(p, ((IInventory) w.getTileEntity(x, y, z)).getStackInSlot(i));
								tile.setInventorySlotContents(i, null);
								break;
							}
						}
					}
				}
			}
		return true;
	}

	public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase p, ItemStack i)
	{
		int dir = MathHelper.floor_double((double) ((p.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		w.setBlockMetadataWithNotify(x, y, z, dir, 0);
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

	public boolean isNormalCube()
	{
		return false;
	}

}
