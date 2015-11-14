package RW.Api;

import java.util.Hashtable;

/**
 * @author Lord_Crystalyx
 * @Description My little NBT replacement
 */
public class RogueData
{
	public Hashtable<String, Object> data = new Hashtable<String, Object>();

	public RogueData()
	{

	}

	public RogueData(Hashtable<String, Object> data)
	{
		this.data = data;
	}

	public void setInt(String tag, int in)
	{
		this.data.put(tag, in);
	}

	public int getInt(String tag)
	{
		return (Integer) this.data.get(tag);
	}
}
