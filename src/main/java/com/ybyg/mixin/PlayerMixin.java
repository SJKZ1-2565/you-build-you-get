package com.ybyg.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.world.World;

@Mixin(PlayerEntity.class)
public abstract class PlayerMixin extends LivingEntity {

	protected PlayerMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "tick", at = @At("HEAD"))
	public void tick(CallbackInfo ci) {
		if (!this.getWorld().isClient()) {
			Random random = new Random();
			if (this.getStatusEffect(StatusEffects.REGENERATION) != null
					&& this.getStatusEffect(StatusEffects.STRENGTH) != null
					&& this.getStatusEffect(StatusEffects.SATURATION) != null) {
				ItemEntity itemEntity = new ItemEntity(this.getWorld(), this.getParticleX(0.5),
						this.getRandomBodyY() + 0.25, this.getParticleZ(0.5), Items.GOLD_NUGGET.getDefaultStack());
				itemEntity.setPickupDelay(60);
				this.getWorld().spawnEntity(itemEntity);
			}
		}
	}

}
