package hermitcore.library;

import hermitcore.library.crafting.LiquidCasting;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tconstruct.library.tools.ToolCore;

public class HermitRegistry 
{
	public static HermitRegistry instance = new HermitRegistry();
	
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
    
    @SuppressWarnings("rawtypes")
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
    
    public static LiquidCasting getTableCasting ()
    {
        return instance.tableCasting();
    }

    LiquidCasting tableCasting ()
    {
        try
        {
            Class<?> clazz = Class.forName("tconstruct.TConstruct");
            Method method = clazz.getMethod("getTableCasting");
            LiquidCasting lc = (LiquidCasting) method.invoke(this);
            return lc;
        }
        catch (Exception e)
        {
            logger.warn("Could not find casting table recipes.");
            return null;
        }
    }

    public static LiquidCasting getBasinCasting ()
    {
        return instance.basinCasting();
    }

    LiquidCasting basinCasting ()
    {
        try
        {
            Class<?> clazz = Class.forName("tconstruct.TConstruct");
            Method method = clazz.getMethod("getBasinCasting");
            LiquidCasting lc = (LiquidCasting) method.invoke(this);
            return lc;
        }
        catch (Exception e)
        {
            logger.warn("Could not find casting basin recipes.");
            return null;
        }
    }
    
}
