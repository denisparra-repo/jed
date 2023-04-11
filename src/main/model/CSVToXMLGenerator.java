package main.model;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVToXMLGenerator extends Thread {

    private String filePath;
    private String delimiter;
    private String rootTag;
    private String rootRecord;
    private boolean isProcesssing;

    private JTextArea textContent;

    public CSVToXMLGenerator(String filePath, String delimiter, String rootTag, String rootRecord, JTextArea textContent) {
        this.filePath = filePath;
        this.delimiter = delimiter;
        this.rootRecord = rootRecord;
        this.rootTag = rootTag;
        this.textContent = textContent;
        isProcesssing = true;
    }

    @Override
    public void run() {
        boolean flag = true;
        List<String> columns = new ArrayList<>();
        while(flag){
            try {
                FileWriter writter = new FileWriter(filePath);
                int lines = textContent.getLineCount();
                if (lines > 0) {
                    writter.write(String.format("<%s>%n", rootTag));
                }
                for (int i = 0; i < lines - 1; i++) {
                    int start = textContent.getLineStartOffset(i);
                    int end = textContent.getLineEndOffset(i);
                    String line = textContent.getText(start, end-start);
                    line = line.replace("\n", "").replace("\r", "");
                    String[] data = line.split(delimiter);
                    if (i == 0) {
                        columns = Arrays.asList(data);
                    } else {
                        writter.write(String.format("  <%s>%n", rootRecord));
                        for (int j = 0; j < data.length; j++) {
                            writter.write(String.format("    <%s>%s</%s>%n", columns.get(j), data[j], columns.get(j)));
                        }
                        writter.write(String.format("  </%s>%n", rootRecord));
                    }
                }
                if (lines > 0) {
                    writter.write(String.format("</%s>%n", rootTag));
                }
                writter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (BadLocationException e) {
                throw new RuntimeException(e);
            } finally {
                flag = false;
            }
        }
    }
}
