/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Skills;

/**
 * @author Lord_Crystalyx
 */
public class SkillRegistry
{
	public static SkillRegistry SCore;

	public static Skill Attack = new SkillAttack(2.0F, 1.0F, 0);
	public static Skill Stun = new SkillStun(2.0F, 0.75F, 3);
	public static Skill FireTrail = new SkillFireTrail(0, 0, 5);
	public static Skill SkillShock = new SkillShock(4.0F, 0.75F, 7);

	public static Skill[] list = new Skill[11];

	public static Skill getSkill(int id)
	{
		if (id <= 10 && id >= 0)
		{
			return list[id];
		}
		else
			return null;
	}

	public static void register()
	{
		list[0] = Attack;
		list[1] = Stun;
		list[2] = FireTrail;
		list[3] = SkillShock;

	}
}
