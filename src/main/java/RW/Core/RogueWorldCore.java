/**
 * This Class Created By Lord_Crystalyx.
 * Special Credits for Modbder that helped me to start Modding by his Modding Streams and his Open Source Mods.
 */

package RW.Core;

import java.io.File;
import java.io.IOException;

import RW.Common.Book.PageRegistry;
import RW.Common.Command.CommandSetDamage;
import RW.Common.Command.CommandSetLevel;
import RW.Common.Event.AffixerEventHandler;
import RW.Common.Event.RogueEventBus;
import RW.Common.Registry.BlockRegistry;
import RW.Common.Registry.EntityRegistry;
import RW.Common.Registry.IntegrationRegistry;
import RW.Common.Registry.ItemRegistry;
import RW.Common.Registry.MiscRegistry;
import RW.Common.Registry.RecipeRegistry;
import RW.Common.Registry.ResearchRegistry;
import RW.Common.Registry.TileRegistry;
import RW.Common.Skills.SkillRegistry;
import RW.Network.CommonProxy;
import RW.Utils.Logger;
import RW.Utils.PlayerTracker;
import RW.Utils.RogueConfig;
import RW.Utils.StructurePoses;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraft.command.CommandHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = "rogueWorld", name = "Rogue World", version = "0.03d")
public class RogueWorldCore {
	@Instance("rogueWorld")
	public static RogueWorldCore core;

	@SidedProxy(clientSide = "RW.Network.ClientProxy", serverSide = "RW.Network.CommonProxy", modId = "rogueWorld")
	public static CommonProxy proxy;

	public static String ModId = "rogueWorld";

	public static final RogueConfig cfg = new RogueConfig();
	public static SimpleNetworkWrapper network;
	public static boolean doLogging = false;
	public BlockRegistry BCore = new BlockRegistry();
	public ItemRegistry ICore = new ItemRegistry();
	public MiscRegistry MCore = new MiscRegistry();
	public RecipeRegistry RCore = new RecipeRegistry();
	public SkillRegistry SCore = new SkillRegistry();
	public TileRegistry TCore = new TileRegistry();
	public EntityRegistry ECore = new EntityRegistry();
	public IntegrationRegistry InCore = new IntegrationRegistry();

	public static RogueWorldCore getCore() {
		return core;
	}

	@EventHandler
	public void serverStart(FMLServerStartingEvent event) {
		MinecraftServer mcserver = event.getServer();
		((CommandHandler) mcserver.getCommandManager()).registerCommand(new CommandSetLevel());
		((CommandHandler) mcserver.getCommandManager()).registerCommand(new CommandSetDamage());
	}

	@EventHandler
	public void PreInit(FMLPreInitializationEvent event) {
		Logger.info("Starting PreInit Actions");
		try {
			registerConfigurationFileForMod(core.getClass(), event.getModConfigurationDirectory().getAbsolutePath());
			loadConfigForMod();
		} catch (IOException e) {
			Logger.warn("Couldn't Create Mod Configuration File. Something Wrong With Your FileSystem");
			e.printStackTrace();
		}

		if (core == null)
			core = this;
		core = this;
		try {
			MinecraftForge.EVENT_BUS.register(new AffixerEventHandler());
			MinecraftForge.EVENT_BUS.register(new RogueEventBus());
			MinecraftForge.EVENT_BUS.register(new PlayerTracker());
			{
				this.BCore.register();
				if (BCore.getFirstNotOccupiedSlotFor(BCore.simpleBlocks) < 0) 
				{
					Logger.warn(
							"Couldn't Load " + BCore.simpleBlocks.length + "'s Block. Expand Block Array to load it.");
				}
			}
			ICore.register();
			MCore.register();
			SCore.register();
			ECore.register();
			StructurePoses.register();
			PageRegistry.register();
			if (cfg.doLoadRecipes)
				RCore.register();
			if (cfg.doLoadIntegr)
				InCore.register();

			NetworkRegistry.INSTANCE.registerGuiHandler(core, proxy);
			if (proxy != null) {
				proxy.registerSpecialRenderer();
				// proxy.registerTiles();
			}
		} catch (Exception ev) {
			Logger.fatal("Couldn't Load ModFiles. Something Went Wrong");
			ev.printStackTrace();
		}
		Logger.info("Ended PreInit Actions");
	}

	private static Configuration config = new Configuration();
	private static boolean isConfigLoaded = false;

	private static void registerConfigurationFileForMod(Class<?> c, String path) throws IOException {
		File file = new File(path, ModId + ".cfg");
		if (!file.exists())
			file.createNewFile();
		config = new Configuration(file);
		config.save();
		Logger.info("Configuration File for mod " + ModId + " was successfully created with path " + path + "/" + ModId
				+ ".cfg");
	}

	private static void loadConfigForMod() throws RuntimeException {
		if (!isConfigLoaded) {
			if (cfg != null && config != null) {
				config.load();
				cfg.load(config);
				config.save();
			} else if ((cfg == null && config != null) || (cfg != null && config == null)) {
				throw new RuntimeException("Either the configuration handler"
						+ " for config was not registered, or the IConfig was not" + "registered.");
			}
			isConfigLoaded = true;
		} else {
			throw new RuntimeException("Configuration handler was already initialised!");
		}
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		Logger.info("Starting Init Actions");
		TCore.register();
		Logger.info("Ended Init Actions");

	}

	@EventHandler
	public void PostInit(FMLPostInitializationEvent event) {
		Logger.info("Starting PostInit Actions");
		if (Loader.isModLoaded("thaumcraft")) {
			if (cfg.doLoadIntegr)
				IntegrationRegistry.register();
			if (cfg.doLoadIntegr)
				ResearchRegistry.register();
		}
		Logger.info("Ended PostInit Actions");
	}
}
