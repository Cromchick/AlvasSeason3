package net.mcreator.alvas_season_three.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureEntity;

import net.mcreator.alvas_season_three.potion.WarSpiritPotionEffect;
import net.mcreator.alvas_season_three.AlvasSeason3Mod;

import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Comparator;
import net.minecraft.entity.monster.MonsterEntity;

public class WarSpiritApplyProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onEntityTick(LivingEvent.LivingUpdateEvent event) {
			Entity entity = event.getEntityLiving();
			World world = entity.world;
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			executeProcedure(dependencies);
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				AlvasSeason3Mod.LOGGER.warn("Failed to load dependency world for procedure WarSpiritApply!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				AlvasSeason3Mod.LOGGER.warn("Failed to load dependency x for procedure WarSpiritApply!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				AlvasSeason3Mod.LOGGER.warn("Failed to load dependency y for procedure WarSpiritApply!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				AlvasSeason3Mod.LOGGER.warn("Failed to load dependency z for procedure WarSpiritApply!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				AlvasSeason3Mod.LOGGER.warn("Failed to load dependency entity for procedure WarSpiritApply!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		double entities_nearby = 0;
		if (entity instanceof PlayerEntity) {
			{
				List<Entity> _entfound = world
						.getEntitiesWithinAABB(Entity.class,
								new AxisAlignedBB(x - (5 / 2d), y - (5 / 2d), z - (5 / 2d), x + (5 / 2d), y + (5 / 2d), z + (5 / 2d)), null)
						.stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
							}
						}.compareDistOf(x, y, z)).collect(Collectors.toList());
				for (Entity entityiterator : _entfound) {
					if(!(entityiterator == entity) && entityiterator instanceof PlayerEntity) {
						entities_nearby = ((_entfound.size() - 1) / 4);
					}
					else {
						entities_nearby = 0;
					}
					
				}
			}
			if(entities_nearby != 0) {
				if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).addPotionEffect(new EffectInstance(WarSpiritPotionEffect.potion, (int) 200000000, (int) entities_nearby - 1));
			}
			else {
				if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).removePotionEffect(WarSpiritPotionEffect.potion);
			}
		}
		else if (entity instanceof MonsterEntity) {
			{
				List<Entity> _entfound = world
						.getEntitiesWithinAABB(Entity.class,
								new AxisAlignedBB(x - (5 / 2d), y - (5 / 2d), z - (5 / 2d), x + (5 / 2d), y + (5 / 2d), z + (5 / 2d)), null)
						.stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
							}
						}.compareDistOf(x, y, z)).collect(Collectors.toList());
				for (Entity entityiterator : _entfound) {
					if(!(entityiterator == entity) && entityiterator instanceof MonsterEntity) {
						entities_nearby = ((_entfound.size() - 1) / 4);
					}
					else {
						entities_nearby = 0;
					}
					
				}
			}
			if(entities_nearby != 0) {
				if (entity instanceof MonsterEntity)
				((MonsterEntity) entity).addPotionEffect(new EffectInstance(WarSpiritPotionEffect.potion, (int) 200000000, (int) entities_nearby - 1));
			}
			else {
				if (entity instanceof MonsterEntity)
				((MonsterEntity) entity).removePotionEffect(WarSpiritPotionEffect.potion);
			}
		}
	}
}
