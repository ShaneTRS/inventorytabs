package com.kqp.inventorytabs.init;

import com.kqp.inventorytabs.api.TabProviderRegistry;
import com.kqp.inventorytabs.interf.TabManagerContainer;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class InventoryTabsClient implements ClientModInitializer {
    public static final KeyBinding NEXT_TAB_KEY_BIND = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "inventorytabs.key.next_tab", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_D, "key.categories.inventory"));
    public static final KeyBinding PREV_TAB_KEY_BIND = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "inventorytabs.key.prev_tab", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_A, "key.categories.inventory"));
    public static final KeyBinding MUCHNEXT_TAB_KEY_BIND = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "inventorytabs.key.muchnext_tab", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_S, "key.categories.inventory"));
    public static final KeyBinding MUCHPREV_TAB_KEY_BIND = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "inventorytabs.key.muchprev_tab", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_W, "key.categories.inventory"));

    public static boolean serverDoSightCheckFlag = true;

    @Override
    public void onInitializeClient() {
        TabProviderRegistry.init();

        // Handle state of tab managerInventoryTabsClient
        ClientTickEvents.START_WORLD_TICK.register(world -> {
            MinecraftClient client = MinecraftClient.getInstance();

            if (client.currentScreen != null) {
                TabManagerContainer tabManagerContainer = (TabManagerContainer) client;

                tabManagerContainer.getTabManager().update();
            }
        });
    }
}
