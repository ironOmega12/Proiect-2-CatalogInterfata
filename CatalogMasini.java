import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class CatalogMasini {

    private ArrayList<Masini> masina = new ArrayList<>();
    private JFrame frame;
    private JButton btnAdd, btnSearch, btnShowCount, butonPanou1, butonPanou2;
    private JTextPane outputArea;
    private JTextField txtInput1, txtInput2, txtInput3, txtInput4, txtInputSasiu;           //Declarari
    private JLabel txtAdd1, txtAdd2, txtAdd3, txtAdd4;
    private JPanel panou1, panou2, panou3;
    private Container panouContent;
    
    public CatalogMasini() {
        frame = new JFrame("Catalog Masini");               //Setarile cadrului
        frame.setSize(500, 430);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panouContent = new Container();
        panouContent = frame.getContentPane();
        panouContent.setLayout(new BoxLayout(panouContent, BoxLayout.Y_AXIS));


        btnAdd = new JButton("Adauga Masina");      // Cele 3 butoane
        btnSearch = new JButton("Cauta Masina");
        btnShowCount = new JButton("Arata numarul de masini");

        outputArea = new JTextPane();
        StyledDocument doc = outputArea.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        panou1 = new JPanel();                                              // Primul Panel
        panou1.setLayout(new BoxLayout(panou1, BoxLayout.PAGE_AXIS));
        panou1.setBounds(200, 200, 200, 200);
        // panou1.setBackground(Color.YELLOW);
        frame.add(panou1);
        panou1.setBorder(new EmptyBorder(10, 10, 10, 10));
        panou1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panou1.add(btnAdd);

        panou2 = new JPanel();
        panou2.setLayout(new BoxLayout(panou2, BoxLayout.PAGE_AXIS));
        // panou2.setBackground(Color.BLUE);
        panou2.setBorder(new EmptyBorder(10, 10, 10, 10));
        panou2.add(btnSearch);
        panou2.setAlignmentX(Component.CENTER_ALIGNMENT);                       // Al 2-lea panel
        frame.add(panou2);


        panou3 = new JPanel();
        panou3.setLayout(new BoxLayout(panou3, BoxLayout.PAGE_AXIS));
        // panou3.setBackground(Color.cyan);
        panou3.setBorder(new EmptyBorder(10, 10, 10, 10));
        panou3.add(btnShowCount);                //Butonul de numarat
        panou3.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(panou3);
        
        frame.add(outputArea);      //Textul ce apare dupa modificari

        btnAdd.addActionListener(new ActionListener() {         //Crearea celor 4 casute de text, din care se vor lua informatiile pentru masina
            public void actionPerformed(ActionEvent e) {
                txtAdd1 = new JLabel();
                txtAdd1.setText("Marca");
                txtAdd2 = new JLabel();
                txtAdd2.setText("Model");
                txtAdd3 = new JLabel();
                txtAdd3.setText("Sasiu");
                txtAdd4 = new JLabel();
                txtAdd4.setText("Kilometraj");

                                                                
                txtInput1 = new JTextField(10);
                txtInput2 = new JTextField(10);
                txtInput3 = new JTextField(10);
                txtInput4 = new JTextField(10);

                panou1.add(txtAdd1);            // Adaugarea lor la panou
                panou1.add(txtInput1);

                panou1.add(txtAdd2);
                panou1.add(txtInput2);

                panou1.add(txtAdd3);
                panou1.add(txtInput3);

                panou1.add(txtAdd4);
                panou1.add(txtInput4);

                butonPanou1 = new JButton("Adaugare"); // Creare buton de adaugare si adaugare la panou
                panou1.add(butonPanou1);
                panou1.revalidate();

                butonPanou1.addActionListener(new ActionListener() {   // Metoda prin care adaugam o masina noua, luam informatiile din casutele de text si le adaugam in array
                    public void actionPerformed(ActionEvent e) {
                        masina.add(new Masini(txtInput1.getText(), txtInput2.getText(), txtInput3.getText(), Integer.parseInt(txtInput4.getText())));
                        outputArea.setText("Succes");
                        outputArea.setForeground(Color.green);
                    }
                });
            }
        });

        btnSearch.addActionListener(new ActionListener() { //Butonul de cautare, care creeaza o casuta noua de text si un buton
            public void actionPerformed(ActionEvent e) {
                txtInputSasiu = new JTextField(10);
                panou2.add(txtInputSasiu);
                butonPanou2 = new JButton("Cautare");
                panou2.add(butonPanou2);
                panou2.revalidate();

                butonPanou2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String sasiu = txtInputSasiu.getText();
                        boolean gasit = false;
                        for (Masini m : masina) {
                            if (m.getSasiu().equals(sasiu)) {           //Daca numarul de sasiu exista, afisam marca si modelul, dupa care ne oprim
                                outputArea.setText("Masina gasita: " + m.toString());
                                outputArea.setForeground(Color.blue);
                                gasit = true;
                                break;
                            }
                            if (!gasit) {
                                outputArea.setText("Masina nu exista in catalog. "); //Daca nu exista, afisam mesajul corespunzator.
                                outputArea.setForeground(Color.red);
                            }
                        }
                    }
                });
            }
        });

        btnShowCount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            outputArea.setText("Numarul de masini: " + masina.size());
           } 
        });

        


        frame.setVisible(true);
    }

}
