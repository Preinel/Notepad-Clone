
//project is similar to notepad having 3 menu: File(open,  close), Edit(Copy,cut.paste), View(change font size,type,color)
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class SimpleNotepad extends JFrame implements ActionListener {
    JTextArea textArea;
    JMenu file, Edit, View;
    JMenuItem openItem, closeItem, copyItem, pasteItem, cutItem, fontSizeItem, fontTypeItem, fontColorItem;

    public SimpleNotepad() {
        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        JMenuBar mb = new JMenuBar();
        file = new JMenu("File");
        Edit = new JMenu("Edit");
        View = new JMenu("View");

        // File Menu

        openItem = new JMenuItem("Open");
        closeItem = new JMenuItem("Close");
        openItem.addActionListener(this);
        closeItem.addActionListener(this);
        file.add(openItem);
        file.add(closeItem);
        mb.add(file);

        // Edit Menu

        copyItem = new JMenuItem("Copy");
        pasteItem = new JMenuItem("Paste");
        cutItem = new JMenuItem("Cut");
        copyItem.addActionListener(this);
        pasteItem.addActionListener(this);
        cutItem.addActionListener(this);
        Edit.add(copyItem);
        Edit.add(pasteItem);
        Edit.add(cutItem);
        mb.add(Edit);

        // View Menu

        fontSizeItem = new JMenuItem("Change Font Size");
        fontTypeItem = new JMenuItem("Change Font Type");
        fontColorItem = new JMenuItem("Change Font Color");
        fontSizeItem.addActionListener(this);
        fontTypeItem.addActionListener(this);
        fontColorItem.addActionListener(this);
        View.add(fontSizeItem);
        View.add(fontTypeItem);
        View.add(fontColorItem);
        mb.add(View);

        setJMenuBar(mb);
        setSize(800, 600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == openItem) {
                JFileChooser jf = new JFileChooser();
            jf.showOpenDialog(this); // this part opens the dialog box to open it
            File f = jf.getSelectedFile(); // stores the file in f
            try {
                Scanner sc = new Scanner(f); // scanner to read the each line
                textArea.setText(""); 

                while (sc.hasNextLine()) {
                    textArea.append(sc.nextLine() + "\n");
                }
                sc.close();
            } catch (Exception ex) {
            }
        }

        if (e.getSource() == closeItem) {
            System.exit(0);
        }
        
        
        else if (e.getSource() == copyItem) {
            textArea.copy();
        } else if (e.getSource() == pasteItem) {
            textArea.paste();
        } else if (e.getSource() == cutItem) {
            textArea.cut();
        }

        else if (e.getSource() == fontSizeItem) {
            String input = JOptionPane.showInputDialog(this, "Enter font size:"); // t his opens input field for text
                                                                                  // size

            int size = Integer.parseInt(input);
            Font f = textArea.getFont(); // gets current font type n style
            textArea.setFont(new Font(f.getFontName(), f.getStyle(), size));

        } else if (e.getSource() == fontTypeItem) {
            String[] fonts = { "Arial", "Serif", "SansSerif", "Monospaced", "Dialog" };
            String font = (String) JOptionPane.showInputDialog(this, "Choose font:", "Font", JOptionPane.PLAIN_MESSAGE,
                    null, fonts, textArea.getFont().getFontName());
            Font f = textArea.getFont();
            textArea.setFont(new Font(font, f.getStyle(), f.getSize()));

        } else if (e.getSource() == fontColorItem) {
            Color color = JColorChooser.showDialog(this, "Choose Font Color", textArea.getForeground());
            textArea.setForeground(color);

        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception exception) {
        }
        new SimpleNotepad();
    }
}