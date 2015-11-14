package RW.Api;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author Lord_Crystalyx
 */
public interface IRing {
	
	public abstract void onUpdate(ItemStack i, World w, Entity e);
	
	public abstract String getSpeedModifierName(ItemStack i);
	
	public abstract float getSpeedModifier(ItemStack i);
	
	public abstract float setDamage(ItemStack i,EntityPlayer p, RogueData data);
	
	public abstract float setFallDistance(ItemStack i,EntityPlayer p, RogueData data);
	
	public abstract void setJump(ItemStack i,EntityPlayer p, RogueData data);
	
	public abstract void onItemUpdate(ItemStack i,EntityPlayer p);
	
	public abstract float setDamageOnAttack(ItemStack i,EntityPlayer p,EntityLivingBase entl, RogueData data);

	public abstract float getKnockbackModifierValue(ItemStack i);
	
	public abstract boolean performJump(ItemStack i,EntityPlayer p);
	
	public abstract boolean holdJump(ItemStack i,EntityPlayer p);
}
