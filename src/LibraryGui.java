/**
 * file name GUIWindow
 * date      10 May 2015
 * author    Stephen Drollinger
 * purpose   Window for user to interact with Also provides thread spawning
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.LinkedBlockingQueue;

public class LibraryGui extends javax.swing.JFrame {
    private static LinkedBlockingQueue<String> lbq = new LinkedBlockingQueue<String>(1);

    public LibraryGui() {
        initComponents();
    }

    public LibraryGui(Library lib) {
        initComponents();
        String mainLibrary = lib.toString();
        jScrollPane1.setViewportView(new JLabel(convertToMultiline(mainLibrary)));

    }

    // Method converts a string to a multi line string to display correctly in the JTextArea
    public static String convertToMultiline(String orig) {
        return "<html>" + orig.replaceAll("\n", "<br>");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("LIMS"));
        jPanel1.setToolTipText("");

        jLabel1.setText("Sort Library by:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Select", "Author - Index", "Author - Name", "Book - Title", "Book - Price", "Book - Index", "Journal - Date", "Journal - Issue"}));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Search :");

        jLabel3.setText("Library contains:");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel3)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel1)
                                                                        .addComponent(jLabel2))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGap(0, 96, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

        // Calling a producer to start a user action (sorting)
        new Thread(new LibraryGui().new Producer(jComboBox1.getSelectedItem().toString())).start();
        new Thread(new LibraryGui().new Consumer()).start();
        //System.out.println("Sort by ComboBox Choice: " + jComboBox1.getSelectedItem().toString());
        jScrollPane1.setViewportView(new JLabel(convertToMultiline(Library.sort(jComboBox1.getSelectedItem().toString()))));

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        new Thread(new LibraryGui().new Producer(jTextField1.getText())).start();
        new Thread(new LibraryGui().new Consumer()).start();
        jScrollPane1.setViewportView(new JLabel(convertToMultiline(Library.search(jTextField1.getText()))));
        //System.out.println("User Searched for: " + jTextField1.getText());
    }//GEN-LAST:event_jTextField1ActionPerformed


    public static void main(String args[]) {
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LibraryGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LibraryGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LibraryGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LibraryGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LibraryGui().setVisible(true);
            }
        });
    }

    public static class SwingControl {

        private JFrame mainFrame;
        private JLabel headerLabel;
        private JLabel statusLabel;
        private JPanel controlPanel;

        public SwingControl() {
            prepareGUI();
        }

        private void prepareGUI() {
            mainFrame = new JFrame("Loading");
            mainFrame.setSize(400, 400);
            mainFrame.setLayout(new GridLayout(3, 1));
            mainFrame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent windowEvent) {
                    System.exit(0);
                }
            });
            headerLabel = new JLabel("", JLabel.CENTER);
            statusLabel = new JLabel("", JLabel.CENTER);

            statusLabel.setSize(350, 100);

            controlPanel = new JPanel();
            controlPanel.setLayout(new FlowLayout());

            mainFrame.add(headerLabel);
            mainFrame.add(controlPanel);
            mainFrame.add(statusLabel);
            mainFrame.setVisible(true);
        }

        private JProgressBar progressBar;
        private Task task;
        private JButton startButton, stopButton, cancelButton;
        private JTextArea outputTextArea;

        private void showProgressBar() {
            final int progressMaxTimer = 25;
            headerLabel.setText("User simulated action JProgressBar");

            progressBar = new JProgressBar(0, progressMaxTimer);
            progressBar.setValue(0);
            progressBar.setStringPainted(true);
            startButton = new JButton("Start");
            stopButton = new JButton("Stop");
            cancelButton = new JButton("Cancel");


            outputTextArea = new JTextArea("", 0, progressMaxTimer);

            JScrollPane scrollPane = new JScrollPane(outputTextArea);

            controlPanel.add(startButton, BorderLayout.EAST);
            controlPanel.add(stopButton, BorderLayout.SOUTH);
            controlPanel.add(cancelButton, BorderLayout.WEST);
            controlPanel.add(progressBar);
            controlPanel.add(scrollPane);
            startButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    task = new Task(progressMaxTimer);
                    task.start();
                }
            });
            stopButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    task.interrupt();
                    mainFrame.setVisible(false);
                }
            });
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.setVisible(false);
                }
            });
            mainFrame.setVisible(true);
        }

        private class Task extends Thread {
            private int progressMaxTimer;

            public Task(int progressMaxTimer) {
                this.progressMaxTimer = progressMaxTimer;
            }

            public void run() {
                for (int i = 0; i <= progressMaxTimer; i += 1) {
                    final int progress = i;
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            progressBar.setValue(progress);
                            outputTextArea.setText(outputTextArea.getText()
                                    + String.format("Completed %d%% of task.\n", progress));
                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
                task.interrupt();
                mainFrame.setVisible(false);
            }
        }
    }

    //Producer creates an object with the name of the sort/search to be consumed sort of an elaborate wait timer
    // as per project requirement
    class Producer implements Runnable {

        private String s;

        public Producer(String s) {
            this.s = s;
        }

        @Override
        public void run() {

            try {
                int i = 1;
                while (i <= 1) {
                    lbq.put(s);
                    System.out.println(s + " added.");
                    i++;
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    // consumer takes producers search/sort string and does the appropriate sorting/searching
    class Consumer implements Runnable {
        @Override
        public void run() {
            String output = "";
            try {
                int i = 1;
                while (i <= 1) {
                    //removes the item
                    String s = lbq.take();
                    System.out.println(s + " removed.");
                    output = s;
                    i++;
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            SwingControl SC = new SwingControl();
            SC.showProgressBar();
            //jScrollPane1.setViewportView(new JLabel(convertToMultiline(Library.sort(jComboBox1.getSelectedItem().toString()))));
            jScrollPane1.setViewportView(new JLabel(convertToMultiline(Library.sort(output))));

        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
