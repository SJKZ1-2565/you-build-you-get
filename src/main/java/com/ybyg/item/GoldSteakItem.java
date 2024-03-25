package com.ybyg.item;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;

public class GoldSteakItem extends Item {

	public GoldSteakItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		var world = context.getWorld();
		var pos = context.getBlockPos();
		var armor = EntityType.ARMOR_STAND.create(world);
		var RAW_GOLD = Blocks.RAW_GOLD_BLOCK.getDefaultState();
		var GOLD = Blocks.GOLD_BLOCK.getDefaultState();
		var YELLOW_TERRACOTTA = Blocks.YELLOW_TERRACOTTA.getDefaultState();
		armor.setNoGravity(true);
		armor.setPosition(pos.getX() + 0.5d, pos.getY() + 9, pos.getZ() + 0.5d);
		armor.setInvisible(true);
		armor.addCommandTag("food");
		world.spawnEntity(armor);

		// Create statues
		world.setBlockState(pos.up(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().east(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().east().east(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().east().east().east(), YELLOW_TERRACOTTA);

		world.setBlockState(pos.up().up().west(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().west().west(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up(), GOLD);
		world.setBlockState(pos.up().up().east(), GOLD);
		world.setBlockState(pos.up().up().east().east(), GOLD);
		world.setBlockState(pos.up().up().east().east().east(), GOLD);
		world.setBlockState(pos.up().up().east().east().east().east(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().east().east().east().east().east(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().north(), GOLD);
		world.setBlockState(pos.up().up().east().north(), GOLD);
		world.setBlockState(pos.up().up().east().east().north(), GOLD);
		world.setBlockState(pos.up().up().east().east().east().north(), GOLD);
		world.setBlockState(pos.up().up().south(), GOLD);
		world.setBlockState(pos.up().up().east().south(), GOLD);
		world.setBlockState(pos.up().up().east().east().south(), GOLD);
		world.setBlockState(pos.up().up().east().east().east().south(), GOLD);

		world.setBlockState(pos.up().up().up().west(), GOLD);
		world.setBlockState(pos.up().up().up().west().north(), GOLD);
		world.setBlockState(pos.up().up().up().west().south(), GOLD);
		world.setBlockState(pos.up().up().up().west().east(), GOLD);
		world.setBlockState(pos.up().up().up().west().west(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up(), GOLD);
		world.setBlockState(pos.up().up().up().north(), GOLD);
		world.setBlockState(pos.up().up().up().south(), GOLD);
		world.setBlockState(pos.up().up().up().east(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().east().north(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().east().south(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().east().east(), GOLD);
		world.setBlockState(pos.up().up().up().east().east().north(), GOLD);
		world.setBlockState(pos.up().up().up().east().east().south(), GOLD);
		world.setBlockState(pos.up().up().up().east().east().east(), GOLD);
		world.setBlockState(pos.up().up().up().east().east().east().north(), GOLD);
		world.setBlockState(pos.up().up().up().east().east().east().south(), GOLD);
		world.setBlockState(pos.up().up().up().east().east().east().east(), GOLD);
		world.setBlockState(pos.up().up().up().east().east().east().east().north(), GOLD);
		world.setBlockState(pos.up().up().up().east().east().east().east().south(), GOLD);
		world.setBlockState(pos.up().up().up().east().east().east().east().east(), GOLD);
		world.setBlockState(pos.up().up().up().east().east().east().east().east().north(), GOLD);
		world.setBlockState(pos.up().up().up().east().east().east().east().east().south(), GOLD);
		world.setBlockState(pos.up().up().up().east().east().east().east().east().east(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().east().east().east().east().east().east().north(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().east().east().east().east().east().east().south(), YELLOW_TERRACOTTA);

		world.setBlockState(pos.up().up().up().up(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().north(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().south(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().west(), GOLD);
		world.setBlockState(pos.up().up().up().up().west().west(), RAW_GOLD);
		world.setBlockState(pos.up().up().up().up().west().west().west(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().east(), GOLD);
		world.setBlockState(pos.up().up().up().up().east().east(), GOLD);
		world.setBlockState(pos.up().up().up().up().east().east().east(), GOLD);
		world.setBlockState(pos.up().up().up().up().east().east().east().east(), GOLD);
		world.setBlockState(pos.up().up().up().up().east().east().east().east().east(), GOLD);
		world.setBlockState(pos.up().up().up().up().east().east().east().east().east().east(), GOLD);
		world.setBlockState(pos.up().up().up().up().east().east().east().east().east().east().east(),
				YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().west().north(), GOLD);
		world.setBlockState(pos.up().up().up().up().east().north(), GOLD);
		world.setBlockState(pos.up().up().up().up().east().east().north(), GOLD);
		world.setBlockState(pos.up().up().up().up().east().east().east().north(), GOLD);
		world.setBlockState(pos.up().up().up().up().east().east().east().east().north(), GOLD);
		world.setBlockState(pos.up().up().up().up().east().east().east().east().east().north(), GOLD);
		world.setBlockState(pos.up().up().up().up().east().east().east().east().east().east().north(), GOLD);
		world.setBlockState(pos.up().up().up().up().west().south(), GOLD);
		world.setBlockState(pos.up().up().up().up().east().south(), GOLD);
		world.setBlockState(pos.up().up().up().up().east().east().south(), GOLD);
		world.setBlockState(pos.up().up().up().up().east().east().east().south(), GOLD);
		world.setBlockState(pos.up().up().up().up().east().east().east().east().south(), GOLD);
		world.setBlockState(pos.up().up().up().up().east().east().east().east().east().south(), GOLD);
		world.setBlockState(pos.up().up().up().up().east().east().east().east().east().east().south(), GOLD);

		world.setBlockState(pos.up().up().up().up().up(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().north(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().south(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().west(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().west().north(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().west().south(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().west().west(), RAW_GOLD);
		world.setBlockState(pos.up().up().up().up().up().west().west().west(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().east(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().east().east(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().east().east().east(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().east().east().east().east(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().east().north(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().east().east().north(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().east().east().east().north(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().east().east().east().east().north(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().east().south(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().east().east().south(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().east().east().east().south(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().east().east().east().east().south(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().east().east().east().east().east(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().east().east().east().east().east().east(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().east().east().east().east().east().east().east(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().east().east().east().east().east().east().east().east(),
				YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().east().east().east().east().east().north(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().east().east().east().east().east().east().north(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().east().east().east().east().east().east().east().north(),
				GOLD);
		world.setBlockState(pos.up().up().up().up().up().east().east().east().east().east().south(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().east().east().east().east().east().east().south(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().east().east().east().east().east().east().east().south(),
				GOLD);

		world.setBlockState(pos.up().up().up().up().up().up(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().west(), RAW_GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().west().west(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().up().north(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().south(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().east(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().east().east(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().east().east().east(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().east().east().east().east(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().up().east().east().east().east().east(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().east().east().east().east().east().east(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().east().east().east().east().east().east().east(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().east().east().east().east().east().east().east().east(),
				YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().up().east().north(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().east().east().north(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().east().east().east().north(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().east().east().east().east().north(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().up().east().east().east().east().east().north(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().east().east().east().east().east().east().north(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().east().east().east().east().east().east().east().north(),
				GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().east().south(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().east().east().south(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().east().east().east().south(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().east().east().east().east().south(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().up().east().east().east().east().east().south(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().east().east().east().east().east().east().south(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().east().east().east().east().east().east().east().south(),
				GOLD);

		world.setBlockState(pos.up().up().up().up().up().up().up(), RAW_GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().west(), RAW_GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().west().west(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().up().up().east(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().east().east(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().east().east().east(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().up().up().east().east().east().east(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().east().east().east().east().east(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().east().east().east().east().east().east(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().east().east().east().east().east().east().east(),
				GOLD);
		world.setBlockState(
				pos.up().up().up().up().up().up().up().east().east().east().east().east().east().east().east(), GOLD);
		world.setBlockState(
				pos.up().up().up().up().up().up().up().east().east().east().east().east().east().east().east().east(),
				YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().up().up().east().north(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().east().east().north(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().east().east().east().north(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().up().up().east().east().east().east().north(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().east().east().east().east().east().north(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().east().east().east().east().east().east().north(),
				GOLD);
		world.setBlockState(
				pos.up().up().up().up().up().up().up().east().east().east().east().east().east().east().north(), GOLD);
		world.setBlockState(
				pos.up().up().up().up().up().up().up().east().east().east().east().east().east().east().east().north(),
				GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().east().south(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().east().east().south(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().east().east().east().south(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().up().up().east().east().east().east().south(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().east().east().east().east().east().south(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().east().east().east().east().east().east().south(),
				GOLD);
		world.setBlockState(
				pos.up().up().up().up().up().up().up().east().east().east().east().east().east().east().south(), GOLD);
		world.setBlockState(
				pos.up().up().up().up().up().up().up().east().east().east().east().east().east().east().east().south(),
				GOLD);

		world.setBlockState(pos.up().up().up().up().up().up().up().up().west(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().up().up().up(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().east(), RAW_GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().east().east(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().east().east().east(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().east().east().east().east(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().east().east().east().east().east(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().east().east().east().east().east().east(),
				YELLOW_TERRACOTTA);
		world.setBlockState(
				pos.up().up().up().up().up().up().up().up().east().east().east().east().east().east().east(), GOLD);
		world.setBlockState(
				pos.up().up().up().up().up().up().up().up().east().east().east().east().east().east().east().east(),
				GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().east().east().east().east().east().east().east()
				.east().east(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().east().east().north(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().east().east().east().north(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().east().east().east().east().north(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().east().east().east().east().east().north(),
				GOLD);
		world.setBlockState(
				pos.up().up().up().up().up().up().up().up().east().east().east().east().east().east().north(),
				YELLOW_TERRACOTTA);
		world.setBlockState(
				pos.up().up().up().up().up().up().up().up().east().east().east().east().east().east().east().north(),
				GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().east().east().east().east().east().east().east()
				.east().north(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().east().east().south(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().east().east().east().south(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().east().east().east().east().south(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().east().east().east().east().east().south(),
				GOLD);
		world.setBlockState(
				pos.up().up().up().up().up().up().up().up().east().east().east().east().east().east().south(),
				YELLOW_TERRACOTTA);
		world.setBlockState(
				pos.up().up().up().up().up().up().up().up().east().east().east().east().east().east().east().south(),
				GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().east().east().east().east().east().east().east()
				.east().south(), GOLD);

		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().east(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().east().east(), RAW_GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().east().east().east(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().east().east().east().east(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().east().east().east().east().east(),
				YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().east().east().east().east().east().east(),
				GOLD);
		world.setBlockState(
				pos.up().up().up().up().up().up().up().up().up().east().east().east().east().east().east().east(),
				GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().east().east().east().east().east().east()
				.east().east(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().east().east().east().east().east().east()
				.east().east().east(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().east().east().east().north(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().east().east().east().east().north(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().east().east().east().east().east().north(),
				YELLOW_TERRACOTTA);
		world.setBlockState(
				pos.up().up().up().up().up().up().up().up().up().east().east().east().east().east().east().north(),
				GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().east().east().east().east().east().east()
				.east().north(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().east().east().east().east().east().east()
				.east().east().north(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().east().east().east().south(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().east().east().east().east().south(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().east().east().east().east().east().south(),
				YELLOW_TERRACOTTA);
		world.setBlockState(
				pos.up().up().up().up().up().up().up().up().up().east().east().east().east().east().east().south(),
				GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().east().east().east().east().east().east()
				.east().south(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().east().east().east().east().east().east()
				.east().east().south(), GOLD);

		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().up().east().east(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().up().east().east().east(), RAW_GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().up().east().east().east().east(),
				YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().up().east().east().east().east().east(),
				GOLD);
		world.setBlockState(
				pos.up().up().up().up().up().up().up().up().up().up().east().east().east().east().east().east(), GOLD);
		world.setBlockState(
				pos.up().up().up().up().up().up().up().up().up().up().east().east().east().east().east().east().east(),
				GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().up().east().east().east().east().east()
				.east().east().east(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().up().east().east().east().east().east()
				.east().east().east().east(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().up().east().east().east().east().north(),
				YELLOW_TERRACOTTA);
		world.setBlockState(
				pos.up().up().up().up().up().up().up().up().up().up().east().east().east().east().east().north(), GOLD);
		world.setBlockState(
				pos.up().up().up().up().up().up().up().up().up().up().east().east().east().east().east().east().north(),
				GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().up().east().east().east().east().east()
				.east().east().north(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().up().east().east().east().east().east()
				.east().east().east().north(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().up().east().east().east().east().south(),
				YELLOW_TERRACOTTA);
		world.setBlockState(
				pos.up().up().up().up().up().up().up().up().up().up().east().east().east().east().east().south(), GOLD);
		world.setBlockState(
				pos.up().up().up().up().up().up().up().up().up().up().east().east().east().east().east().east().south(),
				GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().up().east().east().east().east().east()
				.east().east().south(), GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().up().east().east().east().east().east()
				.east().east().east().south(), GOLD);

		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().up().up().east().east().east(),
				YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().up().up().east().east().east().east(),
				RAW_GOLD);
		world.setBlockState(
				pos.up().up().up().up().up().up().up().up().up().up().up().east().east().east().east().east(),
				RAW_GOLD);
		world.setBlockState(
				pos.up().up().up().up().up().up().up().up().up().up().up().east().east().east().east().east().east(),
				RAW_GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().up().up().east().east().east().east()
				.east().east().east(), RAW_GOLD);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().up().up().east().east().east().east()
				.east().east().east().east(), YELLOW_TERRACOTTA);

		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().up().up().up().east().east().east().east(),
				YELLOW_TERRACOTTA);
		world.setBlockState(
				pos.up().up().up().up().up().up().up().up().up().up().up().up().east().east().east().east().east(),
				YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().up().up().up().east().east().east().east()
				.east().east(), YELLOW_TERRACOTTA);
		world.setBlockState(pos.up().up().up().up().up().up().up().up().up().up().up().up().east().east().east().east()
				.east().east().east(), YELLOW_TERRACOTTA);

		ItemStack itemStack = context.getStack();
		itemStack.damage(1, context.getPlayer(), player -> player.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));

		return super.useOnBlock(context);
	}

}
