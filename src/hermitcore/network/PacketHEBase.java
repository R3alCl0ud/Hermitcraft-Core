package hermitcore.network;

import hermitcore.util.BlockCoord;
import io.netty.channel.ChannelHandlerContext;

import java.io.*;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTSizeTracker;
import cofh.core.network.PacketBase;
import cofh.lib.util.helpers.FluidHelper;
import cofh.lib.util.helpers.ItemHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import net.minecraftforge.fluids.FluidStack;

public class PacketHEBase extends PacketBase {
	public DataInputStream dataIn;
	private ByteArrayOutputStream arrayOut;
	private DataOutputStream dataOut;

	public PacketHEBase() {
		arrayOut = new ByteArrayOutputStream();
		dataOut = new DataOutputStream(arrayOut);
	}

	public PacketHEBase(byte[] data) {
		dataIn = new DataInputStream(new ByteArrayInputStream(data));
	}

	public PacketHEBase addString(String theString) {
		try {
			dataOut.writeUTF(theString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

	public PacketHEBase addInt(int theInteger) {
		try {
			dataOut.writeInt(theInteger);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

	public PacketHEBase addBool(boolean theBoolean) {
		try {
			dataOut.writeBoolean(theBoolean);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

	public PacketHEBase addByte(byte theByte) {
		try {
			dataOut.writeByte(theByte);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

	public PacketHEBase addByte(int theByte) {
		return addByte((byte) theByte);
	}

	public PacketHEBase addShort(short theShort) {
		try {
			dataOut.writeShort(theShort);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

	public PacketHEBase addShort(int theShort) {
		return addShort((short) theShort);
	}

	public PacketHEBase addByteArray(byte theByteArray[]) {
		try {
			dataOut.write(theByteArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

	public PacketHEBase addFloat(float theFloat) {
		try {
			dataOut.writeFloat(theFloat);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

	public PacketHEBase addVec3(Vec3 theVec3) {
		addDouble(theVec3.xCoord);
		addDouble(theVec3.yCoord);
		return addDouble(theVec3.zCoord);
	}

	public PacketHEBase addDouble(double theDouble) {
		try {
			dataOut.writeDouble(theDouble);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

	public PacketHEBase addItemStack(ItemStack theStack) {
		try {
			writeItemStack(theStack);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

	public PacketHEBase addInventory(ItemStack[] itemStacks) {
		try {
			for (ItemStack itemStack : itemStacks) {
				writeItemStack(itemStack);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

	public PacketHEBase addFluidStack(FluidStack theStack) {
		try {
			FluidHelper.writeFluidStackToPacket(theStack, dataOut);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

	public PacketHEBase addCoords(TileEntity theTile) {
		addInt(theTile.xCoord);
		addInt(theTile.yCoord);
		return addInt(theTile.zCoord);
	}

	public PacketHEBase addCoords(int x, int y, int z) {
		addInt(x);
		addInt(y);
		return addInt(z);
	}

	public PacketHEBase addCoords(BlockCoord coord) {
		addInt(coord.x);
		addInt(coord.y);
		return addInt(coord.z);
	}

	public String getString() {
		try {
			return dataIn.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public int getInt() {
		try {
			return dataIn.readInt();
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public boolean getBool() {
		try {
			return dataIn.readBoolean();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public byte getByte() {
		try {
			return dataIn.readByte();
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public short getShort() {
		try {
			return dataIn.readShort();
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void getByteArray(byte theByteArray[]) {
		try {
			dataIn.readFully(theByteArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public float getFloat() {
		try {
			return dataIn.readFloat();
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public double getDouble() {
		try {
			return dataIn.readDouble();
		} catch (IOException e) {
			e.printStackTrace();
			return 0D;
		}
	}

	public Vec3 getVec3() {
		double d1 = getDouble();
		double d2 = getDouble();
		double d3 = getDouble();

		return Vec3.createVectorHelper(d1, d2, d3);
	}

	public ItemStack getItemStack() {
		try {
			return readItemStack();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ItemStack[] getInventory(int count) {
		ItemStack[] itemStacks = new ItemStack[count];
		try {
			for (int i = 0; i < count; i++) {
				itemStacks[i] = readItemStack();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return itemStacks;
	}

	public FluidStack getFluidStack() {
		try {
			return FluidHelper.readFluidStackFromPacket(dataIn);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public int[] getCoords() {
		return new int[] { getInt(), getInt(), getInt() };
	}

	private void writeItemStack(ItemStack theStack) throws IOException {
		if (theStack == null) {
			addShort(-1);
		} else {
			addShort(Item.getIdFromItem(theStack.getItem()));
			addByte(theStack.stackSize);
			addShort(ItemHelper.getItemDamage(theStack));
			writeNBT(theStack.stackTagCompound);
		}
	}

	public ItemStack readItemStack() throws IOException {
		ItemStack stack = null;
		short itemID = getShort();

		if (itemID >= 0) {
			byte stackSize = getByte();
			short damage = getShort();
			stack = new ItemStack(Item.getItemById(itemID), stackSize, damage);
			stack.stackTagCompound = readNBT();
		}

		return stack;
	}

	public void writeNBT(NBTTagCompound nbt) throws IOException {
		if (nbt == null) {
			addShort(-1);
		} else {
			byte[] abyte = CompressedStreamTools.compress(nbt);
			addShort((short) abyte.length);
			addByteArray(abyte);
		}
	}

	public NBTTagCompound readNBT() throws IOException {
		short nbtLength = getShort();

		if (nbtLength < 0) {
			return null;
		} else {
			byte[] abyte = new byte[nbtLength];
			getByteArray(abyte);
			return CompressedStreamTools.func_152457_a(abyte,
					new NBTSizeTracker(2097152L));
		}
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeBytes(arrayOut.toByteArray());
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		dataIn = new DataInputStream(new ByteArrayInputStream(buffer.array()));
		try {
			dataIn.skipBytes(1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		handlePacket(player, false);
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		handlePacket(player, true);
	}

	public void handlePacket(EntityPlayer player, boolean isServer) {
	}
}
