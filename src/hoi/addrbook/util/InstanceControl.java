package hoi.addrbook.util;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

public class InstanceControl extends Thread {
    private JFrame frame = null;

    public void run() {
        int[] ports = {
                43695, 51473, 62584, 47079, 53496 };

        try {
            for (int i = 0; i < ports.length; i++)
                new Socket("127.0.0.1", ports[i]);
            throw new RuntimeException("An instance already running!");
        } catch (RuntimeException re) {
            System.err.println(re.toString());
            System.exit(0);
        } catch (Exception e) {
        }

        for (int i = 0; i < ports.length; i++) {
            final int port = ports[i];
            new Thread() {
                public void run() {
                    try {
                        ServerSocket server = new ServerSocket(port);
                        while (true) {
                            server.accept();
                            if (frame != null) {
                                frame.setVisible(true);
                                frame.requestFocus();
                                frame.requestFocusInWindow();
                                frame.toFront();
                                frame.setState(JFrame.NORMAL);
                            }
                        }
                    } catch (IOException ioe) {
                    }
                }
            }.start();
        }
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

}
