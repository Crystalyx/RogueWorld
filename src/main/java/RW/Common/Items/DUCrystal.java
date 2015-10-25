package RW.Common.Items;

import java.util.List;

import RW.Api.IDUItem;
import RW.Common.Registry.BlockRegistry;
import RW.Common.Registry.MiscRegistry;
import RW.Common.Skills.SkillRegistry;
import RW.Common.Tile.TileEntityReactorCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class DUCrystal extends IDUItem
{
	public DUCrystal()
	{
		super();
		this.setTextureName("rogueWorld:du_crystal");
		this.setUnlocalizedName("rw.ducrystal");
		this.setCreativeTab(MiscRegistry.modTab);
	}
}
