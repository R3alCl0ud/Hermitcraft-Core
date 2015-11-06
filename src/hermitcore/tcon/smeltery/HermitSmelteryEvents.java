package hermitcore.tcon.smeltery;

import hermitcore.tcon.smeltery.blocks.LiquidMetalFinite;
import mantle.world.WorldHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;


public class HermitSmelteryEvents 
{
	
	
	
	
    @SubscribeEvent
    public void bucketFill (FillBucketEvent evt)
    {
        if (evt.current.getItem() == Items.bucket && evt.target.typeOfHit == MovingObjectType.BLOCK)
        {
            int hitX = evt.target.blockX;
            int hitY = evt.target.blockY;
            int hitZ = evt.target.blockZ;

            if (evt.entityPlayer != null && !evt.entityPlayer.canPlayerEdit(hitX, hitY, hitZ, evt.target.sideHit, evt.current))
            {
                return;
            }

            Block bID = evt.world.getBlock(hitX, hitY, hitZ);
            for (int id = 0; id < HermitSmeltery.fluidBlocks.length; id++)
            {
                if (bID == HermitSmeltery.fluidBlocks[id])
                {
                    if (evt.entityPlayer.capabilities.isCreativeMode)
                    {
                        WorldHelper.setBlockToAir(evt.world, hitX, hitY, hitZ);
                    }
                    else
                    {
                        if (HermitSmeltery.fluidBlocks[id] instanceof LiquidMetalFinite)
                        {
                            WorldHelper.setBlockToAir(evt.world, hitX, hitY, hitZ);
                        }
                        else
                        {
                            WorldHelper.setBlockToAir(evt.world, hitX, hitY, hitZ);
                        }

                        evt.setResult(Result.ALLOW);
                        evt.result = new ItemStack(HermitSmeltery.buckets, 1, id);
                    }
                }
            }
        }
    }
}
