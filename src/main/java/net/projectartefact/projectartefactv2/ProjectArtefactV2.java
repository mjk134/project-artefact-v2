package net.projectartefact.projectartefactv2;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectArtefactV2 implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("project-artefact-v2");
    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        LOGGER.info("Hello World!");
    }
}
