package hermitcore.library;

import java.lang.reflect.Method;
import java.util.*;
import net.minecraft.item.*;
import tconstruct.library.tools.ToolCore;

import org.apache.logging.log4j.*;
import hermitcore.library.crafting.*;

public class HermitRegistery 
{
	public static HermitRegistery Instance = new HermitRegistery();
	
	public static Logger logger = LogManager.getLogger("TCon-API");
	
	public static HashMap<String, Item> itemDirectory = new HashMap<String, Item>();

	
    public static void addItemToDirectory (String name, Item itemstack)
    {
        Item add = itemDirectory.get(name);
        if (add != null)
            logger.warn(name + " is already present in the Item directory");

        itemDirectory.put(name, itemstack);
    }
    
    public static Item getItem (String name)
    {
        Item ret = itemDirectory.get(name);
        if (ret == null)
            logger.warn("Could not find " + name + " in the Item directory");

        return ret;
    }
    
    static HashMap<String, ItemStack> itemstackDirectory = new HashMap<String, ItemStack>();
    

    public static void addItemStackToDirectory (String name, ItemStack itemstack)
    {
        ItemStack add = itemstackDirectory.get(name);
        if (add != null)
            logger.warn(name + " is already present in the ItemStack directory");

        itemstackDirectory.put(name, itemstack);
    }
    
    public static ItemStack getItemStack (String name)
    {
        ItemStack ret = itemstackDirectory.get(name);
        if (ret == null)
            logger.warn("Could not find " + name + " in the ItemStack directory");

        return ret;
    }

    public static ArrayList<ToolCore> tools = new ArrayList<ToolCore>(20);
    
    public static HashMap<List, ItemStack> patternPartMapping = new HashMap<List, ItemStack>();
    
    public static void addPartMapping (Item woodPattern, int patternMeta, int materialID, ItemStack output)
    {
        patternPartMapping.put(Arrays.asList(woodPattern, patternMeta, materialID), output);
    }
    
    public static ItemStack getPartMapping (Item item, int metadata, int materialID)
    {
        ItemStack stack = patternPartMapping.get(Arrays.asList(item, metadata, materialID));
        if (stack != null)
            return stack.copy();
        return null;
    }

    public static void addToolMapping (ToolCore tool)
    {
        tools.add(tool);
    }

    public static ArrayList<ToolCore> getToolMapping ()
    {
        return tools;
    }

    
}
