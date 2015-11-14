/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Blocks.Energetic;

import java.util.Random;

import RW.Api.EnergeticTileEntity;
import RW.Common.Registry.ItemRegistry;
import RW.Common.Registry.MiscRegistry;
import RW.Core.RogueWorldCore;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author Lord_Crystalyx
 */
public class CreativeDUStorage extends BlockContainer
{

	public CreativeDUStorage()
	{
		super(Material.anvil);
		this.setBlockUnbreakable();
		this.setBlockTextureName("rogueWorld:creative_storage");
		this.setCreativeTab(MiscRegistry.modTab);
		this.setBlockName("rw.cstorage");
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		EnergeticTileEntity tile = new EnergeticTileEntity(2, 10000, "Creative Storage");
		tile.isCreative = true;
		return tile;
	}

	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int side, float fx, float fy, float fz)
	{
		if (p.getCurrentEquippedItem() != null)
		{
			if (p.getCurrentEquippedItem().getItem() == ItemRegistry.linkingRod)
			{
				return false;
			}
		}
		p.openGui(RogueWorldCore.core, 2, w, x, y, z);
		return true;
	}

}
