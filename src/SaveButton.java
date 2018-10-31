import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * (This purpose of this class is to save the dorm design of the user)
 *
 * <p>
 * Bugs: (none known)
 *
 * @author (Aramide Dada)
 * @author (Aniya Allen)
 * 
 */

public class SaveButton extends Button implements DormGUI {

	public SaveButton(float x, float y, PApplet processing) {
		super(x, y, processing);
		this.processing = processing;

		super.label = "Save Room";

	}

	// used to save the design into DormRoom.ddd and override the mouseDown method
	// inherited
	@Override
	public void mouseDown(Furniture[] furniture) {
		if (isMouseOver()) {
			// Save the room when user clicks on the save button
			saveRoom(furniture);

		}

	}

	public void saveRoom(Furniture[] furniture) {

		float xPosition = 0;
		float yPosition = 0;
		int rotations = 0;
		String furnitureName = "";
		String newLine = "";

		PrintWriter myWriter = null;
		File file = new File("RoomData.ddd");
		try {
			myWriter = new PrintWriter(file);
			// Get positions, furniture name and number of furniture rotations
			// and store it into DormRoom.ddd
			for (int i = 0; i < furniture.length; i++) {
				if (furniture[i] != null) {
					xPosition = furniture[i].getPosition()[0];
					yPosition = furniture[i].getPosition()[1];
					rotations = furniture[i].getRotations();
					furnitureName = furniture[i].getFurniture();
					newLine = furnitureName + ":" + xPosition + "," + yPosition + "," + rotations;
				} else {
					newLine = " ";
				}
				myWriter.println(newLine);
			}

		} catch (IOException ex) {
			System.out.print("WARNING: Could not save room contents to file RoomData.ddd.");
		} finally {
			myWriter.flush();
			// Close the file
			if (myWriter != null) {
				myWriter.close();
			}
		}

	}

}
