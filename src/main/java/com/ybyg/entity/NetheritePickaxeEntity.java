package com.ybyg.entity;

import com.ybyg.YouBuildYouGet;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class NetheritePickaxeEntity extends PushEntity {
	private int explosionPower = 10;

	public NetheritePickaxeEntity(EntityType<? extends PushEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected ItemStack getItem() {
		return new ItemStack(YouBuildYouGet.NETHERITE_PICKAXE_BUILD);
	}

	@Override
	protected void onCollision(HitResult hitResult) {
		super.onCollision(hitResult);
		if (!this.getWorld().isClient) {
			Box box = this.getBoundingBox().expand(3, 0, 3);
			for (BlockPos blockPos : BlockPos.iterate(MathHelper.floor(box.minX), MathHelper.floor(box.minY),
					MathHelper.floor(box.minZ), MathHelper.floor(box.maxX), MathHelper.floor(box.maxY),
					MathHelper.floor(box.maxZ))) {
				BlockState blockState = this.getWorld().getBlockState(blockPos);
				Block block = blockState.getBlock();

				if (block != Blocks.BEDROCK) {
					this.getWorld().breakBlock(blockPos,
							blockState.isIn(BlockTags.COAL_ORES) || blockState.isIn(BlockTags.COPPER_ORES)
									|| blockState.isIn(BlockTags.DIAMOND_ORES)
									|| blockState.isIn(BlockTags.EMERALD_ORES) || blockState.isIn(BlockTags.GOLD_ORES)
									|| blockState.isIn(BlockTags.IRON_ORES) || blockState.isIn(BlockTags.LAPIS_ORES),
							this);
				}
			}
		}
	}

	@Override
	protected void onEntityHit(EntityHitResult entityHitResult) {
		super.onEntityHit(entityHitResult);
		if (this.getWorld().isClient) {
			return;
		}
		Entity entity = entityHitResult.getEntity();
		Entity entity2 = this.getOwner();
		entity.damage(this.getDamageSources().explosion(this, entity2), 6.0f);
		if (entity2 instanceof LivingEntity) {
			this.applyDamageEffects((LivingEntity) entity2, entity);
		}
	}

	@Override
	public void readCustomDataFromNbt(NbtCompound nbt) {
		super.readCustomDataFromNbt(nbt);
		if (nbt.contains("ExplosionPower", NbtElement.NUMBER_TYPE)) {
			this.explosionPower = nbt.getByte("ExplosionPower");
		}
	}

	@Override
	public void writeCustomDataToNbt(NbtCompound nbt) {
		super.writeCustomDataToNbt(nbt);
		nbt.putByte("ExplosionPower", (byte) this.explosionPower);
	}
}
