/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Skills;

import java.util.List;
import java.util.Random;

import RW.Common.Entity.EntityFireSpark;
import RW.Common.Misc.WorldPos;
import RW.Core.RogueWorldCore;
import RW.Utils.MathUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * @author Lord_Crystalyx
 */
public class SkillFireTrail extends Skill
{
	public SkillFireTrail(float Sdamage, float Saccuracy, int Srange)
	{
		super(2, Sdamage, Saccuracy, Srange, "FireTrail", 0, 1);
	}

	@Override
	public Skill useSkillfor1(World w, EntityPlayer p, Entity ent)
	{
		if (this.getType() == 1)
		{
			if (p.getCurrentEquippedItem().getTagCompound().getInteger("CoolDown") <= 0)
			{
				WorldPos tg = MathUtils.getPosByAngle(new WorldPos(p), this.range, p.rotationYawHead, p.rotationPitch);

				double radius = this.range - 1;
				List<EntityLiving> ents = p.worldObj.getEntitiesWithinAABB(EntityLiving.class,
						AxisAlignedBB.getBoundingBox(tg.getX() - radius, tg.getY() - radius, tg.getZ() - radius, tg.getX() + radius, tg.getY() + radius, tg.getZ() + radius));
				for (EntityLiving entl : ents)
				{
					entl.attackEntityFrom(DamageSource.generic.setDamageBypassesArmor(), (float) Math.sqrt(p.getCurrentEquippedItem().getTagCompound().getFloat("Damage") * 100));
					p.getCurrentEquippedItem().setItemDamage(p.getCurrentEquippedItem().getItemDamage() - 1);
				}

				EntityFireSpark sprk = new EntityFireSpark(w, p, p.posX, p.posY, p.posZ);
				sprk.posY += 1.5;
				w.spawnEntityInWorld(sprk);
//
//				for (int i = 0; i < 100; i++)
//					spawnFire(w, w.rand, p);

				// p.getCurrentEquippedItem().getTagCompound().setInteger("CoolDown",200);
			}
		}
		else
		{
			SkillRegistry.Attack.attemptToUseSkill(w, p, ent, 0);
		}
		return this;
	}

	private double motionX;
	private double motionZ;
	private double motionY;
	double rx;
	double ry;
	double rz;

	public void spawnFire(World w, Random r, EntityPlayer p)
	{

		EntityFireSpark sprk = new EntityFireSpark(w, p, 0, 0, 0);
		MathUtils.getMotionsByEntity(1, sprk);
		RogueWorldCore.proxy.spawnParticle("spark", p.posX + MathUtils.getIntInRange(5), p.posY + MathUtils.getIntInRange(5), p.posZ + MathUtils.getIntInRange(5), MathUtils.getIntInRange(10), +MathUtils.getIntInRange(10),
				+MathUtils.getIntInRange(10));

	}

}
