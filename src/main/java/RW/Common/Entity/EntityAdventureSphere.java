/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Entity;

import java.util.UUID;

import RW.Common.Misc.WorldPos;
import RW.Core.RogueWorldCore;
import RW.Utils.Logger;
import RW.Utils.MathUtils;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.AnimalChest;
import net.minecraft.inventory.IInvBasic;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * @author Lord_Crystalyx
 */
public class EntityAdventureSphere extends EntityLiving implements IInvBasic
{
	public World world;
	public EntityPlayer p;
	public AnimalChest inv = new AnimalChest("rw.advsph", 54);

	public EntityAdventureSphere(World w, EntityPlayer p)
	{
		super(w);
		this.worldObj = w;
		this.p = p;
		this.setSize(0.9F, 0.9F);
		// this.getNavigator().setAvoidsWater(true);
		// this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.setInvisible(false);
		this.onEntityUpdate();
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate()
	{
		super.onUpdate();
		// if (this.p == null)
		// this.setDead();
		WorldPos ppos = WorldPos.getWorldPos(this.p);
		if (ppos != null)
		{
			WorldPos pos = new WorldPos(this);
			WorldPos tgpos = MathUtils.getPosByAngle(ppos, 1F, this.p.rotationYaw+180, 1);
			WorldPos vec = tgpos.substruct(pos);
			this.setVelocity(vec.getX(), vec.getY(), vec.getZ());
			this.setRotation(this.p.cameraYaw, this.p.cameraPitch);
		}
		if (this.p == null)
		{
			this.p = this.worldObj.getClosestPlayerToEntity(this, 10);
		}
	}

	/**
	 * Gets called every tick from main Entity class
	 */
	public void onEntityUpdate()
	{
		super.onEntityUpdate();
		// this.onUpdate();
	}

	/**
	 * Called frequently so the entity can update its state every tick as
	 * required. For example, zombies and skeletons use this to react to
	 * sunlight and start to burn.
	 */
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		// this.onUpdate();
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		if (this.p != null)
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(this.p.getAIMoveSpeed());
	}

	@Override
	protected void updateAITasks()
	{
		super.updateAITasks();
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setString("ThePlayer", this.p.getUniqueID().toString());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound tag)
	{
		super.readEntityFromNBT(tag);
		String s = tag.getString("ThePlayer");
		if (s != null)
		{
			UUID uuid = UUID.fromString(s);
			this.p = this.worldObj.func_152378_a(uuid);
		}
	}

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow,
	 * gets into the saddle on a pig.
	 */
	public boolean interact(EntityPlayer p)
	{
		Logger.info("Меня кликнули");
		p.openGui(RogueWorldCore.getCore(), 7, p.worldObj, (int) p.posX, (int) p.posY, (int) p.posZ);
		return true;
	}

	/**
	 * Determines if an entity can be despawned, used on idle far away entities
	 */
	protected boolean canDespawn()
	{
		return true;
	}

	@Override
	public void onInventoryChanged(InventoryBasic p_76316_1_)
	{

	}
}
