package minimizer;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import minimizer.Automaton.Status;

public class GUI extends javax.swing.JFrame {

    private final int cellSizeX = 64, cellSizeY = 48;
    private final List<Component> temporaryComponents = new ArrayList<>();
    private String fileDirectory;
    private Automaton auto;

    public GUI() {
        initComponents();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Data Files", "dat"));
        textFileStatus.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        textTitle = new javax.swing.JLabel();
        mainTabbedPanel = new javax.swing.JTabbedPane();
        inputPanel = new javax.swing.JPanel();
        fileChooser = new javax.swing.JFileChooser();
        textFileStatus = new javax.swing.JLabel();
        displayPanel = new javax.swing.JPanel();
        buttonMinimize = new javax.swing.JButton();
        buttonSelectFile = new javax.swing.JButton();
        buttonClose = new javax.swing.JButton();
        textSelectedFile = new javax.swing.JLabel();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        textTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textTitle.setText("Minimizador de Autômatos");

        mainTabbedPanel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        fileChooser.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        fileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileChooserActionPerformed(evt);
            }
        });

        textFileStatus.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textFileStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout inputPanelLayout = new javax.swing.GroupLayout(inputPanel);
        inputPanel.setLayout(inputPanelLayout);
        inputPanelLayout.setHorizontalGroup(
            inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fileChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(textFileStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        inputPanelLayout.setVerticalGroup(
            inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textFileStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
        );

        mainTabbedPanel.addTab("Arquivo", inputPanel);

        buttonMinimize.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        buttonMinimize.setText("Minimizar");
        buttonMinimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMinimizeActionPerformed(evt);
            }
        });

        buttonSelectFile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        buttonSelectFile.setText("Novo");
        buttonSelectFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSelectFileActionPerformed(evt);
            }
        });

        buttonClose.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        buttonClose.setText("Encerrar");
        buttonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseActionPerformed(evt);
            }
        });

        textSelectedFile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textSelectedFile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout displayPanelLayout = new javax.swing.GroupLayout(displayPanel);
        displayPanel.setLayout(displayPanelLayout);
        displayPanelLayout.setHorizontalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayPanelLayout.createSequentialGroup()
                .addComponent(textSelectedFile, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonMinimize, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                    .addComponent(buttonSelectFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonClose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        displayPanelLayout.setVerticalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayPanelLayout.createSequentialGroup()
                .addContainerGap(318, Short.MAX_VALUE)
                .addComponent(buttonMinimize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSelectFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textSelectedFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonClose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        mainTabbedPanel.addTab("Minimização", displayPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(mainTabbedPanel)
                    .addComponent(textTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainTabbedPanel)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Seleção do Arquivo do Autômato
    private void fileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileChooserActionPerformed
        if (evt.getActionCommand().equals("ApproveSelection")) {

            removeTemporaryComponents();

            File selectedFile = fileChooser.getSelectedFile();
            fileDirectory = selectedFile.getAbsolutePath();
            String fileName = selectedFile.getName();
            String FileExtension = fileName.split("\\.")[1];

            if (FileExtension.equals("dat")) {
                textFileStatus.setText("");
                mainTabbedPanel.setSelectedComponent(displayPanel);
                textSelectedFile.setText("Visualizando: '" + fileName + "'");
                auto = new Automaton();
                readFile();
            } else {
                textFileStatus.setForeground(Color.red);
                textFileStatus.setText("Arquivo inválido");
            }
        } else if (evt.getActionCommand().equals("CancelSelection")) {
            System.exit(0);
        }
    }//GEN-LAST:event_fileChooserActionPerformed

    // Botão de Minimização do Autômato.
    private void buttonMinimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMinimizeActionPerformed

        auto.minimize();

        int xPos = 20, yPos = 20;
        Status[][] RTable = auto.getRelationsTable();
        String[] states = auto.getStates();

        for (int i = 0; i < (auto.getNumberOfStates() - 1); i++) {
            createTableCell(displayPanel, states[i + 1], true, Color.GRAY, xPos, yPos);

            xPos += cellSizeX;
            for (int j = 0; j < (i + 1); j++) {
                createTableCell(displayPanel, String.valueOf(RTable[i][j].getChar()), false, Color.WHITE, xPos, yPos);
                xPos += cellSizeX;
            }
            xPos = 20;
            yPos += cellSizeY;
        }
        xPos += cellSizeX;
        for (int i = 0; i < (auto.getNumberOfStates() - 1); i++) {
            createTableCell(displayPanel, states[i], true, Color.GRAY, xPos, yPos);
            xPos += cellSizeX;
        }
        displayPanel.revalidate();
        displayPanel.repaint();
    }//GEN-LAST:event_buttonMinimizeActionPerformed

    // Botão Novo Arquivo.
    private void buttonSelectFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSelectFileActionPerformed
        removeTemporaryComponents();
        mainTabbedPanel.setSelectedComponent(inputPanel);
    }//GEN-LAST:event_buttonSelectFileActionPerformed

    // Botão Encerrar.
    private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
        System.exit(0);
    }//GEN-LAST:event_buttonCloseActionPerformed

    // Leitura do Arquivo selecionado.
    private boolean readFile() {
        File selectedFile = new File(fileDirectory);
        if (selectedFile.exists()) {
            try {
                auto.getAutomato(fileDirectory);
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    // Criação de um Botão Célula em um Painel da GUI.
    private void createTableCell(JPanel panel, String text, boolean bold, Color bgColor, int xPos, int yPos) {
        JButton cell = new JButton(text);
        Font font = new Font("Segoe UI Symbol", Font.PLAIN, 16);
        if (bold) {
            font = font.deriveFont(Font.BOLD);
        }

        temporaryComponents.addAll(Arrays.asList(cell));
        cell.setBounds(xPos, yPos, 64, 48);
        cell.setBackground(bgColor);
        cell.setFont(font);
        panel.add(cell);
    }

    // Remove Componentes criados na lista dos temporários.
    private void removeTemporaryComponents() {
        for (Component c : displayPanel.getComponents()) {
            if (temporaryComponents.contains(c)) {
                displayPanel.remove(c);
            }
        }
    }

    public static void main(String args[]) throws IOException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        java.awt.EventQueue.invokeLater(() -> {
            new GUI().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonMinimize;
    private javax.swing.JButton buttonSelectFile;
    private javax.swing.JPanel displayPanel;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JPanel inputPanel;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JTabbedPane mainTabbedPanel;
    private javax.swing.JLabel textFileStatus;
    private javax.swing.JLabel textSelectedFile;
    private javax.swing.JLabel textTitle;
    // End of variables declaration//GEN-END:variables
}
