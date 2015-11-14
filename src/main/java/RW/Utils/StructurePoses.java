package RW.Utils;

import java.util.Hashtable;

import RW.Common.Misc.WorldPos;

/**
 * @author Lord_Crystalyx
 */
public class StructurePoses
{
	public static Hashtable<Integer, WorldPos[]> poses = new Hashtable<Integer, WorldPos[]>();

	private static int id = 0;

	public static void register()
	{
		registerPos(new WorldPos[] { new WorldPos(0, 0, 0), new WorldPos(-3, 0, 0), new WorldPos(3, 0, 0), new WorldPos(0, 0, -3), new WorldPos(0, 0, 3), new WorldPos(2, 0, -2), new WorldPos(-2, 0, 2), new WorldPos(2, 0, 2),
				new WorldPos(-2, 0, -2) }, "Reactor Interior");
		registerPos(
				new WorldPos[] { new WorldPos(+4, -3, +1), new WorldPos(+4, -3, 0), new WorldPos(+4, -3, -1), new WorldPos(+4, -4, +1), new WorldPos(+4, -4, 0), new WorldPos(+4, -4, -1), new WorldPos(-4, -3, +1), new WorldPos(-4, -3, 0),
						new WorldPos(-4, -3, -1), new WorldPos(-4, -4, +1), new WorldPos(-4, -4, 0), new WorldPos(-4, -4, -1), new WorldPos(-1, -3, +4), new WorldPos(0, -3, +4), new WorldPos(+1, -3, +4), new WorldPos(-1, -4, +4),
						new WorldPos(0, -4, +4), new WorldPos(+1, -4, +4), new WorldPos(-1, -3, -4), new WorldPos(0, -3, -4), new WorldPos(+1, -3, -4), new WorldPos(-1, -4, -4), new WorldPos(0, -4, -4), new WorldPos(+1, -4, -4) },
				"Reactor Exterior");
	}

	public static void registerPos(WorldPos[] pos, String name)
	{
		poses.put(id, pos);
		Logger.info("Pos with name " + name + " was successfully registered!");
		id++;
	}
}
