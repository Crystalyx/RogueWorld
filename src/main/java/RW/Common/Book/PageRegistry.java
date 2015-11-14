package RW.Common.Book;

import java.util.Hashtable;

import RW.Common.Registry.ItemRegistry;
import RW.Utils.MiscUtils;
import net.minecraft.item.Item;

/**
 * @author Lord_Crystalyx
 */
public class PageRegistry
{
	public static Hashtable<Integer, Chapter> Chapters = new Hashtable<Integer, Chapter>();

	public static int chapterid = 0;
	public static int[] articleids = new int[1];
	public static int[][] pageids = new int[1][1];

	public static void register()
	{
		addChapter("rw.chapter.name", ItemRegistry.RiteBook); // 0 Chapter
		addArticle(0, "rw.article.name", ItemRegistry.DCryst); // 0:0 Article
		addPage(0, 0, new IBookPage(null, "rw.page.darkness.text"));
	}

	public static void addChapter(String name, Item icon)
	{
		Chapters.put(chapterid, new Chapter(name, icon));
		chapterid++;
		MiscUtils.expandArray(articleids, 1);
		MiscUtils.expandArray(pageids, 1);

	}

	public static void addArticle(int chapterid, String name, Item icon)
	{
		Chapters.get(chapterid).Articles.put(articleids[chapterid], new Article(name, icon));
		articleids[chapterid]++;
		MiscUtils.expandArray(pageids[chapterid], 1);
	}

	public static void addPage(int chapterid, int articleid, IBookPage page)
	{
		Chapters.get(chapterid).Articles.get(articleid).Pages.put(pageids[chapterid][articleid], page);
		pageids[chapterid][articleid]++;
	}
}
