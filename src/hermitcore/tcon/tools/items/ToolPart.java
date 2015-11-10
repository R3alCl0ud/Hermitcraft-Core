package hermitcore.tcon.tools.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import tconstruct.library.TConstructRegistry;
import tconstruct.library.util.IToolPart;
import tconstruct.tools.TinkerTools;
import mantle.items.abstracts.CraftingItem;

public class ToolPart extends CraftingItem implements IToolPart
{
    public String partName;

    public ToolPart(String textureType, String name)
    {
        super(toolMaterialNames, buildTextureNames(textureType), "parts/", "tinker", TConstructRegistry.partTab);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.partName = name;
    }
    

    @Override
    public String getItemStackDisplayName (ItemStack par1ItemStack)
    {
        String material = "";
        if(par1ItemStack.getItemDamage() < toolTextureNames.length)
            material = toolTextureNames[par1ItemStack.getItemDamage()];
        String name = "";
        
        if (StatCollector.canTranslate("toolpart." + partName + "." + material))
        {
            name = StatCollector.translateToLocal("toolpart." + partName + "." + material);
        }
        else
        {
            material = StatCollector.translateToLocal("material." + material);
            name = StatCollector.translateToLocal("toolpart." + partName);
            name = name.replaceAll("%%material", material);
        }

        return name;
    }

    private static String[] buildTextureNames (String textureType)
    {
        String[] names = new String[toolMaterialNames.length];
        for (int i = 0; i < toolMaterialNames.length; i++)
        {
            if (toolTextureNames[i].equals(""))
                names[i] = "";
            else
                names[i] = toolTextureNames[i] + textureType;
        }
        return names;
    }

    public static final String[] toolMaterialNames = new String[] { "Limonite", "Amethyst", "Rosite", "Sapphire", "Mystite", "Skeletal", "Varsium", "Lyon" };

    public static final String[] toolTextureNames = new String[] { "limonite", "amethyst", "rosite", "sapphire", "mystite", "skeletal", "varsium", "lyon" };

    @Override
    public void getSubItems (Item b, CreativeTabs tab, List list)
    {
        for (int i = 0; i < 19; i++)
            list.add(new ItemStack(b, 1, i));

        /*if (TinkerTools.thaumcraftAvailable)
            list.add(new ItemStack(b, 1, 31));*/
    }

    @Override
    public int getMaterialID (ItemStack stack)
    {
        if (TConstructRegistry.toolMaterials.keySet().contains(stack.getItemDamage()))
            return stack.getItemDamage();

        return -1;
    }

}
