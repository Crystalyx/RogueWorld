/**
 * This Class Created By Lord_Crystalyx.
 */
package RW.Common.Tile;

import RW.Api.EnergeticTileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileEntityEnergyTower extends EnergeticTileEntity
{

	public TileEntityEnergyTower()
	{
		super(1, 5000,"Energy Tower");
		this.uname="tower";
	}
	

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound syncData = new NBTTagCompound();
		this.writeToNBT(syncData);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		readFromNBT(pkt.func_148857_g());
	}

}
