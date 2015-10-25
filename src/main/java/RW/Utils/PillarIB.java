package RW.Utils;

import RW.Client.Model.ShowCase;
import RW.Common.Registry.BlockRegistry;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class PillarIB extends ItemBlock
{

	public PillarIB()
	{
		super(BlockRegistry.altar_base_pil);
		this.setFull3D();
		MinecraftForgeClient.registerItemRenderer(this, (IItemRenderer) new ShowCase());
	}

}
