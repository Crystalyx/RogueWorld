package RW.Client.RenderUtils;

import org.lwjgl.opengl.GL11;

import RW.Core.RogueWorldCore;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public class RenderUtils
{
	public static String id = RogueWorldCore.ModId;
	public static void renderBlock(RenderBlocks r,Block b)
	{
//		Cube block = new Cube();
//		block.setBounds(0,0,0,2, 2, 2);
//		block.render(b);
		
		GL11.glPushMatrix();
		
		r.renderBlockAllFaces(b, 0, 0, 0);
		
		GL11.glPopMatrix();
		
	}

	public static void renderBlock(RenderBlocks r,Block b,int x,int y,int z)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		Cube block = new Cube();
		block.setBounds(0,0,0,2, 2, 2);
		block.render(b);
		GL11.glPopMatrix();
	}
	
	public static void drawSides(RenderBlocks renderblocks, Block block, IIcon[] icons, boolean solidtop) {
	      Tessellator tessellator = Tessellator.instance;
	      GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
	      tessellator.startDrawingQuads();
	      tessellator.setNormal(0.0F, -1.0F, 0.0F);
	      renderblocks.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, icons[0]);
	      tessellator.draw();
	      if(solidtop) {
	         GL11.glDisable(GL11.GL_ALPHA_TEST);
	      }

	      tessellator.startDrawingQuads();
	      tessellator.setNormal(0.0F, 1.0F, 0.0F);
	      renderblocks.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, icons[1]);
	      tessellator.draw();
	      if(solidtop) {
	         GL11.glEnable(GL11.GL_ALPHA_TEST);
	      }

	      tessellator.startDrawingQuads();
	      tessellator.setNormal(0.0F, 0.0F, 1.0F);
	      renderblocks.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, icons[2]);
	      tessellator.draw();
	      tessellator.startDrawingQuads();
	      tessellator.setNormal(0.0F, 0.0F, -1.0F);
	      renderblocks.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, icons[3]);
	      tessellator.draw();
	      tessellator.startDrawingQuads();
	      tessellator.setNormal(1.0F, 0.0F, 0.0F);
	      renderblocks.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, icons[4]);
	      tessellator.draw();
	      tessellator.startDrawingQuads();
	      tessellator.setNormal(-1.0F, 0.0F, 0.0F);
	      renderblocks.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, icons[5]);
	      tessellator.draw();
	      GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	   }
}
