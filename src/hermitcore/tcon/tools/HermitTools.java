package hermitcore.tcon.tools;

import static net.minecraft.util.EnumChatFormatting.*;
import tconstruct.library.TConstructRegistry;
import tconstruct.library.crafting.PatternBuilder;
import tconstruct.library.tools.ToolCore;
import tconstruct.modifiers.tools.ModAttack;
import tconstruct.modifiers.tools.ModFlux;
import tconstruct.modifiers.tools.ModLapis;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import mantle.pulsar.pulse.Handler;
import mantle.pulsar.pulse.Pulse;
import hermitcore.HECore;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;


@ObjectHolder(HECore.MODID)
@Pulse(id = "Hermitcraft Core' Tools", description = "The main core of the mod! All of the tools, the tables, and the patterns are here.", forced = true)

public class HermitTools {

	@Deprecated
    public static ToolCore arrow;

    public static Item toolRod;
    public static Item toolShard;
	
    public static Item binding;
    public static Item toughBinding;
    public static Item toughRod;
    public static Item largePlate;
    public static Item pickaxeHead;
    public static Item shovelHead;
    public static Item hatchetHead;
    public static Item frypanHead;
    public static Item signHead;
    public static Item chiselHead;
    public static Item scytheBlade;
    public static Item broadAxeHead;
    public static Item excavatorHead;
    public static Item hammerHead;
    public static Item swordBlade;
    public static Item largeSwordBlade;
    public static Item knifeBlade;
    public static Item wideGuard;
    
    
    public static ToolCore pickaxe;
    public static ToolCore shovel;
    public static ToolCore hatchet;
    public static ToolCore broadsword;
    public static ToolCore longsword;
    public static ToolCore rapier;
    public static ToolCore dagger;
    public static ToolCore cutlass;
    public static ToolCore frypan;
    public static ToolCore battlesign;
    public static ToolCore chisel;
    public static ToolCore mattock;
    public static ToolCore scythe;
    public static ToolCore lumberaxe;
    public static ToolCore cleaver;
    public static ToolCore hammer;
    public static ToolCore battleaxe;
    public static Item potionLauncher;
    public static Item handGuard;
    public static Item crossbar;
    public static Item fullGuard;
    public static Block craftedSoil; //TODO: Untwine this
    public static Block multiBrick;
    public static Block multiBrickFancy;
    public static Block multiBrickMetal;
    // Tool modifiers
    public static ModFlux modFlux;
    public static ModLapis modLapis;
    public static ModAttack modAttack;
    public static Item[] patternOutputs;
    public static Item woodPattern;
    //public static Item manualBook;
    public static ToolCore excavator;
    public static Item creativeModifier;
	
	@Handler
	public void preInit(FMLPreInitializationEvent event)
	{
	
		/*
        HermitTools.toolRod = new DynamicToolPart("_rod", "ToolRod");
        HermitTools.toolShard = new ToolShard("_chunk", "ToolShard");
		
        HermitTools.pickaxe = new Pickaxe();
        HermitTools.shovel = new Shovel();
        HermitTools.hatchet = new Hatchet();
        HermitTools.broadsword = new Broadsword();
        HermitTools.longsword = new Longsword();
        HermitTools.rapier = new Rapier();
        HermitTools.dagger = new Dagger();
        HermitTools.cutlass = new Cutlass();

        HermitTools.frypan = new FryingPan();
        HermitTools.battlesign = new BattleSign();
        HermitTools.mattock = new Mattock();
        HermitTools.chisel = new Chisel();

        HermitTools.lumberaxe = new LumberAxe();
        HermitTools.cleaver = new Cleaver();
        HermitTools.scythe = new Scythe();
        HermitTools.excavator = new Excavator();
        HermitTools.hammer = new Hammer();
        HermitTools.battleaxe = new Battleaxe();

        HermitTools.arrow = new Arrow(); // to prevent nullpointers

        Item[] tools = { HermitTools.pickaxe, HermitTools.shovel, HermitTools.hatchet, HermitTools.broadsword, HermitTools.longsword, HermitTools.rapier, HermitTools.dagger, HermitTools.cutlass, HermitTools.frypan, HermitTools.battlesign, HermitTools.mattock, HermitTools.chisel, HermitTools.lumberaxe, HermitTools.cleaver, HermitTools.scythe, HermitTools.excavator, HermitTools.hammer, HermitTools.battleaxe};
        String[] toolStrings = { "pickaxe", "shovel", "hatchet", "broadsword", "longsword", "rapier", "dagger", "cutlass", "frypan", "battlesign", "mattock", "chisel", "lumberaxe", "cleaver", "scythe", "excavator", "hammer", "battleaxe"};

        for (int i = 0; i < tools.length; i++)
        {
            GameRegistry.registerItem(tools[i], toolStrings[i]); // 1.7 compat
            TConstructRegistry.addItemToDirectory(toolStrings[i], tools[i]);
        }
        
        HermitTools.pickaxeHead = new DynamicToolPart("_pickaxe_head", "PickaxeHead");
        HermitTools.shovelHead = new DynamicToolPart("_shovel_head", "ShovelHead");
        HermitTools.hatchetHead = new DynamicToolPart("_axe_head", "AxeHead");
        HermitTools.binding = new DynamicToolPart("_binding", "Binding");
        HermitTools.toughBinding = new DynamicToolPart("_toughbind", "ToughBinding");
        HermitTools.toughRod = new DynamicToolPart("_toughrod", "ToughRod");
        HermitTools.largePlate = new DynamicToolPart("_largeplate", "LargePlate");

        HermitTools.swordBlade = new DynamicToolPart("_sword_blade", "SwordBlade");
        HermitTools.wideGuard = new DynamicToolPart("_large_guard", "LargeGuard");
        HermitTools.handGuard = new DynamicToolPart("_medium_guard", "MediumGuard");
        HermitTools.crossbar = new DynamicToolPart("_crossbar", "Crossbar");
        HermitTools.knifeBlade = new DynamicToolPart("_knife_blade", "KnifeBlade");
        HermitTools.fullGuard = new DynamicToolPart("_full_guard", "FullGuard").hide();

        HermitTools.frypanHead = new DynamicToolPart("_frypan_head", "FrypanHead");
        HermitTools.signHead = new DynamicToolPart("_battlesign_head", "SignHead");
        HermitTools.chiselHead = new DynamicToolPart("_chisel_head", "ChiselHead");

        HermitTools.scytheBlade = new DynamicToolPart("_scythe_head", "ScytheHead");
        HermitTools.broadAxeHead = new DynamicToolPart("_lumberaxe_head", "LumberAxeHead");
        HermitTools.excavatorHead = new DynamicToolPart("_excavator_head", "ExcavatorHead");
        HermitTools.largeSwordBlade = new DynamicToolPart("_large_sword_blade", "LargeSwordBlade");
        HermitTools.hammerHead = new DynamicToolPart("_hammer_head", "HammerHead");
        
        Item[] toolParts = { HermitTools.toolRod, HermitTools.toolShard, HermitTools.pickaxeHead, HermitTools.shovelHead, HermitTools.hatchetHead, HermitTools.binding, HermitTools.toughBinding, HermitTools.toughRod, HermitTools.largePlate, HermitTools.swordBlade, HermitTools.wideGuard, HermitTools.handGuard, HermitTools.crossbar, HermitTools.knifeBlade, HermitTools.fullGuard, HermitTools.frypanHead, HermitTools.signHead, HermitTools.chiselHead, HermitTools.scytheBlade, HermitTools.broadAxeHead, HermitTools.excavatorHead, HermitTools.largeSwordBlade, HermitTools.hammerHead};
        String[] toolPartStrings = { "toolRod", "toolShard", "pickaxeHead", "shovelHead", "hatchetHead", "binding", "toughBinding", "toughRod", "heavyPlate", "swordBlade", "wideGuard", "handGuard", "crossbar", "knifeBlade", "fullGuard", "frypanHead", "signHead", "chiselHead", "scytheBlade", "broadAxeHead", "excavatorHead", "largeSwordBlade", "hammerHead" };

        for (int i = 0; i < toolParts.length; i++)
        {
            GameRegistry.registerItem(toolParts[i], toolPartStrings[i]); // 1.7
                                                                         // compat
            TConstructRegistry.addItemToDirectory(toolPartStrings[i], toolParts[i]);
        }
        
        String[] materialStrings = { "limonite", "amethyst", "rosite", "sapphire", "varsium", "lyon", "mystite" };

        for (int i = 0; i < materialStrings.length; i++)
        {
            TConstructRegistry.addItemStackToDirectory(materialStrings[i], new ItemStack(TinkerTools.materials, 1, i));
        }*/

        
        registerMaterials();
        
        //HermitTools.patternOutputs = new Item[] { HermitTools.toolRod, HermitTools.pickaxeHead, HermitTools.shovelHead, HermitTools.hatchetHead, HermitTools.swordBlade, HermitTools.wideGuard, HermitTools.handGuard, HermitTools.crossbar, HermitTools.binding, HermitTools.frypanHead, HermitTools.signHead, HermitTools.knifeBlade, HermitTools.chiselHead, HermitTools.toughRod, HermitTools.toughBinding, HermitTools.largePlate, HermitTools.broadAxeHead, HermitTools.scytheBlade, HermitTools.excavatorHead, HermitTools.largeSwordBlade, HermitTools.hammerHead, HermitTools.fullGuard, null, null, TinkerWeaponry.arrowhead, null };

	}
    @Handler
    public void init (FMLInitializationEvent event)
    {
        //addPartMapping();
        //addRecipesForToolBuilder();
        //addRecipesForChisel();
        //craftingTableRecipes();
        //setupToolTabs();
        //proxy.initialize();
    }

    @Handler
    public void postInit (FMLPostInitializationEvent evt)
    {
        //vanillaToolRecipes();
        //addOreDictPartMapping();
        //modIntegration();
        //metalPartCraftingIntegration();

        // Fix for chisels harvetslevel derp
        if("chisel".equals(Blocks.stone.getHarvestTool(0)))
            Blocks.stone.setHarvestLevel("pickaxe", 0, 0);
    }
    /*private void addPartMapping ()
    {
        /* Tools 

        int[] nonMetals = { 0, 1, 3, 4, 5, 6, 7, 8, 9, 17 };

        if (PHConstruct.craftMetalTools)
        {
            for (int mat = 0; mat < 46; mat++)
            {
                for (int meta = 0; meta < TinkerTools.patternOutputs.length; meta++)
                {
                    if (TinkerTools.patternOutputs[meta] != null)
                        TConstructRegistry.addPartMapping(TinkerTools.woodPattern, meta + 1, mat, new ItemStack(TinkerTools.patternOutputs[meta], 1, mat));
                }
            }
        }
        else
        {
            for (int mat = 0; mat < nonMetals.length; mat++)
            {
                for (int meta = 0; meta < HermitTools.patternOutputs.length; meta++)
                {
                    if (TinkerTools.patternOutputs[meta] != null)
                        TConstructRegistry.addPartMapping(TinkerTools.woodPattern, meta + 1, nonMetals[mat], new ItemStack(TinkerTools.patternOutputs[meta], 1, nonMetals[mat]));
                }
            }
        }
    }*/
    /*
    private void metalPartCraftingIntegration()
    {

        String[] metals = {"Limonite", "Amethyst", "Rosite", "Sapphire", "Varsium", "Lyon", "Mystite", "Skeletal"};

        for(String metal : metals) {
            HermitTools.registerPatternMaterial("ingot" + metal, 2, metal);
            HermitTools.registerPatternMaterial("block" + metal, 18, metal);
        }
    }*/
    
  
	/*private void addRecipesForToolBuilder ()
    {
        ToolBuilder tb = ToolBuilder.instance;
        tb.addNormalToolRecipe(HermitTools.pickaxe, HermitTools.pickaxeHead, HermitTools.toolRod, HermitTools.binding);
        tb.addNormalToolRecipe(HermitTools.broadsword, HermitTools.swordBlade, HermitTools.toolRod, HermitTools.wideGuard);
        tb.addNormalToolRecipe(HermitTools.hatchet, HermitTools.hatchetHead, HermitTools.toolRod);
        tb.addNormalToolRecipe(HermitTools.shovel, HermitTools.shovelHead, HermitTools.toolRod);
        tb.addNormalToolRecipe(HermitTools.longsword, HermitTools.swordBlade, HermitTools.toolRod, HermitTools.handGuard);
        tb.addNormalToolRecipe(HermitTools.rapier, HermitTools.swordBlade, HermitTools.toolRod, HermitTools.crossbar);
        tb.addNormalToolRecipe(HermitTools.frypan, HermitTools.frypanHead, HermitTools.toolRod);
        tb.addNormalToolRecipe(HermitTools.battlesign, HermitTools.signHead, HermitTools.toolRod);
        tb.addNormalToolRecipe(HermitTools.mattock, HermitTools.hatchetHead, HermitTools.toolRod, HermitTools.shovelHead);
        tb.addNormalToolRecipe(HermitTools.dagger, HermitTools.knifeBlade, HermitTools.toolRod, HermitTools.crossbar);
        tb.addNormalToolRecipe(HermitTools.cutlass, HermitTools.swordBlade, HermitTools.toolRod, HermitTools.fullGuard);
        tb.addNormalToolRecipe(HermitTools.chisel, HermitTools.chiselHead, HermitTools.toolRod);

        tb.addNormalToolRecipe(HermitTools.scythe, HermitTools.scytheBlade, HermitTools.toughRod, HermitTools.toughBinding, HermitTools.toughRod);
        tb.addNormalToolRecipe(HermitTools.lumberaxe, HermitTools.broadAxeHead, HermitTools.toughRod, HermitTools.largePlate, HermitTools.toughBinding);
        tb.addNormalToolRecipe(HermitTools.cleaver, HermitTools.largeSwordBlade, HermitTools.toughRod, HermitTools.largePlate, HermitTools.toughRod);
        tb.addNormalToolRecipe(HermitTools.excavator, HermitTools.excavatorHead, HermitTools.toughRod, HermitTools.largePlate, HermitTools.toughBinding);
        tb.addNormalToolRecipe(HermitTools.hammer, HermitTools.hammerHead, HermitTools.toughRod, HermitTools.largePlate, HermitTools.largePlate);
        tb.addNormalToolRecipe(HermitTools.battleaxe, HermitTools.broadAxeHead, HermitTools.toughRod, HermitTools.broadAxeHead, HermitTools.toughBinding);


    }*/
    
    public static void registerPatternMaterial (String oreName, int value, String materialName)
    {
        for (ItemStack ore : OreDictionary.getOres(oreName))
        {
            PatternBuilder.instance.registerMaterial(ore, value, materialName);
        }
    }
    
    void registerMaterials ()
    {
    	TConstructRegistry.addToolMaterial(MaterialID.Limonite, "Limonite", 5, 1000, 1000, 6, 1.3F, 2, 0f, YELLOW.toString(), 0xFF8040);
    	TConstructRegistry.addToolMaterial(MaterialID.Sapphire, "Sapphire", 4, 925, 900, 10, 1.3F, 2, 0f, BLUE.toString(), 0x2554C7);
    	TConstructRegistry.addToolMaterial(MaterialID.Amethyst, "Amethyst", 4, 750, 900, 10, 1.3F, 2, 0f, BLUE.toString(), 0x6C2DC7);
    	TConstructRegistry.addToolMaterial(MaterialID.Rosite, "Rosite", 5, 750, 1500, 10, 1.3F, 2, 0f, RED.toString(), 0xF62217);
    	TConstructRegistry.addToolMaterial(MaterialID.Varsium, "Varsium", 6, 1550, 900, 10, 1.3F, 2, 0f, GRAY.toString(), 0xFF8050);
    	TConstructRegistry.addToolMaterial(MaterialID.Lyon, "Lyon", 6, 750, 1550, 10, 1.3F, 2, 0f, GRAY.toString(), 0x7E3517);
    	TConstructRegistry.addToolMaterial(MaterialID.Mystite, "Mystite", 7, 750, 2000, 10, 1.3F, 2, 0f, AQUA.toString(), 0x93FFE8);
    	TConstructRegistry.addToolMaterial(MaterialID.Skeletal, "Skeletal", 7, 750, 2000, 10, 1.3F, 2, 0f, GRAY.toString(), 0xEAE9E3);
    	
    	
    	TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Limonite);
    	TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Sapphire);
    	TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Amethyst);
    	TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Rosite);
    	TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Varsium);
    	TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Lyon);
    	TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Mystite);
    	TConstructRegistry.addDefaultToolPartMaterial(MaterialID.Skeletal);
    	
    	
    	
    	
    	
    }
    public static final class MaterialID
    {        
        public static final int Limonite = 40;
        public static final int Sapphire = 41;
        public static final int Amethyst = 42;
        public static final int Rosite = 43;
        public static final int Varsium = 44;
        public static final int Lyon = 45;
        public static final int Mystite = 46;
        public static final int Skeletal = 47;
        public static final int Jade = 48;
        public static final int Emberstone = 49;
    }
    
}
