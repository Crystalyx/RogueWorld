/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Items;

import RW.Common.Registry.BlockRegistry;
import RW.Common.Registry.MiscRegistry;
import RW.Common.Tile.TileEntityReactorCore;
import RW.Core.RogueWorldCore;
import RW.Utils.MiscUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class RiteBook extends Item
{
	public RiteBook()
	{
		this.setTextureName("rogueWorld:dark_book");
		this.setUnlocalizedName("rw.ritebook");
		this.setCreativeTab(MiscRegistry.modTab);
	}

	public ItemStack onItemRightClick(ItemStack i, World w, EntityPlayer p)
	{
		if (w.isRemote)
		{
			p.addChatMessage(new ChatComponentText("Player is seeker:" + MiscUtils.playerData.get(p.getCommandSenderName()).playerIsSeeker));
			p.addChatMessage(new ChatComponentText("Player patronage:" + MiscUtils.playerData.get(p.getCommandSenderName()).patronage));
		}
		return i;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		this.itemIcon = ir.registerIcon("rogueWorld:dark_book");
	}

	public boolean onItemUse(ItemStack i, EntityPlayer p, World w, int x, int y, int z, int meta, float px, float py, float pz)
	{
		MiscUtils.playerData.get(p.getCommandSenderName()).addPatronage(20);
		p.openGui(RogueWorldCore.core, 85951, w, x, y, z);
		
		return true;
	}
}
