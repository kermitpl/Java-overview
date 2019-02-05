import org.mariuszgromada.math.mxparser.Expression;

import javax.swing.*;
import java.awt.event.*;
import java.text.MessageFormat;
import java.util.Date;

public class MainPanel extends JDialog {
    private JPanel contentPane;
    private JList functionsList;
    private JTextField formulaInput;
    private JButton evalButton;
    private JTextArea historyTextArea;
    private JMenu Menu;
    private JMenuBar MenuBar;
    private JMenuItem MenuItemReset;
    private JMenuItem MenuItemExit;
    private JButton buttonOK;
    private String lastAction;
    private DefaultListModel<MathFunction> listModel;

    public MainPanel() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        listModel = new DefaultListModel<>();
        listModel.addElement(new MathFunction("Sinus", "sin()", -1));
        listModel.addElement(new MathFunction("Cosinus", "cos()", -1));
        listModel.addElement(new MathFunction("Square root", "sqrt()", -1));
        listModel.addElement(new MathFunction("Fibonacci number", "Fib()", -1));
        listModel.addElement(new MathFunction("Absolute value", "abs()", -1));
        listModel.addElement(new MathFunction("Pi number", "pi", 0));
        listModel.addElement(new MathFunction("Golden ratio", "[phi]", 0));
        listModel.addElement(new MathFunction("Plastic constant", "[PN]", 0));
        listModel.addElement(new MathFunction("Addition", "+", 0));
        listModel.addElement(new MathFunction("Multiplication", "*", 0));
        listModel.addElement(new MathFunction("Division", "/", 0));
        listModel.addElement(new MathFunction("Last Action", getLastAction(), 0));
        functionsList.setModel(listModel);

        Menu = new JMenu("Options");
        MenuBar.add(Menu);
        MenuItemReset = new JMenuItem("Reset");
        MenuItemReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            historyTextArea.setText("");
            formulaInput.setText("");
            }
        });
        Menu.add(MenuItemReset);

        MenuItemExit = new JMenuItem("Exit");
        MenuItemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        Menu.add(MenuItemExit);

        evalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    evaluate();
                } catch (ArithmeticException exception) {
                    System.out.println(exception);
                }
            }
        });
        formulaInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_UP) {
                    formulaInput.setText(lastAction);
                }
            }
        });
        formulaInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode()==KeyEvent.VK_ENTER) {
                    try {
                        evaluate();
                    } catch (ArithmeticException exception) {
                        System.out.println(exception);
                    }
                }
            }
        });
        functionsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JList list = (JList)e.getSource();
                if (e.getClickCount() == 2) {
                    MathFunction chosen=(MathFunction)list.getSelectedValue();
                    String previousText = formulaInput.getText();
                    formulaInput.setText(previousText+chosen.getNameToParser());
                    formulaInput.requestFocusInWindow();
                    int CursorPosition = formulaInput.getText().length();
                    formulaInput.setCaretPosition(CursorPosition+chosen.getCursorPosition());
                }
            }
        });
    }

    private void evaluate() throws ArithmeticException {
        Expression expression = new Expression(formulaInput.getText());
        if (expression.checkSyntax()) {
            Double result = expression.calculate();
            String message = MessageFormat.format(
                    "At {1,time} on {1,date} you did: \n {0} = {2}.\n\n", formulaInput.getText(), new Date(), result);
            historyTextArea.append(message);
            lastAction=formulaInput.getText();
            formulaInput.setText("");
            listModel.lastElement().NameToParser=getLastAction();
        }
        else {
            String errorMessage = expression.getErrorMessage();
            //throwing exception
            JOptionPane.showMessageDialog(null, errorMessage, "An error occurred", JOptionPane.ERROR_MESSAGE);
            throw new ArithmeticException("Evaluation exception: "+errorMessage);
        }
    }

    private String getLastAction() {
        return lastAction;
    }

    public static void main(String[] args) {
        MainPanel dialog = new MainPanel();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
