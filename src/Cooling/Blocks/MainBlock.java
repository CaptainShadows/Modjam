package Cooling.Blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import Cooling.TE.MainTE;
import Cooling.utils.Registry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class MainBlock extends BlockContainer {

    public MainBlock(int id, Material material) {
        super(id, material);
        // TODO Auto-generated constructor stub
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {

        blockIcon = iconRegister
                .registerIcon(Registry.texture
                        + this.getUnlocalizedName());
    }

    /**
     * Sets the direction of the block when placed
     */
    @Override
    public void onBlockPlacedBy(World world, int x, int y,
            int z, EntityLiving entityLiving,
            ItemStack itemStack) {

        int direction = 0;
        int facing = MathHelper
                .floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        if (facing == 0){
            direction = ForgeDirection.NORTH.ordinal();
        }else if (facing == 1){
            direction = ForgeDirection.EAST.ordinal();
        }else if (facing == 2){
            direction = ForgeDirection.SOUTH.ordinal();
        }else if (facing == 3){
            direction = ForgeDirection.WEST.ordinal();
        }

        world.setBlockMetadataWithNotify(x, y, z,
                direction, 3);

        if (itemStack.hasDisplayName()){
            ((MainTE) world.getBlockTileEntity(x, y, z))
                    .setCustomName(itemStack
                            .getDisplayName());
        }

        ((MainTE) world.getBlockTileEntity(x, y, z))
                .setOwner(entityLiving.getEntityName());
        ((MainTE) world.getBlockTileEntity(x, y, z))
                .setOrientation(direction);
    }
}
