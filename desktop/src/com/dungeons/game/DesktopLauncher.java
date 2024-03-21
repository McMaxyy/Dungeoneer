package com.dungeons.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import config.Dungeons;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Dungeons");
		
		config.setWindowedMode(1280, 720);
		config.setDecorated(true);
		
		int samples = 32;
        config.setBackBufferConfig(8, 8, 8, 8, 16, 0, samples);
		
		new Lwjgl3Application(new Dungeons(), config);
	}
}
