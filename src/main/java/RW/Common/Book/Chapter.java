package RW.Common.Book;

import java.util.Hashtable;

public class Chapter
{
	public Hashtable<Integer, Article> Articles = new Hashtable<Integer, Article>();
	public String name = "";
	public Object Icon;
	
	public Chapter(String name, Object icon)
	{
		this.name = name;
		this.Icon = icon;
	}

}
