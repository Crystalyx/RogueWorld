package RW.Common.Blocks;

import RW.Api.EnergeticTileEntity;
import RW.Common.Registry.ItemRegistry;
import RW.Common.Registry.MiscRegistry;
import RW.Common.Tile.TileEntityDUStorage;
import RW.Core.RogueWorldCore;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class DUStorageBlock extends BlockContainer
{

	public DUStorageBlock()
	{
		super(Material.anvil);
		this.setBlockBounds(0.0625F, 0.0625F,0.0625F, 0.9375F, 0.9375F, 0.9375F);
		this.setCreativeTab(MiscRegistry.modTab);
		this.setBlockName("rw.storage");
		this.setResistance(1.0F);
		this.setHardness(1.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntityDUStorage();
	}
	
	public IIcon top;
	public IIcon side;

	@Override
	public IIcon getIcon(int bside, int meta)
	{
			if(bside == ForgeDirection.UP.ordinal() || bside == ForgeDirection.DOWN.ordinal())
				return top;
			else
				return side;
	}
	@Override
	public void registerBlockIcons(IIconRegister ir)
	{
		top = ir.registerIcon("rogueWorld:storage/storage_top");
		side = ir.registerIcon("rogueWorld:storage/storage_side");
	}
	
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int meta, float px, float py, float pz)
	{
		if(!p.isSneaking())
		{
			if(p.getCurrentEquippedItem() != null)
			{ 
				if(p.getCurrentEquippedItem().getItem() == ItemRegistry.linkingRod)
				{
					return false;
				}
			}		
			p.openGui(RogueWorldCore.core, 6, w, x, y, z);
		}
		else
		{
			TileEntityDUStorage From = (TileEntityDUStorage) w.getTileEntity(x, y, z);
			From.unBoundFull();
		}
		return true;
	}
	
	@Override
    public int getRenderType()
    {
        return 0x8984;
    }
	
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	
	public boolean isNormalCube()
	{
		return false;
	}
}
