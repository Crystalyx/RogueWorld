package RW.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class PlayerTracker
{
	public void loadInventoryFromFile(File file,RogueData iarts) throws IOException
	{
		FileInputStream stream = new FileInputStream(file);
		try 
		{
			
			NBTTagCompound compressedTag = CompressedStreamTools.read(file);
			if(iarts != null)
				iarts.readFromNBT(compressedTag);
			stream.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			stream.close();
		}
	}

	@SubscribeEvent
	public void onPlayerFileLoaded(PlayerEvent.LoadFromFile event)
	{
			String username = event.entityPlayer.getDisplayName();
			File loadedPlayerDirectory = event.playerDirectory;
			File playerNBTFile = new File(loadedPlayerDirectory.getAbsolutePath()+"//RogueData_"+username+".dat");
			
			RogueData ia = createInventoryFor(event.entityPlayer,username);
			if(!event.entityPlayer.worldObj.isRemote)
			{
				try
				{
					loadInventoryFromFile(createFilesFor(playerNBTFile), ia);
				}
				catch(Exception e)
				{
					Logger.fatal(" *File does not exists even after attempting to create it! Please, read the log above this message to find out, what went wrong! DO NOT report this message only, report the log ABOVE! Reporting THIS message and a crash AFTER it WILL BE IGNORED!!!");
					e.printStackTrace();
				}
			}
	}

	@SubscribeEvent
	public void onPlayerFileSaved(PlayerEvent.SaveToFile event)
	{
		try {
			EntityPlayer player = event.entityPlayer;
			if(!player.worldObj.isRemote)
			{
				File loadedPlayerDirectory = event.playerDirectory;
				File playerNBTFile = createFilesFor(new File(loadedPlayerDirectory.getAbsolutePath()+"//RogueData_"+player.getCommandSenderName()+".dat"));
				
				try {
					FileOutputStream stream = new FileOutputStream(playerNBTFile);
					RogueData data = (RogueData) MiscUtils.playerData.get(player.getCommandSenderName());
					if(data != null)
					{
						NBTTagCompound tag = new NBTTagCompound();
						data.writeToNBT(tag);
						CompressedStreamTools.write(tag, playerNBTFile);
					}
					stream.flush();
					stream.close();
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	public File createFilesFor(File f)
	{
		Logger.debug("Setting up player dat file of file "+f);
		try 
		{
			if(f.exists() && f.isFile())
			{
				Logger.debug(" *File found and exist, no modifications needed");
			}else
			{
				if(f.exists())
				{
					throw new IOException("File"+f+" is a directory?");
				}else
				{
					Logger.debug(" *File does not exists, creating new...");
					if(f.createNewFile())
						Logger.debug(" *Success");
					else
						Logger.debug(" *Failure");
				}
			}
				
			return f;
		} 
		catch (Exception e) 
		{
			Logger.error(" *Error creating file "+f + "" + e.toString());
			return f;
		}finally
		{
			Logger.debug("Finished setting up file "+f);
		}
		
	}
	
	public RogueData createInventoryFor(EntityPlayer player, String username)
	{
		RogueData data = new RogueData(player);
		MiscUtils.playerData.put(username, data);
		return data;
	}
}
