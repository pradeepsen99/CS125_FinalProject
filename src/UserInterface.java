import org.json.JSONException;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import java.util.HashMap;

public class UserInterface extends JFrame implements ActionListener {

	private JButton refreshButton = new JButton("refresh");
	private String[][] info;
	private api stops = new api();
	private String[] stopList= stops.getStops();
	private HashMap map;
	private JTextArea schedule= new JTextArea();

	/**
	 *
	 */
	private static final long serialVersionUID = -3509133366062402069L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface frame = new UserInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserInterface() throws JSONException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BorderLayout(0,0));

		JComboBox<String> cb = new JComboBox<String>(stopList);
		cb.addActionListener(this);
		schedule.setEditable(false);
		contentPane.add(cb, BorderLayout.NORTH);
		contentPane.add(schedule , BorderLayout.CENTER);

		setContentPane(contentPane);


	}
	private void update() {

	}
	private String arrayToString(String[] a) {
		String str=a[0];
		for(int i=1; i<a.length;i++) {
			str=str+"\n"+a[i];
		}
		return str;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JComboBox) {
			try {
				schedule.setText(arrayToString(api.getRoutes((String) ((JComboBox<?>) (e.getSource())).getSelectedItem())));
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		} 
	
	}

}
