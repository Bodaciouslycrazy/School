import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class DentalOfficeTester {
	public static void main(String[] args){
		DentalOfficeWindow myFrame = new DentalOfficeWindow();
		myFrame.setTitle("UTD Dental Services");
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
		myFrame.setResizable(false);
		
	}
}


/*
 * Constructor returns services and durations.
 * You can also get costs here.
 * no physical window/panel.
 */
class DentalOffice{
	
	private ArrayList<Double> costs = new ArrayList<Double>();
	
	
	
	public DentalOffice(ArrayList<String> services, ArrayList<String> durations){
		
		String[] tempService = {"Cavity", "Root Canal", "Braces", "Bridge", "Teeth cleaning"};
		
		for(int i = 0; i < tempService.length; i++ ){
			services.add(tempService[i]);
		}
		
		String[] tempDuration = {"one week", "one month", "one year"};
		
		for(int i = 0; i < tempDuration.length; i++){
			durations.add(tempDuration[i]);
		}
		
		double tempCosts[] = {200.00, 600.00, 2000.00, 500.00, 100.00};
		
		
		
		//There were some errors here because in Khan's code, he never initialized the "costs" variable. Gave NulPointerExeptions
		for(int i = 0; i < tempCosts.length; i++){
			costs.add(tempCosts[i]);
		}
		
	}
	
	
	
	//Getters
	
	public double getCost( int index){
		return costs.get(index);
	}
}



/*
 * MAIN CLASS
 * this listens for button clicks and etc. Sets up all of the other panels, and puts them into a frame.
 */

class DentalOfficeWindow extends JFrame implements ActionListener, ItemListener{
	
	private ArrayList<String> services;
	
	private JRadioButton[] jrbuttons;
	private ArrayList<String> durations;
	private ServicePanel servicesPanel;
	private JPanel durationPanel;
	private JPanel actionPanel;
	private JButton button1;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private Icon dentalIcon = new ImageIcon("dentalOffice.png");
	private double total = 0;
	private String returnTime;
	private DentalOffice office;
	private PatientPanel patientPanel;
	
	OutputWindow outFrame;
	
	private ButtonGroup group1 = new ButtonGroup();
	
	//Constructor
	public DentalOfficeWindow(){
		
		//Initializing this buttload of variables
		outFrame = new OutputWindow();
		outFrame.setTitle("Daily Activity Log");
		outFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		outFrame.setSize(450,300);
		outFrame.setLocation(new Point( this.getX() + 550, this.getY() ));
		outFrame.setVisible(true);;
		outFrame.setResizable(false);;
		
		services = new ArrayList<String>();
		durations = new ArrayList<String>();
		office = new DentalOffice(services, durations);
		
		//put in by Bodie to fix small size issue
		setSize(500,500);
		
		//Setting up all the pannels and buttons.
		setLayout(new BorderLayout());
		servicesPanel = new ServicePanel(services);
		durationPanel = new JPanel();
		durationPanel.setLayout( new FlowLayout() );
		
		actionPanel = new JPanel( new GridLayout(2,1));
		jrbuttons = new JRadioButton[durations.size()];
		button1 = new JButton("Calculate Bill");
		button1.addActionListener(this);
		
		label3 = new JLabel(dentalIcon);
		label1 = new JLabel("Return in");
		durationPanel.add(label1);
		
		patientPanel = new PatientPanel();
		
		for(int i = 0; i < jrbuttons.length; i++){
			jrbuttons[i] = new JRadioButton( durations.get(i) );
			jrbuttons[i].addItemListener(this);;
			group1.add(jrbuttons[i]);
			durationPanel.add( jrbuttons[i] );
		}
		
		//Adding panels.
		add(servicesPanel, BorderLayout.WEST);
		add(label3, BorderLayout.CENTER);
		add(durationPanel, BorderLayout.NORTH);
		label2 = new JLabel("Total cost: $", SwingConstants.CENTER);
		actionPanel.add(label2);
		actionPanel.add(button1);
		
		add(actionPanel, BorderLayout.SOUTH);
		add(patientPanel, BorderLayout.EAST);
		
	}
	
	
	//Event listeners.
	public void actionPerformed(ActionEvent e){
		setMessage();
	}
	
	public void itemStateChanged(ItemEvent e){
		//I don't understand why you want this line here...
		//If you want to give instructions to the user, just set the text field at start up, not by clicking a button.
		setMessage();
	}
	
	
	//Sets the text at the bottom of the frame, and outputs similar text to output box.
	public void setMessage(){
		String message = "";
		total = 0;
		boolean checkbox = false;
		boolean radiobutton = false;
		
		for(int k = 0; k < services.size() ; k++){
			if(servicesPanel.isSelected(k) ){
				checkbox = true;
				total += office.getCost(k);
			}
		}
		
		
		message = "Total cost: $" + total;
		for(int i = 0; i < jrbuttons.length ; i++){
			if(jrbuttons[i].isSelected()){
				radiobutton = true;
				message += " and return in " + durations.get(i);
			}
		}
		
		
		if(patientPanel.getText() != null && checkbox && radiobutton){
			label2.setText(message);
			outFrame.setText(patientPanel.getText() + ": " + message + "\n");
			patientPanel.clearText();
			servicesPanel.clear();
			for(int i = 0; i < jrbuttons.length; i++){
				jrbuttons[i].setSelected(false);
			}
		}
		else
			label2.setText("Please provide name, check activities, and select duration");
		
	}
}


//Panel For patient to type his/her name.
class PatientPanel extends JPanel{
	
	private JLabel fName;
	private JLabel lName;
	private JTextField fNameField;
	private JTextField lNameField;
	private JPanel personPanel;
	
	public PatientPanel(){
		personPanel = new JPanel();
		personPanel.setLayout(new GridLayout(2,1));
		
		fName = new JLabel("First Name: ", SwingConstants.RIGHT);
		fNameField = new JTextField(10);
		lName = new JLabel("Last Name: ", SwingConstants.RIGHT);
		lNameField = new JTextField(10);
		
		personPanel.add(fName);
		personPanel.add(fNameField);
		personPanel.add(lName);
		personPanel.add(lNameField);
		
		add(personPanel);
		
	}
	
	
	
	public String getText(){// returns the text from the textfields when populated
		
		if(fNameField.getText().length() > 0 && lNameField.getText().length() > 0)
			return fNameField.getText() + " " + lNameField.getText();
			
		return null;
	}
	
	//Returns patient txt to empty string. (not null)
	public void clearText(){
		fNameField.setText("");
		lNameField.setText("");
	}
	
}



//Contains checkboxes for all the services.
class ServicePanel extends JPanel{
	private JPanel actionPanel;
	private JCheckBox[] checkboxes;
	
	
	
	public ServicePanel(ArrayList<String> services){
		
		checkboxes = new JCheckBox[services.size()];
		actionPanel = new JPanel();
		
		actionPanel.setLayout(new GridLayout(5,1));;
		
		for(int i = 0; i < checkboxes.length; i++){
			checkboxes[i] = new JCheckBox();
			checkboxes[i].setText( services.get(i) ); //Line added to put labels on check boxes.
			actionPanel.add(checkboxes[i]);
		}
		
		add(actionPanel);
	}
	
	
	
	public boolean isSelected(int index){
		return checkboxes[index].isSelected();
	}
	
	
	
	public void clear(){
		for(int i = 0; i < checkboxes.length; i++){
			checkboxes[i].setSelected(false);
		}
	}
	
}


//Output window for dental checkouts.
class OutputWindow extends JFrame{
	private JTextArea output;
	private JScrollPane scrollPane;
	
	public OutputWindow(){
		output = new JTextArea();
		output.setBackground(Color.LIGHT_GRAY);
		Font font = new Font("Arial", Font.BOLD, 12);
		output.setFont(font);
		output.setEditable(false);
		scrollPane = new JScrollPane(output); //scrollPane with output area
		add(scrollPane);
		
	}
	
	public void setText(String x){
		output.setText( output.getText() + x );
	}
}