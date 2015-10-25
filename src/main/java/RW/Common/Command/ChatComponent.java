/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Command;

import java.util.Iterator;
import java.util.List;

import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;

public class ChatComponent implements IChatComponent
{
	
	public String text = "";

	public ChatComponent(String string)
	{
		this.text = string;
	}

	@Override
	public Iterator iterator()
	{
		return null;
	}

	@Override
	public IChatComponent setChatStyle(ChatStyle style)
	{
		return new ChatComponent(style.getFormattingCode()+this.text);
	}

	@Override
	public ChatStyle getChatStyle()
	{
		return null;
	}

	@Override
	public IChatComponent appendText(String txt)
	{
		return new ChatComponent(txt+this.text);
	}

	@Override
	public IChatComponent appendSibling(IChatComponent p_150257_1_)
	{
		return null;
	}

	@Override
	public String getUnformattedTextForChat()
	{
		return null;
	}

	@Override
	public String getUnformattedText()
	{
		return this.text;
	}

	@Override
	public String getFormattedText()
	{
		return this.text;
	}

	@Override
	public List getSiblings()
	{
		return null;
	}

	@Override
	public IChatComponent createCopy()
	{
		return null;
	}

}
