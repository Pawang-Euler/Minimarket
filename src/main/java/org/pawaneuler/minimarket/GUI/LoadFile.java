package org.pawaneuler.minimarket.GUI;

import java.awt.event.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LoadFile implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent action) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open File...");
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setAcceptAllFileFilterUsed(false);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("TCSV and triescript™", "tcsv", "triescript");
        fileChooser.addChoosableFileFilter(filter);

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            System.out.println("Opening : " + fileChooser.getSelectedFile().getAbsolutePath());

            MainProgress.run(fileChooser.getSelectedFile().getAbsolutePath());
        }

    }
    
}