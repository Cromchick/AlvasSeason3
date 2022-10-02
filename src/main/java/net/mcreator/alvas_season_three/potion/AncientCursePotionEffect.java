
package net.mcreator.alvas_season_three.potion;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.world.World;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effect;
import net.minecraft.entity.LivingEntity;

import net.minecraft.util.registry.Registry;

import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;

import net.mcreator.alvas_season_three.procedures.AncientCurseOnEffectActiveTickProcedure;

import java.util.Collections;
import net.minecraft.potion.Effects;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AncientCursePotionEffect {
	@ObjectHolder("alvas_season_3:ancient_curse")
	public static final Effect potion = null;

	@SubscribeEvent
	public static void registerEffect(RegistryEvent.Register<Effect> event) {
		event.getRegistry().register(new EffectCustom().addAttributesModifier(Attributes.ATTACK_SPEED, "55FCED67-E92A-486E-9800-B47F202C4386", (double)-1.1F, AttributeModifier.Operation.MULTIPLY_TOTAL));
	}

	private static Effect register(int id, String key, Effect effectIn) {
      return Registry.register(Registry.EFFECTS, id, key, effectIn);
   }

	public static class EffectCustom extends Effect {
		public EffectCustom() {
			super(EffectType.HARMFUL, -9234643);
			setRegistryName("ancient_curse");
		}

		@Override
		public String getName() {
			return "effect.ancient_curse";
		}

		@Override
		public boolean isBeneficial() {
			return false;
		}

		@Override
		public boolean isInstant() {
			return false;
		}

		@Override
		public boolean shouldRenderInvText(EffectInstance effect) {
			return true;
		}

		@Override
		public boolean shouldRender(EffectInstance effect) {
			return true;
		}

		@Override
		public boolean shouldRenderHUD(EffectInstance effect) {
			return true;
		}

		@Override
		public void performEffect(LivingEntity entity, int amplifier) {
			World world = entity.world;
			double x = entity.getPosX();
			double y = entity.getPosY();
			double z = entity.getPosZ();

			AncientCurseOnEffectActiveTickProcedure.executeProcedure(Collections.EMPTY_MAP);
		}

		@Override
		public boolean isReady(int duration, int amplifier) {
			return true;
		}
	}
}
