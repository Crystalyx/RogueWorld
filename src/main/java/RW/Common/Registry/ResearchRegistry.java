/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Registry;

import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

public class ResearchRegistry
{
	public static ResearchItem atrillium = getResearch("RW.Atrillium", "RW.Category", -2, -2, new AspectList(), 2, new ItemStack(ItemRegistry.AtrilliumI), crtPage("RW.Atrillium.Descr"));
	public static ResearchItem altersteel = getResearch("RW.Altersteel", "RW.Category", 2, -2, new AspectList(), 2, new ItemStack(ItemRegistry.AltersteelI), crtPage("RW.Altersteel.Descr"));
	public static ResearchItem crystald = getResearch("RW.Crystald", "RW.Category", 0, 0, new AspectList(), 2, new ItemStack(ItemRegistry.DCryst), crtPage("RW.Crystald.Descr"));
	public static ResearchItem ARod = getResearch("ROD_Altersteel", "RW.Category", 1, -4, new AspectList().add(Aspect.TOOL,1).add(Aspect.AURA,1), 2, new ItemStack(ItemRegistry.ARod), crtPage("ROD_Altersteel.Descr"));
	public static ResearchItem ACap = getResearch("CAP_Atrillium", "RW.Category", -1, -4, new AspectList().add(Aspect.TOOL,1).add(Aspect.AURA,1), 2, new ItemStack(ItemRegistry.ACap), crtPage("CAP_Atrillium.Descr"));
	
	public static void register()
	{
		tuneResearches();
		ResearchCategories.registerCategory("RW.Category", new ResourceLocation("rogueWorld:textures/items/dark_crystal.png"), new ResourceLocation("rogueWorld:textures/misc/space.png"));
		ResearchCategories.addResearch(atrillium);
		ResearchCategories.addResearch(altersteel);
		ResearchCategories.addResearch(crystald);
		ResearchCategories.addResearch(ARod);
		ResearchCategories.addResearch(ACap);
	}
	
	public static void tuneResearches()
	{
		atrillium.setRound();
		atrillium.setAutoUnlock();
		altersteel.setRound();
		altersteel.setAutoUnlock();
		crystald.setRound();
		crystald.setAutoUnlock();
	}
	
	public static ResearchPage crtPage(Object obj)
	{
		if(obj instanceof String)
		return new ResearchPage((String)obj);
		
		if(obj instanceof IRecipe)
			return new ResearchPage((IRecipe)obj);
		
		if(obj instanceof IRecipe[])
			return new ResearchPage((IRecipe[])obj);	
		
		if(obj instanceof IArcaneRecipe[])
			return new ResearchPage((IArcaneRecipe[])obj);

		if(obj instanceof InfusionRecipe[])
			return new ResearchPage((InfusionRecipe[])obj);
		
		if(obj instanceof List)
			return new ResearchPage((List)obj);
		
		if(obj instanceof IArcaneRecipe)
			return new ResearchPage((IArcaneRecipe)obj);
		
		if(obj instanceof CrucibleRecipe)
			return new ResearchPage((CrucibleRecipe)obj);
		
		if(obj instanceof ItemStack)
			return new ResearchPage((ItemStack)obj);
		
		if(obj instanceof InfusionRecipe)
			return new ResearchPage((InfusionRecipe)obj);
		
		if(obj instanceof InfusionEnchantmentRecipe)
			return new ResearchPage((InfusionEnchantmentRecipe)obj);
		return null;	
	}
	
	public List<ResearchItem> researches;
	public static int id;
	/**
	 * 
	 * @param tag
	 * @param category
	 * @param x
	 * @param y
	 * @param list
	 * @param complex
	 * @param icon
	 * @param param
	 * @param pages - Boolean[9]
	 */
	public static ResearchItem getResearch(String tag,String category,int x,int y,AspectList list,int complex,ResourceLocation icon,ResearchPage ... pages)
	{
		ResearchItem research = new ResearchItem(tag,category,list,x,y,complex,icon);
		research.setPages(pages);
		return research;
		
	}
	public static ResearchItem getResearch(String tag,String category,int x,int y,AspectList list,int complex,ItemStack icon,ResearchPage ... pages)
	{
		ResearchItem research = new ResearchItem(tag,category,list,x,y,complex,icon);
		research.setPages(pages);
		
		return research;
	}
}
