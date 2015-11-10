package hermitcore.tcon.smeltery;

import hermitcore.HECore;
import hermitcore.tcon.smeltery.blocks.HermitFluid;
import hermitcore.tcon.smeltery.items.FilledBucket;
import hermitcore.utils.HELogger;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mantle.blocks.BlockUtils;
import mantle.pulsar.pulse.Handler;
import mantle.pulsar.pulse.Pulse;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import net.nevermine.izer.Blockizer;
import net.nevermine.izer.Itemizer;
import tconstruct.TConstruct;
import tconstruct.library.TConstructRegistry;
import tconstruct.library.crafting.FluidType;
import tconstruct.library.crafting.LiquidCasting;
import tconstruct.library.crafting.Smeltery;
import tconstruct.library.util.IPattern;
import tconstruct.smeltery.TinkerSmeltery;
import tconstruct.tools.TinkerTools;
import tconstruct.util.config.PHConstruct;
import tconstruct.world.items.OreBerries;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;

@SuppressWarnings("unused")
@ObjectHolder(HECore.MODID)
@Pulse(id = "Hermit Core' Smeltery", description = "Adds ability to use TConstruct's smeltery on AoA Ores and bars")
public class HermitSmeltery {
	
	
	//Item
	public static Item metalPattern;
	public static Item buckets;
	
	//Liquids
	public static Material liquidMetal;
	public static Fluid moltenLimoniteFluid;
	public static Fluid moltenAmethystFluid;
	public static Fluid moltenRositeFluid;
	public static Fluid moltenSapphireFluid;
	public static Fluid moltenMystiteFluid;
	public static Fluid moltenSkeletalFluid;
	public static Fluid moltenLyonFluid;
	public static Fluid moltenVarsiumFluid;
	public static Block moltenLimonite;
	public static Block moltenAmethyst;
	public static Block moltenRosite;
	public static Block moltenSapphire;
	public static Block moltenMystite;
	public static Block moltenSkeletal;
	public static Block moltenLyon;
	public static Block moltenVarsium;
	
	
    public static Fluid[] fluids = new Fluid[7];
    public static Block[] fluidBlocks = new Block[7];
    public static FluidStack[] liquids;
	
	
    @Handler
    public void preInit (FMLPreInitializationEvent event)
    {
        HermitSmelteryEvents smelteryEvents = new HermitSmelteryEvents();
        MinecraftForge.EVENT_BUS.register(smelteryEvents);
        FMLCommonHandler.instance().bus().register(smelteryEvents);
        
        
        HermitSmeltery.buckets = new FilledBucket(BlockUtils.getBlockFromItem(HermitSmeltery.buckets));
        GameRegistry.registerItem(HermitSmeltery.buckets, "buckets");
        
        HermitSmeltery.liquidMetal = new MaterialLiquid(MapColor.tntColor);
    	
    	HermitSmeltery.moltenLimoniteFluid = registerFluid("limonite");
    	HermitSmeltery.moltenLimonite = HermitSmeltery.moltenLimoniteFluid.getBlock();
    	
    	HermitSmeltery.moltenAmethystFluid = registerFluid("amethyst");
    	HermitSmeltery.moltenAmethyst = HermitSmeltery.moltenAmethystFluid.getBlock();
    	
    	HermitSmeltery.moltenRositeFluid = registerFluid("rosite");
    	HermitSmeltery.moltenRosite = HermitSmeltery.moltenRositeFluid.getBlock();
    	
    	HermitSmeltery.moltenSapphireFluid = registerFluid("sapphire");
    	HermitSmeltery.moltenSapphire = HermitSmeltery.moltenSapphireFluid.getBlock();

    	HermitSmeltery.moltenMystiteFluid = registerFluid("mystite");
    	HermitSmeltery.moltenMystite = HermitSmeltery.moltenMystiteFluid.getBlock();

    	HermitSmeltery.moltenSkeletalFluid = registerFluid("skeletal");
    	HermitSmeltery.moltenSkeletal = HermitSmeltery.moltenSkeletalFluid.getBlock();
    	
    	HermitSmeltery.moltenLyonFluid = registerFluid("lyon");
    	HermitSmeltery.moltenLyon = HermitSmeltery.moltenLyonFluid.getBlock();
    	
    	HermitSmeltery.moltenVarsiumFluid = registerFluid("varsium");
    	HermitSmeltery.moltenVarsium = HermitSmeltery.moltenVarsiumFluid.getBlock();

    	
    	
    	FluidType.registerFluidType("Limonite", HermitSmeltery.moltenLimonite, 0, 600, HermitSmeltery.moltenLimoniteFluid, true);
    	FluidType.registerFluidType("Amethyst", HermitSmeltery.moltenAmethyst, 0, 600, HermitSmeltery.moltenAmethystFluid, true);
    	FluidType.registerFluidType("Rosite", HermitSmeltery.moltenRosite, 0, 600, HermitSmeltery.moltenRositeFluid, true);
    	FluidType.registerFluidType("Mystite", HermitSmeltery.moltenMystite, 0, 600, HermitSmeltery.moltenMystiteFluid, true);
    	FluidType.registerFluidType("Sapphire", HermitSmeltery.moltenSapphire, 0, 600, HermitSmeltery.moltenSapphireFluid, true);
    	FluidType.registerFluidType("Varsium", HermitSmeltery.moltenVarsium, 0, 600, HermitSmeltery.moltenVarsiumFluid, true);



    	
    	HermitSmeltery.fluids = new Fluid[] { HermitSmeltery.moltenLimoniteFluid, HermitSmeltery.moltenAmethystFluid, HermitSmeltery.moltenRositeFluid, HermitSmeltery.moltenSapphireFluid, HermitSmeltery.moltenMystiteFluid, HermitSmeltery.moltenSkeletalFluid, HermitSmeltery.moltenVarsiumFluid};
    	HermitSmeltery.fluidBlocks = new Block[] {HermitSmeltery.moltenLimonite, HermitSmeltery.moltenAmethyst, HermitSmeltery.moltenRosite, HermitSmeltery.moltenSapphire, HermitSmeltery.moltenMystite, HermitSmeltery.moltenSkeletal, HermitSmeltery.moltenVarsium};
    	
    	
        //Items

    }
    
    @Handler
    public void init (FMLInitializationEvent event)
    {
    	addRecipesForSmeltery();
    	addRecipesForTableCasting();
    	addRecipesForBasinCasting();
    	
    	//addRecipesForCastingBasin();


    }
    @Handler
    public void postInit (FMLPostInitializationEvent evt)
    {
    	addOreDictionarySmelteryRecipes();
    }
    public void addOreDictionarySmelteryRecipes ()
    {
        List<FluidType> exceptions = Arrays.asList(new FluidType[] { FluidType.getFluidType("Water"), FluidType.getFluidType("Stone"), FluidType.getFluidType("Emerald"), FluidType.getFluidType("Ender"), FluidType.getFluidType("Glass"), FluidType.getFluidType("Slime"), FluidType.getFluidType("Obsidian") });
        Iterator iter = FluidType.fluidTypes.entrySet().iterator();
        while (iter.hasNext())
        {
            Map.Entry pairs = (Map.Entry) iter.next();
            FluidType ft = (FluidType) pairs.getValue();
            if (exceptions.contains(ft))
                continue;
            String fluidTypeName = (String) pairs.getKey();

            // Nuggets
            Smeltery.addDictionaryMelting("nugget" + fluidTypeName, ft, -100, TConstruct.nuggetLiquidValue);
            registerNuggetCasting(ft, "nugget" + fluidTypeName);

            // Ingots, Dust
            registerIngotCasting(ft, "ingot" + fluidTypeName);
            Smeltery.addDictionaryMelting("ingot" + fluidTypeName, ft, -50, TConstruct.ingotLiquidValue);
            Smeltery.addDictionaryMelting("dust" + fluidTypeName, ft, -75, TConstruct.ingotLiquidValue);

            // Factorization support
            Smeltery.addDictionaryMelting("crystalline" + fluidTypeName, ft, -50, TConstruct.ingotLiquidValue);

            // Ores
            Smeltery.addDictionaryMelting("ore" + fluidTypeName, ft, 0, (int) (TConstruct.ingotLiquidValue * PHConstruct.ingotsPerOre));

            // NetherOres support
            Smeltery.addDictionaryMelting("oreNether" + fluidTypeName, ft, 75, (int) (TConstruct.ingotLiquidValue * PHConstruct.ingotsPerOre * 2));

            // DenseOres support
            Smeltery.addDictionaryMelting("denseore" + fluidTypeName, ft, 75, (int) (TConstruct.ingotLiquidValue * PHConstruct.ingotsPerOre * 3));

            // DenseOres support
            Smeltery.addDictionaryMelting("orePoor" + fluidTypeName, ft, 75, (int) (TConstruct.nuggetLiquidValue * PHConstruct.ingotsPerOre));

            // Blocks
            registerBlockCasting(ft, "block" + fluidTypeName);
            Smeltery.addDictionaryMelting("block" + fluidTypeName, ft, 100, TConstruct.blockLiquidValue);

            if (ft.isToolpart)
            {
                TinkerTools.registerPatternMaterial("ingot" + fluidTypeName, 2, fluidTypeName);
                TinkerTools.registerPatternMaterial("block" + fluidTypeName, 18, fluidTypeName);
            }
        }
        // Obsidian, different dust amount
        {
            FluidType ft = FluidType.getFluidType("Obsidian");
            String fluidTypeName = "Obsidian";
            Smeltery.addDictionaryMelting("nugget" + fluidTypeName, ft, -100, TConstruct.nuggetLiquidValue);
            registerNuggetCasting(ft, "nugget" + fluidTypeName);

            // Ingots, Dust
            registerIngotCasting(ft, "ingot" + fluidTypeName);
            Smeltery.addDictionaryMelting("ingot" + fluidTypeName, ft, -50, TConstruct.ingotLiquidValue);
            Smeltery.addDictionaryMelting("dust" + fluidTypeName, ft, -75, TConstruct.ingotLiquidValue / 4);

            // Factorization support
            Smeltery.addDictionaryMelting("crystalline" + fluidTypeName, ft, -50, TConstruct.ingotLiquidValue);

            // Ores
            Smeltery.addDictionaryMelting("ore" + fluidTypeName, ft, 0, ((int) TConstruct.ingotLiquidValue * (int) PHConstruct.ingotsPerOre));

            // Poor ores
            Smeltery.addDictionaryMelting("orePoor" + fluidTypeName, ft, 0, (int) (TConstruct.nuggetLiquidValue * PHConstruct.ingotsPerOre * 1.5f));

            // NetherOres support
            Smeltery.addDictionaryMelting("oreNether" + fluidTypeName, ft, 75, ((int) TConstruct.ingotLiquidValue * (int) PHConstruct.ingotsPerOre * 2));

            // Blocks
            Smeltery.addDictionaryMelting("block" + fluidTypeName, ft, 100, TConstruct.ingotLiquidValue*2);

            if (ft.isToolpart)
            {
                TinkerTools.registerPatternMaterial("ingot" + fluidTypeName, 2, fluidTypeName);
                TinkerTools.registerPatternMaterial("block" + fluidTypeName, 18, fluidTypeName);
            }
        }

        // Compressed materials. max 4x because it's too much otherwise.
        for (int i = 1; i <= 4; i++)
        {
            Smeltery.addDictionaryMelting("compressedCobblestone" + i + "x", FluidType.getFluidType("Stone"), 0, TConstruct.stoneLiquidValue * (int) Math.pow(9, i));
        }
        Smeltery.addDictionaryMelting("compressedSand1x", FluidType.getFluidType("Glass"), 175, FluidContainerRegistry.BUCKET_VOLUME * 9);
        Smeltery.addDictionaryMelting("compressedSand2x", FluidType.getFluidType("Glass"), 175, FluidContainerRegistry.BUCKET_VOLUME * 9 * 9);
    }


    private void addRecipesForTableCasting ()
    {
    	ItemStack ingotcast = new ItemStack(TinkerSmeltery.metalPattern, 1, 0);
    	
    	LiquidCasting tableCasting = TConstructRegistry.getTableCasting();
    	
    	tableCasting.addCastingRecipe(new ItemStack(Itemizer.IngotLimonite), new FluidStack(HermitSmeltery.moltenLimoniteFluid, TConstruct.ingotLiquidValue), ingotcast, 80);
    	tableCasting.addCastingRecipe(new ItemStack(Itemizer.AmethystIngot), new FluidStack(HermitSmeltery.moltenAmethystFluid, TConstruct.ingotLiquidValue), ingotcast, 80);
    	tableCasting.addCastingRecipe(new ItemStack(Itemizer.IngotRosite), new FluidStack(HermitSmeltery.moltenRositeFluid, TConstruct.ingotLiquidValue), ingotcast, 80);
    	tableCasting.addCastingRecipe(new ItemStack(Itemizer.IngotMystite), new FluidStack(HermitSmeltery.moltenMystiteFluid, TConstruct.ingotLiquidValue), ingotcast, 80);

    	//tableCasting


        ItemStack bucket = new ItemStack(Items.bucket);

        //Item thermalBucket = GameRegistry.findItem("ThermalFoundation", "bucket");

        for (int sc = 0; sc < 6; sc++)
        {
            if (HermitSmeltery.fluids[sc] != null) {
               //HC support
            	tableCasting.addCastingRecipe(new ItemStack(HermitSmeltery.buckets, 1, sc), new FluidStack(HermitSmeltery.fluids[sc], FluidContainerRegistry.BUCKET_VOLUME), bucket, true, 10);
            }
        }
        
        HermitSmeltery.liquids = new FluidStack[] { new FluidStack(HermitSmeltery.moltenLimoniteFluid, 1), new FluidStack(HermitSmeltery.moltenSapphireFluid, 1), new FluidStack(HermitSmeltery.moltenAmethystFluid, 1), new FluidStack(HermitSmeltery.moltenRositeFluid, 1), new FluidStack(HermitSmeltery.moltenVarsiumFluid, 1), new FluidStack(HermitSmeltery.moltenLyonFluid, 1), new FluidStack(HermitSmeltery.moltenMystiteFluid, 1) };
        int[] liquidDamages = new int[] { 40, 41, 42, 43, 44, 45, 46 }; // ItemStack
                                                                                 // damage
                                                                                 // value
        int fluidAmount = 0;
        Fluid fs = null;
        
        
        //ItemStack rod = new ItemStack(TinkerSmeltery.metalPattern, 1, 1);
    	ItemStack pickaxe = new ItemStack(TinkerSmeltery.metalPattern, 1, 2);



        /*for (int i = 0; i < 7; i++)
        {
        	
            fs = HermitSmeltery.liquids[i].getFluid();
            fluidAmount = ((IPattern) TinkerSmeltery.metalPattern).getPatternCost(pickaxe) * TConstruct.ingotLiquidValue / 2;
            ItemStack metalCast = new ItemStack(TinkerTools.patternOutputs[2], 1, liquidDamages[i]);
            tableCasting.addCastingRecipe(metalCast, new FluidStack(fs, fluidAmount), pickaxe, 50);
            Smeltery.addMelting(FluidType.getFluidType(fs), metalCast, 0, fluidAmount);
        }*/
        
        for (int iter = 0; iter < 21; iter++)
        {
            //if (HermitTools.patternOutputs[iter] != null)
            //{
                ItemStack cast = new ItemStack(TinkerSmeltery.metalPattern, 1, iter + 1);

                //tableCasting.addCastingRecipe(cast, new FluidStack(TinkerSmeltery.moltenAlubrassFluid, TConstruct.ingotLiquidValue), new ItemStack(TinkerTools.patternOutputs[iter], 1, Short.MAX_VALUE), false, 50);
                //tableCasting.addCastingRecipe(cast, new FluidStack(TinkerSmeltery.moltenGoldFluid, TConstruct.ingotLiquidValue * 2), new ItemStack(TinkerTools.patternOutputs[iter], 1, Short.MAX_VALUE), false, 50);

                for (int iterTwo = 0; iterTwo < HermitSmeltery.liquids.length; iterTwo++)
                {
                    fs = HermitSmeltery.liquids[iterTwo].getFluid();
                    fluidAmount = ((IPattern) TinkerSmeltery.metalPattern).getPatternCost(cast) * TConstruct.ingotLiquidValue / 2;
                    ItemStack metalCast = new ItemStack(TinkerTools.patternOutputs[iter], 1, liquidDamages[iterTwo]);
                    tableCasting.addCastingRecipe(metalCast, new FluidStack(fs, fluidAmount), cast, 50);
                    Smeltery.addMelting(FluidType.getFluidType(fs), metalCast, 0, fluidAmount);
                }
        	//}
        }


    }
    protected void addRecipesForBasinCasting ()
    {
        LiquidCasting basinCasting = TConstructRegistry.getBasinCasting();
        // Block Casting
        
    }
    
	protected static void addRecipesForSmeltery ()
    {
    	//Items   
		Smeltery.addMelting(FluidType.getFluidType("Limonite"), new ItemStack(Itemizer.IngotLimonite), 0, TConstruct.ingotLiquidValue);
		Smeltery.addMelting(FluidType.getFluidType("Rosite"), new ItemStack(Itemizer.IngotRosite), 0, TConstruct.ingotLiquidValue);
		Smeltery.addMelting(FluidType.getFluidType("Amethyst"), new ItemStack(Itemizer.AmethystIngot), 0, TConstruct.ingotLiquidValue);
		Smeltery.addMelting(FluidType.getFluidType("Sapphire"), new ItemStack(Itemizer.IngotSapphire), 0, TConstruct.ingotLiquidValue);
		Smeltery.addMelting(FluidType.getFluidType("Mystite"), new ItemStack(Itemizer.IngotMystite), 0, TConstruct.ingotLiquidValue);




		
    	//Ores
    	Smeltery.addMelting(Blockizer.oreLimonite, 0, 600, new FluidStack(HermitSmeltery.moltenLimoniteFluid, TConstruct.ingotLiquidValue * 2));
    	Smeltery.addMelting(Blockizer.oreRosite, 0, 600, new FluidStack(HermitSmeltery.moltenRositeFluid, TConstruct.ingotLiquidValue * 2));
    	Smeltery.addMelting(Blockizer.oreSapphire, 0, 600, new FluidStack(HermitSmeltery.moltenSapphireFluid, TConstruct.ingotLiquidValue * 2));
    	Smeltery.addMelting(Blockizer.oreAmethyst, 0, 600, new FluidStack(HermitSmeltery.moltenAmethystFluid, TConstruct.ingotLiquidValue * 2));
    	Smeltery.addMelting(Blockizer.oreMystite, 0, 600, new FluidStack(HermitSmeltery.moltenMystiteFluid, TConstruct.ingotLiquidValue * 2));
    	Smeltery.addMelting(Blockizer.OreVarsium, 0, 600, new FluidStack(HermitSmeltery.moltenVarsiumFluid, TConstruct.ingotLiquidValue * 2));
    	Smeltery.addMelting(Blockizer.OreLyon, 0, 600, new FluidStack(HermitSmeltery.moltenLyonFluid, TConstruct.ingotLiquidValue * 2));



    }
    
    private void registerIngotCasting (FluidType ft, String name)
	{
	    ItemStack pattern = new ItemStack(HermitSmeltery.metalPattern, 1, 0);
	    LiquidCasting tableCasting = TConstructRegistry.getTableCasting();
	    for (ItemStack ore : OreDictionary.getOres(name))
	    {
	        tableCasting.addCastingRecipe(new ItemStack(ore.getItem(), 1, ore.getItemDamage()), new FluidStack(ft.fluid, TConstruct.ingotLiquidValue), pattern, 80);
	    }
	}
    private void registerNuggetCasting (FluidType ft, String name)
    {
        ItemStack pattern = new ItemStack(HermitSmeltery.metalPattern, 1, 27);
        LiquidCasting tableCasting = TConstructRegistry.getTableCasting();
        for (ItemStack ore : OreDictionary.getOres(name))
        {
            // don't do oreberries. That'd be silly.
            if(ore.getItem() != null && ore.getItem() instanceof OreBerries) {
                boolean isOreberry = false;
                for(int id : OreDictionary.getOreIDs(ore))
                    if(OreDictionary.getOreName(id).startsWith("oreberry"))
                        isOreberry = true;

                if(isOreberry)
                    continue;
            }
            //tableCasting.addCastingRecipe(pattern, new FluidStack(TinkerSmeltery.moltenAlubrassFluid, TConstruct.ingotLiquidValue), new ItemStack(ore.getItem(), 1, ore.getItemDamage()), false, 50);
            //tableCasting.addCastingRecipe(pattern, new FluidStack(TinkerSmeltery.moltenGoldFluid, TConstruct.ingotLiquidValue * 2), new ItemStack(ore.getItem(), 1, ore.getItemDamage()), false, 50);
            tableCasting.addCastingRecipe(new ItemStack(ore.getItem(), 1, ore.getItemDamage()), new FluidStack(ft.fluid, TConstruct.nuggetLiquidValue), pattern, 40);
        }
    }

    private void registerBlockCasting (FluidType ft, String name)
    {
        for (ItemStack ore : OreDictionary.getOres(name))
        {
            TConstructRegistry.getBasinCasting().addCastingRecipe(new ItemStack(ore.getItem(), 1, ore.getItemDamage()), new FluidStack(ft.fluid, TConstruct.blockLiquidValue), 100);
        }
    }

	public static Fluid registerFluid(String name) {
        return registerFluid(name, "liquid_" + name);
    }

    public static Fluid registerFluid(String name, String texture) {
        return registerFluid(name, name + ".molten", "fluid.molten." + name, texture, 3000, 6000, 1300, Material.lava);
    }

	
    public static Fluid registerFluid(String name, String fluidName, String blockName, String texture, int density, int viscosity, int temperature, Material material) {
        // create the new fluid
        Fluid fluid = new Fluid(fluidName).setDensity(density).setViscosity(viscosity).setTemperature(temperature);

        if(material == Material.lava)
            fluid.setLuminosity(12);

        // register it if it's not already existing
        boolean isFluidPreRegistered = !FluidRegistry.registerFluid(fluid);

        // register our fluid block for the fluid
        // this constructor implicitly does fluid.setBlock to it, that's why it's not called separately
        HermitFluid block = new HermitFluid(fluid, material, texture);
        block.setBlockName(blockName);
        GameRegistry.registerBlock(block, blockName);

        fluid.setBlock(block);
        block.setFluid(fluid);

        // if the fluid was already registered we use that one instead
        if (isFluidPreRegistered)
        {
            fluid = FluidRegistry.getFluid(fluidName);

            // don't change the fluid icons of already existing fluids
            if(fluid.getBlock() != null)
                block.suppressOverwritingFluidIcons();
            // if no block is registered with an existing liquid, we set our own
            else
                fluid.setBlock(block);
        }

        if (FluidContainerRegistry.fillFluidContainer(new FluidStack(fluid, 1000), new ItemStack(Items.bucket)) == null) {
            // custom hacks for teh lookup. hoooray for inconsintency.
            if(name.equals("aluminiumbrass"))
                name = "alubrass";
            if(name.equals("platinum"))
                name = "shiny";
            boolean reg = false;
            for(int i = 0; i < FilledBucket.textureNames.length; i++)
                if(FilledBucket.textureNames[i].equals(name)) {
                    FluidContainerRegistry.registerFluidContainer(new FluidContainerData(new FluidStack(fluid, 1000), new ItemStack(HermitSmeltery.buckets, 1, i), new ItemStack(Items.bucket)));
                    reg = true;
                }

            if(!reg)
                HELogger.logger.error("Couldn't register fluid container for " + name);
        }

        return fluid;
        
    }   
}