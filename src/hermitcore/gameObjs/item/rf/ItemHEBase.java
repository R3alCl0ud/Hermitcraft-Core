package hermitcore.gameObjs.item.rf;

import hermitcore.library.HermitRegistry;

import java.util.HashMap;

import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class ItemHEBase extends Item 
{
    public HashMap<Integer, String> items = new HashMap<Integer, String>();
    public HashMap<String, IIcon> icons = new HashMap<String, IIcon>();
    public HashMap<Integer, Integer> rarities = new HashMap<Integer, Integer>();
    private boolean hasTextures = true;
    
    public ItemHEBase()
    {
    	super();
    	this.setDefaultProperties();
    }

	private void setDefaultProperties() 
	{
        this.maxStackSize = 1;
        this.setHasSubtypes(true);
        this.setCreativeTab(HermitRegistry.recordTab);
        this.setNoRepair();
		
	}

}
