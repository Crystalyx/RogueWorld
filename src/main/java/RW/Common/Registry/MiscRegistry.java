/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Registry;

import RW.Common.Misc.CreativeTabRogueWorld;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import thaumcraft.api.research.ResearchPage;


public class MiscRegistry
{
	public static MiscRegistry MCore;
	public static CreativeTabs modTab = new CreativeTabRogueWorld("tabRogueWorld");
	public static ToolMaterial darkMat = EnumHelper.addToolMaterial("Dark", 8, 512, 10.0F, 0, 25);
	//public static BiomeGenBase dbiom = new DarkBiome();
	
	public static void register() 
	{
		//DimensionManager.registerProviderType(68, DarkWorldProvider.class, false);
	}


}
