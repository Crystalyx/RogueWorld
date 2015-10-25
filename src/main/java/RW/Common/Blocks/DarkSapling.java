/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Blocks;

import java.util.Random;

import RW.Common.Misc.WorldGenDarkTree;
import RW.Common.Registry.MiscRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DarkSapling extends BlockBush implements IGrowable
{
	public DarkSapling()
	{
		super();
		this.setCreativeTab(MiscRegistry.modTab);
	}
	
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int meta, float posx, float posy, float posz)
	{
		if(p.getCurrentEquippedItem().getItem() == Items.apple)
			func_149879_c(w, x, y, z, w.rand);
		return false;		
	}
	
	  public static final String[] field_149882_a = new String[] {"oak", "spruce", "birch", "jungle", "acacia", "roofed_oak"};
	    private static final IIcon[] field_149881_b = new IIcon[field_149882_a.length];
	    private static final String __OBFID = "CL_00000305";	

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
    {
        if (!p_149674_1_.isRemote)
        {
            super.updateTick(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, p_149674_5_);

            if (p_149674_1_.getBlockLightValue(p_149674_2_, p_149674_3_ + 1, p_149674_4_) >= 9 && p_149674_5_.nextInt(7) == 0)
            {
                this.func_149879_c(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, p_149674_5_);
            }
        }
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        p_149691_2_ &= 7;
        return field_149881_b[MathHelper.clamp_int(p_149691_2_, 0, 5)];
    }

    public void func_149879_c(World p_149879_1_, int p_149879_2_, int p_149879_3_, int p_149879_4_, Random p_149879_5_)
    {
        int l = p_149879_1_.getBlockMetadata(p_149879_2_, p_149879_3_, p_149879_4_);

        if ((l & 8) == 0)
        {
            p_149879_1_.setBlockMetadataWithNotify(p_149879_2_, p_149879_3_, p_149879_4_, l | 8, 4);
        }
        else
        {
            this.Grow(p_149879_1_, p_149879_2_, p_149879_3_, p_149879_4_, p_149879_5_);
        }
    }

    public void Grow(World w, int x, int y, int z, Random r)
    {
        if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(w, r, x, y, z)) return;
        int l = 0;
        Object object = new WorldGenDarkTree(true);
        int i1 = 0;
        int j1 = 0;
        boolean flag = false;

        Block block = Blocks.air;

        if (flag)
        {
            w.setBlock(x + i1, y, z + j1, block, 0, 4);
            w.setBlock(x + i1 + 1, y, z + j1, block, 0, 4);
            w.setBlock(x + i1, y, z + j1 + 1, block, 0, 4);
            w.setBlock(x + i1 + 1, y, z + j1 + 1, block, 0, 4);
        }
        else
        {
            w.setBlock(x, y, z, block, 0, 4);
        }

        if (!((WorldGenerator)object).generate(w, r, x + i1, y, z + j1))
        {
            if (flag)
            {
                w.setBlock(x + i1, y, z + j1, this, l, 4);
                w.setBlock(x + i1 + 1, y, z + j1, this, l, 4);
                w.setBlock(x + i1, y, z + j1 + 1, this, l, 4);
                w.setBlock(x + i1 + 1, y, z + j1 + 1, this, l, 4);
            }
            else
            {
                w.setBlock(x, y, z, this, l, 4);
                
            }
        }
        ((WorldGenerator)object).generate(w, r, x + i1+1, y, z + j1);
        ((WorldGenerator)object).generate(w, r, x + i1, y, z + j1+1);
        ((WorldGenerator)object).generate(w, r, x + i1+1, y, z + j1+1);
    }

    public boolean func_149880_a(World p_149880_1_, int p_149880_2_, int p_149880_3_, int p_149880_4_, int p_149880_5_)
    {
        return p_149880_1_.getBlock(p_149880_2_, p_149880_3_, p_149880_4_) == this && (p_149880_1_.getBlockMetadata(p_149880_2_, p_149880_3_, p_149880_4_) & 7) == p_149880_5_;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int p_149692_1_)
    {
        return MathHelper.clamp_int(p_149692_1_ & 7, 0, 5);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        for (int i = 0; i < field_149881_b.length; ++i)
        {
            field_149881_b[i] = p_149651_1_.registerIcon("rogueWorld:dark_sapling");
        }
    }

    public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_)
    {
        return true;
    }

    public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_)
    {
        return (double)p_149852_1_.rand.nextFloat() < 0.45D;
    }

    public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_)
    {
        this.func_149879_c(p_149853_1_, p_149853_3_, p_149853_4_, p_149853_5_, p_149853_2_);
    }
}
