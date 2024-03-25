package com.ybyg.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class PushEntity extends ExplosiveProjectileEntity implements FlyingItemEntity {

	private static final TrackedData<ItemStack> ITEM = DataTracker.registerData(PushEntity.class,
			TrackedDataHandlerRegistry.ITEM_STACK);

	public double ages = 0;

	public PushEntity(EntityType<? extends PushEntity> entityType, double d, double e, double f, double g, double h,
			double i, World world) {
		super(entityType, d, e, f, g, h, i, world);
	}

	public PushEntity(EntityType<? extends PushEntity> entityType, LivingEntity livingEntity, double d, double e,
			double f, World world) {
		super(entityType, livingEntity, d, e, f, world);
	}

	public PushEntity(EntityType<? extends PushEntity> entityType, World world) {
		super(entityType, world);
	}

	protected ItemStack getItem() {
		return this.getDataTracker().get(ITEM);
	}

	@Override
	public ItemStack getStack() {
		ItemStack itemStack = this.getItem();
		return itemStack.isEmpty() ? new ItemStack(Items.ACACIA_BOAT) : itemStack;
	}

	@Override
	protected void initDataTracker() {
		this.getDataTracker().startTracking(ITEM, ItemStack.EMPTY);
	}

	@Override
	protected boolean isBurning() {
		return false;
	}

	@Override
	public void readCustomDataFromNbt(NbtCompound nbt) {
		super.readCustomDataFromNbt(nbt);
		ItemStack itemStack = ItemStack.fromNbt(nbt.getCompound("Item"));
		this.setItem(itemStack);
	}

	public void setItem(ItemStack stack) {
		if (!stack.isOf(Items.ACACIA_BOAT) || stack.hasNbt()) {
			this.getDataTracker().set(ITEM, stack.copyWithCount(1));
		}
	}

	@Override
	public void tick() {
		HitResult hitResult;
		Entity entity = this.getOwner();
		if (!this.getWorld().isClient
				&& (entity != null && entity.isRemoved() || !this.getWorld().isChunkLoaded(this.getBlockPos()))) {
			this.discard();
			return;
		}
		if ((hitResult = ProjectileUtil.getCollision(this, this::canHit)).getType() != HitResult.Type.MISS) {
			this.onCollision(hitResult);
		}
		if (entity != null) {
			ages++;
			if (ages == 50) {
				this.discard();
			}
		}
		this.checkBlockCollision();
		Vec3d vec3d = this.getVelocity();
		double d = this.getX() + vec3d.x;
		double e = this.getY() + vec3d.y;
		double f = this.getZ() + vec3d.z;
		ProjectileUtil.setRotationFromVelocity(this, 0.2f);
		float g = this.getDrag();
		if (this.isTouchingWater()) {
			for (int i = 0; i < 4; ++i) {
				this.getWorld().addParticle(ParticleTypes.BUBBLE, d - vec3d.x * 0.25, e - vec3d.y * 0.25,
						f - vec3d.z * 0.25, vec3d.x, vec3d.y, vec3d.z);
			}
			g = 0.8f;
		}
		this.setVelocity(vec3d.add(this.powerX, this.powerY, this.powerZ).multiply(g));
		this.getWorld().addParticle(this.getParticleType(), d, e + 0.5, f, 0.0, 0.0, 0.0);
		this.setPosition(d, e, f);
	}

	@Override
	public void writeCustomDataToNbt(NbtCompound nbt) {
		super.writeCustomDataToNbt(nbt);
		ItemStack itemStack = this.getItem();
		if (!itemStack.isEmpty()) {
			nbt.put("Item", itemStack.writeNbt(new NbtCompound()));
		}
	}

}
