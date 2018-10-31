/**
 * (This purpose of this class is clear the room of all furniture objects.)
 *
 * <p>
 * Bugs: (none known)
 *
 * @author (Aramide Dada)
 * @author (Aniya Allen)
 * 
 */
public class ClearButton extends Button implements DormGUI {
	public ClearButton(float x, float y, PApplet processing) {
		super(x, y, processing);
		this.processing = processing;

		super.label = "Clear Room";
	}

	// used to clear every reference inside the Furniture[]
	@Override
	public void mouseDown(Furniture[] furniture) {
		// sets the array reference to null to clear furniture
		if (isMouseOver()) {
			for (int i = 0; i < furniture.length; i++) {
				if (furniture[i] != null) {
					furniture[i] = null;
				}
			}
		}
	}

}