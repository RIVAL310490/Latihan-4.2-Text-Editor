import java.util.Scanner;
import java.util.Stack;

public class TextEditor {
    private String text = "";
    private Stack<String> undoStack = new Stack<>();
    private Stack<String> redoStack = new Stack<>();

    // Menampilkan teks saat ini
    public void show() {
        System.out.println("Current Text: \"" + text + "\"");
    }

    // Menulis teks baru
    public void write(String newText) {
        undoStack.push(text);   
        text += newText;
        redoStack.clear();
    }

    // Undo perubahan terakhir
    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(text);
            text = undoStack.pop();
        } else {
            System.out.println("Nothing to undo!");
        }
    }

    // Redo perubahan terakhir yang di-undo
    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(text);
            text = redoStack.pop();
        } else {
            System.out.println("Nothing to redo!");
        }
    }

    // Program utama (demo)
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TextEditor editor = new TextEditor();

        while (true) {
            System.out.println("\nCommand: show | write <text> | undo | redo | exit");
            System.out.print("> ");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("show")) {
                editor.show();
            }
            else if (input.startsWith("write ")) {
                String textToWrite = input.substring(6);
                editor.write(textToWrite);
            }
            else if (input.equalsIgnoreCase("undo")) {
                editor.undo();
            }
            else if (input.equalsIgnoreCase("redo")) {
                editor.redo();
            }
            else if (input.equalsIgnoreCase("exit")) {
                break;
            }
            else {
                System.out.println("Unknown command!");
            }
        }

        sc.close();
    }
}
