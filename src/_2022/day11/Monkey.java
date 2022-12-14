package _2022.day11;

import java.util.LinkedList;
import java.util.Queue;

public class Monkey implements Cloneable {
    private Queue<Integer> carryList;
    private Operation operation;
    private int operationValue;
    private int worryDivisor;
    private int trueMonkey;
    private int falseMonkey;

    private Long inspectionCount = 0L;

    public Monkey() {
        this.carryList = new LinkedList<>();
    }

    public Monkey(Queue<Integer> carryList, Operation operation, int operationValue, int worryDivisor, int trueMonkey, int falseMonkey) {
        this.carryList = carryList;
        this.operation = operation;
        this.operationValue = operationValue;
        this.worryDivisor = worryDivisor;
        this.trueMonkey = trueMonkey;
        this.falseMonkey = falseMonkey;
    }



    public int inspect() {
        inspectionCount++;
        int oldValue = carryList.poll();

        double worryLevel = 3.0;

        switch(operation) {
            case PLUS :
                return (int)((oldValue + operationValue) / worryLevel);
            case MINUS :
                return (int)((oldValue - operationValue) / worryLevel);
            case MULTIPLY :
                if(operationValue == -1)
                    return (int)((oldValue * oldValue) / worryLevel);
                return (int)((oldValue * operationValue) / worryLevel);
            case DIVIDE :
                return (int)((oldValue / operationValue) / worryLevel);
        }

        return 0;
    }

    public int inspect2() {
        inspectionCount++;
        int oldValue = carryList.poll();

        double worryLevel = 3.0;

        switch(operation) {
            case PLUS :
                return (int)((oldValue + operationValue));
            case MINUS :
                return (int)((oldValue - operationValue));
            case MULTIPLY :
                if(operationValue == -1)
                    return (int)((oldValue * oldValue));
                return (int)((oldValue * operationValue));
            case DIVIDE :
                return (int)((oldValue / operationValue));
        }

        return 0;
    }
    public int inspect(double worryLevel) {
        inspectionCount++;
        int oldValue = carryList.poll();

//        double worryLevel = 3.0;

        switch(operation) {
            case PLUS :
                return (int)((oldValue + operationValue) * worryLevel);
            case MINUS :
                return (int)((oldValue - operationValue) * worryLevel);
            case MULTIPLY :
                if(operationValue == -1)
                    return (int)((oldValue * oldValue) * worryLevel);
                return (int)((oldValue * operationValue) * worryLevel);
            case DIVIDE :
                return (int)((oldValue / operationValue) * worryLevel);
        }

        return 0;
    }

    public int getNextMonkey(int value){

        if (value % worryDivisor == 0)
            return trueMonkey;
        return falseMonkey;

    }


    public Queue<Integer> getCarryList() {
        return carryList;
    }

    public void setCarryList(Queue<Integer> carryList) {
        this.carryList = carryList;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public int getOperationValue() {
        return operationValue;
    }

    public void setOperationValue(int operationValue) {
        this.operationValue = operationValue;
    }

    public int getWorryDivisor() {
        return worryDivisor;
    }

    public void setWorryDivisor(int worryDivisor) {
        this.worryDivisor = worryDivisor;
    }

    public int getTrueMonkey() {
        return trueMonkey;
    }

    public void setTrueMonkey(int trueMonkey) {
        this.trueMonkey = trueMonkey;
    }

    public int getFalseMonkey() {
        return falseMonkey;
    }

    public void setFalseMonkey(int falseMonkey) {
        this.falseMonkey = falseMonkey;
    }

    public Long getInspectionCount() {
        return inspectionCount;
    }

    public void setInspectionCount(Long inspectionCount) {
        this.inspectionCount = inspectionCount;
    }
}
