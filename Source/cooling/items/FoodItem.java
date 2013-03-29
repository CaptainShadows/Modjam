package cooling.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemFood;
import cooling.utils.Registry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FoodItem extends ItemFood {

    public FoodItem(int id, int healAmount,
            float saturationModifier) {
        super(id, healAmount, saturationModifier, true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateIcons(IconRegister iconRegister) {

        iconIndex = iconRegister
                .registerIcon(Registry.texture
                        + this.getUnlocalizedName());
    }
}