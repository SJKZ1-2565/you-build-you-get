package com.ybyg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ybyg.entity.BedrockSwordEntity;
import com.ybyg.entity.HamsterBallEntity;
import com.ybyg.entity.HerobrineBuildEntity;
import com.ybyg.entity.NetheritePickaxeEntity;
import com.ybyg.entity.WardenBuildEntity;
import com.ybyg.item.BedrockSwordItem;
import com.ybyg.item.GoldSteakItem;
import com.ybyg.item.HamsterBallItem;
import com.ybyg.item.NetheritePickaxeItem;
import com.ybyg.item.WardenBuildItem;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterials;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;

public class YouBuildYouGet implements ModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("ybyg");
	public static String MOD_ID = "ybyg";

	public static final EntityType<NetheritePickaxeEntity> NETHERITE_PICKAXE_BUILD_ENTITY = Registry.register(Registries.ENTITY_TYPE,
			new Identifier(MOD_ID, "netherite_pickaxe_build"),
			FabricEntityTypeBuilder.create(SpawnGroup.MISC, NetheritePickaxeEntity::new)
					.dimensions(EntityDimensions.fixed(1.25f, 5f)).build());

	public static final EntityType<BedrockSwordEntity> BEDROCK_SWORD_BUILD_ENTITY = Registry.register(Registries.ENTITY_TYPE,
			new Identifier(MOD_ID, "bedrock_sword_build"),
			FabricEntityTypeBuilder.create(SpawnGroup.MISC, BedrockSwordEntity::new)
					.dimensions(EntityDimensions.fixed(1.25f, 5f)).build());

	public static final EntityType<HamsterBallEntity> HAMSTER_BALL_ENTITY = Registry.register(Registries.ENTITY_TYPE,
			new Identifier(MOD_ID, "hamster_ball_build"),
			FabricEntityTypeBuilder.create(SpawnGroup.MISC, HamsterBallEntity::new)
					.dimensions(EntityDimensions.fixed(5f, 5f)).build());
	
	public static final EntityType<WardenBuildEntity> WARDEN_BUILD_ENTITY = Registry.register(Registries.ENTITY_TYPE,
			new Identifier(MOD_ID, "warden_build_entity"),
			FabricEntityTypeBuilder.create(SpawnGroup.MISC, WardenBuildEntity::new)
			.dimensions(EntityDimensions.fixed(10f, 25f)).build());
	
	public static final EntityType<HerobrineBuildEntity> HEROBRINE_BUILD_ENTITY = Registry.register(Registries.ENTITY_TYPE,
			new Identifier(MOD_ID, "herobrine_build_entity"),
			FabricEntityTypeBuilder.create(SpawnGroup.MISC, HerobrineBuildEntity::new)
			.dimensions(EntityDimensions.fixed(10f, 20f)).build());

	public static final Item NETHERITE_PICKAXE_BUILD = new NetheritePickaxeItem(ToolMaterials.IRON, 1, -2.8f,
			new FabricItemSettings().maxDamage(256), YouBuildYouGet.NETHERITE_PICKAXE_BUILD_ENTITY);

	public static final Item BEDROCK_SWORD_BUILD = new BedrockSwordItem(ToolMaterials.NETHERITE, 8, -2.4f,
			new FabricItemSettings().maxDamage(256), YouBuildYouGet.BEDROCK_SWORD_BUILD_ENTITY);

	public static final Item GOLD_STEAK_BUILD = new GoldSteakItem(new FabricItemSettings().maxDamage(256));

	public static final Item HAMSTER_BALL_BUILD = new HamsterBallItem(new FabricItemSettings().maxCount(1), YouBuildYouGet.HAMSTER_BALL_ENTITY);
	
	public static final Item WARDEN_BUILD = new WardenBuildItem(new FabricItemSettings().maxDamage(3), YouBuildYouGet.WARDEN_BUILD_ENTITY);
	
	public static final Item HEROBRINE_BUILD = new WardenBuildItem(new FabricItemSettings().maxDamage(3), YouBuildYouGet.HEROBRINE_BUILD_ENTITY);
	
	public static final DefaultParticleType RED_SONIC_BOOM = FabricParticleTypes.simple();


	@Override
	public void onInitialize() {
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(YouBuildYouGet.MOD_ID, "red_sonic_boom"), RED_SONIC_BOOM);

		// 3x3 Block Breal
		PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
			if (player.getMainHandStack().getItem().equals(NETHERITE_PICKAXE_BUILD)) {
				float pitch = Float.valueOf(MathHelper.wrapDegrees(player.getPitch()));
				var direction = player.getHorizontalFacing();
				if (pitch <= 45 && pitch >= -45) {
					if ((direction == Direction.NORTH || direction == Direction.SOUTH)) {
						world.breakBlock(pos.west(), true);
						world.breakBlock(pos.west().up(), true);
						world.breakBlock(pos.west().down(), true);
						world.breakBlock(pos.east(), true);
						world.breakBlock(pos.east().up(), true);
						world.breakBlock(pos.east().down(), true);
						world.breakBlock(pos.up(), true);
						world.breakBlock(pos.down(), true);
					}
					if ((direction == Direction.EAST || direction == Direction.WEST)) {
						world.breakBlock(pos.north(), true);
						world.breakBlock(pos.north().up(), true);
						world.breakBlock(pos.north().down(), true);
						world.breakBlock(pos.south(), true);
						world.breakBlock(pos.south().up(), true);
						world.breakBlock(pos.south().down(), true);
						world.breakBlock(pos.up(), true);
						world.breakBlock(pos.down(), true);
					}
				} else {
					world.breakBlock(pos.west(), true);
					world.breakBlock(pos.west().north(), true);
					world.breakBlock(pos.west().south(), true);
					world.breakBlock(pos.east(), true);
					world.breakBlock(pos.east().north(), true);
					world.breakBlock(pos.east().south(), true);
					world.breakBlock(pos.north(), true);
					world.breakBlock(pos.south(), true);
				}
			}
		});
		// Gold Steak Destroy
		PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
			var RAW_GOLD = Blocks.RAW_GOLD_BLOCK.getDefaultState();
			var GOLD = Blocks.GOLD_BLOCK.getDefaultState();
			var YELLOW_TERRACOTTA = Blocks.YELLOW_TERRACOTTA.getDefaultState();
			if (state.isOf(RAW_GOLD.getBlock()) || state.isOf(GOLD.getBlock())
					|| state.isOf(YELLOW_TERRACOTTA.getBlock())) {
				var armorStandEntity = player.getEntityWorld().getEntitiesByClass(ArmorStandEntity.class,
						new Box(pos).expand(10),
						armorStand -> armorStand.hasNoGravity() && armorStand.getCommandTags().contains("food"));
				if (armorStandEntity.size() != 0 && armorStandEntity != null) {
					player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 400, 1));
					player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 400, 1));
					player.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 400, 1));
				}
			}
		});
		registerItem("netherite_pickaxe_build", NETHERITE_PICKAXE_BUILD);
		registerItem("bedrock_sword_build", BEDROCK_SWORD_BUILD);
		registerItem("gold_steak_build", GOLD_STEAK_BUILD);
		registerItem("hamster_ball_build", HAMSTER_BALL_BUILD);
		registerItem("warden_build", WARDEN_BUILD);
		registerItem("herobrine_build", HEROBRINE_BUILD);
		FabricDefaultAttributeRegistry.register(HAMSTER_BALL_ENTITY, HamsterBallEntity.createCamelAttributes());
		FabricDefaultAttributeRegistry.register(WARDEN_BUILD_ENTITY, WardenEntity.addAttributes());
		FabricDefaultAttributeRegistry.register(HEROBRINE_BUILD_ENTITY, WardenEntity.addAttributes());
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(content -> {
			content.addAfter(Items.ZOMBIFIED_PIGLIN_SPAWN_EGG, NETHERITE_PICKAXE_BUILD);
			content.addAfter(NETHERITE_PICKAXE_BUILD, BEDROCK_SWORD_BUILD);
			content.addAfter(BEDROCK_SWORD_BUILD, GOLD_STEAK_BUILD);
			content.addAfter(GOLD_STEAK_BUILD, HAMSTER_BALL_BUILD);
			content.addAfter(HAMSTER_BALL_BUILD, WARDEN_BUILD);
			content.addAfter(WARDEN_BUILD, HEROBRINE_BUILD);
		});
	}

	public void registerItem(String itemName, Item item) {
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, itemName), item);
	}

}