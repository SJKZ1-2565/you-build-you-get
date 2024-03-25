package com.ybyg;

import com.ybyg.render.FlyingAndFlipItemEntityRenderer;
import com.ybyg.render.HamsterBallRenderer;
import com.ybyg.render.HerobrineBuildRenderer;
import com.ybyg.render.WardenBuildRenderer;
import com.ybyg.render.model.HamsterBallModel;
import com.ybyg.render.model.WardenBuildModel;
import com.ybyg.render.model.herobrine_build;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.particle.SonicBoomParticle;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class YouBuildYouGetClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(YouBuildYouGet.NETHERITE_PICKAXE_BUILD_ENTITY,
				context -> new FlyingAndFlipItemEntityRenderer(context, 5.0f, false));

		EntityRendererRegistry.register(YouBuildYouGet.BEDROCK_SWORD_BUILD_ENTITY,
				context -> new FlyingItemEntityRenderer(context, 5.0f, false));

		EntityRendererRegistry.register(YouBuildYouGet.HAMSTER_BALL_ENTITY, (context) -> {
			return new HamsterBallRenderer(context);
		});
		
		EntityRendererRegistry.register(YouBuildYouGet.WARDEN_BUILD_ENTITY, (context) -> {
			return new WardenBuildRenderer(context);
		});
		
		EntityRendererRegistry.register(YouBuildYouGet.HEROBRINE_BUILD_ENTITY, (context) -> {
			return new HerobrineBuildRenderer(context);
		});

		EntityModelLayerRegistry.registerModelLayer(HamsterBallModel.LAYER_LOCATION,
				HamsterBallModel::createBodyLayer);
		
		EntityModelLayerRegistry.registerModelLayer(WardenBuildModel.LAYER_LOCATION,
				WardenBuildModel::createBodyLayer);
		
		EntityModelLayerRegistry.registerModelLayer(herobrine_build.LAYER_LOCATION,
				herobrine_build::createBodyLayer);
		
        ParticleFactoryRegistry.getInstance().register(YouBuildYouGet.RED_SONIC_BOOM, SonicBoomParticle.Factory::new);

	}
}
