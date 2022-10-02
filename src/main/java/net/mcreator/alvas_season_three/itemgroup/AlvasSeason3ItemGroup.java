
package net.mcreator.alvas_season_three.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.alvas_season_three.item.WarHelmetItem;
import net.mcreator.alvas_season_three.AlvasSeason3ModElements;

@AlvasSeason3ModElements.ModElement.Tag
public class AlvasSeason3ItemGroup extends AlvasSeason3ModElements.ModElement {
	public AlvasSeason3ItemGroup(AlvasSeason3ModElements instance) {
		super(instance, 5);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabalvas_season_3") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(WarHelmetItem.helmet);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}

	public static ItemGroup tab;
}
