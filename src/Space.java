// ----- Imports ----- //

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


// ----- Class ----- //

class Space {

	private final double spaceWidth = 700;
	private final double spaceHeight = 700;
	private final double centreX = spaceWidth / 2;
	private final double centreY = spaceHeight / 2;
	private final double sunSize = 35.0;
	private Rectangle background;
	private Group spaceGroup;
	private Map<String, Planet> planets;
	public String[] orderedPlanets = {"mercury", "venus", "earth", "mars",
		"jupiter", "saturn", "uranus", "neptune"};

	private void buildBackground() {

		background = new Rectangle(spaceWidth, spaceHeight, Color.BLACK);
		spaceGroup.getChildren().add(background);

	}

	private void buildSun () {

		Circle sun = new Circle(centreX, centreY, sunSize, Color.ORANGE);
		spaceGroup.getChildren().add(sun);

	}

	private void addPlanet (String[] planetData) {



	}

	private void addPlanets () throws FileNotFoundException {

		Scanner planetsData = new Scanner(new File("src/planetdata.txt"));

		while (planetsData.hasNext()) {
			String dataLine = planetsData.nextLine();
			String[] planetData = dataLine.split(",");
			Planet planet = new Planet(centreX, centreY, planetData);
			planets.put(planetData[0].toLowerCase(), planet);
		}

		// Planet mercury = new Planet(
		// 	"Mercury", centreX, centreY, 50, 5, 1, new int[]{180, 180, 180});
		// planets.put("mercury", mercury);
		// Planet venus = new Planet(
		// 	"Venus", centreX, centreY, 90, 10, 3, new int[]{250, 200, 180});
		// planets.put("venus", venus);
		// Planet earth = new Planet(
		// 	"Earth", centreX, centreY, 130, 10, 5, new int[]{40, 210, 255});
		// planets.put("earth", earth);
		// Planet mars = new Planet(
		// 	"Mars", centreX, centreY, 170, 8, 10, new int[]{255, 90, 30});
		// planets.put("mars", mars);
		// Planet jupiter = new Planet(
		// 	"Jupiter", centreX, centreY, 210, 20, 60, new int[]{240, 120, 40});
		// planets.put("jupiter", jupiter);
		// Planet saturn = new Planet(
		// 	"Saturn", centreX, centreY, 250, 18, 150, new int[]{220, 160, 40});
		// planets.put("saturn", saturn);
		// Planet uranus = new Planet(
		// 	"Uranus", centreX, centreY, 290, 13, 420, new int[]{180, 200, 240});
		// planets.put("uranus", uranus);
		// Planet neptune = new Planet(
		// 	"Neptune", centreX, centreY, 330, 13, 825, new int[]{0, 50, 240});
		// planets.put("neptune", neptune);

	}

	private void buildPlanets () throws FileNotFoundException {

		addPlanets();

		for (Map.Entry<String, Planet> planet : planets.entrySet()) {
			spaceGroup.getChildren().add(planet.getValue().getOrbitalPath());
			spaceGroup.getChildren().add(planet.getValue().getPlanet());
		}

	}

	public void startMotion () {

		for (Map.Entry<String, Planet> planet : planets.entrySet()) {
			planet.getValue().getMotion().play();
		}

	}

	public Group getSpace () {
		return spaceGroup;
	}

	public Planet getPlanet (String name) {
		return planets.get(name);
	}

	public String[] getPlanets () {
		return orderedPlanets;
	}

	public double getWidth () {
		return spaceWidth;
	}

	public double getHeight () {
		return spaceHeight;
	}

	public Space () throws FileNotFoundException {

		this.planets = new HashMap<>();
		this.spaceGroup = new Group();

		buildBackground();
		buildSun();
		buildPlanets();

	}

}
