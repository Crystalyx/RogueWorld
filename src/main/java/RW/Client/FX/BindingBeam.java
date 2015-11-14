package RW.Client.FX;

import org.lwjgl.opengl.GL11;

import RW.Common.Items.Armors;
import RW.Common.Misc.WorldPos;
import RW.Common.Registry.ItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * @author Lord_Crystalyx
 */
public class BindingBeam extends EntityFX
{

	public float tickPos;
	private static final ResourceLocation particleText = new ResourceLocation("rogueworld:textures/misc/particles.png");
	private static final ResourceLocation sphere = new ResourceLocation("rogueworld:textures/items/sphere_blue.png");

	public int getInt()
	{
		return 16;
	}

	public BindingBeam(World w, double x, double y, double z, double i, double j, double k)
	{
		super(w, x, y, z, (i - x) / 2, (j - y + 0.7) / 2, (k - z) / 2);
		this.tilePosX = x;
		this.tilePosY = y;
		this.tilePosZ = z;

		this.motionX = i - x;
		this.motionY = j - y + 0.7;
		this.motionZ = k - z;
		this.rand.nextFloat();
		this.particleScale = 1F;
		this.particleGreen = 0.8F;
		this.particleBlue = 0F;
		this.particleRed = 0.0F;
		this.particleMaxAge = (int) (Math.random() * 10.0D) + 40;
		this.noClip = true;
		this.setParticleTextureIndex(getInt());
	}

	public BindingBeam(World w, double x, double y, double z, double i, double j, double k, double cR, double cG, double cB)
	{
		this(w, x, y, z, i, j, k);
		this.particleRed = (float) cR;
		this.particleGreen = (float) cG;
		this.particleBlue = (float) cB;
	}

	public BindingBeam(World w, WorldPos source, WorldPos velocity)
	{
		this(w, source.getX(), source.getY(), source.getZ(), velocity.getX(), velocity.getY(), velocity.getZ());
		this.rand.nextFloat();
		this.particleScale = 0.5F;
		this.particleRed = 0;
		this.particleGreen = 0F;
		this.particleBlue = 0F;
		this.particleMaxAge = (int) (Math.random() * 10.0D) + 40;
		this.noClip = true;
		this.setParticleTextureIndex(getInt());
	}

	public BindingBeam(World w, WorldPos source, WorldPos velocity, double cR, double cG, double cB)
	{
		this(w, source.getX(), source.getY(), source.getZ(), velocity.getX(), velocity.getY(), velocity.getZ());
		this.particleRed = (float) cR;
		this.particleGreen = (float) cG;
		this.particleBlue = (float) cB;
	}

	private double tilePosX;
	private double tilePosY;
	private double tilePosZ;
	private double prevYaw;
	private double prevPitch;
	private float length;

	public void renderParticle(Tessellator var3, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		var3.draw();
		GL11.glPushMatrix();

		GL11.glDisable(GL11.GL_ALPHA_TEST);
		boolean enabled = GL11.glIsEnabled(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_BLEND);

		Minecraft.getMinecraft().renderEngine.bindTexture(particleText);
		EntityLivingBase viewer = Minecraft.getMinecraft().thePlayer;
		if (viewer instanceof EntityPlayer)
		{
			if (((EntityPlayer) viewer).getCurrentEquippedItem() != null
					&& (((EntityPlayer) viewer).getCurrentEquippedItem().getItem() == Items.ender_pearl || ((EntityPlayer) viewer).getCurrentEquippedItem().getItem() == ItemRegistry.linkingRod))
			{
				var3.startDrawingQuads();

				float sc = this.particleScale;
				float cR = this.particleRed;
				float cG = this.particleGreen;
				float cB = this.particleBlue;
				float cA = this.particleAlpha;
				this.particleScale *= 1.5F;
				this.particleRed = 0;
				this.particleGreen = 0F;
				this.particleBlue = 0.0F;
				this.particleAlpha = 1F;
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
				var3.setBrightness(512);
				super.renderParticle(var3, par2, par3, par4, par5, par6, par7);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				this.particleScale = sc;
				this.particleRed = cR;
				this.particleGreen = cG;
				this.particleBlue = cB;
				this.particleAlpha = cA;
				super.renderParticle(var3, par2, par3, par4, par5, par6, par7);
				var3.draw();
			}
			else if (((EntityPlayer) viewer).getCurrentArmor(3) != null && ((EntityPlayer) viewer).getCurrentArmor(3).getItem() instanceof Armors)
			{
				var3.startDrawingQuads();

				float sc = this.particleScale;
				float cR = this.particleRed;
				float cG = this.particleGreen;
				float cB = this.particleBlue;
				float cA = this.particleAlpha;
				this.particleScale *= 1.5F;
				this.particleRed = 0;
				this.particleGreen = 0F;
				this.particleBlue = 0.0F;
				this.particleAlpha = 1F;
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
				var3.setBrightness(512);
				super.renderParticle(var3, par2, par3, par4, par5, par6, par7);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				this.particleScale = sc;
				this.particleRed = cR;
				this.particleGreen = cG;
				this.particleBlue = cB;
				this.particleAlpha = cA;
				super.renderParticle(var3, par2, par3, par4, par5, par6, par7);
				var3.draw();
			}
		}
		if (!enabled)
			GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glPopMatrix();
		var3.startDrawingQuads();
	}

	public int getBrightnessForRender(float p_70070_1_)
	{
		return 255;
	}

	/**
	 * Gets how bright this entity is.
	 */
	public float getBrightness(float p_70013_1_)
	{
		float f1 = super.getBrightness(p_70013_1_);
		float f2 = (float) this.particleAge / (float) this.particleMaxAge;
		f2 = f2 * f2 * f2 * f2;
		return f1 * (1.0F - f2) + f2;
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate()
	{
		tickPos += 15 + this.worldObj.rand.nextFloat() * 15;
		if (this.particleAge < this.particleMaxAge / 2)
			this.setParticleTextureIndex(getInt());
		else
			this.setParticleTextureIndex(getInt());
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		float f = (float) this.particleAge / (float) this.particleMaxAge;
		this.posX = (this.tilePosX + this.motionX * (double) f);
		this.posY = (this.tilePosY + this.motionY * (double) f);
		this.posZ = (this.tilePosZ + this.motionZ * (double) f);

		if (this.particleAge++ >= this.particleMaxAge)
		{
			this.setDead();
		}
	}
}