package com.ybyg.item;

import java.util.Objects;

import org.jetbrains.annotations.Nullable;

import com.ybyg.entity.WardenBuildEntity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class WardenBuildItem extends Item{

	public WardenBuildItem(Settings settings, EntityType<?> type) {
		super(settings);
		this.type = type;
	}
	
	private final EntityType<?> type;

	public EntityType<?> getEntityType(@Nullable NbtCompound nbt) {
		NbtCompound nbtCompound;
		if (nbt != null && nbt.contains("EntityTag", NbtElement.COMPOUND_TYPE)
				&& (nbtCompound = nbt.getCompound("EntityTag")).contains("id", NbtElement.STRING_TYPE)) {
			return EntityType.get(nbtCompound.getString("id")).orElse(this.type);
		}
		return this.type;
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		World world = context.getWorld();
		if (!(world instanceof ServerWorld)) {
			return ActionResult.SUCCESS;
		}
		ItemStack itemStack = context.getStack();
		BlockPos blockPos = context.getBlockPos();
		Direction direction = context.getSide();
		BlockState blockState = world.getBlockState(blockPos);
		BlockPos blockPos2 = blockState.getCollisionShape(world, blockPos).isEmpty() ? blockPos
				: blockPos.offset(direction);
		EntityType<?> entityType2 = this.getEntityType(itemStack.getNbt());
		if (entityType2.spawnFromItemStack((ServerWorld) world, itemStack, context.getPlayer(), blockPos2,
				SpawnReason.SPAWN_EGG, true,
				!Objects.equals(blockPos, blockPos2) && direction == Direction.UP) != null) {
			world.emitGameEvent(context.getPlayer(), GameEvent.ENTITY_PLACE, blockPos);
		}
		context.getPlayer().addCommandTag("wardenOwner");
		itemStack.damage(1, context.getPlayer(), player -> player.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
		return ActionResult.CONSUME;
	}

}
