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
<pre>
    &lt;repositories&gt;
        &lt;repository&gt;
            &lt;id&gt;modrinth&lt;/id&gt;
            &lt;name&gt;Modrinth&lt;/name&gt;
            &lt;url&gt;https://api.modrinth.com/maven&lt;/url&gt;
        &lt;/repository&gt;
    &lt;/repositories&gt;
    &lt;dependencies&gt;
        &lt;dependency&gt;
            &lt;groupId&gt;maven.modrinth&lt;/groupId&gt;
            &lt;artifactId&gt;universalfml&lt;/artifactId&gt;
            &lt;version&gt;1.0.0&lt;/version&gt;
            &lt;scope&gt;provided&lt;/scope&gt;
        &lt;/dependency&gt;
    &lt;/dependencies&gt;
</pre>
</details>

> [!CAUTION]
> If you are building for both 1.8-1.12 and 1.13+, you will have to specify the mod ID twice.
> 
> Additionally, Dist.DEDICATED_SERVER will crash the game on 1.8–1.12 (Dist.CLIENT is okay).
