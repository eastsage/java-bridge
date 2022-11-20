package bridge.model;

import bridge.BridgeMaker;
import bridge.BridgeNumberGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {
    Bridge bridge;
    Stack<Direction> movement;
    MakeMap makeMap;

    public BridgeGame() {
        movement = new Stack<>();
        makeMap = new MakeMap();
    }

    public void makeBridge(int size, BridgeNumberGenerator bridgeNumberGenerator) {
        BridgeMaker bridgeMaker = new BridgeMaker(bridgeNumberGenerator);
        List<Direction> newBridge = new ArrayList<>();
        for (String direction : bridgeMaker.makeBridge(size)) {
            newBridge.add(Direction.valueOf(direction));
        }
        bridge = new Bridge(newBridge);
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public String move(Direction direction) {
        movement.push(direction);
        String message = makeMap.buildMessage(movement, bridge);
        return message;
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
        //TODO: IllegalStatementException
        movement.pop();
    }

    public boolean failToMove() {
        if (bridge.canMove(movement)) {
            return false;
        }
        return true;
    }

    public boolean isEndOfBridge() {
        return bridge.arriveAtTheEnd(movement);
    }
}
