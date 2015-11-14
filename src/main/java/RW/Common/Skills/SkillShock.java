package RW.Common.Skills;

import java.util.List;

import RW.Common.Misc.WorldPos;
import RW.Utils.MathUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import thaumcraft.client.fx.bolt.FXLightningBolt;

/**
 * @author Lord_Crystalyx
 */
public class SkillShock extends Skill
{

	public SkillShock(float Sdamage, float Saccuracy, int Srange)
	{
		super(4, Sdamage, Saccuracy, Srange, "SkillShock", 0, 2);
	}

	public Skill useSkillfor2(World w, EntityPlayer p, Entity ent)
	{
		WorldPos target = MathUtils.getPosByAngle(new WorldPos(p), this.range - 1, (int) p.rotationYaw + 90, (int) p.rotationPitch);
		double radius = this.range - 1;
		List<EntityLiving> ents = p.worldObj.getEntitiesWithinAABB(EntityLiving.class,
				AxisAlignedBB.getBoundingBox(target.getX() - radius, target.getY() - radius, target.getZ() - radius, target.getX() + radius, target.getY() + radius, target.getZ() + radius));
		for (EntityLiving entl : ents)
		{
			//entl.attackEntityFrom(DamageSource.generic.setMagicDamage(), this.damage);
			p.getCurrentEquippedItem().setItemDamage(p.getCurrentEquippedItem().getItemDamage() - 1);
		}
		if(w.isRemote)
		{
			FXLightningBolt bolt = new FXLightningBolt(p.worldObj, p.posX, p.posY + p.eyeHeight + 1, p.posZ, target.getX(), target.getY(), target.getZ(), w.rand.nextLong(), 1);
			bolt.defaultFractal();
			
			bolt.setType(2);
			bolt.setWidth(0.125F);
			bolt.finalizeBolt();
		}
		return this;
	}
}
