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
    /*
    @Inject(method = "handleGameEvent",at = @At("RETURN"))
    private void onHandleGameEvent(ServerLevel level, GameEvent.Message message, CallbackInfoReturnable<Boolean> cir){
        System.out.println("GameEvent:" + message.gameEvent().getName());
        //スカルクセンサーのイベント：sculk_sensor_tendrils_clicking
        //スカルクシュリーカーのイベント：shriek
    }
    */
    @Inject(method = "handleGameEvent",at = @At("HEAD"), cancellable = true)
    private void sculk_cancel(ServerLevel level, GameEvent.Message message, CallbackInfoReturnable<Boolean> cir){
        if(message.gameEvent().getName().equals("sculk_sensor_tendrils_clicking") || message.gameEvent().getName().equals("shriek")){
            //System.out.println("sculks event");
            Entity entity = message.context().sourceEntity();

            if (entity instanceof ServerPlayer player){
                //System.out.println("check player");
                ItemStack boots = player.getInventory().getArmor(0);

                if(boots.is(add_tags.VIBRATION_SILENCER)){
                    //System.out.println("event cancel");
                    cir.setReturnValue(false);
                }
            }
        }
    }
}
