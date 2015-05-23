import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Credits extends JPanel {

	public Credits() {
		JTextArea name1 = new JTextArea("Chris");
		name1.setEditable(false);
		add(name1);

		JTextArea name2 = new JTextArea("Mick");
		name2.setEditable(false);
		add(name2);

		JTextArea name3 = new JTextArea("Kelvin");
		name3.setEditable(false);
		add(name3);

		JTextArea name4 = new JTextArea("Wayne");
		name4.setEditable(false);
		add(name4);
	}
}
