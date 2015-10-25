/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Registry;

import RW.Common.Blocks.AGraviterBlock;
import RW.Common.Blocks.AltarBasePillar;
import RW.Common.Blocks.AltersteelBlock;
import RW.Common.Blocks.AtrilliumBlock;
import RW.Common.Blocks.BaseBlock;
import RW.Common.Blocks.BaseOre;
import RW.Common.Blocks.DUStorageBlock;
import RW.Common.Blocks.DarkSapling;
import RW.Common.Blocks.EnergyTowerBlock;
import RW.Common.Blocks.GrassClearer;
import RW.Common.Blocks.GraviterBlock;
import RW.Common.Blocks.MetaBlock;
import RW.Common.Blocks.NexusBlock;
import RW.Common.Blocks.SkyPieceBlock;
import RW.Common.Blocks.TessBlock;
import RW.Common.Blocks.TuberBlock;
import RW.Common.Blocks.VoiderBlock;
import RW.Common.Blocks.Building.AltarBase;
import RW.Common.Blocks.Building.AltarBasedStairs;
import RW.Common.Blocks.Building.DarkLeavesBlock;
import RW.Common.Blocks.Building.DarkPlanks;
import RW.Common.Blocks.Building.DarkWoodBlock;
import RW.Common.Blocks.Building.Dark_Head;
import RW.Common.Blocks.Building.Marker;
import RW.Common.Blocks.Energetic.Affixer;
import RW.Common.Blocks.Energetic.BlockDeconstructor;
import RW.Common.Blocks.Energetic.CreativeDUStorage;
import RW.Common.Blocks.Energetic.DarkExtractor;
import RW.Common.Blocks.Energetic.ModificationAnvil;
import RW.Common.Blocks.Reactor.BlockReactorCore;
import RW.Common.Blocks.Reactor.IOPort;
import RW.Common.Blocks.Reactor.SynchronizerBlock;
import RW.Common.Blocks.UCS.DayCallerModule;
import RW.Common.Blocks.UCS.InhibitorModule;
import RW.Common.Blocks.UCS.NightCallerModule;
import RW.Common.Blocks.UCS.UCSArc;
import RW.Common.Blocks.UCS.UCSCore;
import RW.Common.Items.ItemSkyBlock;
import RW.Core.RogueWorldCore;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BlockRegistry
{
	public static String id = "rw.";

	public static Block[] simpleBlocks = new Block[16];
	public static Block[] ores = new Block[16];

	public static Block SLeaves = new DarkLeavesBlock(net.minecraft.block.material.Material.leaves).setBlockName(id + "dleaves").setHardness(0.5F);
	public static Block SWood = new DarkWoodBlock(net.minecraft.block.material.Material.wood).setBlockName(id + "dwood").setHardness(1.0F);
	public static Block SSapling = new DarkSapling().setBlockName(id + "dsapling");
	public static Block ModAnvil = new ModificationAnvil(net.minecraft.block.material.Material.iron).setBlockName(id + "anvil");
	public static Block DHead = new Dark_Head(net.minecraft.block.material.Material.wood).setBlockName(id + "dhead");
	public static Block altar_base = new AltarBase(net.minecraft.block.material.Material.iron).setHardness(2.0F).setBlockName(id + "abase");
	public static Block altar_base_pil = new AltarBasePillar(net.minecraft.block.material.Material.iron).setHardness(2.0F).setBlockName(id + "pillar");
	public static Block altar_based_stairs = new AltarBasedStairs().setHardness(2.0F).setBlockName(id + "astairs");
	public static Block Dplanks = new DarkPlanks(net.minecraft.block.material.Material.wood).setBlockName(id + "dplank").setHardness(1.0F);
	public static Block DExtractor = new DarkExtractor(Material.iron).setBlockName(id + "extractor");
	public static Block AltersteelBlock = new AltersteelBlock();
	public static Block AtrilliumBlock = new AtrilliumBlock();
	public static Block GClearer = new GrassClearer();
	public static Block rcore = new BlockReactorCore();
	public static Block sinch = new SynchronizerBlock();
	public static Block ETower = new EnergyTowerBlock();
	public static Block CDUStorage = new CreativeDUStorage();
	public static Block deconstr = new BlockDeconstructor();
	public static Block affix = new Affixer();

	public static Block tuber = new TuberBlock();
	public static Block voider = new VoiderBlock();
	public static Block grav = new GraviterBlock();
	public static Block agrav = new AGraviterBlock();
	public static Block tess = new TessBlock();
	public static Block mark = new Marker();
	public static Block nexus = new NexusBlock();
	public static Block storage = new DUStorageBlock();
	public static Block ioport = new IOPort();
	public static Block sky = new SkyPieceBlock();
	public static Block ucscore = new UCSCore();
	public static Block ucsarc = new UCSArc();
	public static Block ncall = new NightCallerModule();
	public static Block dcall = new DayCallerModule();
	public static Block inhib = new InhibitorModule();

	public static MetaBlock metab;

	private static String[] names;
	private static String[] textures;

	public static boolean register()
	{
		GameRegistry.registerBlock(SLeaves, SLeaves.getUnlocalizedName());
		GameRegistry.registerBlock(SWood, SWood.getUnlocalizedName());
		GameRegistry.registerBlock(SSapling, SSapling.getUnlocalizedName());
		GameRegistry.registerBlock(altar_base, altar_base.getUnlocalizedName());
		GameRegistry.registerBlock(altar_based_stairs, altar_based_stairs.getUnlocalizedName());
		GameRegistry.registerBlock(DHead, DHead.getUnlocalizedName());
		GameRegistry.registerBlock(Dplanks, Dplanks.getUnlocalizedName());
		GameRegistry.registerBlock(DExtractor, "rogueWorldDExtr");
		GameRegistry.registerBlock(AltersteelBlock, AltersteelBlock.getUnlocalizedName());
		GameRegistry.registerBlock(AtrilliumBlock, AtrilliumBlock.getUnlocalizedName());
		GameRegistry.registerBlock(GClearer, GClearer.getUnlocalizedName());
		GameRegistry.registerBlock(rcore, rcore.getUnlocalizedName());
		GameRegistry.registerBlock(sinch, sinch.getUnlocalizedName());
		GameRegistry.registerBlock(CDUStorage, CDUStorage.getUnlocalizedName());
		GameRegistry.registerBlock(affix, affix.getUnlocalizedName());
		GameRegistry.registerBlock(tuber, tuber.getUnlocalizedName());
		GameRegistry.registerBlock(voider, voider.getUnlocalizedName());
		GameRegistry.registerBlock(grav, grav.getUnlocalizedName());
		GameRegistry.registerBlock(agrav, agrav.getUnlocalizedName());
		GameRegistry.registerBlock(tess, tess.getUnlocalizedName());
		GameRegistry.registerBlock(mark, mark.getUnlocalizedName());
		GameRegistry.registerBlock(nexus, nexus.getUnlocalizedName());
		GameRegistry.registerBlock(storage, storage.getUnlocalizedName());

		GameRegistry.registerBlock(ModAnvil, ModAnvil.getUnlocalizedName());
		GameRegistry.registerBlock(altar_base_pil, altar_base_pil.getUnlocalizedName());
		GameRegistry.registerBlock(ETower, ETower.getUnlocalizedName());
		GameRegistry.registerBlock(deconstr, deconstr.getUnlocalizedName());
		GameRegistry.registerBlock(ioport, ioport.getUnlocalizedName());
		GameRegistry.registerBlock(sky,ItemSkyBlock.class, sky.getUnlocalizedName());
		GameRegistry.registerBlock(ucscore, ucscore.getUnlocalizedName());
		GameRegistry.registerBlock(ucsarc, ucsarc.getUnlocalizedName());
		GameRegistry.registerBlock(ncall, ncall.getUnlocalizedName());
		GameRegistry.registerBlock(dcall, dcall.getUnlocalizedName());
		GameRegistry.registerBlock(inhib, inhib.getUnlocalizedName());

		registerBlockSimply(Material.iron, "rglass", "reactor/reactorGlass", 1.0F, 1.0F, true, simpleBlocks);
		registerBlockSimply(Material.iron, "rcasing", "reactor/casing", 1.0F, 10.0F, false, simpleBlocks);
		registerBlockSimply(Material.iron, "rtcasing", "reactor/casing_tough", 1.0F, 10.0F, false, simpleBlocks);
		registerBlockSimply(Material.glass, "mframe", "frame", 1.0F, 10.0F, true, simpleBlocks);

		registerOreSimply("oredark", "dark_ore", 0.5F, 0.5F, ores, new ItemStack(ItemRegistry.DCryst, 3, 0), true, 6,false);
		registerOreSimply("oreatrillium", "ore_atrillium", 0.5F, 0.5F, ores,new ItemStack(ItemRegistry.AtrilliumI),false,1,true);

		// metab = new MetaBlock(textures,names);
		// GameRegistry.registerItem(metab, metab.getUnlocalizedName());
		return true;
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

	public static void registerBlockSimply(Material mat, String uName, String texture, float hardness, float resist, float light, boolean alpha, Block[] array)
	{
		int slot = getFirstNotOccupiedSlotFor(array);
		if (slot >= 0)
		{
			Block regB = new BaseBlock(mat, id + uName, texture, hardness, resist, light, RogueWorldCore.core, alpha);
			if (alpha)
				regB.setLightOpacity(0);
			array[slot] = regB;
			GameRegistry.registerBlock(regB, regB.getUnlocalizedName());
		}
	}

	public static void registerBlockSimply(Material mat, String uName, String texture, float hardness, float resist, boolean alpha, Block[] array)
	{
		int slot = getFirstNotOccupiedSlotFor(array);
		if (slot >= 0)
		{
			Block regB = new BaseBlock(mat, id + uName, texture, hardness, resist, RogueWorldCore.core, alpha);
			if (alpha)
				regB.setLightOpacity(0);
			array[slot] = regB;
			GameRegistry.registerBlock(regB, regB.getUnlocalizedName());
		}
	}

	public static void registerOreSimply(String uName, String texture, float hardness, float resist, Block[] array, ItemStack dropped, boolean fortune, int xp, boolean metalore)
	{
		int slot = getFirstNotOccupiedSlotFor(array);
		if (slot >= 0)
		{
			Block regB = new BaseOre(id + uName, texture, hardness, resist, RogueWorldCore.core, dropped, fortune, xp);
			if (metalore)
				regB = new BaseOre(id + uName, texture, hardness, resist, RogueWorldCore.core);
			array[slot] = regB;
			GameRegistry.registerBlock(regB, regB.getUnlocalizedName());
			GameRegistry.addSmelting(Item.getItemFromBlock(regB), dropped, xp);
		}
	}

	public static void registerMetaItem(String text, String name)
	{
		names[getFirstNotOccupiedSlotFor(names)] = name;
		textures[getFirstNotOccupiedSlotFor(textures)] = text;
	}
}
