package RW.Common.Items;

import java.util.List;

import DummyCore.Utils.StructureApi;
import RW.Api.EnergeticTileEntity;
import RW.Common.Misc.WorldPos;
import RW.Common.Registry.BlockRegistry;
import RW.Common.Registry.MiscRegistry;
import RW.Core.RogueWorldCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BoundSphere extends LinkingRod
{
	public BoundSphere()
	{
		this.setCreativeTab(MiscRegistry.modTab);
		this.setUnlocalizedName("rw.boundsphere");
		this.setHasSubtypes(true);
	}
	
	public static IIcon green; 
	public static IIcon red; 
	public static IIcon blue; 
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister ir)
	{
		this.green = ir.registerIcon(RogueWorldCore.ModId+":sphere_green");
		this.red = ir.registerIcon(RogueWorldCore.ModId+":sphere_red");
		this.blue = ir.registerIcon(RogueWorldCore.ModId+":sphere_blue");
	}
	
	public void getSubItems(Item i, CreativeTabs tab, List l)
	{
		l.add(new ItemStack(i,1,0));
		l.add(new ItemStack(i,1,1));
		l.add(new ItemStack(i,1,2));
	}
	
	public IIcon getIconFromDamage(int meta)
	{
		if(meta == 1)
			return this.green;	
		if(meta == 2)
			return this.red;	
		return this.blue;
	}
	
	
	
	public ItemStack onItemRightClick(ItemStack i, World w, EntityPlayer p)
	{
		if(!p.isSneaking())
		{
			//p.setItemInUse(i, Integer.MAX_VALUE);
		}
		else
		{
			if(i.getItemDamage() == 0)
			i.setItemDamage(2);
			else
				if(i.getItemDamage() == 2)
					i.setItemDamage(0);
		}
		return i;		
	}
	
	public boolean onItemUse(ItemStack i, EntityPlayer p, World w, int x, int y, int z, int side, float px, float py, float pz)
	{
		
		if(!p.isSneaking())
		{
			if(i != null)
			{
				if(i.getItemDamage() == 0)
				{
					if(i.getTagCompound() != null)
					{
						i.getTagCompound().setInteger("BlockX", x);
						i.getTagCompound().setInteger("BlockY", y);
						i.getTagCompound().setInteger("BlockZ", z);
						i.getTagCompound().setInteger("Dim", p.dimension);
						
					}
				}
				if(i.getItemDamage() == 1)
				{
					if(i.getTagCompound() != null)
					{
						NBTTagCompound tag = StructureApi.createStructureTag(w,  AxisAlignedBB.getBoundingBox(x, y, z, i.getTagCompound().getInteger("BlockX"), i.getTagCompound().getInteger("BlockY"), i.getTagCompound().getInteger("BlockZ")), false, Blocks.bedrock,BlockRegistry.mark);
						i.getTagCompound().removeTag("BlockX");
						i.getTagCompound().removeTag("BlockY");
						i.getTagCompound().removeTag("BlockZ");
						i.getTagCompound().removeTag("Dim");	
						i.setTagCompound(tag);
					}
				}
				if(i.getItemDamage() == 2)
				{
					if(i.getTagCompound() != null)
					{
						StructureApi.nbtStructureIntoWorld(w, x, y, z, i.getTagCompound());
					}
				}
			}
		}
		else
		{
			i.getTagCompound().removeTag("BlockX");
			i.getTagCompound().removeTag("BlockY");
			i.getTagCompound().removeTag("BlockZ");
			i.getTagCompound().removeTag("Dim");
			
		}
		return true;	
	}
	
	public void onUpdate(ItemStack i, World w, Entity e, int interesting, boolean isInHand)
	{
		if(i.getTagCompound() == null)
		{
			NBTTagCompound tag = new NBTTagCompound();
			i.setTagCompound(tag);
		}

		if(i.getTagCompound().hasKey("BlockX") && i.getItemDamage() == 0)
		{
			i.setItemDamage(1);
		}
		if(!i.getTagCompound().hasKey("BlockX") && i.getItemDamage() == 1)
		{
			i.setItemDamage(0);
		}
	}
	
	public void addInformation(ItemStack i, EntityPlayer p, List l, boolean in)
	{
		if(i.hasTagCompound())
		{
			if(i.getTagCompound().hasKey("BlockX"))
			{
				l.add("Bound to: X = "+i.getTagCompound().getInteger("BlockX") + "; Y = "+i.getTagCompound().getInteger("BlockY") + "; Z = "+i.getTagCompound().getInteger("BlockZ") + ";");
				l.add("In Dimension: " + i.getTagCompound().getInteger("Dim"));
			}
			else
				l.add("Not Bound");
		}
	}
}
