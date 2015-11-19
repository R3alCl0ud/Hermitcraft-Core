package hermitcore.library;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.*;

public class HermitTabs extends CreativeTabs
{
    ItemStack display;

    public HermitTabs(String label)
    {
        super(label);
    }

    public void init (ItemStack stack)
    {
        display = stack;
    }

    public ItemStack getIconItemStack ()
    {
        return display;
    }

    public Item getTabIconItem ()
    {
        return display.getItem();
    }
}
