package hoi.addrbook.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class InfoAddrField extends JPanel implements AccessInterface {

    private static final long serialVersionUID = 9087341622805152794L;
    private static final String LS = "|";
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

    public String getContent() {
        return address.getText() + LS + postcode.getText();
    }

    public void setContent(String content) {
        if (content != null) {
            int k = content.lastIndexOf(LS);
            if (k != -1) { // 可能识别错误，但是不会造成信息丢失
                address.setText(content.substring(0, k));
                postcode.setText(content.substring(k + LS.length()));
            } else {
                address.setText(content);
                postcode.setText("");
            }
        } else {
            address.setText("");
            postcode.setText("");
        }
    }
}
