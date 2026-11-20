/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cine.cola;
import java.util.NoSuchElementException;
import javax.swing.JOptionPane;

/**
 *
 * @author lopez
 */
public class VentanaCine2 extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaCine2.class.getName());
    private final ColaCineFIFO cola = new ColaCineFIFO();
   private javax.swing.JTextField campoTitulo;
    private javax.swing.JTextField campoDirector;
    private javax.swing.JTextField campoAnio;
    private javax.swing.JTextField campoSala;
    private javax.swing.JTextField campoAsiento;
    private javax.swing.JTextField campoAudio;
    

    /**
     * Creates new form VentanaCine2
     */
public VentanaCine2() {
        initComponents();
        setTitle("Gestión de Cine - Cola Dinámica (FIFO)");
        setLocationRelativeTo(null);

        vincularCamposReales();
        cmbTipo.addActionListener(e -> actualizarCamposPorTipo());
        actualizarCamposPorTipo();
        actualizarVistaCola();
    }

    private void vincularCamposReales() {
        java.util.List<javax.swing.JTextField> cajas = new java.util.ArrayList<>();
        for (java.awt.Component c : getContentPane().getComponents()) {
            if (c instanceof javax.swing.JTextField) {
                cajas.add((javax.swing.JTextField) c);
            }
        }
        cajas.sort(java.util.Comparator.comparingInt(java.awt.Component::getY));
        if (cajas.size() >= 6) {
            campoTitulo = cajas.get(0);
            campoDirector = cajas.get(1);
            campoAnio = cajas.get(2);
            campoSala = cajas.get(3);
            campoAsiento = cajas.get(4);
            campoAudio = cajas.get(5);
        }
    
   
    
    }
   private void actualizarCamposPorTipo() {
        if (cmbTipo.getSelectedItem() == null || campoSala == null) return;
        String tipo = cmbTipo.getSelectedItem().toString();

        boolean esTradicional = tipo.contains("Tradicional");
        boolean esVIP = tipo.contains("VIP");
        boolean esIMAX = tipo.contains("IMAX");

        campoSala.setEnabled(esTradicional);
        campoSala.setEditable(esTradicional);

        campoAsiento.setEnabled(esVIP);
        campoAsiento.setEditable(esVIP);

        campoAudio.setEnabled(esIMAX);
        campoAudio.setEditable(esIMAX);

        java.awt.Color activo = java.awt.Color.WHITE;
        java.awt.Color bloqueado = new java.awt.Color(210, 210, 210);

        campoSala.setBackground(esTradicional ? activo : bloqueado);
        campoAsiento.setBackground(esVIP ? activo : bloqueado);
        campoAudio.setBackground(esIMAX ? activo : bloqueado);

        if (!esTradicional) campoSala.setText("");
        if (!esVIP) campoAsiento.setText("");
        if (!esIMAX) campoAudio.setText("");
    }

    private void limpiarFormulario() {
        txtPelicula.setText("");
        txtDirector.setText("");
        txtAnio.setText("");
        lblSalaOld.setText("");
        lblAsientoOld.setText("");
        lblAudioOld.setText("");
        txtPelicula.requestFocus();
    }

    private void actualizarVistaCola() {
        txtAreaCola.setText(cola.obtenerEstadoVisual());
        txtAreaCola.setCaretPosition(0);
    }

    private Proyeccion construirProyeccionValidada() {
        String tipo = cmbTipo.getSelectedItem().toString();
        String pelicula = txtPelicula.getText().trim();
        String director = txtDirector.getText().trim();
        String anioTexto = txtAnio.getText().trim();

        if (pelicula.isEmpty() || director.isEmpty() || anioTexto.isEmpty()) {
            throw new IllegalArgumentException("Completa los datos generales: Película, Director y Año de estreno.");
        }

        int anio;
        try {
            anio = Integer.parseInt(anioTexto);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El año de estreno debe ser un número entero válido.");
        }
        if (anio <= 0) {
            throw new IllegalArgumentException("El año de estreno debe ser mayor a cero.");
        }

        switch (tipo) {
            case "FuncionTradicional":
                String salaTexto = lblSalaOld.getText().trim();
                if (salaTexto.isEmpty()) {
                    throw new IllegalArgumentException("Ingresa el número de sala.");
                }
                int sala;
                try {
                    sala = Integer.parseInt(salaTexto);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("El número de sala debe ser un entero válido.");
                }
                if (sala <= 0) {
                    throw new IllegalArgumentException("El número de sala debe ser mayor a cero.");
                }
                return new FuncionTradicional(pelicula, director, anio, sala);

            case "FuncionVIP":
                String asientoTexto = lblAsientoOld.getText().trim();
                if (asientoTexto.isEmpty()) {
                    throw new IllegalArgumentException("Ingresa el número de asiento VIP.");
                }
                int asiento;
                try {
                    asiento = Integer.parseInt(asientoTexto);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("El número de asiento debe ser un entero válido.");
                }
                if (asiento <= 0) {
                    throw new IllegalArgumentException("El número de asiento debe ser mayor a cero.");
                }
                return new FuncionVIP(pelicula, director, anio, asiento);

            case "FuncionIMAX":
                String audio = lblAudioOld.getText().trim();
                if (audio.isEmpty()) {
                    throw new IllegalArgumentException("Ingresa el formato de audio (ej. Dolby Atmos).");
                }
                return new FuncionIMAX(pelicula, director, anio, audio);

            default:
                throw new IllegalStateException("Tipo de función no reconocido.");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtPelicula = new javax.swing.JLabel();
        txtDirector = new javax.swing.JLabel();
        txtAnio = new javax.swing.JLabel();
        lblSalaOld = new javax.swing.JLabel();
        lblAsientoOld = new javax.swing.JLabel();
        lblAudioOld = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox<>();
        txtTituloPelicula = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        txtSala = new javax.swing.JTextField();
        txtAsiento = new javax.swing.JTextField();
        txtAudio = new javax.swing.JTextField();
        btnOffer = new javax.swing.JButton();
        btnPoll = new javax.swing.JButton();
        btnPeek = new javax.swing.JButton();
        btnVaciar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaCola = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Tipo de Funcion");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 16, 125, -1));

        txtPelicula.setText("Título Película");
        getContentPane().add(txtPelicula, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 41, 125, -1));

        txtDirector.setText("Director");
        getContentPane().add(txtDirector, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 66, 125, -1));

        txtAnio.setText("Año de Estreno");
        getContentPane().add(txtAnio, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 94, 125, -1));

        lblSalaOld.setText("N.º Sala (Tradicional)");
        getContentPane().add(lblSalaOld, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 122, 125, -1));

        lblAsientoOld.setText("N.º Asiento (VIP)");
        getContentPane().add(lblAsientoOld, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 150, 125, -1));

        lblAudioOld.setText("Audio (IMAX)");
        getContentPane().add(lblAudioOld, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 178, 125, -1));

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FuncionTradicional", "FuncionVIP", "FuncionIMAX" }));
        cmbTipo.addActionListener(this::cmbTipoActionPerformed);
        getContentPane().add(cmbTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 13, -1, -1));

        txtTituloPelicula.addActionListener(this::txtTituloPeliculaActionPerformed);
        getContentPane().add(txtTituloPelicula, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 35, 71, -1));
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 63, 71, -1));
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 91, 71, -1));

        txtSala.addActionListener(this::txtSalaActionPerformed);
        getContentPane().add(txtSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 119, 71, -1));
        getContentPane().add(txtAsiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 147, 71, -1));

        txtAudio.addActionListener(this::txtAudioActionPerformed);
        getContentPane().add(txtAudio, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 175, 71, -1));

        btnOffer.setText("Encolar (Offer)");
        btnOffer.addActionListener(this::btnOfferActionPerformed);
        getContentPane().add(btnOffer, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 225, -1, -1));

        btnPoll.setText("Atender (Poll)");
        btnPoll.addActionListener(this::btnPollActionPerformed);
        getContentPane().add(btnPoll, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 225, -1, -1));

        btnPeek.setText("Consultar Frente (Peek)");
        btnPeek.addActionListener(this::btnPeekActionPerformed);
        getContentPane().add(btnPeek, new org.netbeans.lib.awtextra.AbsoluteConstraints(254, 225, -1, -1));

        btnVaciar.setText("Vaciar Cola");
        btnVaciar.addActionListener(this::btnVaciarActionPerformed);
        getContentPane().add(btnVaciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(424, 225, -1, -1));

        txtAreaCola.setEditable(false);
        txtAreaCola.setColumns(20);
        txtAreaCola.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        txtAreaCola.setRows(5);
        jScrollPane1.setViewportView(txtAreaCola);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 260, 508, 275));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTituloPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTituloPeliculaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTituloPeliculaActionPerformed

    private void txtSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSalaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSalaActionPerformed

    private void btnOfferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOfferActionPerformed
        String titulo = campoTitulo.getText().trim();
        String director = campoDirector.getText().trim();
        String anioStr = campoAnio.getText().trim();

        if (titulo.isEmpty() || director.isEmpty() || anioStr.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Completa Título, Director y Año de Estreno.", "Validación de datos", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        int anio;
        try {
            anio = Integer.parseInt(anioStr);
            if (anio <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "El año de estreno debe ser un número entero válido.", "Validación de datos", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        String tipo = cmbTipo.getSelectedItem().toString();
        Proyeccion nueva;

        if (tipo.contains("Tradicional")) {
            String salaStr = campoSala.getText().trim();
            if (salaStr.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "Ingresa el N.º de Sala.", "Validación de datos", javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                int sala = Integer.parseInt(salaStr);
                if (sala <= 0) throw new NumberFormatException();
                nueva = new FuncionTradicional(titulo, director, anio, sala);
            } catch (NumberFormatException ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "El N.º de Sala debe ser un entero positivo.", "Validación de datos", javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }
        } else if (tipo.contains("VIP")) {
            String asientoStr = campoAsiento.getText().trim();
            if (asientoStr.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "Ingresa el N.º de Asiento VIP.", "Validación de datos", javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                int asiento = Integer.parseInt(asientoStr);
                if (asiento <= 0) throw new NumberFormatException();
                nueva = new FuncionVIP(titulo, director, anio, asiento);
            } catch (NumberFormatException ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "El N.º de Asiento debe ser un entero positivo.", "Validación de datos", javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }
        } else {
            String audio = campoAudio.getText().trim();
            if (audio.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "Ingresa el tipo de Audio IMAX.", "Validación de datos", javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }
            nueva = new FuncionIMAX(titulo, director, anio, audio);
        }

        cola.offer(nueva);

        campoTitulo.setText("");
        campoDirector.setText("");
        campoAnio.setText("");
        campoSala.setText("");
        campoAsiento.setText("");
        campoAudio.setText("");

        actualizarVistaCola();
    }//GEN-LAST:event_btnOfferActionPerformed

    private void btnVaciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVaciarActionPerformed
        if (cola.isEmpty()) {
            JOptionPane.showMessageDialog(this, "La cola ya se encuentra vacía.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int confirmacion = JOptionPane.showConfirmDialog(
            this,
            "¿Deseas vaciar todas las funciones en espera?",
            "Confirmar vaciado",
            JOptionPane.YES_NO_OPTION
        );
        if (confirmacion == JOptionPane.YES_OPTION) {
            cola.vaciarCola();
            actualizarVistaCola();
            JOptionPane.showMessageDialog(this, "La cola del cine ha sido vaciada.", "Cola vaciada", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnVaciarActionPerformed

    private void cmbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoActionPerformed
        actualizarCamposPorTipo();
    }//GEN-LAST:event_cmbTipoActionPerformed

    private void btnPollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPollActionPerformed
        try {
            Proyeccion atendida = cola.poll();
            actualizarVistaCola();
            JOptionPane.showMessageDialog(
                this,
                "Función atendida y removida del frente (FIFO):\n" + atendida.toString(),
                "Atender / Desencolar (Poll)",
                JOptionPane.INFORMATION_MESSAGE
            );
        } catch (NoSuchElementException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Cola vacía", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnPollActionPerformed

    private void btnPeekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPeekActionPerformed
        try {
            Proyeccion alFrente = cola.peek();
            JOptionPane.showMessageDialog(
                this,
                "Siguiente función al FRENTE (sin remover):\n" + alFrente.toString(),
                "Consultar Frente (Peek)",
                JOptionPane.INFORMATION_MESSAGE
            );
        } catch (NoSuchElementException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Cola vacía", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnPeekActionPerformed

    private void txtAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAudioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAudioActionPerformed

    /**
     * @param args the command line arguments
     */
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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new VentanaCine2().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOffer;
    private javax.swing.JButton btnPeek;
    private javax.swing.JButton btnPoll;
    private javax.swing.JButton btnVaciar;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel lblAsientoOld;
    private javax.swing.JLabel lblAudioOld;
    private javax.swing.JLabel lblSalaOld;
    private javax.swing.JLabel txtAnio;
    private javax.swing.JTextArea txtAreaCola;
    private javax.swing.JTextField txtAsiento;
    private javax.swing.JTextField txtAudio;
    private javax.swing.JLabel txtDirector;
    private javax.swing.JLabel txtPelicula;
    private javax.swing.JTextField txtSala;
    private javax.swing.JTextField txtTituloPelicula;
    // End of variables declaration//GEN-END:variables
}
