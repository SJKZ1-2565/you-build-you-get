package com.ybyg.render;

import com.ybyg.YouBuildYouGet;
import com.ybyg.entity.HamsterBallEntity;
import com.ybyg.render.model.HamsterBallModel;

import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.util.Identifier;

public class HamsterBallRenderer
		extends LivingEntityRenderer<HamsterBallEntity, HamsterBallModel<HamsterBallEntity>> {

	public HamsterBallRenderer(Context ctx) {
		super(ctx, new HamsterBallModel(ctx.getPart(HamsterBallModel.LAYER_LOCATION)), 0.0f);
	}

	@Override
	public Identifier getTexture(HamsterBallEntity var1) {
		return new Identifier(YouBuildYouGet.MOD_ID, "textures/entity/hamsterball/texhamsterballbuild.png");
	}
}
