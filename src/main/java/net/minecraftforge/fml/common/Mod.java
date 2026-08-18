package net.minecraftforge.fml.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dev.cootshk.universalfml.MinecraftVersion;
import dev.cootshk.universalfml.annotation.AvailableAfter;
import dev.cootshk.universalfml.annotation.AvailableBefore;
import net.minecraftforge.api.distmarker.Dist;

/**
 * This defines a Mod to FML.
 * Any class found with this annotation applied will be loaded as a Mod. The instance that is loaded will
 * represent the mod to other Mods in the system. It will be sent various subclasses of {@code ModLifecycleEvent}
 * at pre-defined times during the loading of the game.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@SuppressWarnings("unused")
public @interface Mod {

    // ------------------------------------------------------------------
    // Minecraft 1.13 and newer
    // ------------------------------------------------------------------

    /**
     * The unique mod identifier for this mod.
     * <b>Required to be lowercased in the English locale for compatibility. Will be truncated to 64 characters long.</b>
     * <br>
     * This will be used to identify your mod for third parties (other mods), it will be used to identify your mod for registries such as block and item registries.
     * By default, you will have a resource domain that matches the modid. All these uses require that constraints are imposed on the format of the modid.
     */
    @AvailableAfter(MinecraftVersion.V1_13)
    String value() default "";

    /**
     * Annotate a class which will be subscribed to an Event Bus at mod construction time.
     * Defaults to subscribing the current modid to the {@code MinecraftForge#EVENT_BUS}
     * on both sides.
     *
     * @see Bus
     */
    @AvailableAfter(MinecraftVersion.V1_13)
    Dist[] dist() default { Dist.CLIENT, Dist.DEDICATED_SERVER };

    // ------------------------------------------------------------------
    // Minecraft 1.8 - 1.12
    // ------------------------------------------------------------------

    /**
     * The unique mod identifier for this mod
     */
    @AvailableBefore(MinecraftVersion.V1_12)
    String modid() default "";

    /**
     * A user friendly name for the mod
     */
    @AvailableBefore(MinecraftVersion.V1_12)
    String name() default "";

    /**
     * A version string for this mod
     */
    @AvailableBefore(MinecraftVersion.V1_12)
    String version() default "";

    /**
     * A simple dependency string for this mod (see modloader's "priorities" string specification)
     */
    @AvailableBefore(MinecraftVersion.V1_12)
    String dependencies() default "";

    /**
     * Whether to use the mcmod.info metadata by default for this mod.
     * If true, settings in the mcmod.info file will override settings in these annotations.
     */
    @AvailableBefore(MinecraftVersion.V1_12)
    boolean useMetadata() default false;

    /**
     * The acceptable range of minecraft versions that this mod will load and run in
     * The default ("empty string") indicates that the currently RUNNING minecraft version is acceptable.
     * This means ANY version that the end user adds the mod to. Modders PLEASS set this.
     * FML will refuse to run with an error if the minecraft version is not in this range across all mods.
     * @return A version range as specified by the maven version range specification or the empty string
     */
    @AvailableBefore(MinecraftVersion.V1_12)
    String acceptedMinecraftVersions() default "";

    /**
     * A replacement for the no-longer-existing "versionRange" of NetworkMod. Specify a remote version range
     * that this mod will accept as valid. Defaults to nothing, which is interpreted as "only this version".
     * Another special value is '*' which means accept all versions.
     * <p>
     * This is ignored if there is a {@link NetworkCheckHandler} annotation on a method in this class.
     *
     * @return A version range as specified by the maven version range specification or the empty string
     */
    @AvailableBefore(MinecraftVersion.V1_12)
    String acceptableRemoteVersions() default "";

    /**
     * A version range specifying compatible save version information. If your mod follows good version numbering
     * practice <a href="http://semver.org/">Like this (http://semver.org/)</a> then this should be sufficient.
     *
     * Advanced users can specify a {@link SaveInspectionHandler} instead.
     * @return A version range as specified by the maven version range specification or the empty string
     */
    @AvailableBefore(MinecraftVersion.V1_12)
    String acceptableSaveVersions() default "";

    /**
     * Specifying this field allows for a mod to expect a signed jar with a fingerprint matching this value.
     * The fingerprint should be SHA-1 encoded, lowercase with ':' removed. An empty value indicates that
     * the mod is not expecting to be signed.
     *
     * Any incorrectness of the fingerprint, be it missing or wrong, will result in the {@link FMLFingerprintViolationEvent}
     * event firing <i>prior to any other event on the mod</i>.
     *
     * @return A certificate fingerprint that is expected for this mod.
     */
    @AvailableBefore(MinecraftVersion.V1_12)
    String certificateFingerprint() default "";

    /**
     * The language the mod is authored in. This will be used to control certain compatibility behaviours for this mod.
     * Valid values are currently "java", "scala"
     *
     * @return The language the mod is authored in
     */
    @AvailableBefore(MinecraftVersion.V1_12)
    String modLanguage() default "java";

    /**
     * The language adapter to be used to load this mod. This overrides the value of modLanguage. The class must have a
     * public zero variable constructor and implement {@link ILanguageAdapter} just like the Java and Scala adapters.
     *
     * A class with an invalid constructor or that doesn't implement {@link ILanguageAdapter} will throw an exception and
     * halt loading.
     *
     * @return The full class name of the language adapter
     */
    @AvailableBefore(MinecraftVersion.V1_12)
    String modLanguageAdapter() default "";

    /**
     * If your mod doesn't have a runtime persistent effect on the state of the game, and can be disabled without side effects
     * (minimap mods, graphical tweak mods) then you can set true here and receive the FMLDeactivationEvent to perform deactivation
     * tasks.
     * This does not affect administrative disabling through the system property fml.modStates or the config file fmlModState.properties.
     * The mod will only be deactivated outside of a running game world - FML will never allow mod deactivation whilst a game server
     * is running.
     *
     * @return if this mod can be deactivated whilst the game is open.
     */
    @AvailableBefore(MinecraftVersion.V1_12)
    boolean canBeDeactivated() default false;

    /**
     * If true, this mod will not be loaded on the Dedicated Server environment.
     * Will crash if both serverSideOnly and clientSideOnly are set to true.
     */
    @AvailableBefore(MinecraftVersion.V1_12)
    boolean clientSideOnly() default false;

    /**
     * If true, this mod will not be loaded on the Client environment.
     * Will crash if both serverSideOnly and clientSideOnly are set to true.
     */
    @AvailableBefore(MinecraftVersion.V1_12)
    boolean serverSideOnly() default false;

    /**
     * An optional GUI factory for this mod. This is the name of a class implementing {@link IModGuiFactory} that will be instantiated
     * on the client side, and will have certain configuration/options guis requested from it.
     *
     * @return The name of a class implementing {@link IModGuiFactory}
     */
    @AvailableBefore(MinecraftVersion.V1_12)
    String guiFactory() default "";

    /**
     * An optional URL to a JSON file that will be checked once per launch to determine if there is an updated
     * version of this mod and notify the end user. For more information see ForgeVersion.
     * Format is defined <a href="https://gist.github.com/LexManos/7aacb9aa991330523884">here</a>
     * @return URL to update metadata json
     */
    @AvailableBefore(MinecraftVersion.V1_12)
    String updateJSON() default "";

    /**
     * A list of custom properties for this mod. Completely up to the mod author if/when they
     * want to put anything in here.
     * @return an optional list of custom properties
     */
    @AvailableBefore(MinecraftVersion.V1_12)
    CustomProperty[] customProperties() default {};

    /**
     * A custom key => value property pair for use with {@link Mod#customProperties()}
     */
    @AvailableBefore(MinecraftVersion.V1_12)
    @Retention(RetentionPolicy.RUNTIME)
    @interface CustomProperty {
        /**
         * A key. Should be unique.
         * @return A key
         */
        String k();
        /**
         * A value. Can be anything.
         * @return A value
         */
        String v();
    }

    /**
     * Marks the associated method as handling an FML lifecycle event.
     * The method must have a single parameter, one of the following types. This annotation
     * replaces the multiple different annotations that previously were used.
     *
     * Current event classes. This first section is standard lifecycle events. They are dispatched
     * at various phases as the game starts. Each event should have information useful to that
     * phase of the lifecycle. They are fired in this order.
     *
     * These suggestions are mostly just suggestions on what to do in each event.
     * <ul>
     * <li> {@link FMLPreInitializationEvent} : Run before anything else. Read your config, create blocks,
     * items, etc, and register them with the {@link GameRegistry}.</li>
     * <li> {@link FMLInitializationEvent} : Do your mod setup. Build whatever data structures you care about. Register recipes,
     * send {@link FMLInterModComms} messages to other mods.</li>
     * <li> {@link FMLPostInitializationEvent} : Handle interaction with other mods, complete your setup based on this.</li>
     * </ul>
     * <p>These are the server lifecycle events. They are fired whenever a server is running, or about to run. Each time a server
     * starts they will be fired in this sequence.
     * <ul>
     * <li> {@link FMLServerAboutToStartEvent} : Use if you need to handle something before the server has even been created.</li>
     * <li> {@link FMLServerStartingEvent} : Do stuff you need to do to set up the server. register commands, tweak the server.</li>
     * <li> {@link FMLServerStartedEvent} : Do what you need to with the running server.</li>
     * <li> {@link FMLServerStoppingEvent} : Do what you need to before the server has started it's shutdown sequence.</li>
     * <li> {@link FMLServerStoppedEvent} : Do whatever cleanup you need once the server has shutdown. Generally only useful
     * on the integrated server.</li>
     * </ul>
     * The second set of events are more specialized, for receiving notification of specific
     * information.
     * <ul>
     * <li> {@link FMLFingerprintViolationEvent} : Sent just before {@link FMLPreInitializationEvent}
     * if something is wrong with your mod signature</li>
     * <li> {@link IMCEvent} : Sent just after {@link FMLInitializationEvent} if you have IMC messages waiting
     * from other mods</li>
     * </ul>
     */
    @AvailableBefore(MinecraftVersion.V1_12)
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface EventHandler {
    }

    /**
     * Populate the annotated field with the mod instance based on the specified ModId. This can be used
     * to retrieve instances of other mods.
     */
    @AvailableBefore(MinecraftVersion.V1_12)
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface Instance {
        /**
         * The mod object to inject into this field
         */
        String value() default "";
    }

    /**
     * Mod instance factory method. Should return an instance of the mod. Applies only to static methods on the same class as {@link Mod}.
     */
    @AvailableBefore(MinecraftVersion.V1_12)
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface InstanceFactory {
    }

    /**
     * Populate the annotated field with the mod's metadata.
     */
    @AvailableBefore(MinecraftVersion.V1_12)
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface Metadata {
        /**
         * The mod id specifying the metadata to load here
         */
        String value() default "";
    }

    /**
     * Marks a class whose static {@code @SubscribeEvent} methods are registered
     * automatically.
     *
     * <p><b>Version caveat.</b> This is the one place where the two eras genuinely differ:
     * on 1.12 the real {@code value()} is typed {@code net.minecraftforge.fml.relauncher.Side},
     * while on 1.13+ it is {@link Dist}, and an annotation element can only have one type.
     * The stub follows 1.13+.
     *
     * <p>That matters less than it looks like it should, because <em>neither</em> era checks
     * the enum's type descriptor. Both read the constant name out of the class file and
     * resolve it against their own enum:
     *
     * <pre>{@code
     * // 1.12.x AutomaticEventSubscriber
     * for (ModAnnotation.EnumHolder h : sidesEnum) sides.add(Side.valueOf(h.getValue()));
     *
     * // 1.16.x AutomaticEventSubscriber
     * sidesValue.stream().map(eh -> Dist.valueOf(eh.getValue()))
     * }</pre>
     *
     * <p>1.16 is emphatic enough about ignoring the descriptor that it synthesizes its own
     * default holders with a {@code null} one. So {@code Dist.CLIENT} written against this
     * stub resolves to {@code Side.CLIENT} on 1.12 and {@code Dist.CLIENT} on 1.13+ — one
     * class file, both eras.
     *
     * <p>The asymmetry is on the other side: 1.12's enum spells it {@code SERVER}, so
     * {@code Dist.DEDICATED_SERVER} becomes {@code Side.valueOf("DEDICATED_SERVER")} and
     * throws {@link IllegalArgumentException} during mod discovery. Client-only is
     * portable; dedicated-server-only is not.
     *
     * <pre>{@code
     * @Mod.EventBusSubscriber                        // 1.12 and 1.13+
     * @Mod.EventBusSubscriber(modid = "examplemod")  // 1.12 and 1.13+
     * @Mod.EventBusSubscriber(Dist.CLIENT)           // 1.12 and 1.13+ (resolved by name)
     * @Mod.EventBusSubscriber(Dist.DEDICATED_SERVER) // 1.13+ ONLY - crashes 1.12
     * @Mod.EventBusSubscriber(bus = Bus.MOD)         // 1.13+; silently ignored on 1.12
     * }</pre>
     *
     * <p>Note that there is no manifest-file equivalent for any of this: neither
     * {@code mcmod.info} (1.12) nor {@code mods.toml} (1.13+) can restrict an individual
     * subscriber class to one side. Per-class sidedness is annotation-only in both eras.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @interface EventBusSubscriber {
        /**
         * Specify targets to load this event subscriber on. Can be used to avoid loading Client specific events
         * on a dedicated server, for example.
         * <p>
         * N.B.: Specifying {@code Dist.DEDICATED_SERVER} will crash the game on 1.8-1.12.
         * @return an array of Dist to load this event subscriber on
         */
        @AvailableAfter(MinecraftVersion.V1_13)
        Dist[] value() default { Dist.CLIENT, Dist.DEDICATED_SERVER };

        /**
         * Optional value, only necessary if this annotation is not on the same class that has a @Mod annotation.
         * Needed to prevent early classloading of classes not owned by your mod.
         * @return a modid
         */
        String modid() default "";

        /**
         * Specify an alternative bus to listen to.
         * <br>
         * If you know all listeners in this class are for a specific bus, you can set it here to speed up registration.
         *
         * @return the bus you wish to listen to
         */
        @AvailableAfter(MinecraftVersion.V1_13)
        Bus bus() default Bus.FORGE;

        @AvailableAfter(MinecraftVersion.V1_13)
        enum Bus {
            /**
             * The main BusGroup that most game events are fired on.
             */
            FORGE,

            /**
             * The mod-specific event BusGroup, usually for mod lifecycle events.
             * @see FMLJavaModLoadingContext#getModBusGroup()
             */
            MOD,

            /**
             * Both the {@link #FORGE} and {@link #MOD} buses. This is slower to register events in your class but
             * allows you to listen to events from different BusGroup types without needing separate classes annotated
             * with {@link EventBusSubscriber}.
             */
            BOTH;
        }
    }
}
