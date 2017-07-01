package com.NeuralNetwork;

import java.util.function.UnaryOperator;

/**
 * Created by josdas on 29.06.2017.
 */
public class WordNN implements NeuralNetwork<int[]> {
    private BasicNN firstNN;
    private BasicNN secondNN;
    private int alp;
    private int neuronsForLetter;

    public WordNN(Coefficient coefficientF,
                  Coefficient coefficientS,
                  int alp,
                  int neuronsForLetter,
                  UnaryOperator<Double> active) {
        assert coefficientF.getNumberIn(0) == neuronsForLetter;
        assert coefficientF.getNumberOut(coefficientF.layersCount() - 1) == neuronsForLetter;
        assert coefficientS.getNumberIn(0) == neuronsForLetter;
        assert coefficientS.getNumberOut(coefficientS.layersCount() - 1) == neuronsForLetter;

        this.firstNN = new BasicNN(coefficientF, active);
        this.secondNN = new BasicNN(coefficientS, active);
        this.alp = alp;
        this.neuronsForLetter = neuronsForLetter;
    }

    @Override
    public double[] get(int[] data) {
        double[][] temp = new double[alp][neuronsForLetter];
        for (int letter : data) {
            for (int j = 0; j < alp; j++) {
                if (j == letter) {
                    temp[j] = firstNN.get(temp[j]);
                } else {
                    temp[j] = secondNN.get(temp[j]);
                }
            }
        }
        double[] result = new double[alp * neuronsForLetter];
        for (int i = 0, cur = 0; i < alp; i++) {
            for (int j = 0; j < neuronsForLetter; j++, cur++) {
                result[cur] = temp[i][j];
            }
        }
        return result;
    }

    public Coefficient getFirstCoef() {
        return firstNN.getCoefficient();
    }

    public Coefficient getSecondCoef() {
        return secondNN.getCoefficient();
    }
}
