package RW.Utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class RogueData
{
	public boolean playerIsSeeker = false;
	public int patronage=0;
	public EntityPlayer player;
	
	public RogueData(EntityPlayer player)
	{
		this.player=player;
	}
	
	public void setSeeker(boolean set)
	{
		this.playerIsSeeker=set;
	}
	
	public void addPatronage(int count)
	{
		this.patronage+=count;
	}
	
	public void setPatronage(int count)
	{
		this.patronage=count;
	}
	
	public void readFromNBT(NBTTagCompound tag)
	{
		this.playerIsSeeker = tag.getBoolean("Seeker");
		this.patronage=tag.getInteger("Patronage");
	}
	
	public void writeToNBT(NBTTagCompound tag)
	{
		tag.setBoolean("Seeker", this.playerIsSeeker);
		tag.setInteger("Patronage", this.patronage);
	}
}
