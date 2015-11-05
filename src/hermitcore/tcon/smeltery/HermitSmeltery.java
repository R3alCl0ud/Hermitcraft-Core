package hermitcore.tcon.smeltery;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import mantle.blocks.BlockUtils;
import mantle.blocks.abstracts.MultiServantLogic;
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
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import tconstruct.TConstruct;
import tconstruct.library.TConstructRegistry;
import tconstruct.library.crafting.FluidType;
import tconstruct.library.crafting.LiquidCasting;
import tconstruct.library.crafting.Smeltery;
import tconstruct.library.util.IPattern;
import tconstruct.smeltery.TinkerSmeltery;
import tconstruct.smeltery.TinkerSmelteryEvents;
import tconstruct.smeltery.blocks.*;
import tconstruct.smeltery.itemblocks.*;
import tconstruct.smeltery.items.FilledBucket;
import tconstruct.smeltery.items.MetalPattern;
import tconstruct.smeltery.logic.*;
import hermitcore.HECore;
import hermitcore.gameObjs.ObjHandler;

import java.util.*;

@SuppressWarnings("unused")
@ObjectHolder(HECore.MODID)
@Pulse(id = "Hermit Core' Smeltery", description = "Adds ability to use TConstruc's smeltery on AoA Ores and bars")
public class HermitSmeltery {
	
	
	
	
	//Liquids
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
	
	
    @Handler
    public void preInit (FMLPreInitializationEvent event)
    {
        TinkerSmelteryEvents smelteryEvents = new TinkerSmelteryEvents();
        MinecraftForge.EVENT_BUS.register(smelteryEvents);
        FMLCommonHandler.instance().bus().register(smelteryEvents);
    	
    	HermitSmeltery.moltenLimoniteFluid = registerFluid("limonite");
    	HermitSmeltery.moltenLimonite = HermitSmeltery.moltenLimoniteFluid.getBlock();
    	
    	
    	
    	FluidType.registerFluidType("limonite", ObjHandler.oreLimonite, 0, 600, HermitSmeltery.moltenLimoniteFluid, true);
    }
    
    @Handler
    public void init (FMLInitializationEvent event)
    {
    	 addRecipesForSmeltery();
    }
    
    protected static void addRecipesForSmeltery ()
    {
    	Smeltery.addMelting(ObjHandler.oreLimonite, 0, 600, new FluidStack(HermitSmeltery.moltenLimoniteFluid, TConstruct.ingotLiquidValue * 2));
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
        TConstructFluid block = new TConstructFluid(fluid, material, texture);
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
                    FluidContainerRegistry.registerFluidContainer(new FluidContainerData(new FluidStack(fluid, 1000), new ItemStack(TinkerSmeltery.buckets, 1, i), new ItemStack(Items.bucket)));
                    reg = true;
                }

            if(!reg)
                TConstruct.logger.error("Couldn't register fluid container for " + name);
        }

        return fluid;
    }
    
}
