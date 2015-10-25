/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Blocks.Building;

import java.util.Random;

import RW.Common.Registry.MiscRegistry;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DarkWoodBlock extends BlockRotatedPillar
{

	public DarkWoodBlock(Material mat) 
	{
		super(mat);
		this.setHardness(1.0F);
		this.setCreativeTab(MiscRegistry.modTab);
	}
	
	Random r = new Random();
	int FaceSide;
    
	public static IIcon wood_top;
	public static IIcon wood_side;
	
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister i)
    {    	
        wood_top = i.registerIcon("rogueWorld:dark_top");
        wood_side = i.registerIcon("rogueWorld:dark_wood");
    }
    
	@Override
	protected IIcon getSideIcon(int side) 
	{
		return wood_side;
	}
	
	@SideOnly(Side.CLIENT)
    protected IIcon getTopIcon(int p_150161_1_)
    {
        return wood_top;
    }
    
}
