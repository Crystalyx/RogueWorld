/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Blocks.Building;

import java.util.Random;

import RW.Common.Registry.MiscRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Dark_Head extends Block
{

	public Dark_Head(Material mat) 
	{
		super(mat);
		this.setHardness(1.0F);
		this.setCreativeTab(MiscRegistry.modTab);
		this.setTickRandomly(true);
	}
	
	public IIcon wood_head;
	public static IIcon wood_top;

	/**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {    	
    	if(side != ForgeDirection.DOWN.ordinal() && side != ForgeDirection.UP.ordinal())
    	{ 		
    		return wood_head;
    	}
    	else
    		return wood_top;
    }
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {    	
        wood_top = p_149651_1_.registerIcon("rogueWorld:dark_top");
        wood_head = p_149651_1_.registerIcon("rogueWorld:dark_head");
    }
    
    @Override
    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int meta, float px, float py, float pz)
    {
		return false;    	
    }
    int rx = -16;
    int ry = -16;
    int rz = -16;
    public void spawnFire(World w,int x,int y,int z,Random r)
    {
    	rx=r.nextInt(16);
    	rx=r.nextInt(16);
    	rx=r.nextInt(16);
        w.spawnParticle("flame", x+0.5D,y-5, z+0.5D, 0.0D+-w.rand.nextDouble(), 2.0D+-w.rand.nextDouble(), 0.0D+-w.rand.nextDouble());
        int rx = -16;
        int ry = -16;
        int rz = -16;
    }
   
}
