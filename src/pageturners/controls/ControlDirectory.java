package pageturners.controls;

import java.util.ArrayList;

public class ControlDirectory {
    private final ArrayList<ControlBase> controls;

    public ControlDirectory() {
        controls = new ArrayList<ControlBase>();
    }

    public ControlBase[] getControls() {
        return controls.toArray(new ControlBase[0]);
    }

    private ControlBase getControlInternal(Class<?> classType) {
        for (ControlBase control : controls) {
            if (control.getClass().equals(classType)) {
                return control;
            }
        }

        return null;
    }

    public ControlBase getControl(Class<?> classType) {
        ControlBase control = getControlInternal(classType);

        if (control != null) {
            return control;
        }
        
        throw new RuntimeException("Cannot find a control of type " + classType.toString());
    }

    public void clearControls() {
        controls.clear();
    }

    public boolean addControl(ControlBase control) {
        if (getControlInternal(control.getClass()) != null) {
            return false;
        }

        controls.add(control);

        return true;
    }
}
