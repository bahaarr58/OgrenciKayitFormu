import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProjeUygulamasi extends JFrame {
    private List<Ders> dersList = new ArrayList<>();
    private List<Ogrenci> ogrenciList = new ArrayList<>();

    private JTextField dersKoduField;
    private JTextField dersAdField;
    private JTextField dersDonemField;

    private JTextField ogrenciNoField;
    private JTextField ogrenciAdField;
    private JTextField ogrenciSoyadField;
    private JTextField ogrenciBolumField;
    private JComboBox<String> ogrenciDerslerComboBox;

    public ProjeUygulamasi() {
        setTitle("Proje Uygulaması");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton dersKayitButton = new JButton("Ders Kayıt Formu");
        JButton ogrenciKayitButton = new JButton("Öğrenci Kayıt Formu");

        dersKayitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acDersKayitFormu();
            }
        });

        ogrenciKayitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acOgrenciKayitFormu();
            }
        });

        JPanel panel = new JPanel();
        panel.add(dersKayitButton);
        panel.add(ogrenciKayitButton);

        add(panel);
    }

    private void acDersKayitFormu() {
        dersKoduField = new JTextField(20);
        dersAdField = new JTextField(20);
        dersDonemField = new JTextField(20);

        JButton kaydetButton = new JButton("Kaydet");
        kaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dersKodu = dersKoduField.getText();
                String dersAd = dersAdField.getText();
                String dersDonem = dersDonemField.getText();

                Ders yeniDers = new Ders(dersKodu, dersAd, dersDonem);
                dersList.add(yeniDers);

                kaydet(dersList, "dersler.json");

                JOptionPane.showMessageDialog(null, "Kayıt Başarılı!");

                dersKoduField.setText("");
                dersAdField.setText("");
                dersDonemField.setText("");
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Ders Kodu:"));
        panel.add(dersKoduField);
        panel.add(new JLabel("Ders Adı:"));
        panel.add(dersAdField);
        panel.add(new JLabel("Dönem:"));
        panel.add(dersDonemField);
        panel.add(kaydetButton);

        JFrame frame = new JFrame("Ders Kayıt Formu");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);
    }

    private void acOgrenciKayitFormu() {
        ogrenciNoField = new JTextField(20);
        ogrenciAdField = new JTextField(20);
        ogrenciSoyadField = new JTextField(20);
        ogrenciBolumField = new JTextField(20);
        ogrenciDerslerComboBox = new JComboBox<>();

        for (Ders ders : dersList) {
            ogrenciDerslerComboBox.addItem(ders.getDersAd());
        }

        JButton kaydetButton = new JButton("Kaydet");
        kaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ogrenciNo = ogrenciNoField.getText();
                String ogrenciAd = ogrenciAdField.getText();
                String ogrenciSoyad = ogrenciSoyadField.getText();
                String ogrenciBolum = ogrenciBolumField.getText();
                String seciliDers = (String) ogrenciDerslerComboBox.getSelectedItem();

                Ogrenci yeniOgrenci = new Ogrenci(ogrenciNo, ogrenciAd, ogrenciSoyad, ogrenciBolum, seciliDers);
                ogrenciList.add(yeniOgrenci);

                kaydet(ogrenciList, "ogrenciler.json");

                JOptionPane.showMessageDialog(null, "Kayıt Başarılı!");

                ogrenciNoField.setText("");
                ogrenciAdField.setText("");
                ogrenciSoyadField.setText("");
                ogrenciBolumField.setText("");
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Öğrenci No:"));
        panel.add(ogrenciNoField);
        panel.add(new JLabel("Öğrenci Adı:"));
        panel.add(ogrenciAdField);
        panel.add(new JLabel("Öğrenci Soyadı:"));
        panel.add(ogrenciSoyadField);
        panel.add(new JLabel("Öğrenci Bölümü:"));
        panel.add(ogrenciBolumField);
        panel.add(new JLabel("Dersler:"));
        panel.add(ogrenciDerslerComboBox);
        panel.add(kaydetButton);

        JFrame frame = new JFrame("Öğrenci Kayıt Formu");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);
    }

    private <T> void kaydet(List<T> list, String dosyaAdi) {
        try (FileWriter writer = new FileWriter(dosyaAdi)) {

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Kayıt sırasında bir hata oluştu.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ProjeUygulamasi projeUygulamasi = new ProjeUygulamasi();
                projeUygulamasi.setVisible(true);
            }
        });
    }
}

class Ders {
    private String dersKodu;
    private String dersAd;
    private String dersDonem;

    public Ders(String dersKodu, String dersAd, String dersDonem) {
        this.dersKodu = dersKodu;
        this.dersAd = dersAd;
        this.dersDonem = dersDonem;
    }

    public String getDersKodu() {
        return dersKodu;
    }

    public void setDersKodu(String dersKodu) {
        this.dersKodu = dersKodu;
    }

    public String getDersAd() {
        return dersAd;
    }

    public void setDersAd(String dersAd) {
        this.dersAd = dersAd;
    }

    public String getDersDonem() {
        return dersDonem;
    }

    public void setDersDonem(String dersDonem) {
        this.dersDonem = dersDonem;
    }
}

class Ogrenci {
    private String ogrenciNo;
    private String ogrenciAd;
    private String ogrenciSoyad;
    private String ogrenciBolum;
    private String ogrenciDers;

    public Ogrenci(String ogrenciNo, String ogrenciAd, String ogrenciSoyad, String ogrenciBolum, String ogrenciDers) {
        this.ogrenciNo = ogrenciNo;
        this.ogrenciAd = ogrenciAd;
        this.ogrenciSoyad = ogrenciSoyad;
        this.ogrenciBolum = ogrenciBolum;
        this.ogrenciDers = ogrenciDers;
    }

    public String getOgrenciNo() {
        return ogrenciNo;
    }

    public void setOgrenciNo(String ogrenciNo) {
        this.ogrenciNo = ogrenciNo;
    }

    public String getOgrenciAd() {
        return ogrenciAd;
    }

    public void setOgrenciAd(String ogrenciAd) {
        this.ogrenciAd = ogrenciAd;
    }

    public String getOgrenciSoyad() {
        return ogrenciSoyad;
    }

    public void setOgrenciSoyad(String ogrenciSoyad) {
        this.ogrenciSoyad = ogrenciSoyad;
    }

    public String getOgrenciBolum() {
        return ogrenciBolum;
    }

    public void setOgrenciBolum(String ogrenciBolum) {
        this.ogrenciBolum = ogrenciBolum;
    }

    public String getOgrenciDers() {
        return ogrenciDers;
    }

    public void setOgrenciDers(String ogrenciDers) {
        this.ogrenciDers = ogrenciDers;
    }
}
