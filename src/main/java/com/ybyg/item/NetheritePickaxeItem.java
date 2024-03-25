package com.ybyg.item;

import java.util.Objects;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class NetheritePickaxeItem extends PickaxeItem {

	private final EntityType<?> type;

	public NetheritePickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings,
			EntityType<?> type) {
		super(material, attackDamage, attackSpeed, settings);
		this.type = type;
	}

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
		return ActionResult.CONSUME;
	}

}
