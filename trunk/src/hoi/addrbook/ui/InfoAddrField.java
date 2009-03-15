package hoi.addrbook.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class InfoAddrField extends JPanel implements AccessInterface {

    private static final long serialVersionUID = 9087341622805152794L;
    private static final char LS = '|';
    private InfoTextField address = new InfoTextField();
    private InfoTextField postcode = new InfoTextField(null, null, UINamesCtrl.getLocalName("Postcode"));
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

    private String quote(String str) {
        return str.replace(LS + "", String.format("\\%d", (int) LS));
    }

    private String unquote(String str) {
        return str.replace(String.format("\\%d", (int) LS), LS + "");
    }

    public String getContent() {
        return address.getText().trim() + LS + quote(postcode.getText().trim());
    }

    public void setContent(String content) {
        if (content != null) {
            int k = content.lastIndexOf(LS);
            if (k != -1) {
                address.setText(content.substring(0, k).replace(LS + "", " ").trim());
                postcode.setText(unquote(content.substring(k).replace(LS + "", " ").trim()));
            } else {
                address.setText(content.replace(LS + "", " ").trim());
            }
        }
    }
}
