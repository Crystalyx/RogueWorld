/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Registry;

import RW.Common.Items.AltersteelIngot;
import RW.Common.Items.AltersteelRodItem;
import RW.Common.Items.Armors;
import RW.Common.Items.AtrilliumCapItem;
import RW.Common.Items.AtrilliumIngot;
import RW.Common.Items.BoundSphere;
import RW.Common.Items.DAxe;
import RW.Common.Items.DCryst;
import RW.Common.Items.DPick;
import RW.Common.Items.DUCrystal;
import RW.Common.Items.DarkSword;
import RW.Common.Items.ElementalShard;
import RW.Common.Items.GoldWrench;
import RW.Common.Items.LinkingRod;
import RW.Common.Items.MetaItem;
import RW.Common.Items.RiteBook;
import RW.Common.Items.SoothingTea;
import RW.Utils.MiscUtils;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public class ItemRegistry
{
	public static ItemRegistry ICore;

	protected static String[] textures = new String[0];
	protected static String[] names = new String[0];

	public static Item RiteBook = new RiteBook();
	public static Item DSword = new DarkSword();
	public static Item DPick = new DPick();
	public static Item DAxe = new DAxe();
	public static Item STea = new SoothingTea();
	public static Item DCryst = new DCryst();
	public static Item AtrilliumI = new AtrilliumIngot();
	public static Item AltersteelI = new AltersteelIngot();
	public static Item EShard = new ElementalShard();
	public static Item ARod = new AltersteelRodItem();
	public static Item ACap = new AtrilliumCapItem();
	public static Item[] AtrArmor = new Armors[4];
	public static Item[] AtrArmorAwak = new Armors[4];
	public static Item[] AltArmor = new Armors[4];
	public static Item[] AltArmorAwak = new Armors[4];
	public static Item linkingRod = new LinkingRod();
	public static Item boundsph = new BoundSphere();
	public static Item battery = new DUCrystal();
	public static Item wrench = new GoldWrench();

	public static MetaItem metai;

	public static void register()
	{
		GameRegistry.registerItem(RiteBook, "rogueWorldRiteBook");
		GameRegistry.registerItem(DSword, "rogueWorldDarkSword");
		GameRegistry.registerItem(DPick, "rogueWorldDarkPick");
		GameRegistry.registerItem(DAxe, "rogueWorldDarkAxe");
		GameRegistry.registerItem(STea, "rogueWorldSTea");
		GameRegistry.registerItem(DCryst, DCryst.getUnlocalizedName());
		GameRegistry.registerItem(AtrilliumI, "rogueWorldAtrilliumIngot");
		GameRegistry.registerItem(AltersteelI, "rogueWorldAltersteelIngot");
		GameRegistry.registerItem(EShard, "rogueWorldElementalScale");
		GameRegistry.registerItem(ARod, ARod.getUnlocalizedName());
		GameRegistry.registerItem(ACap, ACap.getUnlocalizedName());
		GameRegistry.registerItem(linkingRod, linkingRod.getUnlocalizedName());
		GameRegistry.registerItem(battery, battery.getUnlocalizedName());
		GameRegistry.registerItem(wrench, wrench.getUnlocalizedName());

		if (Loader.isModLoaded("DummyCore"))
			GameRegistry.registerItem(boundsph, boundsph.getUnlocalizedName());

		for (int l = 0; l < 4; l++)
		{
			AtrArmor[l] = new Armors(0, l, "armors_atrillium", "atrillium", EnumHelper.addArmorMaterial("Atrillium", 750, new int[] { 4, 12, 9, 4 }, 25));
			GameRegistry.registerItem(AtrArmor[l], AtrArmor[l].getUnlocalizedName());
		}
		for (int l = 0; l < 4; l++)
		{
			AtrArmorAwak[l] = new Armors(1, l, "armors_awak_atrillium", "atrillium_awak", EnumHelper.addArmorMaterial("Atrillium_Awak", 1500, new int[] { 8, 24, 18, 8 }, 25));
			GameRegistry.registerItem(AtrArmorAwak[l], AtrArmorAwak[l].getUnlocalizedName());
		}
		for (int l = 0; l < 4; l++)
		{
			AltArmor[l] = new Armors(2, l, "armor_altersteel", "altersteel", EnumHelper.addArmorMaterial("AlterSteel", 2250, new int[] { 12, 18, 13, 6 }, 35));
			GameRegistry.registerItem(AltArmor[l], AltArmor[l].getUnlocalizedName());
		}
		for (int l = 0; l < 4; l++)
		{
			AltArmorAwak[l] = new Armors(3, l, "armor_awak_altersteel", "altersteel_awak", EnumHelper.addArmorMaterial("AlterSteel_Awak", 4500, new int[] { 24, 36, 26, 12 }, 75));
			GameRegistry.registerItem(AltArmorAwak[l], AltArmorAwak[l].getUnlocalizedName());
		}

		registerMetaItem("range_upgrade", "RogueWorldRangeUpg");
		registerMetaItem("speed_upgrade", "RogueWorldSpeedUpg");
		registerMetaItem("generator_output_upg", "generator_output_upg");
		registerMetaItem("generator_speed_upg", "generator_speed_upg");
		registerMetaItem("altersteel_core_fragment", "altersteel_core_fragment");
		registerMetaItem("atrilium_core_fragment", "atrilium_core_fragment");
		registerMetaItem("dark_core_fragment", "dark_core_fragment");
		registerMetaItem("iron_plate", "iron_plate");
		registerMetaItem("magical_core", "magical_core");
		registerMetaItem("dark_crystal_awak", "dark_crystal_awak");
		registerMetaItem("infernite", "infernite");
		registerMetaItem("tenebrite", "tenebrite");
		registerMetaItem("salisinfernus", "salisinfernus");
		registerMetaItem("salistenebris", "salistenebris");

		metai = new MetaItem(textures, names);
		GameRegistry.registerItem(metai, metai.getUnlocalizedName());
	}

	public static int getFirstNotOccupiedSlotFor(Object[] array)
	{
		for (int i = 0; i < array.length; i++)
		{
			if (array[i] == null)
			{
				return i;
			}
		}
		return -1;
	}

	public static void registerMetaItem(String text, String name)
	{
		names = MiscUtils.expandArray(names, 1);
		textures = MiscUtils.expandArray(textures, 1);
		names[getFirstNotOccupiedSlotFor(names)] = name;
		textures[getFirstNotOccupiedSlotFor(textures)] = text;
	}
}
