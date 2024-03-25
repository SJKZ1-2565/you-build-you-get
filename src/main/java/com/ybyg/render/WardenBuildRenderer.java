package com.ybyg.render;

import com.ybyg.YouBuildYouGet;
import com.ybyg.entity.HamsterBallEntity;
import com.ybyg.entity.WardenBuildEntity;
import com.ybyg.render.model.HamsterBallModel;
import com.ybyg.render.model.WardenBuildModel;

import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.util.Identifier;

public class WardenBuildRenderer
		extends LivingEntityRenderer<WardenBuildEntity, WardenBuildModel<WardenBuildEntity>> {

	public WardenBuildRenderer(Context ctx) {
		super(ctx, new WardenBuildModel(ctx.getPart(WardenBuildModel.LAYER_LOCATION)), 0.0f);
	}

	@Override
	public Identifier getTexture(WardenBuildEntity var1) {
		return new Identifier(YouBuildYouGet.MOD_ID, "textures/entity/wardenbuild/texwarden.png");
	}
}
