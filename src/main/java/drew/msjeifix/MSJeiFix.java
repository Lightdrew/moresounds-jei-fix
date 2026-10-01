package drew.msjeifix;

import com.mojang.logging.LogUtils;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(MSJeiFix.MOD_ID)
public class MSJeiFix {
    /**This logger is used to write text to the console and the log file.
     * It is considered best practice to use your mod id as the logger's name.
     * That way, it's clear which mod wrote info, warnings, and errors.
     */
    public static final String MOD_ID = "msjeifix";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MSJeiFix()
    {
        LOGGER.info("MSJeiFix loaded");
    }
}
