package dev.cootshk.universalfml.annotation;

import dev.cootshk.universalfml.MinecraftVersion;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
public @interface AvailableBefore {
    MinecraftVersion value();
}
