/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Registry;

import RW.Common.Entity.EntityFireSpark;
import RW.Common.Entity.EntityAdventureSphere;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;

/**
 * @author Lord_Crystalyx
 */
public class EntityRegistry
{
	public static EntityRegistry ECore;

	public void register()
	{
		EntityList.addMapping(EntityAdventureSphere.class, "rw.adventureSphere", 256, 0x4C4746, 0x5B1C18);
		EntityList.addMapping(EntityFireSpark.class, "Spark", 255);
	}
}
