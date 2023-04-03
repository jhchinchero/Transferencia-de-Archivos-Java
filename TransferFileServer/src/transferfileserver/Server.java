package transferfileserver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Server extends JFrame {

    JButton btn_select;
    JLabel info_file;
    

    Server() {

        JPanel panel = new JPanel();
        btn_select = new JButton("Select");

        info_file = new JLabel();
        add(panel);
        panel.add(btn_select);
        panel.add(info_file);
        setSize(200, 200);

    }

    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(9000);

        Socket sock = ss.accept();
        DataInputStream read = new DataInputStream(sock.getInputStream());

        int file_size = read.readInt();

        if (file_size > 0) {
            byte[] fileNameBytes = new byte[file_size];
            read.readFully(fileNameBytes, 0, fileNameBytes.length);

            String file_name = new String(fileNameBytes);

            int fileContentBytesLength = read.readInt();

            if (fileContentBytesLength > 0) {
                int input = JOptionPane.showConfirmDialog(null, "Aceptar el archivo?");
                if (input == 1) {
                    System.out.println("No se accepto el archivo");
                }
                if (input == 0) {
                   
                    byte[] fileContentBytes = new byte[fileContentBytesLength];
                    read.readFully(fileContentBytes, 0, fileContentBytesLength);

                    File donwload = new File("src/" + file_name);
                    //System.out.println("ruta: " + ruta);

                    FileOutputStream fos = new FileOutputStream(donwload);
                    fos.write(fileContentBytes);

                    fos.flush();

                    read.close();
                    ss.close();
                    System.out.println("Se guardo el archivo");
                }
                if (input == 2) {
                    System.out.println("se cancelo el archivo");
                }

            }
        }

        //File file = new File("src/img.jpg");
        /*
        System.out.println(file.getName());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.length());
        System.out.println(file.getName().getBytes());*/
    }
    /*
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(btn_select)){
            
            if (select.showDialog(null, "Abrir") == JFileChooser.APPROVE_OPTION) {
                files = select.getCurrentDirectory();
                info_file.setText(files.getName());
                System.out.println("file: " + files.getName());
                System.out.println("ruta: "+files.getAbsolutePath());
                ruta = files.getAbsolutePath();
                
            
            } else {
                System.out.println("boton de cancelar");
            }
        }
    }*/

}
