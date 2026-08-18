package net.minecraftforge.api.distmarker;

import dev.cootshk.universalfml.MinecraftVersion;
import dev.cootshk.universalfml.annotation.AvailableAfter;

/**
 * On 1.8-1.12, this class is instead called {@code Stub.CLIENT} or {@code Stub.SERVER}.
 * On 1.13+, this class is called {@code Dist.CLIENT} or {@code Dist.DEDICATED_SERVER}.
 * <p>
 * Because .CLIENT overlaps, {@link #CLIENT} can be used on all versions,
 * while {@link #DEDICATED_SERVER} is for 1.13+.
 */
@SuppressWarnings("unused")
@AvailableAfter(MinecraftVersion.V1_13)
public enum Dist {
    /**
     * The client distribution. This is the game client players can purchase and play.
     * It contains the graphics and other rendering to present a viewport into the game world.
     * <p>
     * Note that {@code @Mod.EventBusSubscriber(Dist.CLIENT)} works on 1.8-1.12, however
     * {@code @Mod.EventBusSubscriber(Dist.DEDICATED_SERVER)} does not. */
    @AvailableAfter(MinecraftVersion.V1_8) // note: This will still work on 1.8-1.12
    CLIENT,
    /**
     * The dedicated server distribution. This is the server only distribution available for
     * download. It simulates the world, and can be communicated with via a network.
     * It contains no visual elements of the game whatsoever.
     * <p>
     * Note that {@code @Mod.EventBusSubscriber(Dist.CLIENT)} works on 1.8-1.12, however
     * {@code @Mod.EventBusSubscriber(Dist.DEDICATED_SERVER)} does not.
     *
     * @deprecated because this will crash the game on 1.8-1.12.
     */
    @AvailableAfter(MinecraftVersion.V1_13)
    @Deprecated
    DEDICATED_SERVER;

    /**
     * @return If this marks a dedicated server.
     */
    public boolean isDedicatedServer() {
        return !isClient();
    }

    /**
     * @return if this marks a client.
     */
    public boolean isClient() {
        return this == CLIENT;
    }
}
