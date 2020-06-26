package tfar.blastprocessing.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.blastprocessing.ExampleMod;

@Mixin(AbstractFurnaceTileEntity.class)
abstract class AbstractFurnaceTileEntityMixin extends TileEntity {

	@Shadow
	protected NonNullList<ItemStack> items;

	@Shadow
	protected abstract boolean isBurning();

	public AbstractFurnaceTileEntityMixin(TileEntityType<?> type) {
		super(type);
	}

	@Inject(method = "tick",at = @At("RETURN"))
	private void boom(CallbackInfo ci){
		ExampleMod.hook((AbstractFurnaceTileEntity)(Object)this,items,world,isBurning());
	}
}
