/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Event;

import RW.Common.Registry.ItemRegistry;
import RW.Common.Skills.SkillRegistry;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.oredict.OreDictionary;

/**
 * @author Lord_Crystalyx
 */
public class RogueEventBus
{
	NBTTagCompound tag = new NBTTagCompound();
	public NBTTagList tlist = new NBTTagList();
	float Damage = 0.0F;

	@SubscribeEvent
	public void AttackEntityEvent(AttackEntityEvent event)
	{
		if (event.entityPlayer.getCurrentEquippedItem() != null)
		{
			if (event.entityPlayer.getCurrentEquippedItem().getItem() == ItemRegistry.DSword)
			{
				if (event.entityPlayer.getCurrentEquippedItem().hasTagCompound())
				{
					int D = event.entityPlayer.getCurrentEquippedItem().getTagCompound().getInteger("Damage");
					int E = event.entityPlayer.getCurrentEquippedItem().getTagCompound().getInteger("Energy");
					if (D <= E)
					{
						if (event.target instanceof EntityLiving)
						{
							EntityLiving ent;
							ent = (EntityLiving) event.target;
							float xp = ent.getMaxHealth() + event.entityPlayer.getCurrentEquippedItem().getTagCompound().getFloat("Expirience");

							if (ent.getHealth() - event.entityPlayer.getCurrentEquippedItem().getTagCompound().getFloat("Damage") <= 0)
							{
								event.entityPlayer.getCurrentEquippedItem().getTagCompound().setFloat("Expirience", xp);
							}
							else
								event.entityPlayer.getCurrentEquippedItem().getTagCompound().setFloat("Expirience", event.entityPlayer.getCurrentEquippedItem().getTagCompound().getFloat("Expirience"));
						}
						for (int i = 0; i < 11; i++)
						{
							if (SkillRegistry.getSkill(i) != null)
							{
								if (event.entityPlayer.getCurrentEquippedItem().getTagCompound().getInteger("Level") >= SkillRegistry.getSkill(i).lev)
								{
									event.entityPlayer.getCurrentEquippedItem().getTagCompound().setBoolean("Skill" + i, true);
								}
								else
								{
									event.entityPlayer.getCurrentEquippedItem().getTagCompound().setBoolean("Skill" + i, false);
								}
							}
							else
							{
								event.entityPlayer.getCurrentEquippedItem().getTagCompound().setBoolean("Skill" + i, false);

							}
						}

						float xpneed = event.entityPlayer.getCurrentEquippedItem().getTagCompound().getInteger("Level") * 20;
						if (event.entityPlayer.getCurrentEquippedItem().getTagCompound().getFloat("Expirience") > xpneed)
						{
							event.entityPlayer.getCurrentEquippedItem().getTagCompound().setFloat("Expirience", event.entityPlayer.getCurrentEquippedItem().getTagCompound().getFloat("Expirience") - xpneed);
							event.entityPlayer.getCurrentEquippedItem().getTagCompound().setInteger("Level", event.entityPlayer.getCurrentEquippedItem().getTagCompound().getInteger("Level") + 1);
						}
						float dam = 4 + (event.entityPlayer.getCurrentEquippedItem().getTagCompound().getInteger("Level") - 1) / 2;
						event.entityPlayer.getCurrentEquippedItem().getTagCompound().setFloat("Damage", dam);
						SkillRegistry.getSkill(event.entityPlayer.getCurrentEquippedItem().getTagCompound().getInteger("CurSkillId")).attemptToUseSkill(event.entityPlayer.worldObj, event.entityPlayer, event.target, 0);
						event.entityPlayer.getCurrentEquippedItem().getTagCompound().setInteger("Energy", E - D);

					}
					else
					{
					}
				}
			}
		}
	}

	public float sqr(float in)
	{
		return in * in;
	}

	@SubscribeEvent
	public void SimpleBlockRegisterEvent(SimpleBlockRegisterEvent event)
	{
		event.block.setBlockBounds(0.0625F, 0.0625F, 0.0625F, 1.0F - 0.0625F, 1.0F - 0.0625F, 1.0F - 0.0625F);
	}

	@SubscribeEvent
	public void ItemTooltipEvent(ItemTooltipEvent event)
	{
		if (event.itemStack != null)
		{
			int[] ids = OreDictionary.getOreIDs(event.itemStack);
			for (int l = 0; l < ids.length; l++)
			{
				event.toolTip.add("" + OreDictionary.getOreName(ids[l]));
			}
			event.toolTip.add(event.itemStack.getItem().getUnlocalizedName());
			event.toolTip.add("" + event.itemStack.getItem().getIdFromItem(event.itemStack.getItem()) + ":" + event.itemStack.getItemDamage());
		}
	}

	@SubscribeEvent
	public void PickaxeBreakEvent(HarvestDropsEvent event)
	{
		if (event.harvester != null)
		{
			if (event.harvester.getCurrentEquippedItem() != null)
			{
				if (event.harvester.getCurrentEquippedItem().getTagCompound() != null)
				{
					if (event.harvester.getCurrentEquippedItem().getItem() == ItemRegistry.DPick)
					{
						int D = event.harvester.getCurrentEquippedItem().getTagCompound().getInteger("Level");
						int E = event.harvester.getCurrentEquippedItem().getTagCompound().getInteger("Energy");
						if (D <= E)
						{
							if (event.block.getMaterial() == Material.anvil || event.block.getMaterial() == Material.coral || event.block.getMaterial() == Material.glass || event.block.getMaterial() == Material.ice
									|| event.block.getMaterial() == Material.iron || event.block.getMaterial() == Material.packedIce || event.block.getMaterial() == Material.piston || event.block.getMaterial() == Material.rock
									|| event.block.getMaterial() == Material.clay || event.block.getMaterial() == Material.craftedSnow || event.block.getMaterial() == Material.grass || event.block.getMaterial() == Material.ground
									|| event.block.getMaterial() == Material.sand || event.block.getMaterial() == Material.snow)
							{
								if (event.harvester.getCurrentEquippedItem().getTagCompound() != null)
								{
									float xp = ((event.block.getExpDrop(event.world, event.blockMetadata, 1) + event.block.getBlockHardness(event.world, event.x, event.y, event.z) * 2) / 2
											+ event.harvester.getCurrentEquippedItem().getTagCompound().getFloat("Exp"));
									event.harvester.getCurrentEquippedItem().getTagCompound().setFloat("Exp", xp);
								}
								else
								{
									float xp = ((event.block.getExpDrop(event.world, event.blockMetadata, 1) + event.block.getBlockHardness(event.world, event.x, event.y, event.z) * 2) / 2);
									event.harvester.getCurrentEquippedItem().getTagCompound().setFloat("Exp", xp);
								}
								float xpneed = event.harvester.getCurrentEquippedItem().getTagCompound().getInteger("Level") * 20;
								if (event.harvester.getCurrentEquippedItem().getTagCompound().getFloat("Expirience") > xpneed)
								{
									event.harvester.getCurrentEquippedItem().getTagCompound().setFloat("Expirience", event.harvester.getCurrentEquippedItem().getTagCompound().getFloat("Expirience") - xpneed);
									event.harvester.getCurrentEquippedItem().getTagCompound().setInteger("Level", event.harvester.getCurrentEquippedItem().getTagCompound().getInteger("Level") + 1);
								}
							}
							float xpneed = event.harvester.getCurrentEquippedItem().getTagCompound().getInteger("Level") * 20;
							if (event.harvester.getCurrentEquippedItem().getTagCompound().getFloat("Exp") > xpneed)
							{
								event.harvester.getCurrentEquippedItem().getTagCompound().setFloat("Exp", event.harvester.getCurrentEquippedItem().getTagCompound().getFloat("Exp") - xpneed);
								event.harvester.getCurrentEquippedItem().getTagCompound().setInteger("Level", event.harvester.getCurrentEquippedItem().getTagCompound().getInteger("Level") + 1);
							}
							event.harvester.getCurrentEquippedItem().getTagCompound().setInteger("Energy", E - D);
						}
						else
						{
						}
					}
				}
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void pickSpeed(PlayerEvent.BreakSpeed event)
	{
		if (event.entityPlayer != null)
		{
			if (event.entityPlayer.getCurrentEquippedItem() != null)
			{
				if (event.entityPlayer.getCurrentEquippedItem().getItem() == ItemRegistry.DPick)
				{
					int D = event.entityPlayer.getCurrentEquippedItem().getTagCompound().getInteger("Level");
					int E = event.entityPlayer.getCurrentEquippedItem().getTagCompound().getInteger("Energy");
					if (D <= E)
					{
						if (event.block.getMaterial() == Material.anvil || event.block.getMaterial() == Material.coral || event.block.getMaterial() == Material.glass || event.block.getMaterial() == Material.ice
								|| event.block.getMaterial() == Material.iron || event.block.getMaterial() == Material.packedIce || event.block.getMaterial() == Material.piston || event.block.getMaterial() == Material.rock
								|| event.block.getMaterial() == Material.clay || event.block.getMaterial() == Material.craftedSnow || event.block.getMaterial() == Material.grass || event.block.getMaterial() == Material.ground
								|| event.block.getMaterial() == Material.sand || event.block.getMaterial() == Material.snow)
						{
							if (event.entityPlayer.getCurrentEquippedItem().hasTagCompound())
							{
								{
									event.newSpeed += event.entityPlayer.getCurrentEquippedItem().getTagCompound().getInteger("Level") * 0.25;
								}
							}
						}
					}
					else
					{
						event.newSpeed = 0;
					}
				}
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void axeSpeed(PlayerEvent.BreakSpeed event)
	{
		if (event.entityPlayer != null)
		{
			if (event.entityPlayer.getCurrentEquippedItem() != null)
			{
				if (event.entityPlayer.getCurrentEquippedItem().getItem() == ItemRegistry.DAxe)
				{

					int D = event.entityPlayer.getCurrentEquippedItem().getTagCompound().getInteger("Level");
					int E = event.entityPlayer.getCurrentEquippedItem().getTagCompound().getInteger("Energy");
					if (D <= E)
					{
						if (event.block.getMaterial() == Material.wood || event.block.getMaterial() == Material.cactus || event.block.getMaterial() == Material.gourd)
						{
							if (event.entityPlayer.getCurrentEquippedItem().hasTagCompound())
							{
								{
									event.newSpeed += event.entityPlayer.getCurrentEquippedItem().getTagCompound().getInteger("Level") * 0.25;
								}
							}
						}

					}
					else
					{
						event.newSpeed = 0;
					}

				}
			}
		}
	}

	@SubscribeEvent
	public void AxeBreakEvent(HarvestDropsEvent event)
	{
		if (event.harvester != null)
		{
			if (event.harvester.getCurrentEquippedItem() != null)
			{
				if (event.harvester.getCurrentEquippedItem().getTagCompound() != null)
				{
					if (event.harvester.getCurrentEquippedItem().getItem() == ItemRegistry.DAxe)
					{
						int D = event.harvester.getCurrentEquippedItem().getTagCompound().getInteger("Level");
						int E = event.harvester.getCurrentEquippedItem().getTagCompound().getInteger("Energy");
						if (D <= E)
						{
							if (event.block.getMaterial() == Material.wood || event.block.getMaterial() == Material.cactus || event.block.getMaterial() == Material.gourd)
							{
								if (event.harvester.getCurrentEquippedItem().getTagCompound() != null)
								{
									float xp = ((event.block.getExpDrop(event.world, event.blockMetadata, 1) + event.block.getBlockHardness(event.world, event.x, event.y, event.z) * 2) / 2
											+ event.harvester.getCurrentEquippedItem().getTagCompound().getFloat("Exp"));
									event.harvester.getCurrentEquippedItem().getTagCompound().setFloat("Exp", xp);
								}
								else
								{
									float xp = ((event.block.getExpDrop(event.world, event.blockMetadata, 1) + event.block.getBlockHardness(event.world, event.x, event.y, event.z) * 2) / 2);
									event.harvester.getCurrentEquippedItem().getTagCompound().setFloat("Exp", xp);
								}
							}
							float xpneed = event.harvester.getCurrentEquippedItem().getTagCompound().getInteger("Level") * 20;
							if (event.harvester.getCurrentEquippedItem().getTagCompound().getFloat("Exp") > xpneed)
							{
								event.harvester.getCurrentEquippedItem().getTagCompound().setFloat("Exp", event.harvester.getCurrentEquippedItem().getTagCompound().getFloat("Exp") - xpneed);
								event.harvester.getCurrentEquippedItem().getTagCompound().setInteger("Level", event.harvester.getCurrentEquippedItem().getTagCompound().getInteger("Level") + 1);
							}
							event.harvester.getCurrentEquippedItem().getTagCompound().setInteger("Energy", E - D);

						}
						else
						{
						}
					}
				}
			}
		}
	}
}
