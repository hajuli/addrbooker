package hoi.addrbook.ui;

import hoi.addrbook.util.DeltaDate;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class InfoTimerField extends JPanel implements AccessInterface {
    private static final long serialVersionUID = 3640198100387397959L;

    private InfoTextField delta = new InfoTextField();
    private JButton clear = new JButton(UINamesCtrl.getLocalName("Reset"));
    private String contactKey = null;
    private String contentDate = null;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public InfoTimerField(String contactKey, String compName) {
        super(new BorderLayout(1, 1));
        this.contactKey = contactKey;
        setName(compName);
        clear.setBorder(BorderFactory.createCompoundBorder( //
                BorderFactory.createEtchedBorder(), new EmptyBorder(new Insets(1, 7, 1, 7))));

        delta.setEditable(false);
        add(delta, BorderLayout.CENTER);
        add(clear, BorderLayout.EAST);
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setContent(dateFormat.format(new Date()));
            }
        });
        setContent(dateFormat.format(new Date())); // 默认值
    }

    private String formatDelataDate(DeltaDate delta) {
        String syear = UINamesCtrl.getLocalName(delta.year > 1 ? "Years" : "Year");
        String smonth = UINamesCtrl.getLocalName(delta.month > 1 ? "Months" : "Month");
        String sday = UINamesCtrl.getLocalName(delta.day > 1 ? "Days" : "Day");
        if (delta.year >= 1) {
            return delta.day >= 1 ? String.format("%d %s %d %s %d %s", delta.year, syear, delta.month, smonth, delta.day, sday) : //
                    (delta.month >= 1 ? String.format("%d %s %d %s", delta.year, syear, delta.month, smonth) : //
                            String.format("%d %s", delta.year, syear));
        } else if (delta.month >= 1) {
            return delta.day >= 1 ? String.format("%d %s %d %s", delta.month, smonth, delta.day, sday) : //
                    String.format("%d %s", delta.month, smonth);
        } else {
            return String.format("%d %s", delta.day, sday);
        }
    }

    public String getContactKey() {
        return contactKey;
    }

    public String getContent() {
        return contentDate;
    }

    public void setContent(Date date) {
        setContent(dateFormat.format(date));
    }

    public void setContent(String content) {
        if (content == null || content.trim().equals("")) {
            setContent(new Date());
        } else {
            this.contentDate = content.trim();
            try {
                delta.setText(formatDelataDate(new DeltaDate(dateFormat.parse(contentDate), new Date())));
            } catch (ParseException e) {
                delta.setText(UINamesCtrl.getLocalName("Parse Error"));
            }
        }
    }
}
