package drew.msjeifix.jei;

import dev.dvoa.moresounds.MoreSounds;
import dev.imb11.sounds.config.SoundsConfig;
import dev.imb11.sounds.config.UISoundsConfig;
import dev.imb11.sounds.dynamic.DynamicSoundHelper;
import dev.imb11.sounds.sound.context.ItemStackSoundContext;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nullable;

/**
 * JEI compatibility plugin for More Sounds.
 * We register an event that fires on mouse click when registered by JEI.
 * When a valid click is detected, getItemUnderMouse tries to get the item from the screen.
 */
@JeiPlugin
public class JEICompatPlugin implements IModPlugin
{
    @Nullable
    private static IJeiRuntime runtime;

    public JEICompatPlugin() {
        NeoForge.EVENT_BUS.addListener(EventPriority.NORMAL, JEICompatPlugin::onMouseClicked);
    }

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(MoreSounds.MODID, "jei_compat");
    }

    @Override
    public void onRuntimeAvailable(@NotNull IJeiRuntime jeiRuntime) {
        runtime = jeiRuntime;
    }

    @Override
    public void onRuntimeUnavailable() {
        runtime = null;
    }

    private static @Nullable ItemStack getItemUnderMouse(@NotNull IJeiRuntime runtime)
    {
        ItemStack item = runtime.getIngredientListOverlay().getIngredientUnderMouse(VanillaTypes.ITEM_STACK);

        if(item != null) return item;

        item = runtime.getRecipesGui().getIngredientUnderMouse(VanillaTypes.ITEM_STACK).orElse(null);

        if(item != null) return item;

        item = runtime.getBookmarkOverlay().getItemStackUnderMouse();

        return item;
    }

    private static void onMouseClicked(ScreenEvent.MouseButtonPressed.Pre event)
    {
        if (runtime == null) return;

        int button = event.getButton();

        if (button != GLFW.GLFW_MOUSE_BUTTON_LEFT && button != GLFW.GLFW_MOUSE_BUTTON_RIGHT) return;

        ItemStack item = getItemUnderMouse(runtime);

        if (item == null) return;

        SoundsConfig.get(UISoundsConfig.class).itemClickSoundEffect.playDynamicSound(
                item,
                ItemStackSoundContext.of(DynamicSoundHelper.BlockSoundType.PLACE)
        );
    }
}