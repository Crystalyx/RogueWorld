package RW.Common.Items;

import RW.Api.IUSCModule;
import RW.Common.Registry.BlockRegistry;
import RW.Common.Registry.MiscRegistry;
import RW.Common.Tile.TileEntityReactorCore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GoldWrench extends Item
{
	public GoldWrench()
	{
		this.setTextureName("rogueWorld:gold_wrench");
		this.setUnlocalizedName("rw.wrench");
		this.setCreativeTab(MiscRegistry.modTab);
	}

	@Override
	public boolean onItemUse(ItemStack i, EntityPlayer p, World w, int x, int y, int z, int side, float px, float py, float pz)
	{
		if (w.getBlock(x, y, z) == BlockRegistry.rcore)
		{
			((TileEntityReactorCore) w.getTileEntity(x, y, z)).completeStructure();

		}
		if (w.getBlock(x, y, z) == BlockRegistry.ioport)
		{
			int meta = w.getBlockMetadata(x, y, z);
			if(meta<4)
			{
				w.setBlockMetadataWithNotify(x, y, z, meta+4, 2);
				return true;
			}
			if(meta>=4)
			{
				w.setBlockMetadataWithNotify(x, y, z, meta-4, 2);
				return true;
			}
		}
		if(w.getBlock(x, y, z) instanceof IUSCModule)
		{
			((IUSCModule)w.getBlock(x, y, z)).performAction(w, x, y, z, x, y, z);
		}
		return true;
	}
}
