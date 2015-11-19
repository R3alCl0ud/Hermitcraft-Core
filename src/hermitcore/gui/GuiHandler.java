package hermitcore.gui;

import hermitcore.gameObjs.tile.TileHE;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler
{
    public static final int TILE_ID = 0;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (ID)
        {
            case TILE_ID:
                TileEntity tileEntity = world.getTileEntity(x, y, z);
                if (tileEntity instanceof TileHE)
                {
                    return ((TileHE) tileEntity).getGuiServer(player.inventory);
                }
                break;
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (ID)
        {
            case TILE_ID:
                TileEntity tileEntity = world.getTileEntity(x, y, z);
                if (tileEntity instanceof TileHE)
                {
                    return ((TileHE) tileEntity).getGuiClient(player.inventory);
                }
                break;
        }

        return null;
    }
}
