package org.niki3.ddi.mixin;

import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.vibrations.VibrationSystem;
import net.minecraft.world.phys.Vec3;
import org.niki3.ddi.registration.DdiTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VibrationSystem.Listener.class)
public class DdiVibrationEvent {
    @Inject(method = "handleGameEvent", at = @At("HEAD"), cancellable = true)
    private void cancelVibration(ServerLevel level, Holder<GameEvent> event, GameEvent.Context context, Vec3 pos, CallbackInfoReturnable<Boolean> cir){
        if(event.is(GameEvent.STEP) || event.is(GameEvent.HIT_GROUND)){
            Entity entity = context.sourceEntity();

            if(entity instanceof ServerPlayer player){
                ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
                if(boots.is(DdiTags.VIBRATION_SILENCER)){
                    System.out.println("Vibration Canceled");

                    cir.setReturnValue(false);
                }
            }
        }
    }
}
