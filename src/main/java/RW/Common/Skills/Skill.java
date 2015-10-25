/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Skills;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class Skill 
{
	/**
	 * @param
	 * Sid : Id of skill
	 * @param
	 * Sdamage : Damage of attack with skill
	 * @param
	 * Saccuracy : Chance to successfully attack entity
	 * @param
	 * Srange : Range for range attacks; 0 for normal attack
	 * @param
	 * Sname : Name of Skill that used in description
	 * @param
	 * Slev : Level needed to get this Skill
	 * @param
	 * Type : Type of Skill. 0 - Normal attack;1 - for Right Mouse Button;2 - for Using;
	 **/
	public Skill(int Sid, float Sdamage,float Saccuracy,int Srange,String Sname,int Slev,int Type)
	{
		this.id = Sid;
		this.damage = Sdamage;
		this.accuracy = Saccuracy;
		this.range = Srange;
		this.name = Sname;
		this.lev = Slev;
		this.type = Type;
	}
	public int id;
	public float damage;
	public float accuracy;
	public int range;
	public String name;
	public int lev;
	private int type;
	
	public Skill setSkillDamage(float dam)
	{
		this.damage = dam;
		return this;
	}
	
	public Skill setSkillAccuracy(float acc)
	{
		this.accuracy = acc;
		return this;
	}
	
	public Skill setSkillRange(int ran)
	{
		this.range = ran;
		return this;
	}
	
	public Skill setSkillName(String na)
	{
		this.name = na;
		return this;
	}
	
	public Skill setType(int Type)
	{
		this.type = Type;
		return this;
	}
	
	public int getType()
	{
		return this.type;
	}
	
	public Skill attemptToUseSkill(World w , EntityPlayer p,Entity ent,int type)
	{
		if(type == 0)
			useSkillfor0(w, p, ent);
		if(type == 1)
			this.useSkillfor1(w, p, ent);
		if(type == 2)
			this.useSkillfor2(w, p, ent);
		return this;		
	}
	
	public Skill useSkillfor2(World w, EntityPlayer p, Entity ent)
	{
		return this;		
	}

	public Skill useSkillfor1(World w, EntityPlayer p, Entity ent)
	{
		return this;		
	}

	public Skill useSkillfor0(World w , EntityPlayer p,Entity ent)
	{
		SkillRegistry.Attack.attemptToUseSkill(w, p, ent,0);
		return this;		
	}
}
