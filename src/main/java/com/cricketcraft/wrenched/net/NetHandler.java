package com.cricketcraft.wrenched.net;

import com.cricketcraft.wrenched.Wrenched;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

/**
 * Created by LatvianModder on 24.05.2016.
 */
public class NetHandler
{
    public static final SimpleNetworkWrapper NET = new SimpleNetworkWrapper(Wrenched.MODID);

    public static void init()
    {
        NET.registerMessage(MessageButtonClicked.class, MessageButtonClicked.class, 1, Side.SERVER);
    }
}
