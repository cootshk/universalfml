# universalfml

Allows creating Forge mods for 1.8-1.12 and 1.13+ in the same `@Mod` class.

## Usage

1. Add the Modrinth Maven repository
    ```kt
    repositories {
        exclusiveContent {
            forRepository {
                maven {
                    name = "Modrinth"
                    url = "https://api.modrinth.com/maven"
                }
            }
            filter {
                includeGroup("maven.modrinth")
            }
        }
    }
    ```
2. Include it as a **compile-only dependency**
    ```kt
    dependencies {
        compileOnly("maven.modrinth:universalfml:1.0.0")
    }
    ```
3. Use `@Mod`  
    See the [test mod](src/test/java/dev/cootshk/universalfml/test/TestMod.java) as an example.

<details><summary>Maven (pom.xml) usage</summary>
  ```xml
    <repositories>
        <repository>
            <id>modrinth</id>
            <name>Modrinth</name>
            <url>https://api.modrinth.com/maven</url>
        </repository>
    </repositories>
    <dependencies>
        <dependency>
            <groupId>maven.modrinth</groupId>
            <artifactId>universalfml</artifactId>
            <version>1.0.0</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>
  ```
</details>

> [!CAUTION]
> If you are building for both 1.8-1.12 and 1.13+, you will have to specify the mod ID twice.
> 
> Additionally, Dist.DEDICATED_SERVER will crash the game on 1.8–1.12 (Dist.CLIENT is okay).
