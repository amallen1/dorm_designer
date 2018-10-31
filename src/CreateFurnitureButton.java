/**
 * (This purpose of this class is to customize the various buttons that allow
 * the user to add different furniture to their program)
 *
 * <p>
 * Bugs: (none known)
 *
 * @author (Aramide Dada)
 * @author (Aniya Allen)
 * 
 */
public class CreateFurnitureButton extends Button implements DormGUI {

	public CreateFurnitureButton(String type, float x, float y, PApplet processing) {
		super(x, y, processing);
		this.processing = processing;
		// Capitalize the first letter of type
		String capitalize = type.substring(0, 1).toUpperCase();
		capitalize += type.substring(1, type.length());

		super.label = "Create " + capitalize;

	}

	// used to create a new furniture and override the mouseDown method inherited
	@Override
	public void mouseDown(Furniture[] furniture) {
		// Get type passed in
		String type = super.label.substring(7, super.label.length()).toLowerCase();
		Furniture createFurniture = new Furniture(type, processing);
		if (isMouseOver()) {
			for (int i = 0; i < furniture.length; i++) {
				if (furniture[i] == null) {
					furniture[i] = createFurniture;
					break;
				}
			}
		}
	}
}
