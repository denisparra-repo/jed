package main.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileProcessor extends Thread {
    private ArrayList<ChangeEditorTextListener> listeners;
    private File file;

    public FileProcessor(File file, ArrayList<ChangeEditorTextListener> listeners) {
      this.file = file;
      this.listeners = listeners;
    }

    public void addListener(ChangeEditorTextListener listener) {
        listeners.add(listener);
    }

    public void notifyListeners(String text) {
        for (ChangeEditorTextListener listener: listeners) {
            listener.appendText(text);
        }
    }

    @Override
    public void run() {
        boolean flag = true;
        while(flag){
            FileInputStream inputStream = null;
            Scanner sc = null;
            try {
                inputStream = new FileInputStream(file);
                sc = new Scanner(inputStream, "UTF-8");
                StringBuilder builder = new StringBuilder();
                int count = 1;
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    builder.append(String.format("%s%n", line));
                    if (count % 10000 == 0) {
                        notifyListeners(builder.toString());
                        builder = new StringBuilder();
                        Thread.sleep(10000);
                    }
                    count++;
                }
                notifyListeners(builder.toString());
                // note that Scanner suppresses exceptions
                if (sc.ioException() != null) {
                    throw sc.ioException();
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if (sc != null) {
                    sc.close();
                }
                flag = false;
            }
        }
    }
}
