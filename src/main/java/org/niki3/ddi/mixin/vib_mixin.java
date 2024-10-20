package org.niki3.ddi.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.vibrations.VibrationListener;
import org.niki3.ddi.items.event.add_tags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VibrationListener.class)
public class vib_mixin {
    @Inject(method = "handleGameEvent",at = @At("HEAD"),cancellable = true)
    private void Vibration_Canceler(ServerLevel level, GameEvent.Message message, CallbackInfoReturnable<Boolean> cir) {
        if(message.gameEvent().getName().equals("step") || message.gameEvent().getName().equals("hit_ground")){
            Entity entity = message.context().sourceEntity();
            if(entity instanceof ServerPlayer player){
                ItemStack boots = player.getInventory().getArmor(0);
                if(boots.is(add_tags.VIBRATION_SILENCER)){
                    cir.setReturnValue(false);
                }
            }
        }
    }
}
