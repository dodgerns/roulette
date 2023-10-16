package nicos.view.game_node;

import nicos.commons.Callback;
import nicos.view.INode;

public interface IGameNode extends INode {
    void changeState(String key, String newState);
    void setAction(String key, Callback callback);
}
