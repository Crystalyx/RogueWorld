/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Skills;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * @author Lord_Crystalyx
 */
public class SkillStun extends Skill
{
	public SkillStun(float damage, float accuracy, int range)
	{
		super(1, damage, accuracy, range, "Stun", 0, 1);
	}

	public Skill useSkillfor1(World w, EntityPlayer p, Entity ent)
	{
		if (p.getCurrentEquippedItem().getTagCompound().getInteger("CoolDown") <= 0)
		{
			double radius = this.range;
			List<EntityLiving> ents = p.worldObj.getEntitiesWithinAABB(EntityLiving.class,
					AxisAlignedBB.getBoundingBox(p.lastTickPosX - radius, p.lastTickPosY - radius, p.lastTickPosZ - radius, p.lastTickPosX + radius, p.lastTickPosY + radius, p.lastTickPosZ + radius));
			for (EntityLiving entl : ents)
			{
				entl.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 20));
				entl.attackEntityFrom(DamageSource.generic.setDamageBypassesArmor(), (float) Math.sqrt(p.getCurrentEquippedItem().getTagCompound().getFloat("Damage") * 100));
				p.getCurrentEquippedItem().setItemDamage(p.getCurrentEquippedItem().getItemDamage() - 1);
			}
			p.getCurrentEquippedItem().getTagCompound().setInteger("CoolDown", 200);
		}
		return this;
	}

}
