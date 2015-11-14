/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Registry;

import java.util.ArrayList;
import java.util.List;

import RW.Common.Recipes.ModificationAnvilRecipes;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * @author Lord_Crystalyx
 */
public class RecipeRegistry
{
	public static RecipeRegistry RCore;
	public static List rec = new ArrayList();

	public static void register()
	{
		ModificationAnvilRecipes.registerRecipes();

		ShapedOreRecipe abas = new ShapedOreRecipe(BlockRegistry.altar_base, new Object[] { "@@@", "@#@", "@@@", '@', "ingotIroEn", '#', Blocks.iron_block });
		GameRegistry.addRecipe(abas);
		rec.add(abas);

		ShapedOreRecipe manv = new ShapedOreRecipe(BlockRegistry.ModAnvil, new Object[] { "roy", "aga", "lbp", 'a', BlockRegistry.altar_base, 'r', new ItemStack(Blocks.wool, 1, 14), 'o', new ItemStack(Blocks.wool, 1, 1), 'y',
				new ItemStack(Blocks.wool, 1, 4), 'g', new ItemStack(Blocks.wool, 1, 5), 'l', new ItemStack(Blocks.wool, 1, 3), 'b', new ItemStack(Blocks.wool, 1, 11), 'p', new ItemStack(Blocks.wool, 1, 10) });
		GameRegistry.addRecipe(manv);
		rec.add(manv);

		ShapelessOreRecipe planks = new ShapelessOreRecipe(new ItemStack(BlockRegistry.Dplanks, 4), new Object[] { BlockRegistry.SWood });
		GameRegistry.addRecipe(planks);
		rec.add(planks);

		ShapelessOreRecipe crys = new ShapelessOreRecipe(new ItemStack(ItemRegistry.DCryst, 4), new Object[] { Items.diamond, Items.flint });
		GameRegistry.addRecipe(crys);
		rec.add(crys);

		ShapedOreRecipe sword = new ShapedOreRecipe(ItemRegistry.DSword, new Object[] { " c ", " c ", " s ", 'c', ItemRegistry.DCryst, 's', Items.stick });
		GameRegistry.addRecipe(sword);
		rec.add(sword);
	}
}
