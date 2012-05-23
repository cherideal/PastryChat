
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import rice.p2p.commonapi.Node;

/**
 *      Main Frame
 * 
 * Supplies a not-that-ugly-console user interface for interactions with the
 * {@see ChatApp}.
 * 
 * @author Dejvino
 */
public class ChatFrame extends javax.swing.JFrame implements ChatAppListener
{
    private static final long serialVersionUID = 1L;
    
    private ChatApp chat;

    private LinkedList<String> screenLines = new LinkedList<String>();
    private static final int SCREEN_LINES_LIMIT = 100;
    
    /** Creates new form MainFrame */
    public ChatFrame()
    {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        commandBox = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        screenArea = new javax.swing.JTextArea();
        sendButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PastryChat");
        setBackground(new java.awt.Color(255, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        commandBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commandBoxActionPerformed(evt);
            }
        });

        screenArea.setBackground(new java.awt.Color(51, 51, 51));
        screenArea.setColumns(20);
        screenArea.setEditable(false);
        screenArea.setFont(new java.awt.Font("Monospaced", 0, 12));
        screenArea.setForeground(new java.awt.Color(204, 204, 204));
        screenArea.setLineWrap(true);
        screenArea.setRows(5);
        screenArea.setTabSize(4);
        screenArea.setDoubleBuffered(true);
        jScrollPane1.setViewportView(screenArea);

        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(commandBox, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sendButton)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(commandBox, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
    String text = commandBox.getText();
    if (text.length() > 0) {
        appendLine("# "+text);
        handleCommand(text);
    }
    commandBox.setText("");
}//GEN-LAST:event_sendButtonActionPerformed

private void commandBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commandBoxActionPerformed
    sendButtonActionPerformed(evt);
}//GEN-LAST:event_commandBoxActionPerformed

private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
    System.exit(0);
}//GEN-LAST:event_formWindowClosed

    /**
     * Starts the window.
     */
    public void start()
    {
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
            java.util.logging.Logger.getLogger(ChatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {

            @Override
            public void run()
            {
                setVisible(true);
                appendLine("Hello " + chat.getNickname() + "!");
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField commandBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea screenArea;
    private javax.swing.JButton sendButton;
    // End of variables declaration//GEN-END:variables

    // =====================================================================

    public void appendLine(String text)
    {
        this.screenLines.offer(text);
        
        if (this.screenLines.size() > SCREEN_LINES_LIMIT) {
            this.screenLines.pop();
        }
        
        refreshScreenArea();
    }
    
    public void appendTimestampedLine(String text)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        appendLine("["+sdf.format(new Date())+"] " + text);
    }
    
    // =====================================================================

    private void refreshScreenArea()
    {
        StringBuilder sb = new StringBuilder();
        
        for (String line : screenLines) {
            sb.append(line);
            sb.append("\n");
        }
        
        final String text = sb.toString();
        
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                screenArea.setText(text);
            }
        });
    }

    // =====================================================================

    protected void handleCommand(String text)
    {
        if (chat == null) {
            throw new RuntimeException("ChatApp not assigned!");
        }
        chat.handleCommand(text);
    }
    
    // =====================================================================

    @Override
    public void setChatApp(ChatApp app)
    {
        this.chat = app;
    }

    @Override
    public void onNodeConnected(Node node)
    {
        //appendLine("[i] Node " + node + " connected.");
    }

    @Override
    public void onNodeDisconnected(Node node)
    {
        //appendLine("[i] Node " + node + " disconnected.");
    }

    @Override
    public void onMessageDelivered(PrivateMsg msg)
    {
        appendTimestampedLine(msg.getFromName() + " --> " + msg.getToName() + ": " + msg.getText());
    }
    
    @Override
    public void onMessageDelivered(ChannelMsg msg)
    {
        // check recipient
        if (!chat.getNodeId().equals(msg.getTo())) {
            // wrong recipient!
            return;
        }
        appendTimestampedLine(msg.getFromName() + " @ " + msg.getChannelName() + ": " + msg.getText());
    }

    @Override
    public void onQuitCommand()
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                setVisible(false);
                dispose();
            }
        });
    }

    @Override
    public void onPrintln(String text)
    {
        appendLine(text);
    }
}