package org.niki3.ddi.blocks.machines.BaseEntityBlocks.Generator;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niki3.ddi.blocks.addBlockEntity;
import org.niki3.ddi.blocks.machines.BlockEntities.Generators.ThermalGeneratorBlock;

public class ThermalGenerator extends BaseEntityBlock {

    public ThermalGenerator(Properties properties){super(properties);}

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if (!level.isClientSide) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof ThermalGeneratorBlock) {
                NetworkHooks.openScreen(((ServerPlayer) player),(ThermalGeneratorBlock)blockEntity,pos);
            }
            else {
                throw new IllegalStateException("container provider is missing");
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new ThermalGeneratorBlock(addBlockEntity.THERMAL_GENERATOR_ENTITY.get(), pos,state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type){
        return createTickerHelper(type,addBlockEntity.THERMAL_GENERATOR_ENTITY.get(), ThermalGeneratorBlock::tick);
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state){
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState NewState, boolean IsMoving){
        if(state.getBlock() != NewState.getBlock()){
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if(blockEntity instanceof ThermalGeneratorBlock){
                ((ThermalGeneratorBlock) blockEntity).drops();
            }
        }
        super.onRemove(state,level,pos,NewState,IsMoving);
    }
}
