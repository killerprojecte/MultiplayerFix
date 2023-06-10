package org.rgbmc.multiplayerfix;

import net.fabricmc.api.ClientModInitializer;

import java.util.logging.Logger;

public class MultiplayerFix implements ClientModInitializer {
    public static Logger logger = Logger.getLogger("MultiplayerFix");
    @Override
    public void onInitializeClient() {
        logger.info("MultiplayerFix by FlyProject");
    }
}
