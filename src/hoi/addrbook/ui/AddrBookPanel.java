package hoi.addrbook.ui;

import hoi.addrbook.data.AddrBookProps;
import hoi.addrbook.data.ContactProps;
import hoi.addrbook.icon.ImageHelper;
import hoi.addrbook.util.Localization;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AddrBookPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = -1863142517865697366L;
    private static final Color BORDER_COLOR = new JLabel().getBackground();
    private static final int GAP_SIZE = 2;
    private static final int BLANK_SIZE = 5;

    private AddrBookBackupDialog backupDialog = null;
    private AddrBookSettingDialog settingDialog = null;

    private JButton tbarAddButton = new JButton(Localization.getLocalString("Add"), ImageHelper.ICON_ADD);
    private JButton tbarSaveButton = new JButton(Localization.getLocalString("Save"), ImageHelper.ICON_SAVE);
    private JButton tbarDeleteButton = new JButton(Localization.getLocalString("Delete"), ImageHelper.ICON_DELETE);
    private JButton tbarSettingButton = new JButton(Localization.getLocalString("Settings"), ImageHelper.ICON_SETTING);
    private JButton tbarBackupButton = new JButton(Localization.getLocalString("Backup"), ImageHelper.ICON_BACKUP);
    private JButton tbarExitButton = new JButton(Localization.getLocalString("Exit"), ImageHelper.ICON_EXIT);

    private DefaultListModel contactListModel = new DefaultListModel();
    private JList contactList = new JList(contactListModel);

    private SreachBox searchBox = new SreachBox();
    private ContactListPanel contactListPanel = new ContactListPanel(contactList);
    private StatusPanel statusPanel = new StatusPanel();
    private InfoTextField birthdayReminder = new InfoTextField(null, null, Localization.getLocalString("Birthday Reminder"));

    private AddrBookProps addrbook = AddrBookProps.load();

    private InfoTextField infoNameField = new InfoTextField(ContactProps.NAME, Localization.getLocalString(ContactProps.NAME));
    private InfoClassifyField infoClassifyField = new InfoClassifyField(ContactProps.CLASSIFY, Localization.getLocalString(ContactProps.CLASSIFY), addrbook.getClassifys());
    private InfoTextField infoBirthdayLunarField = new InfoTextField(ContactProps.BIRTHDAY_LUNAR, Localization.getLocalString(ContactProps.BIRTHDAY), //
            Localization.getLocalString(ContactProps.LUNAR) + " 1900-01-01");
    private InfoTextField infoBirthdaySolarField = new InfoTextField(ContactProps.BIRTHDAY_SOLAR, null, //
            Localization.getLocalString(ContactProps.SOLAR) + " 1900-01-01");
    private InfoTimerField infoTimerField = new InfoTimerField(ContactProps.TIMER, Localization.getLocalString(ContactProps.TIMER));
    private InfoTextField infoAgeField = new InfoTextField(ContactProps.AGE, Localization.getLocalString(ContactProps.AGE));
    private InfoTextField infoQQField = new InfoTextField(ContactProps.QQ, Localization.getLocalString(ContactProps.QQ));
    private InfoTextField infoMSNField = new InfoTextField(ContactProps.MSN, Localization.getLocalString(ContactProps.MSN));
    private InfoTextField infoMobileField = new InfoTextField(ContactProps.MOBILE, Localization.getLocalString(ContactProps.MOBILE));
    private InfoTextField infoFetionField = new InfoTextField(ContactProps.FETION, Localization.getLocalString(ContactProps.FETION));
    private InfoTextField infoEmailField = new InfoTextField(ContactProps.EMAIL, Localization.getLocalString(ContactProps.EMAIL));
    private InfoTextField infoWebsiteField = new InfoTextField(ContactProps.WEBSITE, Localization.getLocalString(ContactProps.WEBSITE));
    private InfoAddrField infoHomeAddrField = new InfoAddrField(ContactProps.ADDRESS, Localization.getLocalString(ContactProps.ADDRESS));
    private InfoTextField infoCompanyField = new InfoTextField(ContactProps.COMPANY, Localization.getLocalString(ContactProps.COMPANY));
    private InfoNotesArea infoNotesArea = new InfoNotesArea(ContactProps.NOTES);

    private AccessInterface[] infoFields = new AccessInterface[] {
            infoNameField, infoClassifyField, infoBirthdayLunarField, infoBirthdaySolarField, infoTimerField, //
            infoQQField, infoMSNField, infoMobileField, infoFetionField, infoEmailField, //
            infoAgeField, infoWebsiteField, infoHomeAddrField, infoCompanyField, infoNotesArea };

    public AddrBookPanel() {
        JPanel wPanel = new JPanel(new BorderLayout(GAP_SIZE, GAP_SIZE));
        wPanel.add(searchBox, BorderLayout.NORTH);
        wPanel.add(contactListPanel, BorderLayout.CENTER);
        wPanel.add(birthdayReminder, BorderLayout.SOUTH);
        wPanel.setPreferredSize(new Dimension(135, 1));

        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.setLayout(new GridLayout(1, 6));
        toolbar.add(tbarAddButton);
        toolbar.add(tbarSaveButton);
        toolbar.add(tbarDeleteButton);
        toolbar.add(tbarSettingButton);
        toolbar.add(tbarBackupButton);
        toolbar.add(tbarExitButton);
        //BorderFactory.createCompoundBorder( BorderFactory.createEmptyBorder(5,5,5,5), new JButton().getBorder() )
        int k = 10;
        (tbarAddButton).setBorder(BorderFactory.createEmptyBorder(k, 1, k, 1));
        (tbarSaveButton).setBorder(BorderFactory.createEmptyBorder(k, 1, k, 1));
        (tbarDeleteButton).setBorder(BorderFactory.createEmptyBorder(k, 1, k, 1));
        (tbarSettingButton).setBorder(BorderFactory.createEmptyBorder(k, 1, k, 1));
        (tbarBackupButton).setBorder(BorderFactory.createEmptyBorder(k, 1, k, 1));
        (tbarExitButton).setBorder(BorderFactory.createEmptyBorder(k, 1, k, 1));

        JPanel cPanel = new JPanel(new BorderLayout(BLANK_SIZE * 2, BLANK_SIZE * 2));
        cPanel.add(wPanel, BorderLayout.WEST);
        cPanel.add(createInfoPanel(), BorderLayout.CENTER);
        cPanel.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, BLANK_SIZE * 2));

        setLayout(new BorderLayout(0, 0));
        add(toolbar, BorderLayout.NORTH);
        add(cPanel, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);

        setActionListener();
        refreshContactList();
        doNewAdd();
    }

    private void doNewAdd() {
        searchBox.setText(null);
        contactList.clearSelection();
        for (AccessInterface infoField : infoFields)
            infoField.setContent(null);
    }

    private void doSave() {
        ContactProps contact = new ContactProps();
        for (AccessInterface infoField : infoFields)
            contact.setProperty(infoField.getContactKey(), infoField.getContent());
        String newname = contact.getProperty(ContactProps.NAME);
        if (newname == null || newname.trim().equals("")) {
            AddrBookProps.save(addrbook);
            return;
        }
        if (contactList.getSelectedIndex() != -1) {
            String oldname = (String) contactList.getSelectedValue();
            if (oldname.equals(newname)) { // 同名
                addrbook.put(contact.getProperty(ContactProps.NAME), contact);
            } else { // 改变名字
                if (addrbook.containsKey(newname)) { // 名字冲突
                } else { // 名字不冲突
                    addrbook.remove(oldname);
                    addrbook.put(newname, contact);
                }
            }
        } else {

            if (addrbook.containsKey(newname)) { // 名字冲突
            } else { // 名字不冲突
                addrbook.put(newname, contact);
            }
        }
        refreshContactList();
        contactList.setSelectedValue(newname, true);
        AddrBookProps.save(addrbook);
    }

    private void doDelete() {
        int k = contactList.getSelectedIndex();
        if (k != -1) {
            String oldname = (String) contactList.getSelectedValue();
            addrbook.remove(oldname);
            contactListModel.removeElementAt(k);
            if (contactListModel.getSize() > k)
                contactList.setSelectedIndex(k);
            else if (k - 1 >= 0)
                contactList.setSelectedIndex(k - 1);
        }
    }

    private void setActionListener() {
        tbarAddButton.addActionListener(this);
        tbarSaveButton.addActionListener(this);
        tbarDeleteButton.addActionListener(this);
        tbarSettingButton.addActionListener(this);
        tbarBackupButton.addActionListener(this);
        tbarExitButton.addActionListener(this);
        contactList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int k = contactList.getSelectedIndex();
                if (k != -1) {
                    String newname = (String) contactList.getSelectedValue();
                    ContactProps contact = addrbook.get(newname);
                    for (AccessInterface infoField : infoFields)
                        infoField.setContent(contact.getProperty(infoField.getContactKey()));
                } else {
                    doNewAdd();
                }
            }
        });
    }

    private synchronized void refreshContactList() {
        Object[] os = addrbook.keySet().toArray();

        String l[] = new String[os.length];
        for (int i = 0; i < os.length; i++)
            l[i] = os[i].toString();

        List<String> list = Arrays.asList(l);
        Collections.sort(list);

        contactListModel.removeAllElements();
        for (Object key : list)
            contactListModel.addElement(key.toString());
    }

    public void actionPerformed(ActionEvent e) {
        Object object = e.getSource();
        if (object == tbarSettingButton) {
            if (settingDialog == null)
                settingDialog = new AddrBookSettingDialog((Frame) getTopLevelAncestor());
            settingDialog.setVisible(true);
        } else if (object == tbarBackupButton) {
            if (backupDialog == null)
                backupDialog = new AddrBookBackupDialog((Frame) getTopLevelAncestor());
            backupDialog.setVisible(true);
        } else if (object == tbarSaveButton) {
            doSave();
        } else if (object == tbarAddButton) {
            doNewAdd();
        } else if (object == tbarExitButton) {
            System.exit(0);
        } else if (object == tbarDeleteButton) {
            doDelete();
        }
    }

    private JPanel createTempPanel(JComponent... comps) {
        JPanel aPanel = new JPanel(new GridLayout(comps.length, 1, GAP_SIZE, GAP_SIZE));
        JPanel bPanel = new JPanel(new GridLayout(comps.length, 1, GAP_SIZE, GAP_SIZE));
        for (JComponent comp : comps) {
            JLabel label = new JLabel(String.format(" %s: ", comp.getName()), JLabel.RIGHT);
            if (comp.getName() == null) // 避免匿名组件
                label.setText(null);
            //     label.setBorder(BorderFactory.createEtchedBorder());
            aPanel.add(label);
            bPanel.add(comp);
        }
        JPanel cPanel = new JPanel(new BorderLayout(GAP_SIZE, GAP_SIZE));
        cPanel.add(aPanel, BorderLayout.WEST);
        cPanel.add(bPanel, BorderLayout.CENTER);
        return cPanel;
    }

    private JPanel createInfoPanel() {
        JPanel aPanel = new JPanel(new GridLayout(1, 2, GAP_SIZE, GAP_SIZE));
        aPanel.add(createTempPanel(infoNameField, infoBirthdayLunarField, infoBirthdaySolarField, infoQQField, infoMobileField));
        aPanel.add(createTempPanel(infoClassifyField, infoTimerField, infoAgeField, infoMSNField, infoFetionField));

        JPanel bPanel = new JPanel(new BorderLayout(GAP_SIZE, GAP_SIZE));
        bPanel.add(aPanel, BorderLayout.NORTH);
        bPanel.add(createTempPanel(infoEmailField, infoWebsiteField, infoHomeAddrField, infoCompanyField), BorderLayout.CENTER);

        JPanel cPanel = new JPanel(new BorderLayout(GAP_SIZE, GAP_SIZE));
        cPanel.add(bPanel, BorderLayout.NORTH);
        cPanel.add(infoNotesArea, BorderLayout.CENTER);
        return cPanel;
    }
}
