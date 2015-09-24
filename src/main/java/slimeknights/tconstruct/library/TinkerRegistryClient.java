package slimeknights.tconstruct.library;

import gnu.trove.map.hash.THashMap;

import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.apache.logging.log4j.Logger;

import java.util.Map;

import slimeknights.tconstruct.library.client.ToolBuildGuiInfo;

@SideOnly(Side.CLIENT)
public final class TinkerRegistryClient {

  // the logger for the library
  public static final Logger log = Util.getLogger("API-Client");

  private TinkerRegistryClient() {}

  /*---------------------------------------------------------------------------
  | GUI & CRAFTING                                                            |
  ---------------------------------------------------------------------------*/
  private static final Map<Item, ToolBuildGuiInfo> toolBuildInfo = new THashMap<Item, ToolBuildGuiInfo>();

  public static void addToolBuilding(ToolBuildGuiInfo info) {
    toolBuildInfo.put(info.tool.getItem(), info);
  }

  public static ToolBuildGuiInfo getToolBuildInfoForTool(Item tool) {
    return toolBuildInfo.get(tool);
  }
}
