package hoi.addrbook.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class InfoAddrField extends JPanel implements AccessInterface {

    private static final long serialVersionUID = 9087341622805152794L;
    private static final String LS = System.getProperty("line.separator");
    private InfoTextField address = new InfoTextField();
    private InfoTextField postcode = new InfoTextField(null, "邮政编码");
    private String contactKey = null;

    public InfoAddrField(String contactKey, String compName) {
        super(new BorderLayout(1, 1));
        this.contactKey = contactKey;
        setName(compName);
        postcode.setPreferredSize(new Dimension(85, 1));

        add(address, BorderLayout.CENTER);
        add(postcode, BorderLayout.EAST);
    }

    public String getContactKey() {
        return contactKey;
    }

    public String getContent() {
        return address.getText().trim() + LS + postcode.getText().trim();
    }

    public void setContent(String content) {
        if (content != null) {
            int k = content.lastIndexOf(LS);
            if (k != -1) {
                address.setText(content.substring(0, k).replace(LS, " ").trim());
                postcode.setText(content.substring(k).replace(LS, " ").trim());
            } else {
                address.setText(content.replace(LS, " ").trim());
            }
        }
    }
}
