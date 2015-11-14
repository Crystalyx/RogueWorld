package RW.Common.Blocks.Reactor;

import RW.Common.Registry.MiscRegistry;
import RW.Core.RogueWorldCore;
import RW.Utils.MathUtils;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author Lord_Crystalyx
 */
public class ReactorElement extends BlockContainer implements IReactorPart
{

	private boolean isAlpha;
	private TileEntity tale;

	public ReactorElement(Material mat, String uName, float hardness, float resist, RogueWorldCore mod, boolean alpha)
	{
		super(mat);
		this.setBlockName(uName);
		this.setHardness(hardness);
		this.setResistance(resist);
		this.setCreativeTab(MiscRegistry.modTab);
		this.isAlpha = alpha;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return null;
	}
}
