package transferfileclient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JFileChooser;
import views.Ui;

public class Client extends Ui implements ActionListener {

    JFileChooser select = new JFileChooser();
    File file;

    Client() {
        setVisible(true);
        
        btn_open_file.addActionListener((ActionListener) this);

    }

    public static void main(String[] args) {
        new Client();
        /*
        try {
            Socket sock = new Socket("127.192.100.137", 9000);

            File file = new File("src/00281_JAVA_a_fondo8.pdf");
            FileInputStream fis = new FileInputStream(file);

            String file_name = file.getName();
            int file_size = (int) file.length();

            byte[] fileNameBytes = file_name.getBytes();
            byte[] fileContentBytes = new byte[file_size];
            fis.read(fileContentBytes);

            DataOutputStream dos = new DataOutputStream(sock.getOutputStream());

            dos.writeInt(fileNameBytes.length);
            dos.write(fileNameBytes);

            dos.writeInt(fileContentBytes.length);
            dos.write(fileContentBytes);

            dos.flush();
            fis.close();
            dos.close();
            sock.close();
        } catch (IOException ex) {
            System.out.println("Error client");
        }*/

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btn_open_file)) {
            if (select.showDialog(null, "Abrir") == JFileChooser.APPROVE_OPTION) {
                file = select.getSelectedFile();
                info_file.setText(file.getName());
                System.out.println("file: " + file.getName());
                System.out.println("yes");
                enviar();
            
            } else {
                System.out.println("boton de cancelar");
            }
        }
        
        if(e.getSource().equals(btn_send)){
           
        }
    }

    
    public void enviar() {
        try {
            Socket sock = new Socket("127.192.100.137", 9000);
            System.out.println("client socket connect...");
            //File file = new File("src/00281_JAVA_a_fondo8.pdf");
            FileInputStream fis = new FileInputStream(file);

            String file_name = file.getName();
            int file_size = (int) file.length();

            byte[] fileNameBytes = file_name.getBytes();
            byte[] fileContentBytes = new byte[file_size];
            fis.read(fileContentBytes);

            DataOutputStream dos = new DataOutputStream(sock.getOutputStream());

            dos.writeInt(fileNameBytes.length);
            dos.write(fileNameBytes);

            dos.writeInt(fileContentBytes.length);
            dos.write(fileContentBytes);

            dos.flush();
            fis.close();
            dos.close();
            sock.close();
        } catch (IOException ex) {
            System.out.println("Error client");
        }
    }

}
