package com.ybyg.entity;

import com.ybyg.YouBuildYouGet;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class BedrockSwordEntity extends PushEntity {

	private int explosionPower = 10;

	public BedrockSwordEntity(EntityType<? extends BedrockSwordEntity> entityType, World world) {
		super(entityType, world);
		this.intersectionChecked = true;
	}

	@Override
	protected ItemStack getItem() {
		return new ItemStack(YouBuildYouGet.BEDROCK_SWORD_BUILD);
	}

	@Override
	protected void onCollision(HitResult hitResult) {
		super.onCollision(hitResult);
		if (!this.getWorld().isClient) {
			if (this.ages >= 10) {
				boolean bl = this.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING);
				this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), this.explosionPower, false,
						World.ExplosionSourceType.MOB);
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
