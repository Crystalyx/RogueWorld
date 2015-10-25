/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Utils;

import net.minecraftforge.common.config.Configuration;

public class RogueConfig
{
	public RogueConfig instance;
	public static net.minecraftforge.common.config.Configuration config;

	public void load(Configuration config)
	{
		RogueConfig.config = config;
		config.load();
		organizeConfig();
		config.save();
	}

	private void organizeConfig()
	{
		doLoadIntegr = RogueConfig.config.getBoolean("doLoadIntegration", "core", true, "Should Integration Be Loaded?");
		doLoadRecipes = RogueConfig.config.getBoolean("doLoadRecipes", "core", true, "Should Recipes Be Loaded?");
	}
	
	public boolean doLoadIntegr;
	public boolean doLoadRecipes;
}
