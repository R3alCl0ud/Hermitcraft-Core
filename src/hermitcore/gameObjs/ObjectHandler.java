package hermitcore.gameObjs;

import hermitcore.gameObjs.block.blockAmethyst;
import hermitcore.gameObjs.block.blockEmberstone;
import hermitcore.gameObjs.block.blockJade;
import hermitcore.gameObjs.block.blockLimonite;
import hermitcore.gameObjs.block.blockLyon;
import hermitcore.gameObjs.block.blockMystite;
import hermitcore.gameObjs.block.blockRosite;
import hermitcore.gameObjs.block.blockSapphire;
import hermitcore.gameObjs.block.blockVarsium;
import hermitcore.gameObjs.block.rf.BlockCdBurner;
import hermitcore.gameObjs.block.rf.rfCd_Burner;
import hermitcore.gameObjs.item.KlienStarGamma;
import hermitcore.gameObjs.item.Schematic_Tbtn;
import hermitcore.gameObjs.item.itemLaser;
import hermitcore.gameObjs.item.itemVinyl;
import hermitcore.gameObjs.item.record_Blank;
import hermitcore.gameObjs.item.record_Chipstuff;
import hermitcore.gameObjs.item.record_Collide;
import hermitcore.gameObjs.item.record_TakeBackTehNight;
import hermitcore.gameObjs.item.record_Warcraft;
import hermitcore.gameObjs.item.rf.ItemCdBurner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import moze_intel.projecte.gameObjs.ObjHandler;
import moze_intel.projecte.gameObjs.customRecipes.RecipeShapelessHidden;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.nevermine.izer.Itemizer;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

@SuppressWarnings("unused")
public class ObjectHandler 
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
	
	public static Block rfCdBurner;
	
	public static Item Schematic_Tbtn;
	
	public static Item itemVinyl;
	public static Item itemLaser;
	
	public static Item record_Blank;
	public static Item record_Warcraft;
	public static Item record_Chipstuff;
	public static Item record_Collide;
	public static Item record_TakeBackTehNight;
	
	
	public static Item KlienStarGamma;
	
	@SuppressWarnings("rawtypes")
	private static final Map records = new HashMap();
	
	public static void register() 
	{		
		rfCdBurner = new BlockCdBurner();
		GameRegistry.registerBlock(blockLimonite = new blockLimonite("blockLimonite", Material.iron), "blockLimonite");
		GameRegistry.registerBlock(blockRosite = new blockRosite("blockRosite", Material.iron), "blockRosite"); 
		GameRegistry.registerBlock(blockJade = new blockJade("blockJade", Material.iron), "blockJade"); 
		GameRegistry.registerBlock(blockAmethyst = new blockAmethyst("blockAmethyst", Material.iron), "blockAmethyst"); 
		GameRegistry.registerBlock(blockEmberstone = new blockEmberstone("blockEmberstone", Material.iron), "blockEmberstone"); 
		GameRegistry.registerBlock(blockVarsium = new blockVarsium("blockVarsium", Material.iron), "blockVarsium");
		GameRegistry.registerBlock(blockLyon = new blockLyon("blockLyon", Material.iron), "blockLyon");
		GameRegistry.registerBlock(blockMystite = new blockMystite("blockMystite", Material.iron), "blockMystite");
		GameRegistry.registerBlock(blockSapphire = new blockSapphire("blockSapphire", Material.iron), "blockSapphire");
		
		
		
		GameRegistry.registerBlock(rfCdBurner, ItemCdBurner.class, "rfCd_Burner");
		((BlockCdBurner) rfCdBurner).init();
		
		GameRegistry.registerItem(Schematic_Tbtn = new Schematic_Tbtn("schematicTbtn"), "schematicTbtn");
		GameRegistry.registerItem(itemVinyl =  new itemVinyl("itemVinyl"), "itemVinyl");
		GameRegistry.registerItem(itemLaser =  new itemLaser("itemLaser"), "itemLaser");
		GameRegistry.registerItem(record_Blank = new record_Blank("recordBlank"), "recordBlank");
		
		GameRegistry.registerItem(record_Warcraft = new record_Warcraft("recordWarcraft"), "recordWarcraft");
		GameRegistry.registerItem(record_Chipstuff = new record_Chipstuff("recordChipstuff"), "recordChipstuff");
		GameRegistry.registerItem(record_Collide = new record_Collide("recordCollide"), "recordCollide");
		GameRegistry.registerItem(record_TakeBackTehNight = new record_TakeBackTehNight("recordTakeBackTehNight"), "recordTakeBackTehNight");
		
		
		if(Loader.isModLoaded("ProjectE"))
		{
			GameRegistry.registerItem(KlienStarGamma = new KlienStarGamma("KlienStarGamma"), "KlienStarGamma", "KlienStarGamma");
		}
		
	}
	
	public static void addRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(blockLimonite), "LLL", "LLL", "LLL", 'L', Itemizer.IngotLimonite);
		GameRegistry.addShapelessRecipe(new ItemStack(Itemizer.IngotLimonite, 9, 0), blockLimonite);
		
		GameRegistry.addRecipe(new ItemStack(blockRosite), "LLL", "LLL", "LLL", 'L', Itemizer.IngotRosite);
		GameRegistry.addShapelessRecipe(new ItemStack(Itemizer.IngotRosite, 9, 0), blockRosite);
		
		GameRegistry.addRecipe(new ItemStack(blockJade), "LLL", "LLL", "LLL", 'L', Itemizer.IngotJade);
		GameRegistry.addShapelessRecipe(new ItemStack(Itemizer.IngotJade, 9, 0), blockJade);
		
		GameRegistry.addRecipe(new ItemStack(blockAmethyst), "LLL", "LLL", "LLL", 'L', Itemizer.AmethystIngot);
		GameRegistry.addShapelessRecipe(new ItemStack(Itemizer.AmethystIngot, 9, 0), blockAmethyst);
		
		GameRegistry.addRecipe(new ItemStack(blockEmberstone), "LLL", "LLL", "LLL", 'L', Itemizer.IngotEmberstone);
		GameRegistry.addShapelessRecipe(new ItemStack(Itemizer.IngotEmberstone, 9, 0), blockEmberstone);
		
		GameRegistry.addRecipe(new ItemStack(blockVarsium), "LLL", "LLL", "LLL", 'L', Itemizer.IngotVarsium);
		GameRegistry.addShapelessRecipe(new ItemStack(Itemizer.IngotVarsium, 9, 0), blockVarsium);
		
		GameRegistry.addRecipe(new ItemStack(blockSapphire), "LLL", "LLL", "LLL", 'L', Itemizer.IngotSapphire);
		GameRegistry.addShapelessRecipe(new ItemStack(Itemizer.IngotSapphire, 9, 0), blockSapphire);
		
		GameRegistry.addRecipe(new ItemStack(blockLyon), "LLL", "LLL", "LLL", 'L', Itemizer.IngotLyon);
		GameRegistry.addShapelessRecipe(new ItemStack(Itemizer.IngotLyon, 9, 0), blockLyon);
		
		GameRegistry.addRecipe(new ItemStack(blockMystite), "LLL", "LLL", "LLL", 'L', Itemizer.IngotMystite);
		GameRegistry.addShapelessRecipe(new ItemStack(Itemizer.IngotMystite, 9, 0), blockMystite);
		
		GameRegistry.addShapelessRecipe(new ItemStack(record_TakeBackTehNight, 1, 0), Items.record_cat, Items.record_mall);
		
		GameRegistry.addRecipe(new ItemStack(record_Blank), "VVV", "V V", "VVV", 'V', itemVinyl);
		
		GameRegistry.addRecipe(new ItemStack(itemLaser), "IrI", "IrI", " I ", 'I', Items.iron_ingot, 'r', Items.redstone);
		
		
		if(Loader.isModLoaded("ProjectE"))
		{
			ItemStack input1 = new ItemStack(ObjHandler.kleinStars, 1, 5);
			ItemStack output1 = new ItemStack(KlienStarGamma, 1);
			GameRegistry.addRecipe(new RecipeShapelessHidden(output1, input1, input1, input1, input1));
			
			
			ItemStack input2 = new ItemStack(KlienStarGamma, 1, 0);
			ItemStack output2 = new ItemStack(KlienStarGamma, 1, 2);
			GameRegistry.addShapelessRecipe(new ItemStack(KlienStarGamma, 1, 1), input2, input2, input2, input2);
		
			
			
		}
		
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
