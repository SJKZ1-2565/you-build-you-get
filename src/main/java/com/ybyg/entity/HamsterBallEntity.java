package com.ybyg.entity;

import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.JumpingMount;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.EntityView;
import net.minecraft.world.World;

public class HamsterBallEntity extends AbstractHorseEntity implements JumpingMount {

	public HamsterBallEntity(EntityType<? extends HamsterBallEntity> entityType, World world) {
		super(entityType, world);
		this.saddle(null);
	}

	@Override
	protected void initGoals() {
	}

	@Override
	public boolean isCollidable() {
		return true;
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	public static DefaultAttributeContainer.Builder createCamelAttributes() {
		return AbstractHorseEntity.createBaseHorseAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.0f).add(EntityAttributes.HORSE_JUMP_STRENGTH, 2.0f);
	}

	@Override
	protected Vector3f getPassengerAttachmentPos(Entity passenger, EntityDimensions dimensions, float scaleFactor) {
		float f = Math.min(0.25f, this.limbAnimator.getSpeed());
		float g = this.limbAnimator.getPos();
		float h = 0.12f * MathHelper.cos(g * 1.5f) * 2.0f * f;
		return new Vector3f(0.0f, dimensions.height - 3 * scaleFactor, 0.0f);
	}
	
	@Override
	public boolean shouldRenderName() {
		return false;
	}
	

	@Override
	@Nullable
	public LivingEntity getControllingPassenger() {
		Entity entity;
		if ((entity = this.getFirstPassenger()) instanceof PlayerEntity) {
			PlayerEntity playerEntity = (PlayerEntity) this.getFirstPassenger();
			return playerEntity;
		}
		return super.getControllingPassenger();
	}

	@Override
	protected void tickControlled(PlayerEntity controllingPlayer, Vec3d movementInput) {
		this.setRotation(controllingPlayer.getYaw(), controllingPlayer.getPitch() * 0.5f);
		this.bodyYaw = this.headYaw = this.getYaw();
		this.prevYaw = this.headYaw;
		super.tickControlled(controllingPlayer, movementInput);
	}

	@Override
	public void tick() {
		for (Entity entity2 : this.getWorld().getOtherEntities(this, this.getBoundingBox().expand(0.2f, 0.0, 0.2f))) {
			if (entity2 != this.getFirstPassenger() && (entity2 instanceof AnimalEntity || entity2 instanceof MobEntity) && !(entity2 instanceof HamsterBallEntity)
					&& (entity2 != this.getOwner()) && entity2.isAlive() && LivingEntity.class.cast(entity2).getMaxHealth() < 100) {
				entity2.damage(this.getDamageSources().generic(), LivingEntity.class.cast(entity2).getMaxHealth());
				this.getWorld().addParticle(ParticleTypes.EXPLOSION, entity2.getX(), entity2.getY(), entity2.getZ(),
						0.0, 0.0, 0.0);
				this.getWorld().playSound(entity2.getX(), entity2.getY(), entity2.getZ(),
						SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0f,
						(1.0f + (this.getWorld().random.nextFloat() - this.getWorld().random.nextFloat()) * 0.2f)
								* 0.7f,
						false);
			}
		}
		this.setAngry(false);
		super.tick();
	}

	@Override
	protected Vec3d getControlledMovementInput(PlayerEntity controllingPlayer, Vec3d movementInput) {
		return super.getControlledMovementInput(controllingPlayer, movementInput);
	}

	@Override
	public ActionResult interactHorse(PlayerEntity player, ItemStack stack) {
		if (player.shouldCancelInteraction()) {
			return ActionResult.PASS;
		}
		if (!this.getWorld().isClient) {
			if (this.getOwnerUuid() == null) {
				this.setOwnerUuid(player.getUuid());
			}
			return player.startRiding(this) ? ActionResult.CONSUME : ActionResult.PASS;
		}
		return ActionResult.SUCCESS;
	}

	protected void clampPassengerYaw(Entity passenger) {
		passenger.setBodyYaw(this.getYaw());
		float f = MathHelper.wrapDegrees(passenger.getYaw() - this.getYaw());
		float g = MathHelper.clamp(f, -105.0f, 105.0f);
		passenger.prevYaw += g - f;
		passenger.setYaw(passenger.getYaw() + g - f);
		passenger.setHeadYaw(passenger.getYaw());
	}

	@Override
	protected float getSaddledSpeed(PlayerEntity controllingPlayer) {
		return super.getSaddledSpeed(controllingPlayer);
	}

	@Override
	protected void jump(float strength, Vec3d movementInput) {
		double d = this.getJumpStrength() * strength * this.getJumpVelocityMultiplier();
		double e = d + this.getJumpBoostVelocityModifier();
		Vec3d vec3d = this.getVelocity();
		this.setVelocity(vec3d.x, e, vec3d.z);
		this.setInAir(true);
		this.velocityDirty = true;
		if (movementInput.z > 0.0) {
			float f = MathHelper.sin(this.getYaw() * ((float) Math.PI / 180));
			float g = MathHelper.cos(this.getYaw() * ((float) Math.PI / 180));
			this.setVelocity(this.getVelocity().add(-0.4f * f * strength, 0.0, 0.4f * g * strength));
		}
	}

	@Override
	public boolean canJump() {
		return true;
	}

	@Override
	public void setJumpStrength(int strength) {
		if (!this.isOnGround()) {
			return;
		}
		super.setJumpStrength(strength);
	}

	@Override
	public boolean canSprintAsVehicle() {
		return true;
	}

	@Override
	public boolean damage(DamageSource source, float amount) {
		return source.isOf(DamageTypes.GENERIC_KILL) ? true : false;
	}
	
	

	@Override
	public EntityView method_48926() {
		return super.getWorld();
	}

	@Override
	protected void walkToParent() {
	}

	@Override
	protected void dropInventory() {
	}
	
	@Override
	protected void playWalkSound(BlockSoundGroup group) {
	}
	
	@Override
	protected void playJumpSound() {
	}
	
	@Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        int i;
        if ((i = this.computeFallDamage(fallDistance, damageMultiplier)) <= 0) {
            return false;
        }
        this.damage(damageSource, i);
        if (this.hasPassengers()) {
            for (Entity entity : this.getPassengersDeep()) {
                entity.damage(damageSource, i);
            }
        }
        this.playBlockFallSound();
        return true;
    }
}
