package RW.Common.Blocks.Reactor;

import RW.Core.RogueWorldCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class ReactorConstruct extends Block implements IReactorPart
{

	private boolean isAlpha;
	protected ReactorConstruct(Material p_i45394_1_)
	{
		super(p_i45394_1_);
	}
	
	public ReactorConstruct(Material mat,String uName,String texture,float hardness,float resist,RogueWorldCore mod,boolean alpha)
	{
		super(mat);
		this.setBlockName(uName);
		this.setBlockTextureName(mod.ModId+":"+texture);
		this.setHardness(hardness);
		this.setResistance(resist);
		this.setCreativeTab(mod.MCore.modTab);
		this.isAlpha=alpha;
	}
	
	@Override
	public boolean isNormalCube()
	{
		return !isAlpha;		
	}
	public boolean isOpaqueCube()
	{
		return !isAlpha;
	}

}
