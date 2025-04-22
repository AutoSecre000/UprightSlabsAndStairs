package cn.autosec.onecore.uss.definition;

import cn.autosec.onecore.uss.Config;
import cn.autosec.onecore.uss.OneCore;

public class ModLib {
    protected final String modId = OneCore.MODID;

    protected final int magicNumber = Config.magicNumber;

    protected final boolean validate() {
        return true;
    }
}
