/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Registry;

import RW.Common.Integration.TestUpd;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.IWandRodOnUpdate;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;

public class IntegrationRegistry
{

	public static WandRod AltersteelRod;
	public static WandCap AtrilliumCap;
	//public static Aspect Obscr;

	public static IWandRodOnUpdate testUpd = new TestUpd();
	public static ResourceLocation rod = new ResourceLocation("rogueworld:textures/misc/wand_rod_threesteel.png");
	
	public static void register()
	{
		AtrilliumCap = new WandCap("Atrillium", -40, new ItemStack(ItemRegistry.ACap), 8);
		AtrilliumCap.setTexture(new ResourceLocation("rogueworld:textures/misc/wand_cap_atrillium.png"));
		AltersteelRod = new WandRod("Altersteel", 750, new ItemStack(ItemRegistry.ARod), 16, testUpd, rod);
		AltersteelRod.setGlowing(true);
		//Obscr = new AspectObscurus();
	}
}
