import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * (This purpose of this class is to load the positions and furniture names
 * saved into DormRoom.ddd.)
 *
 * <p>
 * Bugs: (none known)
 *
 * @author (Aramide Dada)
 * @author (Aniya Allen)
 * 
 */

public class LoadButton extends Button implements DormGUI {

	public LoadButton(float x, float y, PApplet processing) {
		super(x, y, processing);
		this.processing = processing;

		super.label = "Load Room";

	}

	// used to load the design saved in DormRoom.ddd and override the mouseDown
	// method inherited
	@Override
	public void mouseDown(Furniture furniture[]) {
		if (isMouseOver()) {
			// clear furniture objects
			for (int i = 0; i < furniture.length; i++) {
				furniture[i] = null;

			}

			// create new object array that corresponds to the data of
			// roomdata.ddd
			loadRoom(furniture);

		}

	}

	/*
	 * This purpose of this method is to load the room
	 * 
	 * 
	 */

	public void loadRoom(Furniture[] furniture) {

		float xPosition = 0;
		float yPosition = 0;
		int rotations = 0;
		String furnitures = "";

		try {
			FileInputStream fileByteStream = null; // File input stream
			Scanner inFS = null; // Scanner object
			fileByteStream = new FileInputStream("RoomData.ddd");
			inFS = new Scanner(fileByteStream);

			int i = 0;
			int numLine = 0;

			while (inFS.hasNextLine()) {
				// If there are more than six lines of stored furniture position
				// and name
				if (numLine > 5) {
					System.out.println("WARNING: Unable to load more furniture.");

				}

				// Stores each of data from DormRoom.ddd
				String eachFileLine = inFS.nextLine();
				eachFileLine = eachFileLine.trim();

				if (eachFileLine.isEmpty()) {
					furniture[i] = null;
				} else {
					// Breaks up the data into the furniture name, xPosition,
					// yPosition and number of rotations
					String[] furnitureName = new String[2];
					String[] positions = new String[3];
					furnitureName = eachFileLine.split(":");
					positions = furnitureName[1].split(",");
					furnitures = furnitureName[0].trim();
					xPosition = Float.parseFloat(positions[0].trim());
					yPosition = Float.parseFloat(positions[1].trim());
					rotations = Integer.parseInt(positions[2].trim());

					// If the furniture name does not exist, the following
					// warning message is printed
					if ((!furnitures.equals("bed") && !furnitures.equals("sofa"))
							&& (!furnitures.equals("dresser") && !furnitures.equals("desk"))
							&& (!furnitures.equals("sink") || furnitures == null)) {
						System.out.print(
								"WARNING: Could not find an image for a furniture object of type: " + furnitures);
					} else {
						// Prevents the program from going out of bounds of the
						// furniture[]
						if (i < furniture.length) {
							// Create new furniture based on data from
							// DormRoom.ddd
							furniture[i] = new Furniture(furnitures, xPosition, yPosition, rotations, processing);
						}
					}
				}
				i++; // Increments the furniture array
				numLine++; // keeps counts of number of lines read from
				// DormRoom.ddd

			}
			inFS.close();

		} catch (FileNotFoundException e) {
			System.out.print("WARNING: Could not load room contents from file RoomData.ddd.");
		}
	}

}
