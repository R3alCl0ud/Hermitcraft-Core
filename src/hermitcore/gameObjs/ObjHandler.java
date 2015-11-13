package hermitcore.gameObjs;

import hermitcore.HECore;
import hermitcore.config.HermitCoreConfig;
import hermitcore.gameObjs.block.blockJade;
import hermitcore.gameObjs.block.blockLimonite;
import hermitcore.gameObjs.block.blockRosite;
import hermitcore.gameObjs.block.oreLimonite;
import hermitcore.gameObjs.item.ingotLimonite;
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



import net.nevermine.izer.Itemizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

@SuppressWarnings("unused")
public class ObjHandler 
{
	
	public static Block blockLimonite;
	public static Block blockRosite;
	public static Block blockJade;
	public static Block blockAmethyst;
	public static Block blockSapphire;
	public static Block blockMystite;
	public static Block blockVarsium;
	public static Block blockLyon;
	public static Block blockEmberstone;
	
	public static void register() 
	{
		
		GameRegistry.registerBlock(blockLimonite = new blockLimonite("blockLimonite", Material.iron), "blockLimonite");
		GameRegistry.registerBlock(blockRosite = new blockRosite("blockRosite", Material.iron), "blockRosite"); 
		GameRegistry.registerBlock(blockJade = new blockJade("blockJade", Material.iron), "blockJade"); 
		GameRegistry.registerBlock(blockRosite = new blockRosite("blockRosite", Material.iron), "blockRosite"); 

	}
	
	public static void addRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(blockLimonite), "LLL", "LLL", "LLL", 'L', Itemizer.IngotLimonite);
		GameRegistry.addShapelessRecipe(new ItemStack(Itemizer.IngotLimonite, 9, 0), blockLimonite);
		
		GameRegistry.addRecipe(new ItemStack(blockRosite), "LLL", "LLL", "LLL", 'L', Itemizer.IngotRosite);
		GameRegistry.addShapelessRecipe(new ItemStack(Itemizer.IngotRosite, 9, 0), blockRosite);
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

}
