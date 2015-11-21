package hermitcore.gameObjs.item.rf;

import hermitcore.utils.Key;

import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IKeyHandler 
{
    public abstract void handleKey(EntityPlayer player, ItemStack itemStack, Key.KeyCode key);

    public abstract Set<Key.KeyCode> getHandledKeys();

}
