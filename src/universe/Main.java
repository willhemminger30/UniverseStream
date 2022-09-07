package universe;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import universe.system.*;

public class Main {
	
	
	/**
	 * function printUnverseInfoUsingArrayList
	 * prints information from the Universe object using nested for loops, iterating through each ArrayList
	 * @param u
	 */
	public static void printUniverseInfoUsingArrayList(Universe u) {
		ArrayList<CelestialBody> bodies = u.getBodies();
		System.out.println("NumGalaxies: " + bodies.size());
		
		
		for(CelestialBody g : bodies) {
			for(CelestialBody ss :  g.getBodies()) {
				System.out.println("\tSystem: " + ss.getName());
				
				for(CelestialBody s : ss.getBodies()) {
					System.out.println("\tStar: \n\t" + s.getName());
					System.out.printf("\t\t%.2f Solar Masses\n", ((SystemBody)s).getMass());
					
					for(CelestialBody p : s.getBodies()) {
						System.out.println("\t\t\t" + p.getName());
						System.out.printf("\t\t\t\t%.2f Earth Masses\n", ((SystemBody)p).getMass());
						
						if(!p.getBodies().isEmpty()) {
							for(CelestialBody m : p.getBodies()) {
								System.out.println("\t\t\t\t" + m.getName());
								System.out.printf("\t\t\t\t\t%.4f Earth Masses\n", ((SystemBody)m).getMass());
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * function getUniverseInfoUsingStream
	 * will return a List of Strings with the names of all objects in the current universe
	 * @param u
	 * @return List<String> names of objects
	 */
	public static List<String> getUniverseInfoUsingStream(Universe u) {
		System.out.println("\nUniverse Information:");
		
		List<String> bodies = new ArrayList<>();
		
		u.getBodies().stream() // galaxies
		.forEach(b -> {bodies.add("Galaxy: " + b.getName());
			b.getBodies().stream() // systems
			.forEach(c -> {bodies.add("\tStar System: " + c.getName());
				c.getBodies().stream() //stars
				.forEach(d -> {bodies.add("\t\tStar: " + d.getName()
				+ "\n\t\t\tMass: " + String.format("%.4f", ((SystemBody)d).getMass()) + " Solar Masses");
					d.getBodies().stream() //planets
					.forEach(e -> {bodies.add("\t\t\tPlanet: " + e.getName()
					+ "\n\t\t\t\tMass: " + String.format("%.2f", ((SystemBody)e).getMass()) + " Earth Masses");
						e.getBodies().stream() // moons
						.forEach(f -> {bodies.add("\t\t\t\tMoon: " + f.getName()
						+ "\n\t\t\t\t\tMass: " + String.format("%.4f", ((SystemBody)f).getMass()) + " Earth Masses");
						;})
						;})
					;})
				;})
			;});
		
		return bodies;
		
	
	}
	
	/**
	 * function mostPlanets
	 * will return the Star with the most planets in the Universe
	 * @param u
	 * @return Star
	 */
	public static Star mostPlanets(Universe u) {
		
		  Optional<CelestialBody> star = u.getBodies().stream() //galaxies
				.flatMap(b -> b.getBodies().stream()) //systems
				.flatMap(b -> b.getBodies().stream()) //stars
				.collect(Collectors.maxBy(Comparator.comparing(b -> ((SystemBody)b).getBodies().size())));
		  
		  if(star.isPresent()) {
			  return (Star) star.get();
		  } else {
			  return null;
		  }
	}
	
	/**
	 * function mostMoons
	 * will return the Planet with the most moons in the Universe
	 * @param u
	 * @return Planet
	 */
	public static Planet mostMoons(Universe u) {
		Optional<CelestialBody> planet = u.getBodies().stream()
				.flatMap(b -> b.getBodies().stream()) //systems
				.flatMap(b -> b.getBodies().stream()) //stars
				.flatMap(b -> b.getBodies().stream()) //planets
				.collect(Collectors.maxBy(Comparator.comparing(b -> ((SystemBody)b).getBodies().size())));
		  
		  if(planet.isPresent()) {
			  return (Planet) planet.get();
		  } else {
			  return null;
		  }
	}
	
	/**
	 * function mostMassive
	 * will return the most massive Star in the Universe
	 * @param u
	 * @return Star
	 */
	public static Star mostMassive(Universe u) {
		
		Optional<CelestialBody> star = u.getBodies().stream()
				.flatMap(b -> b.getBodies().stream()) //systems
				.flatMap(b -> b.getBodies().stream()) //stars
				.collect(Collectors.maxBy(Comparator.comparingDouble(b -> ((SystemBody)b).getMass())));
		
		  if(star.isPresent()) {
			  return (Star) star.get();
		  } else {
			  return null;
		  }
	}
		
	/**
	 * function getSystemMap
	 * returns a map of each system and its corresponding CelestialBody objects, which will be stars
	 * @param u
	 * @return Map<String, List<CelestialBody>>, map of system name and its associated CelestialBody objects
	 */
	public static Map<String,List<CelestialBody>> getSystemMap(Universe u) {
		
		return u.getBodies().stream()
				.flatMap(b -> b.getBodies().stream()) //systems
				.collect(Collectors.toMap(CelestialBody::getName, CelestialBody::getBodies));
	}
	
	/**
	 * function starsWithMoreThanTenPlanets
	 * returns a list of all stars with more than ten planets
	 * @param u
	 * @return List<Star>, a list of Star objects with more than 10 planets
	 */
	public static List<Star> starsWithMoreThanTenPlanets(Universe u) {
		return u.getBodies().stream()
				.flatMap(b -> b.getBodies().stream())
				.flatMap(b -> b.getBodies().stream())
				.filter(b -> b.getBodies().size() > 10)
				.collect(Collectors.mapping(b -> (Star)b, Collectors.toList()));
				
	}
	
	public static void callUniverseInfo(Universe u) {
		for(String s : getUniverseInfoUsingStream(u)) {
			System.out.println(s);
		}
	}
	
	public static void callMostPlanets(Universe u) {
		Star mostPlanets = mostPlanets(u);
		if(!mostPlanets.equals(null)) {
			System.out.println("\nStar " + mostPlanets.getName() + " has the most planets with " + mostPlanets.getBodies().size() + " orbiting.");
		}
	}
	
	public static void callMostMoons(Universe u) {
		Planet mostMoons = mostMoons(u);
		if(!mostMoons.equals(null)) {
			System.out.println("\nPlanet " + mostMoons.getName() + " has the most moons with " + mostMoons.getBodies().size() + " orbiting.");
		}
	}
	
	public static void callMostMassive(Universe u) {
		Star mostMassive = mostMassive(u);
		if(!mostMassive.equals(null)) {
			System.out.println("\nStar " + mostMassive.getName() + " is the most massive in the universe with a mass of  " + String.format("%.2f", mostMassive.getMass()) + " solar masses.");
		}
	}
	
	public static void callSystemMap(Universe u) {
		System.out.println("\nSystem Map:");
		Map<String, List<CelestialBody>> systemMap = getSystemMap(u);
		
		systemMap.forEach((system, stars) -> {
			System.out.println("\nSystem:\t" + system);
			System.out.println("Stars:");
			stars.forEach(star -> {
				System.out.println("\t" + star.getName());
				System.out.println("\tMass: " + String.format("%.2f Solar Masses", ((Star)star).getMass()));
			});
		});
	}
	
	public static void callTenPlanets(Universe u) {
		System.out.println("\nStars with more than 10 planets: ");
		starsWithMoreThanTenPlanets(u).forEach(b -> System.out.printf("Star: %s\t\tSolar Masses: %06.2f\t\tPlanets: %d\n", 
				b.getName(), b.getMass(), b.getBodies().size()));
	}
	
	/**
	 * function main
	 * begins program execution
	 * @param args
	 */
	public static void main(String [] args) {
		System.out.println("Creating new universe...");
		Universe u = new Universe(3);
		System.out.println("Done initializing");
		
		//printUniverseInfoUsingArrayList(u);
		
		
		
		callUniverseInfo(u);
		callMostPlanets(u);
		callMostMoons(u);
		callMostMassive(u);
		callSystemMap(u);
		callTenPlanets(u);
		
		
	}
}
