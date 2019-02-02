package org.betacraft.launcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class Wersja extends JFrame {

	ImageIcon image = new ImageIcon();
	JList list;
	DefaultListModel listModel;
	JButton OK;

	public Wersja() {
		Logger.a("Otwarto okno wyboru wersji.");
		setSize(282, 386);
		setLayout(null);
		setTitle("Wybór wersji");
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

		int i = 0;
		int index = 0;
        listModel = new DefaultListModel();
        for (Release item : Release.versions) {
        	listModel.addElement(item);
        	if (Window.chosen_version.equalsIgnoreCase(item.getName())) {
        		index = i;
        	}
        	i++;
        }

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setBounds(30, 10, 180, 300);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(3);
        list.setSelectedIndex(index);
        
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setBounds(10, 10, 262, 300);
        listScroller.setWheelScrollingEnabled(true);
        //listScroller.setPreferredSize(new Dimension(250, 80));
        //add(list);
        getContentPane().add(listScroller);
        //add(list);

        OK = new JButton("OK");
        OK.setBounds(10, 320, 60, 20);
        add(OK);
        OK.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		Release ver = (Release) list.getSelectedValue();
				Window.chosen_version = ver.getName();
				Launcher.write(Launcher.getBetacraft() + "launcher.settings", new String[] {"version:" + ver.getName()}, false);
				setVisible(false);
			}
        });
	}
}