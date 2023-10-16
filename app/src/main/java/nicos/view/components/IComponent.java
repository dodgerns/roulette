package nicos.view.components;

import nicos.commons.Callback;

public interface IComponent {
    void changeText(String newText);
    void setAction(Callback callback);
}
