package hermitcore.gameObjs.tile;


import hermitcore.network.PacketHEBase;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.IEnergyContainerItem;
import cofh.lib.util.helpers.ServerHelper;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileCdBurner extends TileBurner
{
    public static final int[] RECEIVE = {0, 1 * 2000, 10 * 2000};
    public static final int[] SEND = {10 * 1000000, 1 * 2000, 10 * 2000};
    public static final int[] CAPACITY = {-1, 1 * 2000000, 10 * 1000000};

    public int sentPower = 0;

    public TileCdBurner()
    {
        super();

        this.tileName = "Cd Burner";
    }
    public static void init()
    {
        GameRegistry.registerTileEntity(TileCdBurner.class, "tile." + "cdburner");
    }

    public int getMaxEnergyStored(int meta)
    {
        return CAPACITY[meta];
    }

    public int getMaxReceiveRate(int meta)
    {
        return RECEIVE[meta];
    }

    public int getMaxSendRate(int meta)
    {
        return SEND[meta];
    }

    @Override
    public String toString()
    {
        return "Charge Pad: position " + this.xCoord + ", " + this.yCoord + ", " + this.zCoord;
    }

    public Set<ItemStack> chargeableItemsInInventory(ItemStack[] itemStacks)
    {
        Set<ItemStack> itemsToCharge = new HashSet<ItemStack>();

        for (ItemStack itemStack : itemStacks)
        {
            if (itemStack == null) continue;

            Item item = itemStack.getItem();
            if (item instanceof IEnergyContainerItem)
            {
                IEnergyContainerItem chargeableItem = (IEnergyContainerItem) item;
                if (chargeableItem.receiveEnergy(itemStack, 1, true) == 1) itemsToCharge.add(itemStack);
            }
        }

        return itemsToCharge;
    }

    public List<Entity> getChargeableEntitiesInAABB(AxisAlignedBB aabb)
    {
        List<Entity> chargeableEntitiesInRange = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, aabb);
        List<EntityItem> chargeableItemsInRange = this.worldObj.getEntitiesWithinAABB(EntityItem.class, aabb);
        for (EntityItem entityItem : chargeableItemsInRange)
        {
            ItemStack itemStack = entityItem.getEntityItem();
            if (itemStack != null)
            {
                Item item = itemStack.getItem();
                if (item != null)
                {
                    if (item instanceof IEnergyContainerItem) chargeableEntitiesInRange.add(entityItem);
                }
            }
        }

        return chargeableEntitiesInRange;
    }

    public List<ItemStack> getItemsToChargeFromEntity(Entity entity)
    {
        LinkedList<ItemStack> itemsToCharge = new LinkedList<ItemStack>();

        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;
            itemsToCharge.addAll(this.chargeableItemsInInventory(player.inventory.mainInventory));
            itemsToCharge.addAll(this.chargeableItemsInInventory(player.inventory.armorInventory));
        } else if (entity instanceof EntityItem)
        {
            EntityItem entityItem = (EntityItem) entity;
            ItemStack item = entityItem.getEntityItem();

            if (item.stackSize == 1) itemsToCharge.add(item);
        }

        return itemsToCharge;
    }

    public int chargeItemsGivenEntity(Entity entity, int maxCharge, int meta)
    {
        List<ItemStack> itemsToCharge = this.getItemsToChargeFromEntity(entity);
        double efficiency = this.calculateEfficiencyForEntity(entity);

        int totalSent = 0;
        int itemCount = itemsToCharge.size();
        if (itemCount > 0)
        {
            int chargePerItem = (int) Math.floor(maxCharge / itemCount);
            if (chargePerItem == 0 && maxCharge > 0) chargePerItem = 1;

            for (ItemStack itemStack : itemsToCharge)
            {
                IEnergyContainerItem chargeableItem = (IEnergyContainerItem) itemStack.getItem();
                int couldReceive = chargeableItem.receiveEnergy(itemStack, chargePerItem, true);
                int toSend = this.extractEnergy(couldReceive, meta, false);
                if (this.isCreative) toSend = couldReceive;

                int sent = chargeableItem.receiveEnergy(itemStack, (int) (toSend * efficiency), false);
                if (sent > 0 && entity instanceof EntityItem)
                {
                    EntityItem entityItem = (EntityItem) entity;
                    if (entityItem.lifespan < Integer.MAX_VALUE) entityItem.lifespan = Integer.MAX_VALUE;
                }

                totalSent += sent;
            }

            if (totalSent >= maxCharge) return maxCharge;
        }

        return totalSent;
    }

    @Override
    public void updateEntity()
    {
        int meta = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
        this.isCreative = (meta == 0);

        if (ServerHelper.isServerWorld(this.worldObj))
        {
            boolean oldActive = this.isActive;
            this.isActive = this.sentPower > 0;

            sentPower = 0;
            int totalChargeSendable = this.extractEnergy(getMaxSendRate(meta), meta, true);
            if (this.isCreative) totalChargeSendable = SEND[0];

            if (totalChargeSendable > 0)
            {
                AxisAlignedBB front = this.getAABBInFront(2);
                List<Entity> ownersInRange = this.getChargeableEntitiesInAABB(front);

                int totalChargeForEntity = (int) (((double) totalChargeSendable) / ownersInRange.size());
                if (ownersInRange.size() > 0)
                {
                    for (Entity entity : ownersInRange)
                    {
                        int powerSentToEntity = this.chargeItemsGivenEntity(entity, totalChargeForEntity, meta);
                        sentPower += powerSentToEntity;
                    }
                }
            }

            this.chargeFromGUISlot();

            boolean shouldSendUpdate = false;
            shouldSendUpdate = shouldSendUpdate || (this.isActive != oldActive);

            if (this.ticksSinceLastUpdate == TICKS_PER_UPDATE)
            {
                this.ticksSinceLastUpdate = 0;
                shouldSendUpdate = true;
            }

            if (shouldSendUpdate) this.sendDescriptionPacket();

            this.ticksSinceLastUpdate++;
            if (this.ticksSinceLastUpdate > TICKS_PER_UPDATE) this.ticksSinceLastUpdate = TICKS_PER_UPDATE;
        }

        if (this.sentPower > 0 && ServerHelper.isClientWorld(this.worldObj)) this.spawnParticles(meta);
    }

    @Override
    public PacketHEBase getPacket()
    {
        PacketHEBase packet = super.getPacket();
        packet.addInt(this.sentPower);

        return packet;
    }

    @Override
    public void handleTilePacket(PacketHEBase tilePacket, boolean isServer)
    {
        super.handleTilePacket(tilePacket, isServer);

        int sentPower = tilePacket.getInt();

        if (!isServer)
        {
            this.sentPower = sentPower;
        }
    }



    @SideOnly(Side.CLIENT)
    public void spawnParticles(int meta)
    {
        EffectRenderer er = FMLClientHandler.instance().getClient().effectRenderer;
        ForgeDirection orientation = this.getOrientation();
        Random rand = this.worldObj.rand;

        for (int particle = this.getParticleCount(meta); particle > 0; particle--)
        {
            double xSign = (rand.nextBoolean() ? -1 : 1);
            double ySign = (rand.nextBoolean() ? -1 : 1);
            double zSign = (rand.nextBoolean() ? -1 : 1);

            double xAddition = xSign * (rand.nextDouble() * 0.3) + (0.05 * xSign);
            double yAddition = ySign * (rand.nextDouble() * 0.3) + (0.05 * ySign);
            double zAddition = zSign * (rand.nextDouble() * 0.3) + (0.05 * zSign);

            double x = this.xCoord + (0.5F * orientation.offsetX) + 0.5 + xAddition;
            double y = this.yCoord + (0.5F * orientation.offsetY) + 0.5 + yAddition;
            double z = this.zCoord + (0.5F * orientation.offsetZ) + 0.5 + zAddition;

            //er.addEffect(new EntityChargePadFX(this.worldObj, x, y, z, getParticleMaxAge(), getParticleVelocity(), getParticleColour(rand), this.getParticleSizeModifier(meta)));
        }
    }

    @SideOnly(Side.CLIENT)
    public int getParticleMaxAge()
    {
        return 16;
    }

    @SideOnly(Side.CLIENT)
    public double[] getParticleVelocity()
    {
        ForgeDirection orientation = this.getOrientation();
        return new double[] {orientation.offsetX * 0.15D, orientation.offsetY * 0.15D, orientation.offsetZ * 0.15D};
    }



    @SideOnly(Side.CLIENT)
    public int getParticleCount(int meta)
    {
        if (meta == 0) return 5;
        else if (meta == 2) return 2;
        else return 1;
    }

    @SideOnly(Side.CLIENT)
    public float getParticleSizeModifier(int meta)
    {
        if (meta == 0) return 2.0F;
        else if (meta == 2) return 1.75F;
        else return 1.5F;
    }
}
