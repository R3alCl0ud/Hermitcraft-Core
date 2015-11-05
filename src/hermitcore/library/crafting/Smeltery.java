package hermitcore.library.crafting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mantle.utils.ItemMetaWrapper;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import tconstruct.library.crafting.AlloyMix;
import tconstruct.library.crafting.FluidType;

public class Smeltery 
{
	public static Smeltery instance = new Smeltery();

    private final Map<ItemMetaWrapper, FluidStack> smeltingList = new HashMap<ItemMetaWrapper, FluidStack>();
    private final Map<ItemMetaWrapper, Integer> temperatureList = new HashMap<ItemMetaWrapper, Integer>();
    private final Map<ItemMetaWrapper, ItemStack> renderIndex = new HashMap<ItemMetaWrapper, ItemStack>();
    //private final List<AlloyMix> alloys = new ArrayList<AlloyMix>();
    //private final Map<Fluid, Integer[]> smelteryFuels = new HashMap<Fluid, Integer[]>(); // fluid -> [power, duration]

	
    public static void addMelting (ItemStack input, Block block, int metadata, int temperature, FluidStack liquid)
    {
        ItemMetaWrapper in = new ItemMetaWrapper(input);
        instance.smeltingList.put(in, liquid);
        instance.temperatureList.put(in, temperature);
        instance.renderIndex.put(in, new ItemStack(block, input.stackSize, metadata));
    }
    
    public static void addMelting (ItemStack stack, int temperature, FluidStack output)
    {
        if (stack.getItem() instanceof ItemBlock)
            addMelting(stack, ((ItemBlock) stack.getItem()).field_150939_a, stack.getItemDamage(), temperature, output);
        else
            throw new IllegalArgumentException("ItemStack must house a block.");
    }
    
    public static void addMelting (Block block, int metadata, int temperature, FluidStack output)
    {
        addMelting(new ItemStack(block, 1, metadata), block, metadata, temperature, output);
    }
    public static void addMelting (FluidType type, ItemStack input, int temperatureDifference, int fluidAmount)
    {
        int temp = type.baseTemperature + temperatureDifference;
        if (temp <= 20)
            temp = type.baseTemperature;

        if (input.getItem() instanceof ItemBlock)
            addMelting(input, ((ItemBlock) input.getItem()).field_150939_a, input.getItemDamage(), type.baseTemperature + temperatureDifference, new FluidStack(type.fluid, fluidAmount));
        else
            addMelting(input, type.renderBlock, type.renderMeta, type.baseTemperature + temperatureDifference, new FluidStack(type.fluid, fluidAmount));
    }
    
    public static void addDictionaryMelting (String oreName, FluidType type, int temperatureDifference, int fluidAmount)
    {
        for (ItemStack is : OreDictionary.getOres(oreName))
            addMelting(type, is, temperatureDifference, fluidAmount);
    }
    
}
