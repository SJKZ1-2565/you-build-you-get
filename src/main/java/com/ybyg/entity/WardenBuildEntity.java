package com.ybyg.entity;

import com.ybyg.YouBuildYouGet;
import com.ybyg.entity.goal.FollowPlayerGoal;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.IronGolemLookGoal;
import net.minecraft.entity.ai.goal.IronGolemWanderAroundGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.TrackIronGolemTargetGoal;
import net.minecraft.entity.ai.goal.UniversalAngerGoal;
import net.minecraft.entity.ai.goal.WanderAroundPointOfInterestGoal;
import net.minecraft.entity.ai.goal.WanderNearTargetGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class WardenBuildEntity extends IronGolemEntity {

	public WardenBuildEntity(EntityType<? extends WardenBuildEntity> entityType, World world) {
		super(entityType, world);
		this.setStepHeight(1.0f);
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(1, new MeleeAttackGoal(this, 1.5, true));
		this.goalSelector.add(2, new WanderNearTargetGoal(this, 0.9, 32.0f));
		this.goalSelector.add(2, new WanderAroundPointOfInterestGoal((PathAwareEntity) this, 0.6, false));
		this.goalSelector.add(4, new IronGolemWanderAroundGoal(this, 0.6));
		this.goalSelector.add(6, new FollowPlayerGoal(this, 1.0, 40.0f, 20.0f, false));
		this.goalSelector.add(8, new LookAroundGoal(this));
		this.targetSelector.add(1, new TrackIronGolemTargetGoal(this));
		this.targetSelector.add(2, new RevengeGoal(this, new Class[0]));
		this.targetSelector.add(3, new ActiveTargetGoal<PlayerEntity>(this, PlayerEntity.class, 50, true, false,
				entity -> !entity.getCommandTags().contains("wardenOwner")));
		this.targetSelector.add(3, new ActiveTargetGoal<MobEntity>(this, MobEntity.class, 50, false, false,
				entity -> entity instanceof MobEntity && entity.getType() != YouBuildYouGet.WARDEN_BUILD_ENTITY));
        this.targetSelector.add(4, new UniversalAngerGoal<WardenBuildEntity>(this, true));

	}

	@Override
	public boolean canTarget(EntityType<?> type) {
		if(type == YouBuildYouGet.HEROBRINE_BUILD_ENTITY)
		{
			return true;
		}
		return super.canTarget(type);
	}

	@Override
	public boolean tryAttack(Entity target) {
		float f = 10;
		float g = (int) f > 0 ? f / 2.0f + (float) this.random.nextInt((int) f) : f;
		boolean bl = target.damage(this.getDamageSources().mobAttack(this), g);
		if (true) {
			Vec3d vec3d = this.getPos().add(0.0, 20, 0.0);
			Vec3d vec3d2 = target.getEyePos().subtract(vec3d);
			Vec3d vec3d3 = vec3d2.normalize();
			for (int i = 1; i < MathHelper.floor(vec3d2.length()) + 22; ++i) {
				Vec3d vec3d4 = vec3d.add(vec3d3.multiply(i));
				ServerWorld.class.cast(this.getWorld()).spawnParticles(ParticleTypes.SONIC_BOOM, vec3d4.x, vec3d4.y,
						vec3d4.z, 1, 0.0, 0.0, 0.0, 0.0);
			}
			this.playSound(SoundEvents.ENTITY_WARDEN_SONIC_BOOM, 3.0f, 1.0f);
			target.damage(ServerWorld.class.cast(this.getWorld()).getDamageSources().sonicBoom(this), 10.0f);
			double d = 0.5
					* (1.0 - ((LivingEntity) target).getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE));
			double e = 2.5
					* (1.0 - ((LivingEntity) target).getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE));
			target.addVelocity(vec3d3.getX() * e, vec3d3.getY() * d, vec3d3.getZ() * e);
		}
		this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0f, 1.0f);
		return bl;
	}

}
