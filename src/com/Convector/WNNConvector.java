package com.Convector;

import com.NeuralNetwork.Coefficient;
import com.NeuralNetwork.Option;
import com.NeuralNetwork.WordNN;

/**
 * Created by josdas on 29.06.2017.
 */
public class WNNConvector implements WordConvector {
    private WordNN wordNN;

    public WNNConvector(WordNN wordNN) {
        this.wordNN = wordNN;
    }

    public WNNConvector(Coefficient coefficientF,
                        Coefficient coefficientS,
                        Option option) {
        this.wordNN = new WordNN(coefficientF, coefficientS, option);
    }

    @Override
    public double[] get(String str) {
        int[] temp = new int[str.length()];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = str.charAt(i) - com.Testing.MINIMAL_LETTER;;
        }
        return wordNN.get(temp);
    }

    public Coefficient[] getCoefficient() {
        return new Coefficient[]{
                new Coefficient(wordNN.getFirstCoef()),
                new Coefficient(wordNN.getSecondCoef())
        };
    }

    public void setAlp(int alp) {
        wordNN.setAlp(alp);
    }

    public int getAlp() {
        return wordNN.getAlp();
    }
}
