package slimeknights.tconstruct.tools.client;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.tools.client.module.GuiButtonsStencilTable;
import slimeknights.tconstruct.tools.client.module.GuiSideInventory;
import slimeknights.tconstruct.tools.inventory.ContainerPatternChest;
import slimeknights.tconstruct.tools.inventory.ContainerSideInventory;
import slimeknights.tconstruct.tools.inventory.ContainerStencilTable;
import slimeknights.tconstruct.tools.inventory.ContainerTinkerStation;
import slimeknights.tconstruct.tools.network.StencilTableSelectionPacket;
import slimeknights.tconstruct.tools.tileentity.TileStencilTable;

@SideOnly(Side.CLIENT)
public class GuiStencilTable extends GuiTinkerStation {
  private static final ResourceLocation BACKGROUND = Util.getResource("textures/gui/stenciltable.png");

  public static final int Column_Count = 4;

  protected GuiButtonsStencilTable buttons;

  public GuiStencilTable(InventoryPlayer playerInv, World world, BlockPos pos, TileStencilTable tile) {
    super(world, pos, (ContainerTinkerStation) tile.createContainer(playerInv, world, pos));

    buttons = new GuiButtonsStencilTable(this, inventorySlots, false);
    this.addModule(buttons);

    if(inventorySlots instanceof ContainerStencilTable) {
      ContainerStencilTable container = (ContainerStencilTable) inventorySlots;
      ContainerSideInventory chestContainer = container.getSubContainer(ContainerPatternChest.SideInventory.class);
      if(chestContainer != null) {
        this.addModule(new GuiSideInventory(this, chestContainer, chestContainer.getSlotCount(), chestContainer.columns, true, false));
      }
    }
  }

  public void onSelectionPacket(StencilTableSelectionPacket packet) {
    buttons.setSelectedbuttonByItem(packet.output);
  }

  @Override
  protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    drawBackground(BACKGROUND);

    drawIcon(inventorySlots.getSlot(0), ICON_Pattern);

    super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
  }
}
