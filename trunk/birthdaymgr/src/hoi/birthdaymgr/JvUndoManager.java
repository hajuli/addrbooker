package hoi.birthdaymgr;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;

/**
 * http://www.jroller.com/nweber/entry/undo_in_jtable
 * 
 * @author Administrator
 */
public class JvUndoManager extends UndoManager {
    private static final long serialVersionUID = 557357060957002133L;
    protected Action undoAction;
    protected Action redoAction;

    public JvUndoManager() {
        this.undoAction = new JvUndoAction(this);
        this.redoAction = new JvRedoAction(this);

        synchronizeActions(); // to set initial names
    }

    public Action getUndoAction() {
        return undoAction;
    }

    public Action getRedoAction() {
        return redoAction;
    }

    @Override
    public boolean addEdit(UndoableEdit anEdit) {
        try {
            return super.addEdit(anEdit);
        } finally {
            synchronizeActions();
        }
    }

    @Override
    protected void undoTo(UndoableEdit edit) throws CannotUndoException {
        try {
            super.undoTo(edit);
        } finally {
            synchronizeActions();
        }
    }

    @Override
    protected void redoTo(UndoableEdit edit) throws CannotRedoException {
        try {
            super.redoTo(edit);
        } finally {
            synchronizeActions();
        }
    }

    protected void synchronizeActions() {
        undoAction.setEnabled(canUndo());
        undoAction.putValue(Action.NAME, getUndoPresentationName());

        redoAction.setEnabled(canRedo());
        redoAction.putValue(Action.NAME, getRedoPresentationName());
    }
}

class JvCellEdit extends AbstractUndoableEdit {
    private static final long serialVersionUID = -3450869343391492593L;
    protected BMgrTableModel tableModel;
    protected Object oldValue;
    protected Object newValue;
    protected int row;
    protected int column;

    public JvCellEdit(BMgrTableModel tableModel, Object oldValue, Object newValue, int row, int column) {
        this.tableModel = tableModel;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.row = row;
        this.column = column;
    }

    @Override
    public String getPresentationName() {
        return ""; // "Cell Edit";
    }

    @Override
    public void undo() throws CannotUndoException {
        super.undo();

        tableModel.setValueAt(oldValue, row, column, false);
    }

    @Override
    public void redo() throws CannotUndoException {
        super.redo();

        tableModel.setValueAt(newValue, row, column, false);
    }
}

class JvUndoAction extends AbstractAction {
    private static final long serialVersionUID = -8801086506384949990L;
    protected final UndoManager manager;

    public JvUndoAction(UndoManager manager) {
        this.manager = manager;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            manager.undo();
        } catch (CannotUndoException ex) {
            //            ex.printStackTrace();
        }
    }
}

class JvRedoAction extends AbstractAction {
    private static final long serialVersionUID = 6773023935230942100L;
    protected final UndoManager manager;

    public JvRedoAction(UndoManager manager) {
        this.manager = manager;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            manager.redo();
        } catch (CannotRedoException ex) {
            //            ex.printStackTrace();
        }
    }
}
