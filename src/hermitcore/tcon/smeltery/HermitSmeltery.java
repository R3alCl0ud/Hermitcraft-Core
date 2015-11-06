package hermitcore.tcon.smeltery;

import hermitcore.HECore;
import hermitcore.gameObjs.ObjHandler;
import hermitcore.library.HermitRegistry;
import hermitcore.tcon.smeltery.blocks.HermitFluid;
import hermitcore.tcon.smeltery.items.FilledBucket;
import hermitcore.utils.HELogger;
import mantle.blocks.BlockUtils;
import mantle.pulsar.pulse.Handler;
import mantle.pulsar.pulse.Pulse;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.init.Blocks;
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
import tconstruct.TConstruct;
import tconstruct.library.TConstructRegistry;
import tconstruct.library.crafting.FluidType;
import tconstruct.library.crafting.LiquidCasting;
import tconstruct.library.crafting.Smeltery;
import tconstruct.smeltery.TinkerSmeltery;
import tconstruct.smeltery.items.MetalPattern;
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
	
	
    public static Fluid[] fluids = new Fluid[3];
    public static Block[] fluidBlocks = new Block[3];
	
	
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

    	
    	
    	FluidType.registerFluidType("Limonite", HermitSmeltery.moltenLimonite, 0, 600, HermitSmeltery.moltenLimoniteFluid, true);
    	FluidType.registerFluidType("Amethyst", HermitSmeltery.moltenAmethyst, 0, 600, HermitSmeltery.moltenAmethystFluid, true);
    	
    	HermitSmeltery.fluids = new Fluid[] { HermitSmeltery.moltenLimoniteFluid, HermitSmeltery.moltenAmethystFluid, HermitSmeltery.moltenRositeFluid};
    	HermitSmeltery.fluidBlocks = new Block[] {HermitSmeltery.moltenLimonite, HermitSmeltery.moltenAmethyst, HermitSmeltery.moltenRosite};
    	
    	
        //Items
        HermitSmeltery.metalPattern = new MetalPattern("cast_", "materials/").setUnlocalizedName("hermitcore.MetalPattern");
        GameRegistry.registerItem(HermitSmeltery.metalPattern, "metalPattern");
        HermitRegistry.addItemToDirectory("metalPattern", HermitSmeltery.metalPattern);
        String[] patternTypes = { "ingot", "toolRod", "pickaxeHead", "shovelHead", "hatchetHead", "swordBlade", "wideGuard", "handGuard", "crossbar", "binding", "frypanHead", "signHead", "knifeBlade", "chiselHead", "toughRod", "toughBinding", "largePlate", "broadAxeHead", "scytheHead", "excavatorHead", "largeBlade", "hammerHead", "fullGuard" };
        for (int i = 0; i < patternTypes.length; i++)
        {
            HermitRegistry.addItemStackToDirectory(patternTypes[i] + "Cast", new ItemStack(HermitSmeltery.metalPattern, 1, i));
        }
    }
    
    @Handler
    public void init (FMLInitializationEvent event)
    {
    	addRecipesForTableCasting();
    	addRecipesForBasinCasting();
    	addRecipesForSmeltery();
    	//addRecipesForCastingBasin();


    }
    @Handler
    public void postInit (FMLPostInitializationEvent evt)
    {
        
    }

    private void addRecipesForTableCasting ()
    {
    	ItemStack ingotcast = new ItemStack(TinkerSmeltery.metalPattern, 1, 0);
    	LiquidCasting tableCasting = TConstructRegistry.getTableCasting();
    	
    	tableCasting.addCastingRecipe(new ItemStack(ObjHandler.ingotLimonite), new FluidStack(HermitSmeltery.moltenLimoniteFluid, TConstruct.ingotLiquidValue), ingotcast, 80);
        ItemStack bucket = new ItemStack(Items.bucket);

        //Item thermalBucket = GameRegistry.findItem("ThermalFoundation", "bucket");

        for (int sc = 0; sc < 1; sc++)
        {
            if (HermitSmeltery.fluids[sc] != null) {
                // TE support
            	tableCasting.addCastingRecipe(new ItemStack(HermitSmeltery.buckets, 1, sc), new FluidStack(HermitSmeltery.fluids[sc], FluidContainerRegistry.BUCKET_VOLUME), bucket, true, 10);
            }
        }

    }
    protected void addRecipesForBasinCasting ()
    {
        LiquidCasting basinCasting = TConstructRegistry.getBasinCasting();
        // Block Casting
        basinCasting.addCastingRecipe(new ItemStack(Blocks.iron_block), new FluidStack(HermitSmeltery.moltenLimoniteFluid, TConstruct.blockLiquidValue), null, true, 100); // Iron

    }
    
	protected static void addRecipesForSmeltery ()
    {
    	//Items   
		Smeltery.addMelting(FluidType.getFluidType("limonite"), new ItemStack(ObjHandler.ingotLimonite), 0, TConstruct.ingotLiquidValue);
		
    	//Ores
    	Smeltery.addMelting(ObjHandler.oreLimonite, 0, 600, new FluidStack(HermitSmeltery.moltenLimoniteFluid, TConstruct.ingotLiquidValue * 2));
    }
    
    private void registerIngotCasting (FluidType ft, String name)
	{
	    ItemStack pattern = new ItemStack(HermitSmeltery.metalPattern, 1, 0);
	    LiquidCasting tableCasting = TConstructRegistry.getTableCasting();
	    for (ItemStack ore : OreDictionary.getOres(name))
	    {
	        //tableCasting.addCastingRecipe(pattern, new FluidStack(TinkerSmeltery.moltenAlubrassFluid, TConstruct.ingotLiquidValue), new ItemStack(ore.getItem(), 1, ore.getItemDamage()), false, 50);
	        //tableCasting.addCastingRecipe(pattern, new FluidStack(TinkerSmeltery.moltenGoldFluid, TConstruct.ingotLiquidValue * 2), new ItemStack(ore.getItem(), 1, ore.getItemDamage()), false, 50);
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
            HermitRegistry.getBasinCasting().addCastingRecipe(new ItemStack(ore.getItem(), 1, ore.getItemDamage()), new FluidStack(ft.fluid, TConstruct.blockLiquidValue), 100);
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