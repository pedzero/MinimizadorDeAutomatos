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

/**
 * GUI para interação com as classes Minimizer e Connector.
 *
 * @author Pedro
 */
public class GUI extends javax.swing.JFrame {

    private final int cellSizeX = 72, cellSizeY = 54;
    private final List<Component> temporaryComponents = new ArrayList<>();

    private String fileDirectory;
    private Minimizer minimizer;
    private Connector connector;
    private String HTMLAutomaton;

    public GUI() {
        initComponents();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Data Files", "dat"));
        textPanel.setContentType("text/html");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        textTitle = new javax.swing.JLabel();
        mainTabbedPanel = new javax.swing.JTabbedPane();
        panelFile = new javax.swing.JPanel();
        fileChooser = new javax.swing.JFileChooser();
        panelDetails = new javax.swing.JScrollPane();
        textPanel = new javax.swing.JTextPane();
        panelConnection = new javax.swing.JPanel();
        panelMinimization = new javax.swing.JPanel();
        buttonClose = new javax.swing.JButton();
        buttonNewFile = new javax.swing.JButton();
        buttonMinimize = new javax.swing.JButton();
        checkBoxStepByStep = new javax.swing.JCheckBox();
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
            .addComponent(fileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 786, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panelFileLayout.setVerticalGroup(
            panelFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFileLayout.createSequentialGroup()
                .addComponent(fileChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainTabbedPanel.addTab("Arquivo", panelFile);

        panelDetails.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        textPanel.setEditable(false);
        textPanel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        panelDetails.setViewportView(textPanel);

        mainTabbedPanel.addTab("Detalhes", panelDetails);

        javax.swing.GroupLayout panelConnectionLayout = new javax.swing.GroupLayout(panelConnection);
        panelConnection.setLayout(panelConnectionLayout);
        panelConnectionLayout.setHorizontalGroup(
            panelConnectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 786, Short.MAX_VALUE)
        );
        panelConnectionLayout.setVerticalGroup(
            panelConnectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 486, Short.MAX_VALUE)
        );

        mainTabbedPanel.addTab("Conectividade", panelConnection);

        javax.swing.GroupLayout panelMinimizationLayout = new javax.swing.GroupLayout(panelMinimization);
        panelMinimization.setLayout(panelMinimizationLayout);
        panelMinimizationLayout.setHorizontalGroup(
            panelMinimizationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 786, Short.MAX_VALUE)
        );
        panelMinimizationLayout.setVerticalGroup(
            panelMinimizationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 486, Short.MAX_VALUE)
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

        textSelectedFile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textSelectedFile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        buttonConnection.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        buttonConnection.setText("Verificar");
        buttonConnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConnectionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainTabbedPanel)
                    .addComponent(textTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textSelectedFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonConnection, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkBoxStepByStep)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonNewFile, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainTabbedPanel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textSelectedFile, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonClose)
                        .addComponent(buttonNewFile)
                        .addComponent(buttonMinimize)
                        .addComponent(buttonConnection)
                        .addComponent(checkBoxStepByStep)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Botão Minimizar acionado. implementa algumas verificações do Autômato.
     *
     * @param evt
     */
    private void buttonMinimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMinimizeActionPerformed
        if ((minimizer != null) && (minimizer.isDeterministic()) && (connector != null) && (connector.isConnected())) {
            if (minimizer.getStage() == Stage.minimized) {
                buttonNewFile.grabFocus();
            }

            // Caso seja operação passo a passo.
            if (checkBoxStepByStep.isSelected()) {
                buttonMinimize.setText("Próximo");
                if (minimizer.minimizeStep()) {
                    insertRelationsTableCells();
                } else {
                    textSelectedFile.setText("Autômato não suportado");
                    buttonNewFile.grabFocus();
                }
                if (minimizer.getStage() == Stage.minimized) {
                    checkBoxStepByStep.setSelected(false);
                    buttonMinimize.setText("Minimizar");
                }
            } else {
                if (minimizer.minimize()) {
                    insertRelationsTableCells();
                } else {
                    textSelectedFile.setText("Autômato não suportado");
                    buttonNewFile.grabFocus();
                }
            }
        } else if (!connector.isConnected()) {
            insertConnectionCells();
            mainTabbedPanel.setSelectedComponent(panelConnection);
            textSelectedFile.setText("Automato Não-Conexo!");
            buttonNewFile.grabFocus();
        } else if (!minimizer.isDeterministic()) {
            textSelectedFile.setText("Automato Não-Determinístico!");
            buttonNewFile.grabFocus();
        } else {
            textSelectedFile.setText("Nenhum arquivo selecionado!");
            buttonNewFile.grabFocus();
        }
    }//GEN-LAST:event_buttonMinimizeActionPerformed

    /**
     * Botão Novo acionado.
     *
     * @param evt
     */
    private void buttonNewFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNewFileActionPerformed
        removeTemporaryComponents();
        textPanel.setText("<table style=\"border-collapse: collapse; border: none;\">");
        textSelectedFile.setText("");
        mainTabbedPanel.setSelectedComponent(panelFile);

        minimizer = null;
        connector = null;
    }//GEN-LAST:event_buttonNewFileActionPerformed

    /**
     * Botão Encerrar acionado.
     *
     * @param evt
     */
    private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
        System.exit(0);
    }//GEN-LAST:event_buttonCloseActionPerformed

    /**
     * Ação na Caixa de Seleção.
     *
     * @param evt
     */
    private void checkBoxStepByStepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxStepByStepActionPerformed
        // Evita que a CheckBox seja desmarcada durante o processo de minimização.
        if (minimizer != null) {
            if (!checkBoxStepByStep.isSelected() && !(minimizer.getStage() == Stage.minimized || minimizer.getStage() == Stage.file)) {
                checkBoxStepByStep.setSelected(true);
            }
        }

    }//GEN-LAST:event_checkBoxStepByStepActionPerformed

    /**
     * Botão Verificar acionado (conexões).
     *
     * @param evt
     */
    private void buttonConnectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConnectionActionPerformed
        if (connector != null && connector.isDeterministic()) {
            insertConnectionCells();
        } else if (!connector.isDeterministic()) {
            textSelectedFile.setText("Automato Não-Determinístico!");
            buttonNewFile.grabFocus();
        } else {
            textSelectedFile.setText("Nenhum arquivo selecionado!");
            buttonNewFile.grabFocus();
        }
    }//GEN-LAST:event_buttonConnectionActionPerformed

    /**
     * Listener para o Painel com Guias. Diferentes configurações são
     * selecionadas de acordo com a guia selecionada.
     *
     * @param evt
     */
    private void mainTabbedPanelStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_mainTabbedPanelStateChanged
        var tabComponent = mainTabbedPanel.getSelectedComponent();

        if (tabComponent == panelFile) {
            updateComponentsVisibility(0b000000);
            changeToDefaultDirectory();
        }
        if (tabComponent == panelDetails) {
            updateComponentsVisibility(0b001101);
            panelDetails.revalidate();
            panelDetails.repaint();
        }
        if (tabComponent == panelConnection) {
            updateComponentsVisibility(0b011101);
        }
        if (tabComponent == panelMinimization) {
            updateComponentsVisibility(0b101111);
        }
    }//GEN-LAST:event_mainTabbedPanelStateChanged

    /**
     * Ação no Seletor de Arquivos.
     *
     * @param evt
     */
    private void fileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileChooserActionPerformed
        if (evt.getActionCommand().equals("ApproveSelection")) {

            removeTemporaryComponents();
            textPanel.setText("<table style=\"border-collapse: collapse; border: none;\">");

            File selectedFile = fileChooser.getSelectedFile();
            fileDirectory = selectedFile.getAbsolutePath();
            String fileName = selectedFile.getName();
            String FileExtension = fileName.split("\\.")[1];

            if (FileExtension.equals("dat")) {
                mainTabbedPanel.setSelectedComponent(panelMinimization);
                textSelectedFile.setText("Visualizando '" + fileName + "'");
                readFile();
            } else {
                JOptionPane.showMessageDialog(null, "Arquivo Inválido", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else if (evt.getActionCommand().equals("CancelSelection")) {
            System.exit(0);
        }
    }//GEN-LAST:event_fileChooserActionPerformed

    /**
     * Exibição do código HTML no Painel de Texto de exibição.
     */
    private void insertAutomatonDetails() {
        HTMLAutomaton = connector.automatonToHTML();
        textPanel.setText(HTMLAutomaton);
    }

    /**
     * Criação de células (botões) que representam estados do Autômato. Facilita
     * a visualização das relações de equivalência.
     */
    private void insertRelationsTableCells() {

        removeTemporaryComponents();

        int xPos = 8, yPos = 6;
        Status[][] RTable = minimizer.getRelationsTable();
        String[] states = minimizer.getStates();

        // Posicionamento dos botões.
        for (int i = 0; i < (minimizer.getNumberOfStates() - 1); i++) {
            createTableCell(panelMinimization, states[i + 1], true, Color.GRAY, xPos, yPos);

            xPos += cellSizeX;
            for (int j = 0; j < (i + 1); j++) {
                if ((RTable[i][j].equals(Status.equivalent)) && (minimizer.getStage() == Stage.minimized)) {
                    createTableCell(panelMinimization, String.valueOf(RTable[i][j].getChar()), false, Color.getHSBColor(0.43f, 0.55f, 0.55f), xPos, yPos);
                } else {
                    createTableCell(panelMinimization, String.valueOf(RTable[i][j].getChar()), false, Color.WHITE, xPos, yPos);
                }
                xPos += cellSizeX;
            }
            xPos = 8;
            yPos += cellSizeY;
        }
        xPos += cellSizeX;
        for (int i = 0; i < (minimizer.getNumberOfStates() - 1); i++) {
            createTableCell(panelMinimization, states[i], true, Color.GRAY, xPos, yPos);
            xPos += cellSizeX;
        }
        panelMinimization.revalidate();
        panelMinimization.repaint();
    }

    /**
     * Criação de células (botões) que representam estados do Autômato. Exibe os
     * estados que são alcançáveis a partir do estado inicial.
     */
    private void insertConnectionCells() {
        int xPos = 8, yPos = 6;
        String[] states = connector.getStates();
        String[][] transitions = connector.getTransitions();
        boolean[] connected = connector.getConnections();

        // Posicionamento dos botões.
        for (int i = 0; i < (connector.getNumberOfStates()); i++) {
            if (connected[i] || connector.isInitial(i)) {
                createTableCell(panelConnection, states[i], true, Color.getHSBColor(0.43f, 0.55f, 0.55f), xPos, yPos);
            } else {
                createTableCell(panelConnection, states[i], true, Color.getHSBColor(0f, 0.7f, 0.7f), xPos, yPos);
            }

            yPos += cellSizeY;
            for (int j = 0; j < (connector.getNumberOfSymbols()); j++) {
                if (transitions[i][j] != null) {
                    createTableCell(panelConnection, transitions[i][j], false, Color.WHITE, xPos, yPos);
                    yPos += cellSizeY;
                }
            }
            yPos = 6;
            xPos += cellSizeX;
        }
        panelConnection.revalidate();
        panelConnection.repaint();
    }

    /**
     * Criação de uma célula individual, com parâmetros personalizados.
     *
     * @param panel Painel onde os botões serão criados.
     * @param text String para o texto dos botões.
     * @param bold Define se o texto é em negrito.
     * @param bgColor Cor de fundo do botão.
     * @param xPos Posição no eixo horizontal.
     * @param yPos Posição no eixo Vertical.
     */
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

    /**
     * Alteração da visibilidade de componentes.
     *
     * @param visibility Sequência de bits que definem qual Componente será
     * visível/escondido.
     */
    private void updateComponentsVisibility(int visibility) {
        textSelectedFile.setVisible((visibility & 1) != 0);
        checkBoxStepByStep.setVisible((visibility & 2) != 0);
        buttonClose.setVisible((visibility & 4) != 0);
        buttonNewFile.setVisible((visibility & 8) != 0);
        buttonConnection.setVisible((visibility & 16) != 0);
        buttonMinimize.setVisible((visibility & 32) != 0);
    }

    /**
     * Leitura de um arquivo e instanciação dos objetos usados.
     *
     * @return true para leitura bem sucedida, false caso contrário.
     */
    private boolean readFile() {
        File selectedFile = new File(fileDirectory);
        if (selectedFile.exists()) {
            try {
                minimizer = new Minimizer();
                connector = new Connector();

                minimizer.readAutomaton(fileDirectory);
                connector.readAutomaton(fileDirectory);
                if (!minimizer.isDeterministic()) {
                    textSelectedFile.setText("Autômato Não Determinístico");
                    buttonNewFile.grabFocus();
                    return false;
                }
                connector.checkConnectivity();

                insertAutomatonDetails();
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    /**
     * Remove os componentes temporários de uma lista. Esses componentes são, em
     * geral, células (botões) criados dinamincamente nos painéis.
     */
    private void removeTemporaryComponents() {
        for (Component c : panelMinimization.getComponents()) {
            if (temporaryComponents.contains(c)) {
                panelMinimization.remove(c);
            }
        }
        for (Component c : panelConnection.getComponents()) {
            if (temporaryComponents.contains(c)) {
                panelConnection.remove(c);
            }
        }
        temporaryComponents.clear();
    }

    /**
     * Mudança do diretório padrão do Seletor de Arquivos, para que sempre sejam
     * exibidos os arquivos de exemplo.
     */
    private void changeToDefaultDirectory() {
        fileDirectory = System.getProperty("user.dir") + "\\examples";
        fileChooser.setCurrentDirectory(new File(fileDirectory));
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
    private javax.swing.JButton buttonMinimize;
    private javax.swing.JButton buttonNewFile;
    private javax.swing.JCheckBox checkBoxStepByStep;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JTabbedPane mainTabbedPanel;
    private javax.swing.JPanel panelConnection;
    private javax.swing.JScrollPane panelDetails;
    private javax.swing.JPanel panelFile;
    private javax.swing.JPanel panelMinimization;
    private javax.swing.JTextPane textPanel;
    private javax.swing.JLabel textSelectedFile;
    private javax.swing.JLabel textTitle;
    // End of variables declaration//GEN-END:variables
}
