/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Event;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.block.Block;

/**
 * @author Lord_Crystalyx
 */
public class SimpleBlockRegisterEvent extends Event
{
	public Block block;

	public SimpleBlockRegisterEvent(Block regB)
	{
		block = regB;
	}
}
