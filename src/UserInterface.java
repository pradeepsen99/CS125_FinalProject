import java.awt.BorderLayout;
import java.awt.EventQueue;
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
	private String[] stopList= {"a","b","c","d"};
	private HashMap map;
	private JTextArea schedule;
	
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
	public UserInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JComboBox<String> cb = new JComboBox<String>(stopList);
		cb.addActionListener(this);
		contentPane.add(cb);
		contentPane.add(schedule);
		
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
			schedule.setText(arrayToString((String[])map.get(((JComboBox<?>) (e.getSource())).getSelectedItem())));
		} 
	
	}

}
