package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.gamelib.backends.lwjgl2.Lwjgl2Backend;
import sk.tuke.kpi.oop.game.scenarios.CustomLevel;

public class Main {

	public static void main(String[] args) {
		WindowSetup windowSetup = new WindowSetup("Project Ellen", 800, 600);
		Game game = new GameApplication(windowSetup, new Lwjgl2Backend());
		Scene scene = new World("world", "maps/custom-level/custom-level.tmx", new CustomLevel.Factory());
		scene.addListener(new CustomLevel());
		game.addScene(scene);

		game.start();
		game.getInput().onKeyPressed(Input.Key.ESCAPE, () -> game.stop());

	}
}
