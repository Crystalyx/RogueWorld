/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Skills;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

/**
 * @author Lord_Crystalyx
 */
public class SkillAttack extends Skill
{

	public SkillAttack(float Sdamage, float Saccuracy, int Srange)
	{
		super(0, Sdamage, Saccuracy, Srange, "Attack", 0, 0);
	}

	@Override
	public Skill useSkillfor0(World w, EntityPlayer p, Entity ent)
	{
		if (ent instanceof EntityLiving)
		{
			ent.attackEntityFrom(DamageSource.generic.setDamageBypassesArmor(), p.getCurrentEquippedItem().getTagCompound().getFloat("Damage"));
			p.getCurrentEquippedItem().setItemDamage(p.getCurrentEquippedItem().getItemDamage() - 1);
		}
		if (ent instanceof EntityDragonPart)
		{
			((EntityDragonPart) ent).entityDragonObj.attackEntityFromPart((EntityDragonPart) ent, DamageSource.generic.setDamageBypassesArmor(), p.getCurrentEquippedItem().getTagCompound().getFloat("Damage"));
		}
		return this;
	}

}
