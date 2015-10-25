/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Command;

import RW.Common.Registry.ItemRegistry;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.IChatComponent;

public class CommandSetDamage extends CommandBase
{

	@Override
	public String getCommandName()
	{
		return "setSwordDamage";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_)
	{
		return "/setSwordDamage <player> <damage>";
	}
	
	public int getRequiredPermissionLevel()
    {
        return 3;
    }

	@Override
	public void processCommand(ICommandSender sender, String[] arg)
	{
	        EntityPlayerMP p = arg.length == 0 ? getCommandSenderAsPlayer(sender) : getPlayer(sender, arg[0]);
	    	int damage = parseIntWithMin(sender, arg[1], 1);
			if(p.getCurrentEquippedItem() != null)
			{
				if(p.getCurrentEquippedItem().getItem() == ItemRegistry.DSword)
				{
					if(p.getCurrentEquippedItem().getTagCompound() != null)
					{
						p.getCurrentEquippedItem().getTagCompound().setInteger("Damage", damage);
					}
				}
			}
			
	}
	 /**
     * Return whether the specified command parameter index is a username parameter.
     */
    public boolean isUsernameIndex(int par1)
    {
        return par1 == 0;
    }

}
