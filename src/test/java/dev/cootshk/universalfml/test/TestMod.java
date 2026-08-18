package dev.cootshk.universalfml.test;

import net.minecraftforge.fml.common.Mod;

@Mod(
        // 1.13+
        value = TestMod.MODID,
        // 1.8 - 1.12
        modid = TestMod.MODID,
        name = "UniversalFML Test",
        version = "1.0.0",
        // allow client/server mismatch with the mod
        acceptableRemoteVersions = "*"
)
public class TestMod {
    public static final String MODID = "universalfmltest";

    public TestMod() {
        System.out.println("Hello, from universalfml!");
    }
}
