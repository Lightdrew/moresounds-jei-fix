package drew.msjeifix.mixinsquared;

import com.bawnorton.mixinsquared.api.MixinCanceller;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import java.util.List;

public class MoreSoundsMixinCanceller implements MixinCanceller {
    private static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public boolean shouldCancel(List<String> targetClassNames, String mixinClassName) {
        return mixinClassName.equals("dev.dvoa.moresounds.mixins.JEICompat");
    }
}
