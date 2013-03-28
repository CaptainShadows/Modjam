package com.shadows.Cooling.inv;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.shadows.Cooling.TE.TECooler;
import com.shadows.Cooling.utils.Registry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GUICooler extends GuiContainer {

	private TECooler CoolerTE;

	public GUICooler(InventoryPlayer player, TECooler coolerTE) {

		super(new CoolerC(player, coolerTE));
		ySize = 176;
		this.CoolerTE = coolerTE;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {

		String containerName = CoolerTE.isInvNameLocalized() ? CoolerTE
				.getInvName() : StatCollector.translateToLocal(CoolerTE
				.getInvName());
		fontRenderer.drawString(containerName,
				xSize / 2 - fontRenderer.getStringWidth(containerName) / 2, 6,
				4210752);
		fontRenderer.drawString(
				StatCollector.translateToLocal(Registry.ContainerINV), 8,
				ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(Registry.CoolerGUI);
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
	}
}
