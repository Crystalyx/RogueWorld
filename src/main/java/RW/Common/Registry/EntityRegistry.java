/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Registry;

import RW.Common.Entity.EntityFireSpark;
import RW.Common.Entity.EntityTest;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;

public class EntityRegistry
{
	public static EntityRegistry ECore;
	public void register()
	{
		 EntityList.addMapping(EntityTest.class, "Test", 256, 0x0033FF, 0x00CCFF);
		 EntityList.addMapping(EntityFireSpark.class, "Spark", 255);
	}
}
