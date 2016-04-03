package com.shc.silenceengine.core;

import com.shc.silenceengine.audio.IAudioDevice;
import com.shc.silenceengine.core.gameloops.VariableTimeSteppedLoop;
import com.shc.silenceengine.graphics.IGraphicsDevice;
import com.shc.silenceengine.input.InputDevice;
import com.shc.silenceengine.io.IODevice;
import com.shc.silenceengine.logging.ILogDevice;

/**
 * The SilenceEngine class is the core of the entire engine, and contains all the devices for a platform that we are
 * running upon. It also explains which platform are we running.
 *
 * @author Sri Harsha Chilakapati
 */
public final class SilenceEngine
{
    private SilenceEngine()
    {
    }

    /**
     * The {@link IGameLoop} that generates events in the game.
     */
    public static IGameLoop gameLoop = new VariableTimeSteppedLoop();

    /**
     * The {@link IDisplayDevice} that handles the display, that is the window thing.
     */
    public static IDisplayDevice display;

    /**
     * The {@link IGraphicsDevice} that handles the graphics.
     */
    public static IGraphicsDevice graphics;

    /**
     * The {@link IAudioDevice} that handles the audio.
     */
    public static IAudioDevice audio;

    /**
     * The {@link IODevice} that handles IO operations.
     */
    public static IODevice io;

    /**
     * The {@link InputDevice} that handles game input.
     */
    public static InputDevice input;

    /**
     * The {@link ILogDevice} that handles logging.
     */
    public static ILogDevice log;

    /**
     * The {@link EventManager} that handles the events in SilenceEngine.
     */
    public static EventManager eventManager = new EventManager();

    public enum Platform
    {
        WINDOWS_32,
        WINDOWS_64,
        LINUX_64,
        MACOSX,
        HTML5,
        UNKNOWN
    }
}
