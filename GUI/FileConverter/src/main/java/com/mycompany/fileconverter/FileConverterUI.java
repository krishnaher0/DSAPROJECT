/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.fileconverter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;


/**
 *
 * @author krishnabhandari
 */
public class FileConverterUI extends javax.swing.JPanel {
//    private File selectedFile;
//
//      public FileConverterUI() {
//             System.out.println("File received");
//        initComponents();
//    }

    private List<File> selectedFiles = new ArrayList<>();
    private  final ExecutorService executorService;

    public FileConverterUI() {
        System.out.println("File received");
        initComponents();
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        selectFilesButton = new javax.swing.JButton();
        convertButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        logTextArea = new javax.swing.JTextArea();
        fileChooser = new javax.swing.JFileChooser();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setText("Pdf to docs converter");

        selectFilesButton.setText("Select");
        selectFilesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectFilesButtonActionPerformed(evt);
            }
        });

        convertButton.setText("Convert");
        convertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                convertButtonActionPerformed(evt);
            }
        });

        logTextArea.setColumns(20);
        logTextArea.setRows(5);
        jScrollPane1.setViewportView(logTextArea);

        jLabel2.setText("progress:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(273, 273, 273)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(selectFilesButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 335, Short.MAX_VALUE)
                .addComponent(convertButton)
                .addGap(168, 168, 168))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 207, Short.MAX_VALUE)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 712, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(fileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(339, 339, 339))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectFilesButton)
                    .addComponent(convertButton))
                .addGap(56, 56, 56)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addContainerGap(42, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void selectFilesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectFilesButtonActionPerformed
  fileChooser.setMultiSelectionEnabled(true);
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
//            selectedFiles.clear(); // Clear any previously selected files
            selectedFiles.addAll(Arrays.asList(fileChooser.getSelectedFiles()));
            logTextArea.append("Selected files:\n");
            for (File file : selectedFiles) {
                logTextArea.append(file.getAbsolutePath() + "\n");
            }
        }
    }//GEN-LAST:event_selectFilesButtonActionPerformed

    private void convertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_convertButtonActionPerformed
       if (!selectedFiles.isEmpty()) {
            logTextArea.append("Starting conversion...\n");
            jProgressBar1.setValue(0);
            jProgressBar1.setMaximum(100);

            List<CompletableFuture<Void>> futures = new ArrayList<>();
            int totalFiles = selectedFiles.size();
            for (File file : selectedFiles) {
                CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                    convertFile(file);
                    SwingUtilities.invokeLater(() -> {
                        int completedFiles = (jProgressBar1.getValue() * totalFiles) / 100 + 1;
                        int progress = (completedFiles * 100) / totalFiles;
                        jProgressBar1.setValue(progress);
                        jLabel2.setText("Converting: " + progress + "%");
                        if (progress == 100) {
                            jLabel2.setText("Converted completely");
                        }
                    });
                }, executorService);
                futures.add(future);
            }

            // Once all files are processed, you can join the CompletableFutures
            CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
            allOf.thenRun(() -> logTextArea.append("All files converted.\n"));
        } else {
            logTextArea.append("No files selected.\n");
        }
       
    }//GEN-LAST:event_convertButtonActionPerformed
    
    private void convertFile(File file) {
   SwingUtilities.invokeLater(() -> logTextArea.append("Processing file: " + file.getAbsolutePath() + "\n"));
     try {
            // Load the PDF document
            PDDocument document = PDDocument.load(file);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);
            document.close();

            // Create a new DOCX document
            XWPFDocument docx = new XWPFDocument();
            XWPFParagraph paragraph = docx.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(text);

            // Save the DOCX document
            String outputFilePath = file.getAbsolutePath().replace(".pdf", ".docx");
            try (FileOutputStream out = new FileOutputStream(new File(outputFilePath))) {
                docx.write(out);
            }
            docx.close();

            SwingUtilities.invokeLater(() -> logTextArea.append("Conversion completed: " + outputFilePath + "\n"));
        } catch (IOException e) {
            SwingUtilities.invokeLater(() -> logTextArea.append("Error during conversion of file " + file.getAbsolutePath() + ": " + e.getMessage() + "\n"));
        }
    }
      public void shutdownExecutorService() {
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdown();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton convertButton;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea logTextArea;
    private javax.swing.JButton selectFilesButton;
    // End of variables declaration//GEN-END:variables
}
