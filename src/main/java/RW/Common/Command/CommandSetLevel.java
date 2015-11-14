/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Command;

import RW.Common.Registry.ItemRegistry;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.IChatComponent;

/**
 * @author Lord_Crystalyx
 */
public class CommandSetLevel extends CommandBase
{

	@Override
	public String getCommandName()
	{
		return "setSwordLevel";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_)
	{
		return "/setSwordLevel <player> <level>";
	}

	public int getRequiredPermissionLevel()
	{
		return 3;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] arg)
	{
		int level = parseIntWithMin(sender, arg[1], 1);
		EntityPlayerMP p = arg.length == 0 ? getCommandSenderAsPlayer(sender) : getPlayer(sender, arg[0]);
		if (p.getCurrentEquippedItem() != null)
		{
			if (p.getCurrentEquippedItem().getItem() == ItemRegistry.DSword || p.getCurrentEquippedItem().getItem() == ItemRegistry.DPick || p.getCurrentEquippedItem().getItem() == ItemRegistry.DAxe)
			{
				if (p.getCurrentEquippedItem().getTagCompound() != null)
				{
					p.getCurrentEquippedItem().getTagCompound().setInteger("Level", level);
				}

			}

		}
	}

	/**
	 * Return whether the specified command parameter index is a username
	 * parameter.
	 */
	public boolean isUsernameIndex(int par1)
	{
		return par1 == 0;
	}

}
