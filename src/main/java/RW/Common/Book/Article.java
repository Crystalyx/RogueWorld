package RW.Common.Book;

import java.util.Hashtable;

public class Article
{
	public Hashtable<Integer, IBookPage> Pages = new Hashtable<Integer, IBookPage>();
	public String name = "";
	public Object Icon;

	public Article(String name, Object icon)
	{
		this.name = name;
		this.Icon = icon;
	}
}
