package tfar.blastprocessing;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ExampleMod.MODID)
public class ExampleMod
{
    // Directly reference a log4j logger.

    public static final String MODID = "blastprocessing";


    public ExampleMod() {
        // Register the setup method for modloading
        MinecraftForge.EVENT_BUS.addListener(this::setup);
    }

    private void setup(final FurnaceFuelBurnTimeEvent event) {
        if (event.getItemStack().getItem().isIn(Tags.Items.GUNPOWDER)){
            event.setBurnTime(1000);
        }
    }

    public static void hook(AbstractFurnaceTileEntity te, NonNullList<ItemStack> items, World world,boolean isBurning) {
        ItemStack stack = items.get(1);
        if (stack.getItem().isIn(Tags.Items.GUNPOWDER) && isBurning) {
            float boom = stack.getItem().isIn(Tags.Items.GUNPOWDER) ? 3 : 9;
            boom *= Math.pow(stack.getCount(),1/3d);
            world.createExplosion(null, te.getPos().getX(), te.getPos().getY()+1, te.getPos().getZ(),
                    boom, Explosion.Mode.BREAK);
        }
    }
}
