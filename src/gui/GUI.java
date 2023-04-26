package gui;

import java.io.*;
import java.awt.*;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.charset.StandardCharsets;

import automaton.*;
import automaton.Automaton.*;
import automaton.Minimizer.*;
import javax.swing.JOptionPane;

public class GUI extends javax.swing.JFrame {

    private final int cellSizeX = 72, cellSizeY = 54;
    private final List<Component> temporaryComponents = new ArrayList<>();
    private final List<Component> fixedComponents = new ArrayList<>();
    private String fileDirectory;
    private Minimizer m;

    public GUI() {
        initComponents();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Data Files", "dat"));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        textTitle = new javax.swing.JLabel();
        mainTabbedPanel = new javax.swing.JTabbedPane();
        panelFile = new javax.swing.JPanel();
        fileChooser = new javax.swing.JFileChooser();
        panelDisplay = new javax.swing.JPanel();
        panelConnection = new javax.swing.JPanel();
        panelMinimization = new javax.swing.JPanel();
        buttonClose = new javax.swing.JButton();
        buttonNewFile = new javax.swing.JButton();
        buttonMinimize = new javax.swing.JButton();
        checkBoxStepByStep = new javax.swing.JCheckBox();
        buttonExampleFile = new javax.swing.JButton();
        textSelectedFile = new javax.swing.JLabel();
        buttonConnection = new javax.swing.JButton();

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

        mainTabbedPanel.setFocusCycleRoot(true);
        mainTabbedPanel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mainTabbedPanel.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                mainTabbedPanelStateChanged(evt);
            }
        });

        fileChooser.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        fileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileChooserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelFileLayout = new javax.swing.GroupLayout(panelFile);
        panelFile.setLayout(panelFileLayout);
        panelFileLayout.setHorizontalGroup(
            panelFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fileChooser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
        );
        panelFileLayout.setVerticalGroup(
            panelFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFileLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fileChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
        );

        mainTabbedPanel.addTab("Arquivo", panelFile);

        javax.swing.GroupLayout panelDisplayLayout = new javax.swing.GroupLayout(panelDisplay);
        panelDisplay.setLayout(panelDisplayLayout);
        panelDisplayLayout.setHorizontalGroup(
            panelDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 792, Short.MAX_VALUE)
        );
        panelDisplayLayout.setVerticalGroup(
            panelDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );

        mainTabbedPanel.addTab("Exibir", panelDisplay);

        javax.swing.GroupLayout panelConnectionLayout = new javax.swing.GroupLayout(panelConnection);
        panelConnection.setLayout(panelConnectionLayout);
        panelConnectionLayout.setHorizontalGroup(
            panelConnectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 792, Short.MAX_VALUE)
        );
        panelConnectionLayout.setVerticalGroup(
            panelConnectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );

        mainTabbedPanel.addTab("Conectividade", panelConnection);

        javax.swing.GroupLayout panelMinimizationLayout = new javax.swing.GroupLayout(panelMinimization);
        panelMinimization.setLayout(panelMinimizationLayout);
        panelMinimizationLayout.setHorizontalGroup(
            panelMinimizationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 792, Short.MAX_VALUE)
        );
        panelMinimizationLayout.setVerticalGroup(
            panelMinimizationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );

        mainTabbedPanel.addTab("Minimizar", panelMinimization);

        buttonClose.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        buttonClose.setText("Encerrar");
        buttonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseActionPerformed(evt);
            }
        });

        buttonNewFile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        buttonNewFile.setText("Novo");
        buttonNewFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewFileActionPerformed(evt);
            }
        });

        buttonMinimize.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        buttonMinimize.setText("Minimizar");
        buttonMinimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMinimizeActionPerformed(evt);
            }
        });

        checkBoxStepByStep.setText("Passo a Passo  ");
        checkBoxStepByStep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxStepByStepActionPerformed(evt);
            }
        });

        buttonExampleFile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        buttonExampleFile.setText("Exemplo");
        buttonExampleFile.setMaximumSize(new java.awt.Dimension(60, 20));
        buttonExampleFile.setMinimumSize(new java.awt.Dimension(60, 20));
        buttonExampleFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExampleFileActionPerformed(evt);
            }
        });

        textSelectedFile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textSelectedFile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        buttonConnection.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        buttonConnection.setText("Verificar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mainTabbedPanel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(checkBoxStepByStep))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textSelectedFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonConnection, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonExampleFile, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buttonNewFile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonClose, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonMinimize, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainTabbedPanel)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonMinimize)
                            .addComponent(checkBoxStepByStep))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonNewFile)
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonClose)
                            .addComponent(buttonExampleFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonConnection)))
                    .addComponent(textSelectedFile, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Botão de Minimização do Autômato.
    private void buttonMinimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMinimizeActionPerformed
        if (m != null) {
            if (m.getStage() == Stage.minimized) {
                buttonNewFile.grabFocus();
            }

            // Caso seja operação passo a passo.
            if (checkBoxStepByStep.isSelected()) {
                buttonMinimize.setText("Próximo");
                m.minimizeStep();
                insertRelationsTableCells();
                if (m.getStage() == Stage.minimized) {
                    checkBoxStepByStep.setSelected(false);
                    buttonMinimize.setText("Minimizar");
                }
            } else {
                m.minimize();
                insertRelationsTableCells();
            }
        } else {
            textSelectedFile.setText("Nenhum arquivo selecionado!");
            buttonNewFile.grabFocus();
        }
    }//GEN-LAST:event_buttonMinimizeActionPerformed

    // Botão Novo Arquivo.
    private void buttonNewFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNewFileActionPerformed
        removeTemporaryComponents();
        textSelectedFile.setText("");
        mainTabbedPanel.setSelectedComponent(panelFile);
    }//GEN-LAST:event_buttonNewFileActionPerformed

    // Botão Encerrar.
    private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
        System.exit(0);
    }//GEN-LAST:event_buttonCloseActionPerformed

    // Autômato de Exemplo.
    private void buttonExampleFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExampleFileActionPerformed

        mainTabbedPanel.setSelectedComponent(panelMinimization);
        textSelectedFile.setText("Visualizando Exemplo");
        m = new Minimizer();
        fileDirectory = System.getProperty("user.dir") + "\\automaton\\example.dat";
        readFile();
    }//GEN-LAST:event_buttonExampleFileActionPerformed

    // CheckBox Passo a Passo
    private void checkBoxStepByStepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxStepByStepActionPerformed
        // Evita que a CheckBox seja desmarcada durante o processo de minimização.
        if (m != null) {
            if (!checkBoxStepByStep.isSelected() && !(m.getStage() == Stage.minimized || m.getStage() == Stage.file)) {
                checkBoxStepByStep.setSelected(true);
            }
        }

    }//GEN-LAST:event_checkBoxStepByStepActionPerformed

    // Seleção do Arquivo do Autômato
    private void fileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileChooserActionPerformed
        if (evt.getActionCommand().equals("ApproveSelection")) {

            removeTemporaryComponents();

            File selectedFile = fileChooser.getSelectedFile();
            fileDirectory = selectedFile.getAbsolutePath();
            String fileName = selectedFile.getName();
            String FileExtension = fileName.split("\\.")[1];

            if (FileExtension.equals("dat")) {

                mainTabbedPanel.setSelectedComponent(panelMinimization);
                textSelectedFile.setText("Visualizando '" + fileName + "'");
                m = new Minimizer();
                readFile();
            } else {
                JOptionPane.showConfirmDialog(null, "Arquivo Inválido");
            }
        } else if (evt.getActionCommand().equals("CancelSelection")) {
            System.exit(0);
        }
    }//GEN-LAST:event_fileChooserActionPerformed

    private void mainTabbedPanelStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_mainTabbedPanelStateChanged
        var tabComponent = mainTabbedPanel.getSelectedComponent();

        if (tabComponent == panelFile) {
            updateComponentsVisibility(0b0001000);
        } else if (tabComponent == panelDisplay) {
            updateComponentsVisibility(0b0010101);
        } else if (tabComponent == panelConnection) {
            updateComponentsVisibility(0b0110101);
        } else if (tabComponent == panelMinimization) {
            updateComponentsVisibility(0b1010111);
        }
    }//GEN-LAST:event_mainTabbedPanelStateChanged

    private void updateComponentsVisibility(int visibility) {
        textSelectedFile.setVisible((visibility & 1) != 0);
        checkBoxStepByStep.setVisible((visibility & 2) != 0);
        buttonClose.setVisible((visibility & 4) != 0);
        buttonExampleFile.setVisible((visibility & 8) != 0);
        buttonNewFile.setVisible((visibility & 16) != 0);
        buttonConnection.setVisible((visibility & 32) != 0);
        buttonMinimize.setVisible((visibility & 64) != 0);
    }

    // Criação da Tabela de Relações.
    private void insertRelationsTableCells() {

        removeTemporaryComponents();

        int xPos = 8, yPos = 6;
        Status[][] RTable = m.getRelationsTable();
        String[] states = m.getStates();

        // Posicionamento dos botões.
        for (int i = 0; i < (m.getNumberOfStates() - 1); i++) {
            createTableCell(panelMinimization, states[i + 1], true, Color.GRAY, xPos, yPos);

            xPos += cellSizeX;
            for (int j = 0; j < (i + 1); j++) {
                createTableCell(panelMinimization, String.valueOf(RTable[i][j].getChar()), false, Color.WHITE, xPos, yPos);
                xPos += cellSizeX;
            }
            xPos = 8;
            yPos += cellSizeY;
        }
        xPos += cellSizeX;
        for (int i = 0; i < (m.getNumberOfStates() - 1); i++) {
            createTableCell(panelMinimization, states[i], true, Color.GRAY, xPos, yPos);
            xPos += cellSizeX;
        }
        panelMinimization.revalidate();
        panelMinimization.repaint();
    }

    // Leitura do Arquivo selecionado.
    private boolean readFile() {
        File selectedFile = new File(fileDirectory);
        if (selectedFile.exists()) {
            try {
                m.readAutomaton(fileDirectory);
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
        cell.setBounds(xPos, yPos, cellSizeX, cellSizeY);
        cell.setBackground(bgColor);
        cell.setFont(font);
        panel.add(cell);
    }

    // Remove Componentes criados na lista dos temporários.
    private void removeTemporaryComponents() {
        for (Component c : panelMinimization.getComponents()) {
            if (temporaryComponents.contains(c)) {
                panelMinimization.remove(c);
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
    private javax.swing.JButton buttonConnection;
    private javax.swing.JButton buttonExampleFile;
    private javax.swing.JButton buttonMinimize;
    private javax.swing.JButton buttonNewFile;
    private javax.swing.JCheckBox checkBoxStepByStep;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JTabbedPane mainTabbedPanel;
    private javax.swing.JPanel panelConnection;
    private javax.swing.JPanel panelDisplay;
    private javax.swing.JPanel panelFile;
    private javax.swing.JPanel panelMinimization;
    private javax.swing.JLabel textSelectedFile;
    private javax.swing.JLabel textTitle;
    // End of variables declaration//GEN-END:variables
}
