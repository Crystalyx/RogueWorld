/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Integration;

import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;

public class AspectObscurus extends Aspect
{

	public AspectObscurus()
	{
		//super("obscure", 0, new Aspect[] {Aspect.MIND,Aspect.ENTROPY}, new ResourceLocation("rogueWorld:textures/misc/aspect.png"), 1);
		super("ligilo", 0x50b3ff,null, new ResourceLocation("rogueWorld:textures/misc/aspect_ligilo2.png"), 1);
		this.setChatcolor(EnumChatFormatting.BLUE.getFormattingCode()+"");
	}
}
