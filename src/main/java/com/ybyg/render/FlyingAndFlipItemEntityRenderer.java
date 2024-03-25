package com.ybyg.render;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;

public class FlyingAndFlipItemEntityRenderer<T extends Entity> extends EntityRenderer<T> {
	private static final float MIN_DISTANCE = 12.25f;
	private final ItemRenderer itemRenderer;
	private final float scale;
	private final boolean lit;
	private float deg;

	public FlyingAndFlipItemEntityRenderer(EntityRendererFactory.Context context) {
		this(context, 1.0f, false);
	}

	public FlyingAndFlipItemEntityRenderer(EntityRendererFactory.Context ctx, float scale, boolean lit) {
		super(ctx);
		this.itemRenderer = ctx.getItemRenderer();
		this.scale = scale;
		this.lit = lit;
	}

	@Override
	protected int getBlockLight(T entity, BlockPos pos) {
		return this.lit ? 15 : super.getBlockLight(entity, pos);
	}

	@Override
	public Identifier getTexture(Entity entity) {
		return SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE;
	}

	@Override
	public void render(T entity, float yaw, float tickDelta, MatrixStack matrices,
			VertexConsumerProvider vertexConsumers, int light) {
		if (((Entity) entity).age < 2 && this.dispatcher.camera.getFocusedEntity().squaredDistanceTo(entity) < 12.25) {
			return;
		}
		float dis = (float) entity.getVelocity().horizontalLength();
		if (dis > 0.01f) {
			if (entity.age % 1 == 0) {
				deg += 15;
				if (deg >= 360) {
					deg = 0;
				}
			}
		}
		matrices.push();
		matrices.scale(this.scale, this.scale, this.scale);
		matrices.multiply(this.dispatcher.getRotation());
		if (dis > 0.01f) {
			matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(deg));
		}
		matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0f));
		this.itemRenderer.renderItem(((FlyingItemEntity) entity).getStack(), ModelTransformationMode.GROUND, light,
				OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, ((Entity) entity).getWorld(),
				((Entity) entity).getId());
		matrices.pop();
		super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
	}

}
