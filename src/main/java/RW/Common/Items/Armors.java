/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Items;

import RW.Client.Model.DarkHelmet;
import RW.Common.Registry.MiscRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.EnumHelper;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;

public class Armors extends ItemArmor implements IVisDiscountGear,IRunicArmor
{
	public int mat;
	public String way;
	public String icway;

	public Armors(int mat,int type,String way,String iconway,ArmorMaterial mater)
	{
		super(mater, 0,type);
		this.setCreativeTab(MiscRegistry.modTab);
		this.setUnlocalizedName("rw."+iconway+"armor"+this.armorType);
		this.mat=mat;
		this.way=way;
		this.icway=iconway;
	}

	@Override
	public int getVisDiscount(ItemStack stack, EntityPlayer player,Aspect aspect)
	{
		return 7;
	}
//	@Override
//	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
//	{	
//		DarkHelmet model = new DarkHelmet();
//		return model;		
//	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		return this.armorType != 2 ? "rogueWorld:textures/misc/armor/"+this.way+".png":"rogueWorld:textures/misc/armor/"+this.way+"2.png";		
	}
	
	public IIcon[][] icons = new IIcon[4][4];
	
	/**
     * Gets an icon index based on an item's damage value
     */
	@Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int p_77617_1_)
    {
        return icons[this.mat][this.armorType];
    }
	
	public void registerIcons(IIconRegister ir)
	{		
		
		icons[this.mat][0]= ir.registerIcon("rogueWorld:armor/"+this.icway+"_helmet");
		icons[this.mat][1]= ir.registerIcon("rogueWorld:armor/"+this.icway+"_chestplate");
		icons[this.mat][2]= ir.registerIcon("rogueWorld:armor/"+this.icway+"_leggings");
		icons[this.mat][3]= ir.registerIcon("rogueWorld:armor/"+this.icway+"_boots");
	}

	@Override
	public int getRunicCharge(ItemStack var1)
	{
		return 0;
	}
}
