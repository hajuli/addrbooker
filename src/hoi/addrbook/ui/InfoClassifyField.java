package hoi.addrbook.ui;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class InfoClassifyField extends JPanel implements AccessInterface {

    private static final long serialVersionUID = 7531465834540410473L;
    private JComboBox classify = null;
    private String contactKey = null;

    public InfoClassifyField(String contactKey, String compName, Vector<String> classifys) {
        super(new BorderLayout(0, 0));
        this.contactKey = contactKey;
        setName(compName);
        classify = new JComboBox(classifys);
        classify.setBorder(BorderFactory.createEtchedBorder());
        classify.setUI(new hoi.addrbook.look.AComboBoxUI());
        classify.setRenderer(new ComplexCellRenderer());
        classify.setEditable(true);
        add(classify, BorderLayout.CENTER);
    }

    public String getContactKey() {
        return contactKey;
    }

    public String getContent() {
        return classify.getSelectedItem().toString();
    }

    public void setContent(String content) {
        classify.setSelectedItem(content);
    }
}
