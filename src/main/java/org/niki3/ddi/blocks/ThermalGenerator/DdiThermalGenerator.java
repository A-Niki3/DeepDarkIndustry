package org.niki3.ddi.blocks.ThermalGenerator;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niki3.ddi.registration.DdiBlockEntities;

public class DdiThermalGenerator extends BaseEntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public static final MapCodec<DdiThermalGenerator> CODEC = simpleCodec(DdiThermalGenerator::new);

    public DdiThermalGenerator(Properties properties){
        super(properties.lightLevel(state -> state.getValue(LIT) ? 13 : 0));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT,false));
    }

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context){
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){
        builder.add(FACING, LIT);
    }

    @Override
    public MenuProvider getMenuProvider(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos){
        BlockEntity be = level.getBlockEntity(pos);

        if (be instanceof DdiThermalGeneratorBlockEntity generator) {
            return new SimpleMenuProvider(
                    (id, inventory, player) -> new DdiThermalGeneratorMenu(id, inventory, generator.getContainer(), generator),
                    Component.translatable("block.ddi.menu.thermal_generator")
            );
        }
        return null;
    }

    @Override
    public @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull BlockHitResult hit){
        if(!level.isClientSide){
            player.openMenu(state.getMenuProvider(level, pos));
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new DdiThermalGeneratorBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity>BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type){
        return createTickerHelper(
                type,
                DdiBlockEntities.THERMAL_GENERATOR.get(),
                DdiThermalGeneratorBlockEntity::tick
        );
    }

    @Override
    public void onRemove(BlockState state, @NotNull Level level, @NotNull BlockPos pos, BlockState newState, boolean isMoving){
        if (state.getBlock() != newState.getBlock()){
            BlockEntity be = level.getBlockEntity(pos);

            if(be instanceof DdiThermalGeneratorBlockEntity generatorBlock){
                Containers.dropContents(level, pos, generatorBlock.getContainer());
            }

            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    public boolean hasAnalogOutputSignal(@NotNull BlockState state){
        return true;
    }

    @Override
    public int getAnalogOutputSignal(@NotNull BlockState state, Level level, @NotNull BlockPos pos){
        BlockEntity be = level.getBlockEntity(pos);
        if(be instanceof DdiThermalGeneratorBlockEntity generatorBlock){
            return AbstractContainerMenu.getRedstoneSignalFromContainer(generatorBlock.getContainer());
        }
        return 0;
    }
}
