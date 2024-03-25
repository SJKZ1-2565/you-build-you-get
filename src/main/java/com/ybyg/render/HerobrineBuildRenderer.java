package com.ybyg.render;

import com.ybyg.YouBuildYouGet;
import com.ybyg.entity.HamsterBallEntity;
import com.ybyg.entity.HerobrineBuildEntity;
import com.ybyg.entity.WardenBuildEntity;
import com.ybyg.render.model.HamsterBallModel;
import com.ybyg.render.model.WardenBuildModel;
import com.ybyg.render.model.herobrine_build;

import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.util.Identifier;

public class HerobrineBuildRenderer
		extends LivingEntityRenderer<HerobrineBuildEntity, herobrine_build<HerobrineBuildEntity>> {

	public HerobrineBuildRenderer(Context ctx) {
		super(ctx, new herobrine_build(ctx.getPart(herobrine_build.LAYER_LOCATION)), 0.0f);
	}

	@Override
	public Identifier getTexture(HerobrineBuildEntity var1) {
		return new Identifier(YouBuildYouGet.MOD_ID, "textures/entity/herobrinebuild/texherobrine.png");
	}
}
