package net.thesaminator.letterblocks

import net.minecraft.block.*
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView

class LetterBlock : HorizontalFacingBlock(
	Settings
		.of(Material.STONE)
		.noCollision()
) {
	init {
		defaultState = defaultState.with(FACING, Direction.NORTH)
	}
	
	override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
		super.appendProperties(builder)
		builder.add(FACING)
	}
	
	override fun hasDynamicBounds(): Boolean {
		return true
	}
	
	override fun getOutlineShape(state: BlockState, view: BlockView?, pos: BlockPos?, ePos: ShapeContext?): VoxelShape {
		return when (state[FACING]) {
			Direction.NORTH -> VoxelShapes.cuboid(0.0, 0.0, 0.9375, 1.0, 1.0, 1.0)
			Direction.SOUTH -> VoxelShapes.cuboid(0.0, 0.0, 0.0, 1.0, 1.0, 0.0625)
			Direction.WEST -> VoxelShapes.cuboid(0.9375, 0.0, 0.0, 1.0, 1.0, 1.0)
			Direction.EAST -> VoxelShapes.cuboid(0.0, 0.0, 0.0, 0.0625, 1.0, 1.0)
			else -> VoxelShapes.fullCube()
		}
	}
	
	override fun getPlacementState(ctx: ItemPlacementContext): BlockState {
		return defaultState.with(FACING, ctx.playerFacing.opposite)
	}
}
