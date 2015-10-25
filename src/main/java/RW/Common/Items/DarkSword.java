/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Items;

import java.util.List;

import RW.Api.IDUSword;
import RW.Common.Registry.MiscRegistry;
import RW.Common.Skills.SkillRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class DarkSword extends IDUSword
{
	public DarkSword()
	{
		super(EnumHelper.addToolMaterial("Dark_Sword", 4, 512, 10.0F, 4, 25));
		this.setTextureName("rogueWorld:dark_sword");
		this.setUnlocalizedName("rw.darksword");
		this.setCreativeTab(MiscRegistry.modTab);
	}
	
	public void onUsingTick(ItemStack i, EntityPlayer p, int count)
	{
		if(i.getTagCompound() != null)
		{
			if(SkillRegistry.getSkill(p.getCurrentEquippedItem().getTagCompound().getInteger("CurSkillId")) != null)
			{				
				SkillRegistry.getSkill(p.getCurrentEquippedItem().getTagCompound().getInteger("CurSkillId")).attemptToUseSkill(p.worldObj, p, null,2);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack i, EntityPlayer p, List l, boolean bool)
	{
		if (i.getTagCompound() != null)
		{
			float Dam = i.getTagCompound().getFloat("Damage");
			float lev = i.getTagCompound().getInteger("Level");
			float exp = i.getTagCompound().getFloat("Expirience");
			l.add(EnumChatFormatting.BLUE + "Level: " + lev);
			l.add(EnumChatFormatting.AQUA + "Expirience: "	+ exp);
			l.add(EnumChatFormatting.RED + "Damage: " + Dam);

			if (SkillRegistry.getSkill(i.getTagCompound().getInteger("CurSkillId")) != null)
			{
				if (SkillRegistry.getSkill(i.getTagCompound().getInteger("CurSkillId")).lev <= lev)
				{
					l.add(EnumChatFormatting.DARK_RED+ "Skill: "+ SkillRegistry.getSkill(i.getTagCompound().getInteger("CurSkillId")).name);
				}
				else
				{
					i.getTagCompound().setBoolean("Skill"+ i.getTagCompound().getInteger("CurSkillId"), false);
				}
			}
			l.add(EnumChatFormatting.GRAY + "Cooldown: "+ i.getTagCompound().getInteger("CoolDown")/ 20);
			l.add("");
		}
		super.addInformation(i, p, l, bool);
	}

	/**
	 * returns the action that specifies what animation to play when the items
	 * is being used
	 */
	@Override
	public EnumAction getItemUseAction(ItemStack i)
	{
		return EnumAction.block;		
	}

	public NBTTagCompound tag = new NBTTagCompound();

	public ItemStack onItemRightClick(ItemStack i, World w, EntityPlayer p)
	{
		if (p.isSneaking())
		{
			if (i.hasTagCompound())
			{
				for (int l = 1; l < 11; l++)
				{
					if (i.getTagCompound().getInteger("CurSkillId") + l >= 10)
					{
						if (SkillRegistry.getSkill(i.getTagCompound()
								.getInteger("CurSkillId") + l - 10) != null)
						{
							i.getTagCompound().setInteger("CurSkillId",	i.getTagCompound().getInteger("CurSkillId")+ l - 10);
							break;
						}
					}
					else
					{
						if (SkillRegistry.getSkill(i.getTagCompound().getInteger("CurSkillId") + l) != null)
						{
							i.getTagCompound().setInteger("CurSkillId",i.getTagCompound().getInteger("CurSkillId")+ l);
							break;
						}
					}
				}
			}
		}
		else
		{
				SkillRegistry.getSkill(p.getCurrentEquippedItem().getTagCompound().getInteger("CurSkillId")).attemptToUseSkill(w,p, null,1);
		}
		p.setItemInUse(i, Integer.MAX_VALUE);
		return i;
	}

	public void onUpdate(ItemStack i, World w, Entity e, int p_77663_4_,boolean inhand)
	{
		if (i.getTagCompound() != null)
		{
			if (i.getTagCompound().getInteger("CoolDown") > 0)
			{
				i.getTagCompound().setInteger("CoolDown",i.getTagCompound().getInteger("CoolDown") - 1);
			}
		}
		else
		{
			// 0 for Fire,1 for Earth,2 for Water,3 for Power,4 for Magic,5 for Sky
			tag.setIntArray("Fragments", new int[]
			{ 0, 0, 0, 0, 0, 0 });
			tag.setFloat("Expirience", 0);
			tag.setInteger("Level", 1);
			tag.setFloat("Damage", 4);
			i.setTagCompound(tag);
		}
	}

}
