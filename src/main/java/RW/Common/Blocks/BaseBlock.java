/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Blocks;

import RW.Core.RogueWorldCore;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BaseBlock extends Block
{
	public boolean isAlpha=false;
	public BaseBlock(Material mat,String uName,String texture,float hardness,float resist,float light,RogueWorldCore mod,boolean alpha)
	{
		super(mat);
		this.setBlockName(uName);
		this.setBlockTextureName(mod.ModId+":"+texture);
		this.setHardness(hardness);
		this.setResistance(resist);
		this.setLightLevel(light);
		this.setCreativeTab(mod.MCore.modTab);
		this.isAlpha=alpha;
	}
	
	public BaseBlock(Material mat,String uName,String texture,float hardness,float resist,RogueWorldCore mod,boolean alpha)
	{
		super(mat);
		this.setBlockName(uName);
		this.setBlockTextureName(mod.ModId+":"+texture);
		this.setHardness(hardness);
		this.setResistance(resist);
		this.setCreativeTab(mod.MCore.modTab);
		this.isAlpha=alpha;
	}
	
//	@SideOnly(Side.CLIENT)
//	public int getRenderBlockPass()
//	{
//		return this.isAlpha ? 1 : 0;		
//	}
	
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
