package hermitcore.gameObjs;

import hermitcore.HECore;
import hermitcore.config.HermitCoreConfig;
import hermitcore.gameObjs.block.oreLimonite;

import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;
import net.minecraftforge.oredict.ShapedOreRecipe;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

@SuppressWarnings("unused")
public class ObjHandler 
{

	
	public static Item ingotLimonite;
	
	
	public static Block oreLimonite;
	
	
	public static void register() 
	{
		GameRegistry.registerBlock(oreLimonite = new oreLimonite("Limonite Ore", Material.iron), "Limonite Ore");
		

	}

	@SuppressWarnings("unchecked")
	public static void removeRecipes(String toDelete) {

	    ItemStack resultItem = new ItemStack((Item)Item.itemRegistry.getObject(toDelete));
	    resultItem.stackSize = 1;
	    resultItem.setItemDamage(0);

	    List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();

	    for (int i = 0; i < recipes.size(); i++)
	    {
	        IRecipe tmpRecipe = recipes.get(i);

	        ItemStack recipeResult = tmpRecipe.getRecipeOutput();


	        if (ItemStack.areItemStacksEqual(resultItem, recipeResult))
	        {
	            recipes.remove(i--);
	        }
	    }

	}
	public static void addRecipes () 
	{
		
	}
}
