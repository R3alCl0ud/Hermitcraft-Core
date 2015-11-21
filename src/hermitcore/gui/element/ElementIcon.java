package hermitcore.gui.element;

import net.minecraft.util.IIcon;
import cofh.lib.gui.GuiBase;
import cofh.lib.gui.element.ElementBase;

public class ElementIcon extends ElementBase 
{

	   public IIcon iconToDraw;

	    public ElementIcon(GuiBase gui, int posX, int posY)
	    {
	        super(gui, posX, posY);
	    }

	    public void setIconToDraw(IIcon icon)
	    {
	        this.iconToDraw = icon;
	    }

	    @Override
	    public void drawBackground(int mouseX, int mouseY, float gameTicks)
	    {

	    }

	    @Override
	    public void drawForeground(int mouseX, int mouseY)
	    {
	        if (this.iconToDraw != null) gui.drawIcon(this.iconToDraw, this.posX, this.posY, 0);
	    }
}
