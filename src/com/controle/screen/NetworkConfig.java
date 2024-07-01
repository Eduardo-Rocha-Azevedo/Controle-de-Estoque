/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controle.screen;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 *
 * @author eduaz
 */
public class NetworkConfig extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public NetworkConfig() {
        initComponents();
        loadServerConfig();
    }

    // Método para obter os valores dos campos de texto e usar para conectar ao banco de dados
    public static Connection conector() {
        // Verifica se os campos de texto foram inicializados
        if (txtIp1 == null || txtIp2 == null || txtIp3 == null || txtIp4 == null) {
            return null;
        }

        // Chama o driver
        String driver = "com.mysql.cj.jdbc.Driver";
        // Armazena informações do banco
        String ip = txtIp1.getText() + "." + txtIp2.getText() + "." + txtIp3.getText() + "." + txtIp4.getText();
        String url = "jdbc:mysql://" + ip + ":3306/dbcontrole";
        String user = txtUser.getText();
        String password = txtSenha.getText();

        Connection conexao = null;
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);

            // Salva o IP do servidor no arquivo de configuração
            saveServerIp(ip);

            return conexao;
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC não encontrado: " + e);
            return null;
        } catch (SQLException e) {
            System.out.println("Falha ao conectar ao banco de dados: " + e);
            return null;
        }
    }

    // Método para salvar o IP do servidor em um arquivo de configuração
    private static void saveServerIp(String ip) {
        try (FileOutputStream fileOut = new FileOutputStream("server_config.dat");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(new ServerConfig(ip));
        } catch (IOException e) {
            // Tratamento para erros de I/O ao salvar o arquivo de configuração
            e.printStackTrace();
        }
    }

    // Método para carregar as configurações do servidor
    private static void loadServerConfig() {
        try (FileInputStream fileIn = new FileInputStream("server_config.dat");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            ServerConfig config = (ServerConfig) in.readObject();
            String[] ipParts = config.getIp().split("\\.");
            if (ipParts.length == 4) {
                txtIp1.setText(ipParts[0]);
                txtIp2.setText(ipParts[1]);
                txtIp3.setText(ipParts[2]);
                txtIp4.setText(ipParts[3]);
            } else {
                throw new IOException("Configuração de IP inválida.");
            }
        } catch (IOException | ClassNotFoundException e) {
            // Tratamento para erros de I/O ao carregar o arquivo de configuração
            e.printStackTrace();
        }
    }

    // Classe auxiliar para armazenar a configuração do servidor
    static class ServerConfig implements Serializable {
        private static final long serialVersionUID = 1L;
        private String ip;

        public ServerConfig(String ip) {
            this.ip = ip;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }
    }

    // Evento do botão "Cancelar"
    private void cancelar() {
        int opc = JOptionPane.showConfirmDialog(this, "Deseja realmente cancelar a configuração?", "Cancelar",
                JOptionPane.YES_NO_OPTION);
        if (opc == JOptionPane.YES_OPTION) {
            this.dispose(); // Fecha a janela
        }
    }

    private void salvarConfiguracoes() {
        String ip = txtIp1.getText() + "." + txtIp2.getText() + "." + txtIp3.getText() + "." + txtIp4.getText();
        Connection conexao = conector();
        if (conexao != null) {
            JOptionPane.showMessageDialog(this, "Conexão bem sucedida!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Falha ao conectar ao banco de dados.");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtIp1 = new javax.swing.JTextField();
        txtIp2 = new javax.swing.JTextField();
        txtIp3 = new javax.swing.JTextField();
        txtIp4 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configuração de rede ");

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel1.setText("Configuração de rede");

        jLabel2.setText("IP do servidor");

        txtIp1.setText("127");
        txtIp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIp1ActionPerformed(evt);
            }
        });

        txtIp2.setText("0");
        txtIp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIp2ActionPerformed(evt);
            }
        });

        txtIp3.setText("0");
        txtIp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIp3ActionPerformed(evt);
            }
        });

        txtIp4.setText("1");
        txtIp4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIp4ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel3.setText(".");

        jLabel4.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel4.setText(".");

        jLabel5.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel5.setText(".");

        jButton1.setText("Salvar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setText("Username");

        txtUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserActionPerformed(evt);
            }
        });

        jLabel7.setText("Senha");

        txtSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUser)
                            .addComponent(txtSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtIp1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIp2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIp3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIp4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIp3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIp4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtIp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(27, 27, 27))
        );

        setSize(new java.awt.Dimension(416, 339));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        salvarConfiguracoes();

    }// GEN-LAST:event_jButton1ActionPerformed

    private void txtIp3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtIp3ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtIp3ActionPerformed

    private void txtSenhaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtSenhaActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtSenhaActionPerformed

    private void txtUserActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtUserActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtUserActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        cancelar();
    }// GEN-LAST:event_jButton2ActionPerformed

    private void txtIp1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtIp1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtIp1ActionPerformed

    private void txtIp4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtIp4ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtIp4ActionPerformed

    private void txtIp2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtIp2ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtIp2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NetworkConfig.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NetworkConfig.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NetworkConfig.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NetworkConfig.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NetworkConfig().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    protected static javax.swing.JTextField txtIp1;
    protected static javax.swing.JTextField txtIp2;
    protected static javax.swing.JTextField txtIp3;
    protected static javax.swing.JTextField txtIp4;
    protected static javax.swing.JPasswordField txtSenha;
    protected static javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
