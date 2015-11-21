package hermitcore.gameObjs.item.rf;

import hermitcore.utils.Key;
import hermitcore.utils.Key.KeyCode;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class ItemExchanger extends ItemExchangerBase implements IKeyHandler
{
	
    public static final int[] RECEIVE = {0, 1 * 2000, 10 * 2000};
    public static final int[] SEND = {10 * 1000000, 10 * 1000000, 10 * 1000000};
    public static final int[] CAPACITY = {0, 1 * 2000000, 10 * 1000000};
    public static Set<Block> creativeOverrideBlocks;
    private static Set<Key.KeyCode> handledKeys;
    
    static
    {
        handledKeys = new HashSet<Key.KeyCode>();
        handledKeys.add(Key.KeyCode.TOOL_INCREASE);
        handledKeys.add(Key.KeyCode.TOOL_DECREASE);

        creativeOverrideBlocks = new HashSet<Block>();
        creativeOverrideBlocks.add(Blocks.bedrock);
    }

	
    public ItemExchanger()
    {
        super();
        this.setNoRepair();
    }
	
    public static boolean isCreative(ItemStack stack)
    {
        if (stack == null) return false;

        return stack.getItemDamage() == Types.CREATIVE.ordinal();
    }
    
    public ItemStack getSourceItemStack(ItemStack stack)
    {
        boolean hasKey = (stack.hasTagCompound()) && (stack.stackTagCompound.hasKey("source"));
        if (hasKey)
        {
            ItemStack ret = new ItemStack(Blocks.air);
            ret.readFromNBT(stack.stackTagCompound.getCompoundTag("source"));
            return ret;
        }

        return null;
    }

    @Override
    public Set<Key.KeyCode> getHandledKeys()
    {
        return ItemExchanger.handledKeys;
    }
    
    public int getTargetRadius(ItemStack stack)
    {
        int radius = 3;

        if (stack.hasTagCompound() && (stack.getTagCompound().hasKey("targetRadius")))
        {
            radius = stack.getTagCompound().getInteger("targetRadius");
        }

        /*if (radius > ItemConfig.itemExchangerMaxRadius)
        {
            radius = ItemConfig.itemExchangerMaxRadius;
        }*/

        return radius;
    }

	@Override
	public void handleKey(EntityPlayer player, ItemStack itemStack, KeyCode key) {
		
		
	}

    public static enum Types
    {
        CREATIVE, REDSTONE, RESONANT;
    }

}
