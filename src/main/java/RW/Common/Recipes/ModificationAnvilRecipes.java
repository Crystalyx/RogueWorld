package RW.Common.Recipes;

import java.util.Hashtable;

import RW.Common.Registry.ItemRegistry;
import RW.Common.Tile.TileEntityModificationAnvil;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * @author Lord_Crystalyx
 */
public class ModificationAnvilRecipes
{
	public static Hashtable<Integer, ModificationAnvilRecipe> rlist = new Hashtable<Integer, ModificationAnvilRecipe>();
	public static int id = 0;

	public static void registerRecipes()
	{
		registerRecipe(new ItemStack[] { new ItemStack(Items.diamond_sword), new ItemStack(ItemRegistry.DCryst), new ItemStack(ItemRegistry.DCryst), new ItemStack(ItemRegistry.DCryst), new ItemStack(ItemRegistry.DCryst),
				new ItemStack(ItemRegistry.DCryst), new ItemStack(ItemRegistry.DCryst) }, new ItemStack(ItemRegistry.DSword), 10000);
		registerRecipe(new ItemStack[] { new ItemStack(Items.diamond_axe), new ItemStack(ItemRegistry.DCryst), new ItemStack(ItemRegistry.DCryst), new ItemStack(ItemRegistry.DCryst), new ItemStack(ItemRegistry.DCryst),
				new ItemStack(ItemRegistry.DCryst), new ItemStack(ItemRegistry.DCryst) }, new ItemStack(ItemRegistry.DAxe), 10000);
		registerRecipe(new ItemStack[] { new ItemStack(Items.diamond_pickaxe), new ItemStack(ItemRegistry.DCryst), new ItemStack(ItemRegistry.DCryst), new ItemStack(ItemRegistry.DCryst), new ItemStack(ItemRegistry.DCryst),
				new ItemStack(ItemRegistry.DCryst), new ItemStack(ItemRegistry.DCryst) }, new ItemStack(ItemRegistry.DPick), 10000);

	}

	public static void registerRecipe(ModificationAnvilRecipe rec)
	{
		rlist.put(id, rec);
		id++;
	}

	public static void registerRecipe(ItemStack[] ing, ItemStack out, int DUReq)
	{
		registerRecipe(new ModificationAnvilRecipe(ing, out, DUReq));
	}

	public static ModificationAnvilRecipe findRecipeFor(TileEntityModificationAnvil ing)
	{
		for (int l = 0; l < rlist.size(); l++)
		{
			ModificationAnvilRecipe ites = rlist.get(l);
			if (ites.matches((IInventory) ing, ing.getWorldObj()))
			{
				return rlist.get(l);
			}
		}
		return null;
	}
}
