package hermitcore.library.tools;

import net.minecraft.util.StatCollector;

public class ToolMaterial 
{
    public final String materialName;
    public final int harvestLevel;
    public final int durability;
    public final int miningspeed; // <-- divided by 100
    public final int attack;
    public final float handleModifier;
    public final int reinforced;
    public final float stonebound;
    public final String tipStyle;
    public final int primaryColor;
    
    public final String localizationString;

    @Deprecated
    public String displayName;
    @Deprecated
    public String ability;
    
    
    public ToolMaterial(String name, String localizationString, int level, int durability, int speed, int damage, float handle, int reinforced, float stonebound, String style, int primaryColor)
    {
        this.materialName = name;
        this.harvestLevel = level;
        this.durability = durability;
        this.miningspeed = speed;
        this.attack = damage;
        this.handleModifier = handle;
        this.reinforced = reinforced;
        this.stonebound = stonebound;
        this.tipStyle = style;
        this.primaryColor = primaryColor;

        this.localizationString = localizationString;

        this.displayName = prefixName();
        this.ability = ability();
    }
    
    public String localizedName ()
    {
        return StatCollector.translateToLocal(localizationString);
    }

    public String prefixName ()
    {
        // check if there's a special name, otherwise use the regular one
        if (StatCollector.canTranslate(String.format("%s.display", localizationString)))
            return StatCollector.translateToLocal(String.format("%s.display", localizationString));
        return localizedName();
    }

    public String ability ()
    {
        if (StatCollector.canTranslate(String.format("%s.ability", localizationString)))
            return StatCollector.translateToLocal(String.format("%s.ability", localizationString));
        return "";
    }
}
