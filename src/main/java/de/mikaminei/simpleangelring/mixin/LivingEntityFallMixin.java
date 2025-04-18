package de.mikaminei.simpleangelring.mixin;

import de.mikaminei.simpleangelring.util.AngelRingHelper;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityFallMixin {
    @Inject(method = "fall", at = @At("HEAD"), cancellable = true)
    private void onFallMixin(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition, CallbackInfo callbackInfo) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (entity instanceof ServerPlayerEntity player) {
            if (AngelRingHelper.hasAngelRing(player)) {
                callbackInfo.cancel();
            }
        }
    }
}
