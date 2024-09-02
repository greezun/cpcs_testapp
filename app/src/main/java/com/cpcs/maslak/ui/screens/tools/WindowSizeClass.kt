package com.cpcs.maslak.ui.screens.tools

import android.content.res.Configuration


/**
 * Enum class representing different size classes for UI layouts based on screen width.
 *
 * This class helps in determining the layout configuration that should be used based on the
 * screen width of the device.
 */
enum class SizeClass {
    /** Represents a compact screen width, typically less than 600dp. */
    COMPACT,

    /** Represents a medium screen width, typically between 600dp and 840dp. */
    MEDIUM,

    /** Represents an expanded screen width, typically greater than 840dp. */
    EXPANDED,
}

/**
 * Determines the [SizeClass] for the current window configuration based on screen width.
 *
 * @param configuration The [Configuration] object that provides the screen width in density-independent pixels (dp).
 * @return The corresponding [SizeClass] based on the screen width.
 */
fun windowConfiguration(configuration: Configuration): SizeClass {
    return when {
        configuration.screenWidthDp < 600 -> SizeClass.COMPACT
        configuration.screenWidthDp in 600..840 -> SizeClass.MEDIUM
        else -> SizeClass.EXPANDED
    }
}